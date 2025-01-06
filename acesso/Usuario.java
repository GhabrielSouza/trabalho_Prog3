package acesso;

import java.util.List;

import biblioteca.Aluno;
import biblioteca.Bibliotecario;
import biblioteca.Professor;
import divisao.Setor;

import java.util.ArrayList;


public class Usuario {
    private String nome;
    private String login;
    private String senha;

    private List<Funcionalidade> funcionalidades = null;
    private static List<Usuario> usuarios = null;

    public Usuario(String nome, String login, String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;

        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        
        List<Funcionalidade> funcs = new ArrayList<>();
        funcs.add(Funcionalidade.CAD_CONS);
        funcs.add(Funcionalidade.REM_CONS);
        funcs.add(Funcionalidade.CAD_RESE);
        this.funcionalidades = funcs;
    }

    public Usuario(String nome, String login, String senha, List<Funcionalidade> funcionalidades){
        this(nome, login, senha);

        if(funcionalidades == null){
            funcionalidades = new ArrayList<>();
        }
        this.funcionalidades = funcionalidades;
    }

    static {
		usuarios = new ArrayList<Usuario>();
		usuarios.add(new Aluno("Roberto Santos", "rsantos", "123456", 00154));
		usuarios.add(new Bibliotecario("Joana Silva", "jsilva", "654321", "09945789632", new Setor("ADM")));
		usuarios.add(new Professor("José Maria Santos", "jmsantos", "132465", "08632176245", new Setor("Matematica")));
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
