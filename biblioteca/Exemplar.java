package biblioteca;

public class Exemplar {
    private int codigo;
    private String dataAquisicao;
    private int situacao;
    private Livro livro;

    public Exemplar(Livro livro){
        this.livro = new Livro(livro);
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
