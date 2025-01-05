package acesso;

public enum Funcionalidade {
    CAD_CONS("Cadastro de Consumidor"),
    REM_CONS("Remover Consumidor"),
    CAD_RESE("Cadastro de Reserva");

    private final String sigla;

    Funcionalidade(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }
}