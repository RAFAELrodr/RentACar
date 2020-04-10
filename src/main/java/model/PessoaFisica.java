package model;

import util.Endereco;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class PessoaFisica extends Pessoa {

    private final long cpf;
    private final LocalDate dataNascimento;


    public PessoaFisica(String nome, Endereco endereco, String email, long cpf, LocalDate dataNascimento) {
        super(nome, endereco, email);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
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
