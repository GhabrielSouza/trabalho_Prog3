package biblioteca;

import java.util.List;

public class Emprestimo {
    private String dataRetirada;
    private String dataDevolucao;
    private List<Exemplar> exemplares;

    public Emprestimo(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getDataRetidara(){
        return dataRetirada;
    }

    public String getDataDevolucao(){
        return dataDevolucao;
    }

    public List<Exemplar> getExemplares(){
        return exemplares;
    }

    private List<Exemplar> carregarExemplares(List<Livro> livros){
        
    }
}