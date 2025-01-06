package biblioteca;

import infraestrutura.Util;

public class Exemplar {
    private int codigo;
    private String dataAquisicao;
    private int situacao;
    private Livro livro;

    public Exemplar(Livro livro){
        this.livro = livro;
        this.dataAquisicao = Util.obterDataAtual();
        this.situacao = 1;
        this.codigo = 1;  
    }

    public int getCodigo(){
        return codigo;
    }

    public int getSituacao(){
        return situacao;
    }

    public void setSituacao(int situacao){
        this.situacao = situacao;
    }

    public Livro getLivro(){
        return livro;
    }
       
    
}