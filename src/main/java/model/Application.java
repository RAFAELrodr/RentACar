package model;

import exceptions.ReservaDuplicada;
import jdk.vm.ci.meta.Local;
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

        System.out.println("hello word");
        CurrencyUnit real = Monetary.getCurrency("BRL");

        PessoaFisica pessoaFisica = new PessoaFisica("Rafael",
                new Endereco("Rua x", 111, "Assis", "SP"),
                "rafael@gmail", 123456798, LocalDate.of(1991, 2, 25));

        PessoaFisica pessoaFisica1 = new PessoaFisica("Jose",
                new Endereco("Rua x", 111, "Assis", "SP"),
                "rafael@gmail", 123456789, LocalDate.of(1991, 2, 15));

       // List<Carro> carros = new LinkedList<>();
        Carro carro = new Carro(123,
                "Volks",
                "gol",
                Year.of(2019),
                Money.of(10, real));
        //carros.add(carro);

       // List<Carro> carros2 = new LinkedList<>();
        Carro carro2 = new Carro(5000,
                "Chevrolet",
                "opala",
                Year.of(1860),
                Money.of(50, real));
        //carros2.add(carro2);

        try{
            pessoaFisica.reservaCarro(pessoaFisica, LocalDate.of(2020,10,10),carro2);
            pessoaFisica.reservaCarro(pessoaFisica, LocalDate.of(2020,10,10),carro);
            pessoaFisica.reservaCarro(pessoaFisica1, LocalDate.of(2020,10,10),carro);
            pessoaFisica.reservaCarro(pessoaFisica1, LocalDate.of(2020,10,10),carro2);
        }catch (ReservaDuplicada mi){
            mi.printStackTrace();
        }


        pessoaFisica.imprimirReserva();


        System.out.println("Teste");

        //Map<Pessoa, Map<LocalDate, List<Carro>>> reservaCarro = new LinkedHashMap<>();

        //Map<LocalDate, List<Carro>> map1 = new LinkedHashMap<>();
        //Map<LocalDate, List<Carro>> map2 = new LinkedHashMap<>();

        //map1.put(LocalDate.of(2020, 04, 11), carros);
        //map1.put(LocalDate.of(2020, 04, 10), carros);
        //map1.put(LocalDate.of(2020, 04, 10), carros);



        //map2.put(LocalDate.of(2020, 04, 15), carros);
       // map2.put(LocalDate.of(2020, 04, 20), carros);

        //reservaCarro.put(pessoaFisica, map1);

        //reservaCarro.put(pessoaFisica1, map2);

        //reservaCarro.keySet().forEach(pessoa -> {
            //System.out.println("Nome: " + pessoa.getNome());
           // reservaCarro.get(pessoa).keySet().forEach(localDate -> {
               // System.out.println("Data: " + localDate);
                //reservaCarro.get(pessoa).get(localDate).forEach(carro1 -> {
                   // System.out.println(carro1.getModelo());
                //});
           // });

       // });
    }
}
