/*
 ************************************************************************
 Copyright [2018] [Drsolutions Tecnologia em Informática Ltda.-ME]
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ************************************************************************
 */

package br.com.drsolutions.chatbot.mensagens;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Processa as mensagens enviadas pelo usuário final, encontrando a resposta
 * apropriada, quando possível.
 * 
 * @author Diego Mendes Rodrigues
 * @version 2.1
 */
public final class ProcessarMensagem implements Serializable {
	private static final long serialVersionUID = -6107789012931376619L;
	
	/** Perguntas e Respostas */
    private final ArrayList<String> perguntas = new ArrayList<>();
    private final ArrayList<String> respostas = new ArrayList<>();

    private boolean perguntaEncontrada;

    private String resposta;

    /**
     * Inicializar as propriedades da instância desta classe, além de chamar o 
     * método privado incluirPerguntasRespostas(...) que configura as perguntas 
     * e as respontas que uma instância dessa classe consegue responder.
     * 
     * @param arquivoMensagens sendo um arquivo txt, que ficará dentro do jar, 
     *                         com as regex referente as mensagens do usuário
     * @param arquivoRespostas sendo um arquivo txt, que ficará dentro do jar,
     *                         com as respostas para as mensagens do usuário
     */
    public ProcessarMensagem(String arquivoMensagens, 
                             String arquivoRespostas) {
        incluirPerguntasRespostas(arquivoMensagens, arquivoRespostas);
        perguntaEncontrada = false;
    }

    /**
     * Pesquisar mensagens, que podem ser perguntas do usuário final,
     * utilizando as opções que foram configuradas no método construtor.
     * 
     * @param mensagem String sendo a mensagem do usuário final
     * @return boolean sendo true caso a pesquisa tenha encontrado um 
     *         valor válido
     */
    public boolean pesquisarPergunta(String mensagem) {
        perguntaEncontrada = false;
        String pergunta;
        int i;

        for (i = 0; i < perguntas.size(); i++) {
            pergunta = perguntas.get(i);
            resposta = respostas.get(i);

            if (mensagem.matches(pergunta)) {
                perguntaEncontrada = true;
                break;
            }
        }

        return perguntaEncontrada;
    }

    /**
     * Método privado - Configurar as perguntas e as respontas que a classe 
     * consegue processar.
     * 
     * @param arquivoMensagens sendo um arquivo txt, que ficará dentro do jar, 
     *                         com as regex referente as mensagens do usuário
     * @param arquivoRespostas sendo um arquivo txt, que ficará dentro do jar,
     *                         com as respostas para as mensagens do usuário
     */
    private void incluirPerguntasRespostas(String arquivoMensagens, 
                                           String arquivoRespostas) {
        /** Lendo as mensagens (perguntas) */
        try {
            URL urlToDictionary = this.getClass().getResource(arquivoMensagens);
            InputStream is = urlToDictionary.openStream();
            InputStreamReader ir = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(ir);
            
            String s;
            try {
                s = br.readLine();
                
                while (s != null) {
                    perguntas.add(s);
                    s = br.readLine();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Falha ao ler linhas do "
                        + "arquivo:\n" + arquivoMensagens,
                        "Erro!", JOptionPane.ERROR_MESSAGE); 
                System.exit(0);
            }
            
            br.close();
        } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"Falha ao abrir o "
                        + "arquivo:\n" + arquivoMensagens,
                        "Erro!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Falha:\n"
                    + ex,
                    "Erro!", JOptionPane.ERROR_MESSAGE);             
            System.exit(0);
        }
        
        /** Lendo as respostas */
        try {
            URL urlToDictionary = this.getClass().getResource(arquivoRespostas);
            InputStream is = urlToDictionary.openStream();
            InputStreamReader ir = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(ir);
            
            String s;
            try {
                s = br.readLine();
                
                while (s != null) {
                    respostas.add(s);
                    s = br.readLine();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Falha ao ler linhas do "
                        + "arquivo:\n" + arquivoRespostas,
                        "Erro!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            
            br.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Falha ao abrir o "
                    + "arquivo:\n" + arquivoRespostas,
                    "Erro!", JOptionPane.ERROR_MESSAGE);            
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Falha:\n"
                    + ex,
                    "Erro!", JOptionPane.ERROR_MESSAGE); 
            System.exit(0);
        }        
    }

    /**
     * Retornar a propriedade resposta de acordo com a mensagem processada.
     * 
     * @return String sendo a resposta para a mensagem do usuário final
     */
    public String getResposta() {
        return resposta;
    }
}