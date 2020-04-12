package model;

import exceptions.ReservaDuplicada;
import util.Endereco;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

public class PessoaFisica extends Pessoa {

    private long cpf;
    private LocalDate dataNascimento;


    public PessoaFisica(String nome, Endereco endereco, String email, long cpf, LocalDate dataNascimento) {
        super(nome, endereco, email);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public long getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    private Map<LocalDate, List<Carro>> pedidoReserva = new LinkedHashMap<>();
    private Map<Pessoa, Map<LocalDate, List<Carro>>> reservaCarro = new LinkedHashMap<>();

    public void reservaCarro(PessoaFisica pessoa, LocalDate dataReserva, Carro carro) throws ReservaDuplicada {

        Map<LocalDate, List<Carro>>carros = Optional.ofNullable(this.reservaCarro.get(pessoa)).orElse( new LinkedHashMap<>());
        List<Carro>  carro3 = Optional.ofNullable(carros.get(dataReserva)).orElse( new LinkedList<>());
        if(carro3.contains(carro)){
            throw  new ReservaDuplicada("valor repetido", carro);
        }
            carro3.add(carro);
            carros.put(dataReserva, carro3);

            this.reservaCarro.put(pessoa, carros);

    }

    public void imprimirReserva(){
        reservaCarro.keySet().forEach(pessoa -> {
            System.out.println("Nome: " + pessoa.getNome());
            reservaCarro.get(pessoa).keySet().forEach(localDate -> {
                System.out.println("Data: " + localDate);
                reservaCarro.get(pessoa).get(localDate).forEach(carro1 -> {
                    System.out.println(carro1.getModelo());
                });
            });

        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaFisica that = (PessoaFisica) o;
        return cpf == that.cpf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }


}
