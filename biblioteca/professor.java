package biblioteca;

public class Professor implements ILivroReservado {

    private String nome;
    private String login;
    private String senha;
    private String cpf;
    private Setor lotacao;

    public Professor(String nome, String login, String senha, String cpf, Setor lotacao) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.lotacao = lotacao;
    }

    @Override
    public void ocorreu(Reserva reserva) {
        this.reserva = reserva;
        System.out.println("Reserva registrada para o professor.");
    }

    @Override
    public String informarReserva() {
        if (reserva == null) {
            return "Nenhuma reserva registrada.";
        } else {
            return "Reserva registrada: " + reserva.toString();
        }
    }

}