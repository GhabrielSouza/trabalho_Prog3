import java.util.List;

import javax.swing.JOptionPane;

import acesso.Funcionalidade;
import acesso.Usuario;
import biblioteca.Aluno;
import biblioteca.ILivroReservado;

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

    public static String[] construirMenu(Usuario usuario) {
        // Obtém as funcionalidades diretamente do objeto 'usuario' usando o método 'getFuncionalidade'
        List<Funcionalidade> funcionalidadesList = usuario.getFuncionalidade();
        
        // Se a lista for nula ou estiver vazia, retorna um array vazio
        if (funcionalidadesList == null || funcionalidadesList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma funcionalidade disponível.", "Erro", JOptionPane.ERROR_MESSAGE);
            return new String[] {};
        }
        
        // Converte a lista de funcionalidades para um array de strings para exibição no menu
        String[] funcionalidadesMenu = new String[funcionalidadesList.size()];
        for (int i = 0; i < funcionalidadesList.size(); i++) {
            funcionalidadesMenu[i] = funcionalidadesList.get(i).toString(); // Adapte conforme a implementação de 'toString' de Funcionalidade
        }

        // Chama a função selecionarFuncionalidade para obter a funcionalidade escolhida
        int funcionalidadeSelecionada = selecionarFuncionalidade(funcionalidadesMenu);
        
        // Se o usuário selecionar uma opção válida, retorna um array com a opção escolhida
        if (funcionalidadeSelecionada != -1) {
            return new String[] { funcionalidadesMenu[funcionalidadeSelecionada] };
        } else {
            return new String[] {}; // Retorna um array vazio se nenhuma funcionalidade for selecionada
        }
    }

    public static int selecionarFuncionalidade(String[] funcionalidadesMenu) {
        if (funcionalidadesMenu == null || funcionalidadesMenu.length == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma funcionalidade disponível.", "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    
        // Constrói a lista de funcionalidades em formato de String para exibição no JOptionPane
        StringBuilder funcionalidadesList = new StringBuilder("Selecione uma funcionalidade:\n");
        for (int i = 0; i < funcionalidadesMenu.length; i++) {
            funcionalidadesList.append((i + 1) + ". " + funcionalidadesMenu[i] + "\n");
        }
    
        // Exibe as funcionalidades com JOptionPane
        String input = JOptionPane.showInputDialog(null, funcionalidadesList.toString(), "Escolha uma funcionalidade", JOptionPane.QUESTION_MESSAGE);
        
        try {
            if (input != null && !input.isEmpty()) {
                int escolha = Integer.parseInt(input);
                if (escolha >= 1 && escolha <= funcionalidadesMenu.length) {
                    return escolha - 1; // Retorna o índice da funcionalidade selecionada
                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return -1;
                }
            } else {
                return -1; // Caso o input seja nulo ou vazio
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static void processarFuncionalidade(Usuario usuario, int funcionalidadeSelecionada) {
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuário não autenticado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        switch (funcionalidadeSelecionada) {
            case 0: // Cadastrar Consumidor
                JOptionPane.showMessageDialog(null, "Função Cadastrar Consumidor ainda não implementada.", "Info", JOptionPane.INFORMATION_MESSAGE);
                break;
    
            case 1: // Remover Consumidor
                JOptionPane.showMessageDialog(null, "Função Remover Consumidor ainda não implementada.", "Info", JOptionPane.INFORMATION_MESSAGE);
                break;
    
            case 2: // Cadastrar Reserva
                JOptionPane.showMessageDialog(null, "Função Cadastrar Reserva ainda não implementada.", "Info", JOptionPane.INFORMATION_MESSAGE);
                break;
    
            case 3: // Sair
                sair(usuario);
                break;
    
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public static ILivroReservado selecionarConsumidor(Aluno usuario, boolean naListaProdutor){

    }

    public static void listarLivros(){

    }

    public static void exibirMensagemConsumidores(Aluno aluno){

    }

    public static void sair(Usuario usuario){

    }
}
