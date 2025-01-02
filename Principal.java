import javax.swing.JOptionPane;

import acesso.Usuario;
import biblioteca.Aluno;

public class Principal {
    public static void main(String[] args) {
        
    }

    public static Usuario autenticar(){
        String loginValue = JOptionPane.showInputDialog(null, "Digite seu login","Informe seu login:", JOptionPane.QUESTION_MESSAGE );
        String senhaValue = JOptionPane.showInputDialog(null, "Digite sua senha","Informe seu senha:", JOptionPane.QUESTION_MESSAGE );

        if(loginValue != null && senhaValue != null){
            Usuario usuarioValue = Usuario.obter(loginValue, senhaValue);

            if(usuarioValue != null){

                return usuarioValue;
            }


        }

        return null;
    }

    public static String[] construirMenu(Usuario usuario){

    }

    public static int selecionarFuncionalidade(String[] funcionalidadesMenu){

    } 

    public static void processarFuncionalidade(Usuario usuario, int funcionalidadeSelecionada){

    }

    public static void listarLivros(){

    }

    public static void exibirMensagemConsumidores(Aluno aluno){

    }

    public static void sair(Usuario usuario){

    }
}
