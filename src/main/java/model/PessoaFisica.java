package model;

import enums.StatusCarro;
import exceptions.AluguelNotFound;
import exceptions.CarroNotAble;
import util.Endereco;
import util.IClientes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PessoaFisica extends Pessoa  implements IClientes {

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

    @Override
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
        System.out.println(this);
        this.carrosAlugados.keySet().forEach(dataAluguel -> {

            System.out.println("Data: " + dataAluguel);
            this.carrosAlugados.get(dataAluguel).forEach(carros -> {
                System.out.println(carros);
            });
        });
    }

    @Override
    public void devolverCarro(LocalDate dataAluguel, LocalDate dataDevolucao, Carro carro) throws AluguelNotFound {
        Objects.requireNonNull(dataAluguel, "Data nao pode ser nula");

        if (this.carrosAlugados.containsKey(dataAluguel)) {
            if (this.carrosAlugados.get(dataAluguel).contains(carro)) {
                this.carrosAlugados.remove(carro);
                carro.setStatusCarro(StatusCarro.DISPONIVEL);
                long diasAluguel = ChronoUnit.DAYS.between(dataAluguel, dataDevolucao);

                System.out.println("Cliente: " + this.getNome() +
                        " Carro: " + carro.getModelo() +
                        ", Data do Aluguel: "+ dataAluguel +
                        ", Data devolução: " + dataDevolucao +
                        ", Valor do Aluguel " + carro.getValorDiaria().multiply(diasAluguel));
                //carro.setValorDiaria() ;
            } else {
                throw new AluguelNotFound("Aluguel nao existe nesta data: ", dataAluguel);
            }
        }
    }

    public List<Carro> procurarAluguel(LocalDate dataAluguel) throws AluguelNotFound {
        if (this.carrosAlugados.containsKey(dataAluguel)) {
            return this.carrosAlugados.get(dataAluguel);
        } else {
            throw new AluguelNotFound("Data nao consta Aluguel", dataAluguel);
        }
    }

    /*private Map<LocalDate, List<Carro>> pedidoReserva = new LinkedHashMap<>();
    private Map<Pessoa, Map<LocalDate, List<Carro>>> reservaCarro = new LinkedHashMap<>();

    public void reservaCarro(PessoaFisica pessoa, LocalDate dataAluguel, Carro carro) throws ReservaDuplicada {

        Map<LocalDate, List<Carro>> carros = Optional.ofNullable(this.reservaCarro.get(pessoa)).orElse(new LinkedHashMap<>());
        List<Carro> carro3 = Optional.ofNullable(carros.get(dataAluguel)).orElse(new LinkedList<>());
        if (carro3.contains(carro)) {
            throw new ReservaDuplicada("valor repetido", carro);
        }
        carro3.add(carro);
        carros.put(dataAluguel, carro3);

        this.reservaCarro.put(pessoa, carros);

        carro.setStatusCarro(StatusCarro.ALUGADO);
    }*/


   /* public void devolverCarro(Pessoa pessoa, LocalDate dataAluguel, List<Carro> carro) {
        Objects.requireNonNull(dataAluguel, "Data de reserva ser informada");

        if (this.carrosAlugados.get(dataAluguel).containsKey(carro)) {
            if (this.reservaCarro.get(pessoa).get(dataAluguel).contains(carro)) {
                this.reservaCarro.get(pessoa).get(dataAluguel).remove(carro);
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
    public String toString() {
        return "TIPO: " + PessoaFisica.class.getSimpleName() +
                " Nome: " + this.getNome() +
                " Cpf:" + cpf +
                " Endereço: " + this.getEndereco() +
                " Data de Nascimento: " + dataNascimento +
                " Email: " + this.getEmail();
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
