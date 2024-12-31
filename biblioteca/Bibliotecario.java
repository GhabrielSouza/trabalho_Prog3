package biblioteca;
import java.util.ArrayList;
import java.util.List;
import divisao.Setor;
import javax.swing.JOptionPane;

public class Bibliotecario extends Funcionario implements ILivroReservado{

    private List<Emprestimo> emprestimos;

    public Bibliotecario(String nome, String login, String senha, String cpf, Setor lotacao) {
        super(nome, login, senha, lotacao);
        this.emprestimos = new ArrayList<>();
    }


    public void cadastrarEmprestimo(Reserva reserva){
        Emprestimo emprestimo = new Emprestimo(Reserva);

        emprestimo.add(emprestimo);

        System.out.println("Empréstimo cadastrado para a reserva: " + reserva.toString());
    }

    public void listarEmprestimos() {
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
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