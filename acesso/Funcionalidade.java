package acesso;

public enum Funcionalidade {
    CAD_CONS("REM_CONS"),
    REM_CONS("REM_CONS"),
    CAD_RESE("CAD_RESE");

    private final String sigla;

    Funcionalidade(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }
}