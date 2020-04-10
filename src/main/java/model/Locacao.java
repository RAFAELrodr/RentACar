package model;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Locacao {

    private final int id;
    private Pessoa pessoa;
    private Carro carro;
    private LocalDate dataLocacao;
    private LocalDate dataEntrega;
    private boolean pagamento;

    private Map<Pessoa, Map<LocalDate, List<Carro>>> reservaCarro = new LinkedHashMap<>();


    public Locacao(int id, Pessoa pessoa, Carro carro, LocalDate dataLocacao, LocalDate dataEntrega, boolean pagamento) {
        this.id = id;
        this.pessoa = pessoa;
        this.carro = carro;
        this.dataLocacao = dataLocacao;
        this.dataEntrega = dataEntrega;
        this.pagamento = pagamento;
    }
}
