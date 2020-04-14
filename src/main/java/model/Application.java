package model;

import enums.StatusCarro;
import exceptions.AluguelNotFound;
import exceptions.CarroNotAble;

import org.javamoney.moneta.Money;
import util.Endereco;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) throws Exception {

        System.out.println("hello word");
        CurrencyUnit real = Monetary.getCurrency("BRL");

        PessoaFisica pessoaFisica = new PessoaFisica("Rafael",
                new Endereco("Rua", 111, "Assis", "SP"),
                "rafael@gmail", 123456798, LocalDate.of(1991, 2, 25));

        PessoaFisica pessoaFisica1 = new PessoaFisica("Jose",
                new Endereco("Rua x", 111, "Assis", "SP"),
                "rafael@gmail", 123456789, LocalDate.of(1991, 2, 15));

        List<Carro> carros = new LinkedList<>();
        carros.add(new Carro(123, "Volks", "Gol", Year.of(2019), Money.of(10, real)));
        carros.add(new Carro(5000,"Chevrolet", "Opala", Year.of(1960), Money.of(50, real)));
        carros.add(new Carro(321,"Volks", "Fusca", Year.of(1947), Money.of(110, real)));
        carros.add(new Carro(5000,"Ford", "DelRey", Year.of(1970), Money.of(90, real)));
        /*Carro carro = new Carro(123,
                "Volks",
                "gol",
                Year.of(2019),
                Money.of(10, real));
        carros.add(carro);*/

        // List<Carro> carros2 = new LinkedList<>();
        /*Carro carro2 = new Carro(5000,
                "Chevrolet",
                "opala",
                Year.of(1860),
                Money.of(50, real));*/
        //carros2.add(carro2);


        try {
            pessoaFisica.alugarCarro(LocalDate.of(2020, 10, 5), carros.get(0));
            pessoaFisica.alugarCarro(LocalDate.of(2020, 10, 11), carros.get(1));
            pessoaFisica.alugarCarro(LocalDate.of(2020, 10, 10), carros.get(2));

            pessoaFisica1.alugarCarro(LocalDate.of(2020, 11, 10), carros.get(3));
            pessoaFisica1.alugarCarro(LocalDate.of(2020, 11, 1), carros.get(2));

        } catch (CarroNotAble mi) {
            mi.printStackTrace();
        }
try{
    System.out.println("LISTAR CARROS ALUGADOS");
    pessoaFisica.imprimirAlugados();
    pessoaFisica1.imprimirAlugados();

    System.out.println("DEVOLVER CARRO");
    pessoaFisica.devolverCarro(LocalDate.of(2020,10,11),
            LocalDate.of(2020,11,11),
            carros.get(1));
    pessoaFisica.devolverCarro(LocalDate.of(2020,10,11),
            LocalDate.of(2020,11,11),
            carros.get(0));
}catch (AluguelNotFound mi){
    mi.printStackTrace();
}

        System.out.println("PROCURAR ALUGUEL");
        System.out.println(pessoaFisica1.procurarAluguel(LocalDate.of(2020,11,10)));

        System.out.println("Filtrar carro por Status Alugado");
        List<String> carrosFiltrados = carros.stream()
                .filter(carro -> carro.getStatusCarro().equals(StatusCarro.ALUGADO))
                .map(carro -> carro.getModelo()).collect(Collectors.toList());
        System.out.println(carrosFiltrados);

        // System.out.println("Nome:" + carro2.getAno() + "Status: " + carro2.getStatusCarro());
       // pessoaFisica.devolverCarro(pessoaFisica, );


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
