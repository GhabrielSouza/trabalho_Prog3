
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

public class  Principal {
                    
    private static String TITULO = "Sistema Bibliotecário | v1.0";
    public static List<ILivroReservado> usuariosDisponiveis = Usuario.listar(ILivroReservado.class);
   
  
    public static void main(String[] args) {
        List<Funcionalidade> funcionalidades = new ArrayList<>();
        funcionalidades.add(Funcionalidade.CAD_CONS);
        funcionalidades.add(Funcionalidade.REM_CONS);
        funcionalidades.add(Funcionalidade.CAD_RESE);

    

        Usuario usuarioLogado = null;
        
        usuarioLogado = Principal.autenticar();
        

        String[] opcoesMenu = construirMenu(usuarioLogado); 

        if (opcoesMenu.length == 0)
            return; 

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
           
            String loginValue = JOptionPane.showInputDialog(null, "Digite seu login", "Informe seu login:",
                    JOptionPane.QUESTION_MESSAGE);
            if (loginValue == null) { 
                return null; 
            }
    
            
            String senhaValue = JOptionPane.showInputDialog(null, "Digite sua senha", "Informe sua senha:",
                    JOptionPane.QUESTION_MESSAGE);
            if (senhaValue == null) {
                return null;
            }
    
           
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
    
          
            Usuario usuarioValue = Usuario.obter(loginValue, senhaValue);
            if (usuarioValue != null) {
                JOptionPane.showMessageDialog(null, "Usuário logado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                return usuarioValue; 
            } else {
                JOptionPane.showMessageDialog(null, "Login ou senha inválidos. Tente novamente.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    
        return null; 
    }

    public static String[] construirMenu(Usuario usuario) {
        
    
        List<Funcionalidade> funcionalidadesList = usuario.getFuncionalidade();

        
        if (funcionalidadesList == null || funcionalidadesList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma funcionalidade disponível.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return new String[] { "Sair" };
        }

       
        String[] funcionalidades = funcionalidadesList.stream()
                .map(Funcionalidade::getSigla)
                .toArray(String[]::new);

       
        String[] menu = new String[funcionalidades.length + 1];
        System.arraycopy(funcionalidades, 0, menu, 0, funcionalidades.length);

        
        menu[menu.length - 1] = "Sair";

        return menu;
    }

    public static int selecionarFuncionalidade(String[] funcionalidadesMenu) {
        if (funcionalidadesMenu == null || funcionalidadesMenu.length == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma funcionalidade disponível.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return -1;
        }

     
        StringBuilder funcionalidadesList = new StringBuilder("Selecione uma funcionalidade:\n");
        for (int i = 0; i < funcionalidadesMenu.length; i++) {
            funcionalidadesList.append((i + 1) + ". " + funcionalidadesMenu[i] + "\n");
        }

    
        String input = JOptionPane.showInputDialog(null, funcionalidadesList.toString(), "Escolha uma funcionalidade",
                JOptionPane.QUESTION_MESSAGE);

        try {
            if (input != null && !input.isEmpty()) {
                int escolha = Integer.parseInt(input);
                if (escolha >= 1 && escolha <= funcionalidadesMenu.length) {
                    return escolha - 1; 
                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return -1;
                }
            } else {
                return -1; 
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
        //List<String> nomesDisponiveis = null;
        List<ILivroReservado> consumidores = aluno.getConsumidores();
        int opcao;

        switch (funcionalidadeSelecionada) {
            case 0:
            
            if (usuariosDisponiveis.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há consumidores disponíveis para cadastrar.");
            } else {
              
                JComboBox<String> comboBoxA = new JComboBox<>(usuariosDisponiveis.stream()
                .map(u -> ((Usuario) u).getNome())
                .toArray(String[]::new));
        
               
                opcao = JOptionPane.showConfirmDialog(
                        null,
                        comboBoxA,
                        "Selecione um Consumidor",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
        
               
                if (opcao == JOptionPane.OK_OPTION) {
                    ILivroReservado consumidorSelecionado = usuariosDisponiveis.get(comboBoxA.getSelectedIndex());
        
                    consumidores.add(consumidorSelecionado);  
                    usuariosDisponiveis.remove(consumidorSelecionado);  
        
                    JOptionPane.showMessageDialog(null, "Consumidor selecionado e cadastrado com sucesso.");
                }
            }
            break;
        
        case 1:
            
            if (consumidores.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há consumidores para remover.");
            } else {
               
                List<String> nomesConsumidores = new ArrayList<>();
                for (ILivroReservado consumidor : consumidores) {
                    Usuario user = (Usuario) consumidor;
                    nomesConsumidores.add(user.getNome()); 
                }
        
                
                JComboBox<String> comboBoxRemover = new JComboBox<>(nomesConsumidores.toArray(new String[0]));
        
               
                opcao = JOptionPane.showConfirmDialog(
                        null,
                        comboBoxRemover,
                        "Selecione um Consumidor para Remover",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
        
               
                if (opcao == JOptionPane.OK_OPTION) {
                    int consumidorSelecionado = comboBoxRemover.getSelectedIndex();
        
                    ILivroReservado consumidorARemover = consumidores.get(consumidorSelecionado);
                    consumidores.remove(consumidorSelecionado);
        
                    usuariosDisponiveis.add(consumidorARemover);
        
                    JOptionPane.showMessageDialog(null, "Consumidor removido com sucesso.");
                }
            }
            break;
            case 2:

                List<String> tituloLivros = Livro.listar();

                if (tituloLivros.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Não há livros disponíveis.");
                    break;
                }

               
                StringBuilder livrosStr = new StringBuilder();
                for (String titulo : tituloLivros) {
                    livrosStr.append(titulo).append("\n");
                }

              
                JOptionPane.showMessageDialog(null, livrosStr.toString(), "Títulos dos Livros",
                        JOptionPane.INFORMATION_MESSAGE);

                List<String> livrosReservados = new ArrayList<>();
                boolean continuarReservando = true;

                while (continuarReservando) {
                    
                    String livroEscolhido = JOptionPane.showInputDialog(
                            null,
                            "Digite o título do livro para reserva:",
                            "Cadastro de Reserva",
                            JOptionPane.PLAIN_MESSAGE);

                    if (livroEscolhido != null && !livroEscolhido.trim().isEmpty()) {
                        
                        if (tituloLivros.contains(livroEscolhido.trim())) {
                            livrosReservados.add(livroEscolhido.trim());
                            System.out.println("Livro escolhido: " + livroEscolhido);
                        } else {
                            
                            JOptionPane.showMessageDialog(null, "Título não encontrado. Tente novamente.");
                            continue; 
                        }
                    } else {
                        
                        JOptionPane.showMessageDialog(null, "Nenhum livro foi escolhido.");
                        break; 
                    }

                    
                    int resposta = JOptionPane.showConfirmDialog(
                            null,
                            "Você deseja adicionar mais um livro para reserva?",
                            "Adicionar mais livros?",
                            JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.NO_OPTION) {
                        continuarReservando = false;
                    }
                }

                if (!livrosReservados.isEmpty()) {
                   
                    String dataAtual = Util.obterDataAtual();
                    if (usuario instanceof Aluno) {
                        Aluno alunoLogado = (Aluno) usuario;
                        alunoLogado.cadastrarReserva(livrosReservados, dataAtual);

                        for (ILivroReservado consumidor : consumidores) {
                            if (consumidor instanceof ILivroReservado) {
                                ILivroReservado livroReservado = (ILivroReservado) consumidor;
                               
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

            case 3:
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
     * naListaProdutor){}
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
                   
            if (consumidor instanceof ILivroReservado) {
                ILivroReservado livroReservado = (ILivroReservado) consumidor ;
                livroReservado.informarReserva();
            }

        }

        JOptionPane.showMessageDialog(null, "Usuario: "+ usuario.getNome() + " do tipo: " + usuario.getClass() + ", deixou o sistema.", TITULO, JOptionPane.WARNING_MESSAGE);
    }
}