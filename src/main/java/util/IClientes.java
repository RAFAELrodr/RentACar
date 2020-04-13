package util;

import exceptions.AluguelNotFound;
import exceptions.CarroNotAble;
import model.Carro;

import java.time.LocalDate;

public interface IClientes {

    public void alugarCarro(LocalDate dataAluguel, Carro carro) throws CarroNotAble;

    public void devolverCarro(LocalDate dataAluguel, LocalDate dataDevolucao, Carro carro)  throws AluguelNotFound;
}
