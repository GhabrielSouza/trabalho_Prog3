package biblioteca;

import acesso.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aluno extends Usuario implements IReservouLivro  {
    private int matricula;
    private List<Reserva> reservas;
    private List<ILivroReservado> consumidores;
    private static List<Funcionalidade> funcionalidades;


    public Aluno(String nome, String login, String senha, int matricula){
        super(nome, nome, senha);
        this.matricula = matricula;
    }

    public List<ILivroReservado> getConsumidores(){
        return consumidores;
    }

    public void cadastrarReserva(List<String> tituloLivros, String data){
        for (String titulo : tituloLivros) {
                Reserva reserva = new Reserva(data, tituloLivros );
        }
    }

    public void adicionar(ILivroReservado livroReservado){
        consumidores.add(livroReservado);
    }

    public void notificar(Reserva reserva){
        List<Livro> livros = reserva.getLivros();
        for (Livro livro : livros) {
            System.out.println("titulo: " + livro.getTitulo() + " autor: " + livro.getAutor() + " editora: " + livro.getEditora());
        }
        System.out.println(reserva.getData());
    }

    public void remover(ILivroReservado livroReservado){
        consumidores.remove(livroReservado);
    }


} 