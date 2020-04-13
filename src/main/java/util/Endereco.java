package util;

import java.util.Objects;

public class Endereco {

    private String rua;
    private int numero;
    private String cidade;
    private String estado;

    public Endereco(String rua, int numero, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return numero == endereco.numero &&
                rua.equals(endereco.rua) &&
                cidade.equals(endereco.cidade) &&
                estado.equals(endereco.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rua, numero, cidade, estado);
    }

    @Override
    public String toString() {
        return  "RUA: " + rua +
                ", Nº: " + numero +
                ", Cidade: " + cidade +
                ", UF: " + estado;
    }
}


