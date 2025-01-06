package biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Livro {
    private String titulo;
    private String autor;
    private String editora; 
    private static Map<Livro, List<Exemplar>> biblioteca = criarBiblioteca();


    public Livro(String titulo){
        this.titulo = titulo;
    }

    
    public Livro(String titulo, String autor, String editora){
        this(titulo);
        this.autor = autor;
        this.editora = editora;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getAutor(){
        return this.autor;
    }

    public String getEditora(){
        return this.editora;
    }

    public static Livro obterLivro(String titulo){
         for (Livro livro : biblioteca.keySet()) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) { 
                return livro;
            }
        }
        return null;
        
    }

    public static Exemplar obterExemplar(Livro livro) {
        List<Exemplar> exemplares = biblioteca.get(livro);  // Obter lista de exemplares do livro
        if (exemplares != null && !exemplares.isEmpty()) {  // Verificar se a lista não está vazia
            for (Exemplar exemplar : exemplares) {  // Iterar sobre os exemplares
                if (exemplar.getSituacao() == 1) {  // Verificar se o exemplar está disponível (situacao == 0)
                    exemplar.setSituacao(0);  // Alterar a situação para 1, por exemplo, para indicar que foi retirado
                    return exemplar;  // Retornar o exemplar encontrado
                }
            }
        }
        return null;  // Retornar null se não encontrar um exemplar disponível
    }
    public static List<String> listar(){
        List<String> listagem = new ArrayList<>();
        for (Livro livro : biblioteca.keySet()) {
            listagem.add(livro.titulo);
        }

        return listagem;
    }

    private static Map<Livro, List<Exemplar>> criarBiblioteca() {
        Map<Livro, List<Exemplar>> biblioteca = new HashMap<>();

        Livro livro1 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "HarperCollins");
       

        biblioteca.put(livro1, null);

        criarListaExemplares(biblioteca);

        return biblioteca;
    }   

    private static void criarListaExemplares(Map<Livro, List<Exemplar>> biblioteca) {
        for (Livro livro : biblioteca.keySet()) {
            // Verifica se a lista de exemplares do livro já foi criada. Se não, cria uma lista vazia.
            if (!biblioteca.containsKey(livro)) {
                List<Exemplar> exemplares = new ArrayList<>();
                
                for (int i = 0; i < 5; i++) {
                    exemplares.add(new Exemplar(livro));
                }
                biblioteca.put(livro,exemplares);
            }

            
        } 
    }   
}
