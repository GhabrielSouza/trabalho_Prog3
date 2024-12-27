package biblioteca;
import java.util.ArrayList;
import java.util.List;

public class Bibliotecario {

    private list<Emprestimo> emprestimos;

    private String nome;
    private String login;
    private String senha;
    private String cpf;
    private Setor lotacao;

    public Bibliotecario(String nome, String login, String senha, String cpf, Setor lotacao) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.lotacao = lotacao;
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

    
}