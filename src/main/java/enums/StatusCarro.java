package enums;

public enum StatusCarro {
    RESERVADO (1),
    ALUGADO(2),
    DISPONIVEL(3);

    public int codigo;
    StatusCarro(int i) {
        codigo = i;
    }
}
