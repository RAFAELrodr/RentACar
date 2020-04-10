package model;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

public class Carro {
    private final long id;
    private final String marca;
    private final String modelo;
    private final Year ano;
    private MonetaryAmount valorDiaria;

    //CurrencyUnit real = Monetary.getCurrency("BRL");


    public Carro(long id, String marca, String modelo, Year ano, MonetaryAmount valorDiaria) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return id == carro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

