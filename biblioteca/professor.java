package biblioteca;

import javax.swing.JOptionPane;

import divisao.Setor;

public class Professor extends Funcionario implements ILivroReservado {

    private Reserva reserva;

    public Professor(String nome, String login, String senha, String cpf, Setor lotacao) {
        super(nome, login, senha, cpf, lotacao);
    }

    @Override
    public void ocorreu(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String informarReserva() {
        if (reserva == null) {
            String mensagemSemRegistro = "Nao ha reservas registradas.";
            JOptionPane.showMessageDialog(null, mensagemSemRegistro, "Informacoes de Reservas",
                    JOptionPane.INFORMATION_MESSAGE);
            return mensagemSemRegistro;
        }

        StringBuilder mensagem = new StringBuilder("Reserva do Professor:\n\n");

        mensagem.append("Data de Reserva: ").append(reserva.getData()).append("\n");
        for (Livro livro : reserva.getLivros()) {
            mensagem.append("Livro: ").append(livro.getTitulo()).append("\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString(), "Informacoes de Reservas",
                JOptionPane.INFORMATION_MESSAGE);
        return mensagem.toString();
    }

}