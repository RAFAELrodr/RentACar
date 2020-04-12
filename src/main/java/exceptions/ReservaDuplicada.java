package exceptions;

import model.Carro;

public class ReservaDuplicada extends Exception {
    public ReservaDuplicada(String message, Carro carro) {
        super(message + " : " + carro);
    }
}
