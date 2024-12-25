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

    }

    public static <T> List<T> listar(){

    }

    public static Usuario obter(String login, String senha){

    }
}
