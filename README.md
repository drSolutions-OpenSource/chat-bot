# Chat Bot

Chat possuindo um Bot em Java, com uma interface desenvolvida no Swing, onde o usuário final, interage com o Bot que responde às mensagens e perguntas feitas.  

As mensagens entendidas pelo Bot foram programadas com expressões regulares, sendo que, para cada mensagem, ou pergunta do usuário, existe uma resposta que será exibida.

A programação das mensagens e das respostas, foi realizada através de 2 arquivos de texto, sendo que o Bot de exemplo, consegue responder mensagens como:

+ Tudo bem?
+ Como está?
+ Como anda?
+ Qual a sua idade?
+ Sua idade?
+ Quantos anos você tem?
+ Quantos anos?
+ Qual seu nome?
+ Onde mora?
+ Onde vive?
+ Onde você mora?
+ Onde estuda?
+ Qual faculdade?
+ Qual faculdade você faz?
+ Que curso?
+ Qual curso?
+ O que gosta de fazer?
+ O que faz no seu tempo livre?
+ Que livro está lendo?
+ Que tipo de livro você gosta?
+ Que tipo de filme você gosta?
+ Que tipo de música você gosta?
+ Qual seu cantor favorito?
+ Qual sua música favorita?
+ Gosta de samba?
+ Onde você gosta de ir?
+ Que livraria?
+ Que cinema você vai?
+ dentre outras... dê uma olhada nos arquivos mensagens.txt e respostas.txt.

Durante a execução do Chat, quando o usuário final envia uma mensagem, o sistema converte ela para caracteres minúsculos, remove acentos e caracteres especiais, depois remove os espaços duplicados, para finalmente, realizar a busca da mensagem (pergunta) através de expressões regulares.

Quando o Chat não entende a pergunta, ou seja, a mensagem enviada, responde para o usuário uma mensagem, como por exemplo '*Não entendi o que você escreveu*', ou '*Sua mensagem não ficou clara, por favor, tente de outra maneira*', dentre outras opções.

# Começando

As instruções abaixo irão te auxiliar para que você tenha uma cópia do **Chat Bot** na sua máquina.

## Pré-requisitos

1. Realize o download e instale [Open JDK 8](http://openjdk.java.net/install/), ou posterior.

## Instalando

1. Realize o download do arquivo ZIP com o código-fonte do projeto (todas as plataformas)
2. Descompacte o arquivo
3. Execute java -jar dist/ChatBot.jar

## Executando o código fonte

1. Realize o download do arquivo ZIP com o código-fonte do projeto (todas as plataformas)
2. Importe o projeto para sua IDE (Netbeans, Eclipse, IntelliJ)
3. Execute /br/com/drsolutions/chatbot/ui/JanelaPrincipal.java

# Autor

**Diego Mendes Rodrigues** (São Paulo, Brasil)

Caso você precise de suporte para customização ou para uma licença comercial, fique a vontade para entrar em contato comigo.

# Licença

Copyright 2018 [drSolutions - Agência Digital]
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    <http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.