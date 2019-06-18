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

import java.io.Serializable;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Armazena e gerencia as mensagens enviadas pelo usuário final.
 * 
 * @author Diego Mendes Rodrigues
 * @version 2.1
 */
public class Mensagem implements Serializable {
	private static final long serialVersionUID = -6345895860148790205L;

	/** 
     * Mensagem do usuário final tratada, ou seja, sem acentos, espaços, 
     * caracteres especiais, pontuação e convetida para caracteres minúsculos 
     */
    private String mensagem;

    /** 
     * Padrão utilizando para remover os acentos das mensagens através de regex
     */
    public static final Pattern CARACTERES = Pattern.compile(
            "[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

    /**
     * Inicializar a propriedade mensagem da instância desta classe.
     */    
    public Mensagem() {
        this.mensagem = "";
    }

    /**
     * Retornar a mensagem tratada do usuário final.
     * 
     * @return String sendo a mensagem tratada do usuário final
     */
    public String getMensagem() {
        return mensagem;
    }
    
    /**
     * Retornar a quantidade de caracteres da mensagem do usuário final.
     * 
     * @return int sendo quantidade de caracteres da mensagem do usuário final
     */    
    public int mensagemLength() {
        return mensagem.length();
    }

    /**
     * Armazenar a mensagem do usuário final de uma forma tratada, na instância
     * da classe, sem acentos, espaços, caracteres especiais, pontuação 
     * e convertida para caracteres minúsculos.
     * 
     * @param mensagem String sendo a mensagem original do usuário
     */
    public void setMensagem(String mensagem) {
        String novaMensagem = mensagem.toLowerCase().trim();
        
        // Removendo acentos
        novaMensagem = stripDiacritics(novaMensagem);

        // Removendo dígitos, potuação, carateres especiais, etc
        novaMensagem = novaMensagem.replaceAll("[^a-zA-Z0-9\\s]+","");
        
        // Removendo espaços em branco 
        novaMensagem = novaMensagem.replaceAll("\\s+", " ");
        this.mensagem = novaMensagem;
    }
    
    /**
     * Método privado - Remover os acentos das mensagens.
     * 
     * @param str String sendo a mensagem original do usuário
     * @return String sendo a mensagem sem acentos
     */
    private static String stripDiacritics(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = CARACTERES.matcher(str).replaceAll("");
        return str;
    }
}