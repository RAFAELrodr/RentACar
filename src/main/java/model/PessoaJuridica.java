package model;

import exceptions.AluguelNotFound;
import exceptions.CarroNotAble;
import util.Endereco;
import util.IClientes;

import java.time.LocalDate;
import java.util.Objects;

public class PessoaJuridica extends Pessoa implements IClientes {
    private final long cnpj;


    public PessoaJuridica(String nome, Endereco endereco, String email, long cnpj) {
        super(nome, endereco, email);
        this.cnpj = cnpj;
    }
    public void reservaCarro(Pessoa pessoa, LocalDate dataReserva, Carro carro){

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaJuridica that = (PessoaJuridica) o;
        return cnpj == that.cnpj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }


    @Override
    public void alugarCarro(LocalDate dataAluguel, Carro carro) throws CarroNotAble {

    }

    @Override
    public void devolverCarro(LocalDate dataAluguel, LocalDate dataDevolucao, Carro carro) throws AluguelNotFound {

    }
}
