package biblioteca;

import java.util.ArrayList;
import java.util.List;
import divisao.Setor;
import javax.swing.JOptionPane;

public class Bibliotecario extends Funcionario implements ILivroReservado {

    private List<Emprestimo> emprestimos;

    public Bibliotecario(String nome, String login, String senha, String cpf, Setor lotacao) {
        super(nome, login, senha, lotacao);
        this.emprestimos = new ArrayList<>();
    }

    public void cadastrarEmprestimo(Reserva reserva) {
        Emprestimo emprestimo = new Emprestimo(reserva);
        emprestimos.add(emprestimo);

        JOptionPane.showMessageDialog(null, "Empréstimo cadastrado para a reserva: " + reserva.toString(),
                "Cadastro de Empréstimo", JOptionPane.INFORMATION_MESSAGE);
    }

    public void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum empréstimo cadastrado.", "Lista de Empréstimos",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder lista = new StringBuilder("Empréstimos cadastrados:\n");
            for (Emprestimo emprestimo : emprestimos) {
                lista.append(emprestimo.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString(), "Lista de Empréstimos",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void ocorreu(Reserva reserva) {
        JOptionPane.showMessageDialog(null, "Reserva registrada para o professor: " + reserva.toString(),
                "Registro de Reserva", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String informarReserva() {
        String mensagem = "Reserva registrada com sucesso.";
        JOptionPane.showMessageDialog(null, mensagem, "Registro de Reserva", JOptionPane.INFORMATION_MESSAGE);
        return mensagem;
    }

}