/*
 * Copyright 2018 [Drsolutions Tecnologia em Informática Ltda.-ME].
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.drsolutions.chatbot.ui;

import br.com.drsolutions.chatbot.mensagens.Mensagem;
import br.com.drsolutions.chatbot.mensagens.MensagemDesculpa;
import br.com.drsolutions.chatbot.mensagens.ProcessarMensagem;
import br.com.drsolutions.chatbot.nomes.NomeDoUsuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Chat Bot (antigo Chat Simples) com GUI
 * -
 * O usuário final interage com o 'Bot' (Julia ou Regina), sendo que este 
 * responde as mensagens e perguntas feitas pelo usuário. 
 * <p>
 * As mensagens foram programadas com expressões regulares, sendo que, para 
 * cada uma delas, existe uma resposta que será exibida.
 * </p><p>
 * Quando o Bot não entende a mensagem do usuário, responde no chat a mensagem
 * 'Não entendi o que você escreveu', dentre outras.
 * </p><p>
 * As mensagens previstas parao usuário final estão no arquivo 'mensagens.txt'
 * enquanto as respostas do bot estão no 'respostas.txt' do pacote
 * 'br.com.drsolutions.chatsimples.textos'.
 * </p>
 * @author Diego Mendes Rodrigues
 * @version 2.1
 */
public class JanelaPrincipal extends javax.swing.JFrame {

	private static final long serialVersionUID = -6316278843821873822L;

	/**
     * Enviar a mensagem que está no campo de texto para a área de mensagens.
     */
    public void enviarMensagem() {
        String msgUsuario;
        String msgBot;
        String mensagem;
        
        msgUsuario = txtMensagem.getText();
        
        /* 
         Verificando se o usuário digitou algum caractere, ou se apenas
         pressionou o botão 'Enviar'
        */
        if (msgUsuario.length() == 0 ) {
            java.awt.Toolkit.getDefaultToolkit().beep();
        } else {
            /** Processando a mensagem enviada pelo usuário */
            mensagemDigitada.setMensagem(msgUsuario);
            mensagem = mensagemDigitada.getMensagem();
            
            if (processar.pesquisarPergunta(mensagem))
                msgBot = processar.getResposta();
            else
                msgBot = mensagemDesculpa.mensagem();

            if (sexoUsuario == 1) {
                msgBot = msgBot.replace("{@}", "o");
                msgBot = msgBot.replace("{SEU}", "seu");    
            } else {
                msgBot = msgBot.replace("{@}", "a");
                msgBot = msgBot.replace("{SEU}", "sua");    

            }
            msgBot = msgBot.replace("{NOMEBOT}", nomeBot);
            msgBot = msgBot.replace("{NOMEUSER}", nomeUsuario);
            
            mensagemUsuario(msgUsuario);
            mensagemBot(msgBot);

            /* 
             Habilitando o botão 'Salvar', pois a área de conversa 
             possui conteúdo
            */
            if (!btnSalvar.isEnabled())
                btnSalvar.setEnabled(true);

            txtMensagem.setText("");
        }
        txtMensagem.requestFocus();
    }
    
    /**
     * Enviar a mensagem do usuário final para o campo da conversa.
     * 
     * @param mensagem String sendo a mensagem do usuário final
     */
    public void mensagemUsuario(String mensagem) {
        txtConversa.append(nomeUsuario + ": " + mensagem + "\n");
    }
    
    /**
     * Enviar a mensagem do Bot para o campo da conversa.
     * 
     * @param mensagem String sendo a mensagem do usuário Bot
     */    
    public final void mensagemBot(String mensagem) {
        txtConversa.append(nomeBot + ": " + mensagem + "\n\n");
    }
    
