package biblioteca;

import java.util.ArrayList;
import java.util.List;

import infraestrutura.Util;

public class Emprestimo {
    private String dataRetirada;
    private String dataDevolucao;
    private List<Exemplar> exemplares;

    public Emprestimo(Reserva reserva) {
        dataRetirada = Util.obterDataAtual();
        dataDevolucao = Util.somarDiasData(dataRetirada, 7);
        
        exemplares = carregarExemplares( reserva.getLivros());
    }

    public String getDataRetirada(){
        return dataRetirada;
    }

    public String getDataDevolucao(){
        return dataDevolucao;
    }

    public List<Exemplar> getExemplares(){
        return exemplares;
    }

    private List<Exemplar> carregarExemplares(List<Livro> livros){
        List<Exemplar> livrosExemplares = new ArrayList<>();
        for(Livro livro : livros){
            livrosExemplares.add(new Exemplar(livro));
        }

        return livrosExemplares;
    }
}