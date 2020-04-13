package exceptions;

import java.time.LocalDate;

public class AluguelNotFound extends Exception {
    public AluguelNotFound(String message, LocalDate data){
        super(message + " : " + data);
    }

}