    /** 
     * Criar o formulário da Chat Bot (antigo Chat Simples).
     */
    public JanelaPrincipal() {
        initComponents();
        
        /* Inserindo o ícone no JFrame */
        String iconeCaminho = "/br/com/drsolutions/chatbot/imagens/"
                + "Icone-Jframe-16x16.png";
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(iconeCaminho)));
        
        /* Centralizar a janela */
        setLocationRelativeTo(null);
        
        /* 
         Ajustar a fonte, além da quebra de linha automática no campo da
         conversa
        */
        txtConversa.setFont(new Font("Serif", Font.PLAIN, 13));
        txtConversa.setLineWrap(true);
        txtConversa.setWrapStyleWord(true);
        txtConversa.setForeground(Color.DARK_GRAY);
        
        /* Desativando o botão 'Salvar' pois o campo da conversa está vazio */
        btnSalvar.setEnabled(false);
        
        /* Recebendo o nome do usuário */
        NomeDoUsuario nome = new NomeDoUsuario();
        nomeUsuario = nome.receberNomeUsuario();
        
        /* Sexo do usuário */
        sexoUsuario = nome.getSexo();
        
        /* 
         Nome do bot, que costuma ser Julia, mas caso o usuário se chame Julia,
         o bot irá se chamar Regina
        */
        if (nomeUsuario.equalsIgnoreCase("julia") || 
                nomeUsuario.equalsIgnoreCase("júlia"))
            nomeBot = "Regina";
        else
            nomeBot = "Julia";
                    
        /* Mensagem de boas vindas */
        if (sexoUsuario == 1)
            mensagemBot(nomeUsuario + ", bem-vindo!");
        else
            mensagemBot(nomeUsuario + ", bem-vinda!");
        
        /* 
         Mensagens de 'desculpa' utilizadas pelo bot quando ele não entender
         a mensagem do usuário final
        */
        mensagemDesculpa = new MensagemDesculpa();
        
        /* Colocar o foco na caixa de texto para a digitação da mensagem */
        txtMensagem.requestFocus();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtConversa = new javax.swing.JTextArea();
        txtMensagem = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        painelBotoes = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnSobre = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat Bot v2.1");
        setResizable(false);

        txtConversa.setEditable(false);
        txtConversa.setColumns(20);
        txtConversa.setRows(5);
        txtConversa.setFocusable(false);
        txtConversa.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(txtConversa);

        txtMensagem.setToolTipText("Digite sua mensagem");
        txtMensagem.addKeyListener(new java.awt.event.KeyAdapter() {
        	@Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMensagemKeyPressed(evt);
            }
        });

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        painelBotoes.setBackground(java.awt.Color.white);

        btnSalvar.setBackground(java.awt.Color.white);
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/drsolutions/chatbot/imagens/Icone Salvar.png"))); // NOI18N
        btnSalvar.setToolTipText("Salvar essa conversa");
        btnSalvar.setBorder(null);
        btnSalvar.setBorderPainted(false);
        btnSalvar.setDefaultCapable(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnSobre.setBackground(java.awt.Color.white);
        btnSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/drsolutions/chatbot/imagens/Icone Sobre.png"))); // NOI18N
        btnSobre.setToolTipText("Sobre");
        btnSobre.setBorder(null);
        btnSobre.setBorderPainted(false);
        btnSobre.setMaximumSize(new java.awt.Dimension(38, 38));
        btnSobre.setMinimumSize(new java.awt.Dimension(38, 38));
        btnSobre.setPreferredSize(new java.awt.Dimension(38, 38));
        btnSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSobreActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/drsolutions/chatbot/imagens/Icone Chat Bot.png"))); // NOI18N

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSobre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelBotoesLayout.createSequentialGroup()
                        .addComponent(btnSobre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ao pressinoar o botão 'Enviar', a mensagem digitada será enviada para
     * a área da conversa.
     * 
     * @param evt 
     */
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        enviarMensagem();
    }//GEN-LAST:event_btnEnviarActionPerformed

    /**
     * Ao pressionar o botão 'Sobre' uma janela será aberta informando o nome
     * do Chat Bot (antigo Chat Simples), empresa que desenvolveu, desenvolvedor 
     * e a versão.
     * 
     * @param evt 
     */
    private void btnSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobreActionPerformed
        java.awt.Toolkit.getDefaultToolkit().beep();
        
        JanelaSobreChat sobre = new JanelaSobreChat(null, true);
        sobre.setVisible(true);

        txtMensagem.requestFocus();
    }//GEN-LAST:event_btnSobreActionPerformed

    /**
     * Quando usuário pressiona <ENTER> no campo de digitação a mensagem 
     * é enviada.
     * 
     * @param evt 
     */ 
    private void txtMensagemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensagemKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){  
            enviarMensagem();
        }
    }//GEN-LAST:event_txtMensagemKeyPressed
    
    /**
     * Ao ser pressionado o botão 'Salvar', o usuário deve selcionar qual o
     * arquivo que irá receber o conteúdo da conversa. 
     * Depois de salvar o texto, uma nova caixa de diálogo abre questinando 
     * se o usuário deseja limpar a área das mensagens.
     * 
     * @param evt 
     */
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        /*  Recuperando o texto da conversa */
        String conversa;
        conversa = txtConversa.getText();
        
        /* Diálogo para selecionar onde salvar o arquivo */
        JFileChooser chooser;
        chooser = new JFileChooser();
        
        String caminho = "";
        @SuppressWarnings("unused")
		File file;
        
        int retorno = chooser.showSaveDialog(null); 
        
        if (retorno==JFileChooser.APPROVE_OPTION){
            /*
             getSelectedFile() - Pega o arquivo 
             getAbsolutePath() - Retorna uma string contendo o endereço            
            */
            caminho = chooser.getSelectedFile().getAbsolutePath(); 
        }
        
        if (!caminho.equals("")) {
            /* A extensão do arquivo será 'txt' */
            file = new File(caminho + ".txt");
            
            String finalNome;
            finalNome = caminho.toLowerCase().substring(caminho.length()-4);
            
            if (!finalNome.equals(".txt"))
                caminho += ".txt";
            
            /* Cria um novo arquivo e salva o conteúdo da mensagem */
            OutputStream os;
            try {
                os = new FileOutputStream(caminho);
                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
                
                BufferedWriter bw = new BufferedWriter(osw);
                
                /* Quebra de linha no formato Windows */
                String conversaComQuebraDeLinha;
                conversaComQuebraDeLinha = conversa.replaceAll("\n", "\r\n");
                
                bw.write(conversaComQuebraDeLinha);

                bw.close();

                /* Caixa questinando se devemos limpar a conversa atual */
                int resposta;
                resposta = JOptionPane.showConfirmDialog(null,"Arquivo salvo!"
                        + "\n\nVocê deseja limpar a conversa atual?", 
                        "Limpar", 
                        JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    txtConversa.setText("");
                    
                    /* 
                     Desabilitando o botão 'Salvar', pois a área de conversa 
                     não possui nenhum conteúdo
                    */
                    if (btnSalvar.isEnabled())
                        btnSalvar.setEnabled(false);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Falha ao criar "
                        + "o arquivo.\n\nTente novamente!",
                        "Erro!", JOptionPane.ERROR_MESSAGE); 
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Falha ao escrever "
                        + "no arquivo.\n\nTente novamente!",
                        "Erro!", JOptionPane.ERROR_MESSAGE); 
            } 
            txtMensagem.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null,"Você precisa selecionar uma "
                    + "pasta além de\nescolher o nome do arquivo.\n\n"
                    + "Nenhum arquivo foi salvo neste momento!",
                    "Erro!", JOptionPane.ERROR_MESSAGE);      
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    /**
     * Inicializa o formulário principal do Chat Bot (antigo Chat Simples).
     * 
     * @param args argumentos da linha de comando
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }

    /* Variáveis do Chat Bot (antigo Chat Simples) */
    private String nomeBot;
    private String nomeUsuario;
    private int sexoUsuario;
    private MensagemDesculpa mensagemDesculpa;
    
    /** Arquivos com as mensagens do usuário e com as respostas */
    String arquivoCaminho = "/br/com/drsolutions/chatbot/textos/";
    String arquivoMensagens = arquivoCaminho + "mensagens.txt";
    String arquivoRespostas = arquivoCaminho + "respostas.txt";

    /** 
     * Utilizar a classe Mensagem para armazenar e gerenciar as mensagens
     * do usuário final
     */
    Mensagem mensagemDigitada = new Mensagem();

    /** 
     * Utilizar a classe ProcessarMensagem para verificar se as mensagens
     * do usuário final possuem respostas programadas
     */
    ProcessarMensagem processar = new ProcessarMensagem(arquivoMensagens,
                                                        arquivoRespostas);
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSobre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTextArea txtConversa;
    private javax.swing.JTextField txtMensagem;
    // End of variables declaration//GEN-END:variables

}
