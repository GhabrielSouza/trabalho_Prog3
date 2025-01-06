
import javax.swing.JComboBox;
import java.util.List;

import javax.swing.JOptionPane;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import acesso.*;
import biblioteca.*;
import divisao.Setor;
import infraestrutura.Util;

public class Principal {

    private static String TITULO = "Sistema Bibliotecário | v1.0";
    public static List<ILivroReservado> usuariosDisponiveis = Usuario.listar(ILivroReservado.class);
    //public static List<Usuario> consumidores = new ArrayList<>();
  
    public static void main(String[] args) {
        List<Funcionalidade> funcionalidades = new ArrayList<>();
        funcionalidades.add(Funcionalidade.CAD_CONS);
        funcionalidades.add(Funcionalidade.REM_CONS);
        funcionalidades.add(Funcionalidade.CAD_RESE);

        // Livro livro1 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "HarperCollins");
       
        // Exemplar exemplar1 = new Exemplar(livro1);
        // Exemplar exemplar2 = new Exemplar(livro1);
        
        Usuario usuario1 = new Usuario("LUIIIZ", "Luiz1", "1234", funcionalidades);

        Usuario usuarioLogado = null;
        // while (usuarioLogado == null) {
        usuarioLogado = Principal.autenticar();
        // }

        String[] opcoesMenu = construirMenu(usuarioLogado); // Gera as opções do menu

        if (opcoesMenu.length == 0)
            return; // Sai se não houver funcionalidades

        while (true) {
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    TITULO,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoesMenu,
                    opcoesMenu[0]);

            if (escolha == -1 || escolha == 3) {
                Principal.processarFuncionalidade(usuarioLogado, 3);
                JOptionPane.showMessageDialog(null, "Menu encerrado.");
                break;
            }

            Principal.processarFuncionalidade(usuarioLogado, escolha);

        }

    }

    public static Usuario autenticar() {
        boolean logado = false;
    
        while (!logado) {
            // Solicitar login
            String loginValue = JOptionPane.showInputDialog(null, "Digite seu login", "Informe seu login:",
                    JOptionPane.QUESTION_MESSAGE);
            if (loginValue == null) { // Verifica se foi clicado em "Cancelar"
                return null; // Encerra o método e cancela o login
            }
    
            // Solicitar senha
            String senhaValue = JOptionPane.showInputDialog(null, "Digite sua senha", "Informe sua senha:",
                    JOptionPane.QUESTION_MESSAGE);
            if (senhaValue == null) { // Verifica se foi clicado em "Cancelar"
                return null; // Encerra o método e cancela o login
            }
    
            // Validar campos vazios
            if (loginValue.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Login não pode estar vazio.", "Erro",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }
    
            if (senhaValue.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Senha não pode estar vazia.", "Erro",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }
    
            // Tentar obter o usuário
            Usuario usuarioValue = Usuario.obter(loginValue, senhaValue);
            if (usuarioValue != null) {
                JOptionPane.showMessageDialog(null, "Usuário logado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                return usuarioValue; // Login bem-sucedido, retorna o usuário
            } else {
                JOptionPane.showMessageDialog(null, "Login ou senha inválidos. Tente novamente.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    
        return null; // Caso algo falhe, retorna null
    }

    public static String[] construirMenu(Usuario usuario) {
        // Obtém as funcionalidades diretamente do objeto 'usuario' usando o método
        // 'getFuncionalidade'
        List<Funcionalidade> funcionalidadesList = usuario.getFuncionalidade();

        // Se a lista for nula ou estiver vazia, retorna apenas a opção "Sair"
        if (funcionalidadesList == null || funcionalidadesList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma funcionalidade disponível.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return new String[] { "Sair" };
        }

        // Converte a lista de funcionalidades em um vetor de Strings com as siglas
        String[] funcionalidades = funcionalidadesList.stream()
                .map(Funcionalidade::getSigla)
                .toArray(String[]::new);

        // Cria um novo vetor com espaço adicional para a opção "Sair"
        String[] menu = new String[funcionalidades.length + 1];
        System.arraycopy(funcionalidades, 0, menu, 0, funcionalidades.length);

        // Adiciona a opção fixa "Sair" no final
        menu[menu.length - 1] = "Sair";

        return menu;
    }

    public static int selecionarFuncionalidade(String[] funcionalidadesMenu) {
        if (funcionalidadesMenu == null || funcionalidadesMenu.length == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma funcionalidade disponível.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }

        // Constrói a lista de funcionalidades em formato de String para exibição no
        // JOptionPane
        StringBuilder funcionalidadesList = new StringBuilder("Selecione uma funcionalidade:\n");
        for (int i = 0; i < funcionalidadesMenu.length; i++) {
            funcionalidadesList.append((i + 1) + ". " + funcionalidadesMenu[i] + "\n");
        }

        // Exibe as funcionalidades com JOptionPane
        String input = JOptionPane.showInputDialog(null, funcionalidadesList.toString(), "Escolha uma funcionalidade",
                JOptionPane.QUESTION_MESSAGE);

        try {
            if (input != null && !input.isEmpty()) {
                int escolha = Integer.parseInt(input);
                if (escolha >= 1 && escolha <= funcionalidadesMenu.length) {
                    return escolha - 1; // Retorna o índice da funcionalidade selecionada
                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return -1;
                }
            } else {
                return -1; // Caso o input seja nulo ou vazio
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número válido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static void processarFuncionalidade(Usuario usuario, int funcionalidadeSelecionada) {
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuário não autenticado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Aluno aluno = (Aluno) usuario;
        List<String> nomesDisponiveis = null;
        List<ILivroReservado> consumidores = aluno.getConsumidores();
        int opcao;

        switch (funcionalidadeSelecionada) {
            case 0:
            // Verifica se há consumidores disponíveis
            if (usuariosDisponiveis.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há consumidores disponíveis para cadastrar.");
            } else {
                // Cria o JComboBox com os nomes dos consumidores disponíveis
                JComboBox<String> comboBoxA = new JComboBox<>(usuariosDisponiveis.stream()
                .map(u -> ((Usuario) u).getNome())
                .toArray(String[]::new));
        
                // Mostra o JComboBox em um JOptionPane
                opcao = JOptionPane.showConfirmDialog(
                        null,
                        comboBoxA,
                        "Selecione um Consumidor",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
        
                // Se o usuário pressionar OK, adicione o consumidor à lista de consumidores cadastrados
                if (opcao == JOptionPane.OK_OPTION) {
                    ILivroReservado consumidorSelecionado = usuariosDisponiveis.get(comboBoxA.getSelectedIndex());
        
                    consumidores.add(consumidorSelecionado);  // Adiciona à lista de consumidores
                    usuariosDisponiveis.remove(consumidorSelecionado);  // Remove da lista de disponíveis
        
                    JOptionPane.showMessageDialog(null, "Consumidor selecionado e cadastrado com sucesso.");
                }
            }
            break;
        
        case 1:
            // Remover Consumidor
            if (consumidores.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há consumidores para remover.");
            } else {
                // Crie uma lista com os nomes dos consumidores cadastrados
                List<String> nomesConsumidores = new ArrayList<>();
                for (ILivroReservado consumidor : consumidores) {
                    Usuario user = (Usuario) consumidor;
                    nomesConsumidores.add(user.getNome());  // Assumindo que a interface ILivroReservado tem o método getNome()
                }
        
                // Crie o JComboBox com os nomes dos consumidores cadastrados
                JComboBox<String> comboBoxRemover = new JComboBox<>(nomesConsumidores.toArray(new String[0]));
        
                // Mostre o JComboBox em um JOptionPane
                opcao = JOptionPane.showConfirmDialog(
                        null,
                        comboBoxRemover,
                        "Selecione um Consumidor para Remover",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
        
                // Se o usuário pressionar OK, obtenha o índice do consumidor selecionado
                if (opcao == JOptionPane.OK_OPTION) {
                    int consumidorSelecionado = comboBoxRemover.getSelectedIndex();
        
                    // Remova o consumidor da lista de consumidores
                    ILivroReservado consumidorARemover = consumidores.get(consumidorSelecionado);
                    consumidores.remove(consumidorSelecionado);
        
                    // Adicione de volta na lista de consumidores disponíveis
                    usuariosDisponiveis.add(consumidorARemover);
        
                    JOptionPane.showMessageDialog(null, "Consumidor removido com sucesso.");
                }
            }
            break;
            case 2:
                // Listar todos os títulos de livros na biblioteca
                List<String> tituloLivros = Livro.listar();

                if (tituloLivros.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Não há livros disponíveis.");
                    break;
                }

                // Exibe todos os títulos de livros
                StringBuilder livrosStr = new StringBuilder();
                for (String titulo : tituloLivros) {
                    livrosStr.append(titulo).append("\n");
                }

                // Exibe os títulos em um JOptionPane
                JOptionPane.showMessageDialog(null, livrosStr.toString(), "Títulos dos Livros",
                        JOptionPane.INFORMATION_MESSAGE);

                List<String> livrosReservados = new ArrayList<>();
                boolean continuarReservando = true;

                while (continuarReservando) {
                    // Solicitar ao usuário que digite o título do livro
                    String livroEscolhido = JOptionPane.showInputDialog(
                            null,
                            "Digite o título do livro para reserva:",
                            "Cadastro de Reserva",
                            JOptionPane.PLAIN_MESSAGE);

                    // Verificar se o título foi digitado
                    if (livroEscolhido != null && !livroEscolhido.trim().isEmpty()) {
                        // Verificar se o título está na lista de livros disponíveis
                        if (tituloLivros.contains(livroEscolhido.trim())) {
                            livrosReservados.add(livroEscolhido.trim());
                            System.out.println("Livro escolhido: " + livroEscolhido);
                        } else {
                            // Caso o título não esteja na lista
                            JOptionPane.showMessageDialog(null, "Título não encontrado. Tente novamente.");
                            continue; // Volta para a solicitação de um novo título
                        }
                    } else {
                        // O usuário não digitou nada ou cancelou
                        JOptionPane.showMessageDialog(null, "Nenhum livro foi escolhido.");
                        break; // Encerra o loop de reserva
                    }

                    // Perguntar se o usuário quer adicionar mais livros
                    int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "Você deseja adicionar mais um livro para reserva?",
                            "Adicionar mais livros?",
                            JOptionPane.YES_NO_OPTION);

                    // Se o usuário escolher "Não", sair do loop
                    if (resposta == JOptionPane.NO_OPTION) {
                        continuarReservando = false;
                    }
                }

                if (!livrosReservados.isEmpty()) {
                    // Registrar a reserva com a data atual
                    String dataAtual = Util.obterDataAtual();
                    if (usuario instanceof Aluno) {
                        Aluno alunoLogado = (Aluno) usuario;
                        // Agora você pode chamar o método cadastrarReserva na classe Aluno
                        System.out.println("1");
                        alunoLogado.cadastrarReserva(livrosReservados, dataAtual);
                        System.out.println("2");

                        // Rodar a lista de consumidores e verificar se algum é instância de
                        // ILivroReservado
                        for (ILivroReservado consumidor : consumidores) {
                            if (consumidor instanceof ILivroReservado) {
                                ILivroReservado livroReservado = (ILivroReservado) consumidor;
                                // Chama o método ocorreuReserva()
                                for (Reserva reserva : alunoLogado.getReservas()) {
                                    livroReservado.ocorreu(reserva);
                                }
                            }
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum livro foi reservado.");
                }
                break;

            case 3: // Sair
                    sair(usuario);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    /*
     * public static ILivroReservado selecionarConsumidor(Aluno usuario, boolean
     * naListaProdutor){
     * 
     * }
     */

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
        Aluno aluno = (Aluno) usuario;
        List<ILivroReservado> consumidores = aluno.getConsumidores();

        for (ILivroReservado consumidor : consumidores) {
                    // Verifica se o usuário tem uma reserva
            if (consumidor instanceof ILivroReservado) {
                ILivroReservado livroReservado = (ILivroReservado) consumidor ;
                livroReservado.informarReserva();
            }
            
            if(consumidor instanceof Aluno){

            }
            else {
                //JOptionPane.showMessageDialog(null, usuario.getNome() + " não tem reservas.", "Sem Reservas", JOptionPane.WARNING_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null, "Usuario: "+ usuario.getNome() + " do tipo: " + usuario.getClass() + ", deixou o sistema.", TITULO, JOptionPane.WARNING_MESSAGE);
    }
}