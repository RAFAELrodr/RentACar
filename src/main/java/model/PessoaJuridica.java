package model;

import util.Endereco;
import util.IPagamento;

import java.util.Objects;

public class PessoaJuridica extends Pessoa implements IPagamento {
    private final long cnpj;


    public PessoaJuridica(String nome, Endereco endereco, String email, long cnpj) {
        super(nome, endereco, email);
        this.cnpj = cnpj;
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
    public String cartaao() {
        return null;
    }
}
