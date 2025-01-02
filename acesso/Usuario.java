package acesso;

import java.util.List;
import java.util.ArrayList;


public class Usuario {
    private String nome;
    private String login;
    private String senha;

    private List<Funcionalidade> funcionalidades;
    private static List<Usuario> usuarios;


    public Usuario(String nome, String login, String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nome, String login, String senha, List<Funcionalidade> funcionalidades){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.funcionalidades = funcionalidades;
    }

    public String getNome(){
        return nome;
    }

    public List<Funcionalidade> getFuncionalidade(){
        return funcionalidades;
    }

    public String sair(){
        return "Saindo...";
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> listar(Class<T> instaciaClasse){
        List<T> instaciaDaClasse = new ArrayList<>();
        
        for(Usuario usuario : usuarios ){
            if(instaciaClasse.isInstance(usuario)){
                instaciaDaClasse.add((T) usuario);
            }
        }

        return instaciaDaClasse;
    }


    public static Usuario obter(String login, String senha){
        for(Usuario usuario : usuarios){
            if(usuario.login.equals(login)  && usuario.senha.equals(senha)){
                return usuario;
            }
        }

        return null;
    }
}
