package biblioteca;

import javax.swing.JOptionPane;

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
        if (reservas == null || reservas.isEmpty()) {
            String mensagemSemRegistro = "Nao ha reservas registradas.";
            JOptionPane.showMessageDialog(null, mensagemSemRegistro, "Informacoes de Reservas", JOptionPane.INFORMATION_MESSAGE);
            return mensagemSemRegistro;
        }

        StringBuilder mensagem = new StringBuilder("Reservas do Professor:\n\n");

        for (Reserva reserva : reservas) {
            mensagem.append("Data de Reserva: ").append(reserva.getData()).append("\n");
            for (Livro livro : reserva.getLivros()) {
                mensagem.append("Livro: ").append(livro.getTitulo()).append("\n");
            }
            mensagem.append("\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString(), "Informacoes de Reservas", JOptionPane.INFORMATION_MESSAGE);
        return mensagem.toString();
    }

}