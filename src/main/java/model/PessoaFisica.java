package model;

import enums.StatusCarro;
import exceptions.CarroNotAble;
import exceptions.CarroNotFound;
import exceptions.ReservaDuplicada;
import util.Endereco;

import javax.money.MonetaryAmount;
import javax.money.MonetaryQuery;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
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

    private Map<LocalDate, List<Carro>> carrosAlugados = new LinkedHashMap<>();

    public void alugarCarro(LocalDate dataAluguel, Carro carro) throws CarroNotAble {
        Objects.requireNonNull(dataAluguel, "Data nao pode se nula");
        Objects.requireNonNull(carro, "Carro nao pode ser nulo");

        if (carro.getStatusCarro().equals(StatusCarro.ALUGADO)) {
            throw new CarroNotAble("Carro alugado");
        }

        List<Carro> carros = Optional.ofNullable(this.carrosAlugados.get(dataAluguel)).orElse(new LinkedList<>());

        carros.add(carro);
        this.carrosAlugados.put(dataAluguel, carros);
        carro.setStatusCarro(StatusCarro.ALUGADO);


    }

    public void imprimirAlugados() {

        this.carrosAlugados.keySet().forEach(dataReserva -> {
            System.out.println(PessoaFisica.super.getNome());
            System.out.println("Data: " + dataReserva);
            this.carrosAlugados.get(dataReserva).forEach(carros -> {
                System.out.println("Carro: " + carros.getModelo());
            });
        });
    }

    public void devolverCarro(LocalDate dataReserva, LocalDate dataDevolucao, Carro carro) {
        Objects.requireNonNull(dataReserva, "Data nao pode ser nula");

        if (this.carrosAlugados.containsKey(dataReserva)) {
            if (this.carrosAlugados.get(dataReserva).contains(carro)) {
                this.carrosAlugados.remove(carro);
                carro.setStatusCarro(StatusCarro.DISPONIVEL);
                long diasAluguel = ChronoUnit.DAYS.between(dataReserva,dataDevolucao);

                System.out.println("Carro devolvido: " + carro.getModelo() + " Valor do Aluguel " + carro.getValorDiaria().multiply(diasAluguel));
                //carro.setValorDiaria() ;
            }
        }
    }
    /*private Map<LocalDate, List<Carro>> pedidoReserva = new LinkedHashMap<>();
    private Map<Pessoa, Map<LocalDate, List<Carro>>> reservaCarro = new LinkedHashMap<>();

    public void reservaCarro(PessoaFisica pessoa, LocalDate dataReserva, Carro carro) throws ReservaDuplicada {

        Map<LocalDate, List<Carro>> carros = Optional.ofNullable(this.reservaCarro.get(pessoa)).orElse(new LinkedHashMap<>());
        List<Carro> carro3 = Optional.ofNullable(carros.get(dataReserva)).orElse(new LinkedList<>());
        if (carro3.contains(carro)) {
            throw new ReservaDuplicada("valor repetido", carro);
        }
        carro3.add(carro);
        carros.put(dataReserva, carro3);

        this.reservaCarro.put(pessoa, carros);

        carro.setStatusCarro(StatusCarro.ALUGADO);
    }*/


   /* public void devolverCarro(Pessoa pessoa, LocalDate dataReserva, List<Carro> carro) {
        Objects.requireNonNull(dataReserva, "Data de reserva ser informada");

        if (this.carrosAlugados.get(dataReserva).containsKey(carro)) {
            if (this.reservaCarro.get(pessoa).get(dataReserva).contains(carro)) {
                this.reservaCarro.get(pessoa).get(dataReserva).remove(carro);
                carro.setStatusCarro(StatusCarro.DISPONIVEL);
            }else{
                throw new CarroNotFound("Carro nao encontrado");
            }


        }
    }*/

    /*public void imprimirReserva() {
        reservaCarro.keySet().forEach(pessoa -> {
            System.out.println("Nome: " + pessoa.getNome());
            reservaCarro.get(pessoa).keySet().forEach(localDate -> {
                System.out.println("Data: " + localDate);
                reservaCarro.get(pessoa).get(localDate).forEach(carro1 -> {
                    System.out.println(carro1.getModelo());
                });
            });

        });
    }*/

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
