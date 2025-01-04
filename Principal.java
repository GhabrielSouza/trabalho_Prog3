import java.util.List;

import javax.swing.JOptionPane;

import acesso.Usuario;
import biblioteca.*;

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

    public static ILivroReservado selecionarConsumidor(Aluno usuario, boolean naListaProdutor){

    }

    public static void listarLivros() {
        List<String> titulos = Livro.listar();

        if (titulos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum livro registrado na biblioteca.", "Lista de Livros",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder mensagem = new StringBuilder("Livros registrados na biblioteca:\n");
        for (String titulo : titulos) {
            mensagem.append("- ").append(titulo).append("\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString(), "Lista de Livros", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void exibirMensagemConsumidores(Aluno aluno) {
        List<ILivroReservado> consumidores = aluno.getConsumidores();

        if (consumidores == null || consumidores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum consumidor registrado para este aluno.", "Consumidores",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder mensagem = new StringBuilder("Consumidores registrados:\n");
        for (ILivroReservado consumidor : consumidores) {
            mensagem.append(consumidor.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString(), "Consumidores", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void sair(Usuario usuario) {
        StringBuilder mensagemInfoUsuario = new StringBuilder("Informacoes do Usuario:\n\n");
        Emprestimo emprestimo = new emprestimos.getExemplares();

        mensagemInfoUsuario.append("Data de Retirada: ").append(emprestimo.getDataRetirada()).append("\n");
        mensagemInfoUsuario.append("Data de Devolução: ").append(emprestimo.getDataDevolucao()).append("\n\n");

        for (Exemplar exemplar : emprestimo.getExemplares()) {
            Livro livro = exemplar.getLivro();
            mensagemInfoUsuario.append("Livro: ").append(livro.getTitulo()).append("\n");
            mensagemInfoUsuario.append("Autor: ").append(livro.getAutor()).append("\n");
            mensagemInfoUsuario.append("Editora: ").append(livro.getEditora()).append("\n");
            mensagemInfoUsuario.append("Exemplar Código: ").append(exemplar.getCodigo()).append("\n");
            mensagemInfoUsuario.append("Situação: ").append(exemplar.getSituacao()).append("\n\n");

        JOptionPane.showMessageDialog(null, mensagemInfoUsuario.toString(), "Informações do Empréstimo", JOptionPane.INFORMATION_MESSAGE);

        // -----------------------------------
        
        String nomeUsuario = usuario.getNome();
        String tipoUsuario = usuario.getClass().getSimpleName();
        String mensagem = "O usuario " + nomeUsuario + ", do tipo " + tipoUsuario + ", deixou o sistema.";

        JOptionPane.showMessageDialog(null, mensagem, "Saida do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}