package model;

import org.javamoney.moneta.Money;
import util.Endereco;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class Application {

    public static void main(String[] args) {

        CurrencyUnit real = Monetary.getCurrency("BRL");


        PessoaFisica pessoaFisica = new PessoaFisica("Rafael",
                new Endereco("Rua x", 111,"Assis","SP"),
                "rafael@gmail",123456789, LocalDate.of(25,02,1991));

        List<Carro> carros = new LinkedList<>();
        Carro carro = new Carro(123,
                "volks",
                "gol",
                Year.of(2019),
                Money.of(10, real));
        carros.add(carro);

        Map<Pessoa, Map<LocalDate, List<Carro>>> reservaCarro = new LinkedHashMap<>();
        reservaCarro.compute(pessoaFisica, (k, v) -> new HashMap<>()).put(LocalDate.of(2020,04,10),carros);

        System.out.println(reservaCarro);
    }
}
