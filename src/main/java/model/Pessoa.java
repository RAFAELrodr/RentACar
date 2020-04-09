package model;

import util.Endereco;

import java.math.BigDecimal;

public abstract class Pessoa {

    private String nome;
    private Endereco endereco;
    private String email;

    public Pessoa(String nome, Endereco endereco, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
    }



}
