import javax.swing.JOptionPane;

import acesso.Usuario;
import biblioteca.Aluno;

public class Principal {

    private static String TITULO = "Sistema Bibliotecário | v1.0"; 
    public static void main(String[] args) {
        
    }

    public static Usuario autenticar(){
        String loginValue = JOptionPane.showInputDialog(null, "Digite seu login","Informe seu login:", JOptionPane.QUESTION_MESSAGE );
        String senhaValue = JOptionPane.showInputDialog(null, "Digite sua senha","Informe seu senha:", JOptionPane.QUESTION_MESSAGE );

        if(loginValue == null || loginValue.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Login e senha não podem estar vazios.", "Erro", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if(senhaValue == null || senhaValue.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Login e senha não podem estar vazios.", "Erro", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if(loginValue != null && senhaValue != null){
            Usuario usuarioValue = Usuario.obter(loginValue, senhaValue);

            if(usuarioValue != null){
                JOptionPane.showMessageDialog(null, "Usuário logado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                return usuarioValue;
            }
        }

        JOptionPane.showMessageDialog(null, "Login ou senha inválidos. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public static String[] construirMenu(Usuario usuario){
        Object[] options = { "Cadastrar Consumidor", "Remover Consumidor", "Cadastrar Reserva", "Sair" }; 

         int opcoes =   JOptionPane.showOptionDialog(null, "Selecione a opção desejada:", "Aviso", 
             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
             null, options, options[0]);

        if(opcoes == JOptionPane.CLOSED_OPTION){
            return new String[]{};
        }    
        
        return new String[] {options[opcoes].toString()};
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
