package biblioteca;

import acesso.*;
import divisao.*;

abstract class Funcionario extends Usuario{
    private String cpf;
    private int pis;
    private Setor lotacao;

    public Funcionario( String nome,String login, String senha, Setor lotacao){
        super(nome, login, senha);
        this.lotacao = lotacao;
    }

    public Funcionario(String nome,String login, String senha,String cpf ,Setor lotacao){
        this(nome, login, senha, lotacao);
        this.cpf = cpf;
    }
    
    public Funcionario(String nome,String login, String senha,String cpf , int pis,Setor lotacao){
        this(nome, login, senha, cpf, lotacao);
        this.pis = pis;
    }
    
    public Setor getlotacao(){
        return lotacao;
    }

    public void setLotacao(Setor lotacao){
        this.lotacao = lotacao;
    }
    
}