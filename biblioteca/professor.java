package biblioteca;

import divisao.Setor;

class Professor extends Funcionario implements ILivroReservado {

    private Reserva reserva;

    public Professor(String nome, String login, String senha, String cpf, Setor lotacao) {
        super(nome, login, senha, cpf, lotacao);
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