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

package br.com.drsolutions.chatbot.mensagens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Retorna mensagens de 'desculpas' para que o bot utilize quando não 
 * identificar a mensagem do usuário final.
 * 
 * @author Diego Mendes Rodrigues
 * @version 2.1
 */
public class MensagemDesculpa implements Serializable {
	private static final long serialVersionUID = 1470265999493160528L;
	private final ArrayList<String> listaDeMensagens = new ArrayList<>();
    private final int quantidadeMensagens;
    private int mensagemAtual;
    
    /**
     * Inicializar as variáveis da instância desta classe, incluindo a lista
     * das mensagens de desculpas.
     */
    public MensagemDesculpa() {
        /* Preenchendo a lista de mensagens de 'desculpas' */
        String[] mensagens = new String[] {
            "Não entendi o que você escreveu",
            "Não entendi suas palavras", 
            "Sua mensagem não ficou clara, por favor, tente de outra maneira",
            "Peço desculpa, mas não entendi o que você disse",
            "Não compreendo bem sua mensagem, por favor, tente de outra forma"};
        
        List<String> lista = Arrays.asList(mensagens);
        listaDeMensagens.addAll(lista);
        
        /* Setando a propriedade quantidadeMensagens e mensagemAtual */
        quantidadeMensagens = listaDeMensagens.size();
        
        Random random = new Random();
        mensagemAtual = random.nextInt(quantidadeMensagens);
    }
    
    /**
     * Retornar uma mensagem de desculpas.
     * 
     * @return String sendo a mensagem de desculpas
     */
    public String mensagem() {
        /* Recuperando a mensagem de 'desculpas' da lista de mensagens */
        String retorno = listaDeMensagens.get(mensagemAtual);
        
        /* Incrementando o valor da variável mensagemAtual */
        if (mensagemAtual == (quantidadeMensagens-1))
            this.mensagemAtual = 0;
        else
            this.mensagemAtual = this.mensagemAtual +1;
        
        return retorno;
    }
}
