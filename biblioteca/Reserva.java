package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
private String data;
private List<Livro> livros;

Reserva(String data, List<String> titulosLivros){
    this.data = data;
    this.livros = carregarLivros(titulosLivros); 
}

public String getData() {
    return data;
}

public List<Livro> getLivros() {
    return livros;
}

private List<Livro> carregarLivros(List<String> tituloLivros){
    List<Livro> livrosCarregados = new ArrayList<>();
        for (String titulo : tituloLivros) {
            livrosCarregados.add(Livro.obterLivro(titulo));
        }
        return livrosCarregados;
}

@Override
public String toString() {
    StringBuilder livrosStr = new StringBuilder();
    for (Livro livro : livros) {
        livrosStr.append(livro.getTitulo()).append(", ");
    }
    return "Reserva para o dia " + data + " com os livros: " + livrosStr.toString();
}

}