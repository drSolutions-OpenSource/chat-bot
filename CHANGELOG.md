# Changelog

Todas as mudanças relevantes deste projeto serão documentadas neste arquivo.

O formato deste arquivo é baseado em [Keep a Changelog] (http://keepachangelog.com/en/1.0.0/). 


## [2.1.0] - 2018-07-29
### Adicionado
- Imagens em execução no link: https://drive.google.com/open?id=1Febi5NyNlasfokl-u_qKflB_cxkygkAS
- Arquivos README.md, CHANGELOG.md, LICENSE.md
- Adicionado um ícone na janela principal
- Tratamento do nome do usuário, removendo acentos e caracteres especiais
- Detecção do sexo do usuário
- Mensagens voltadas para o sexo do usuário
- Diversas mensagens que são enviadas quando o bot não entende a mensagem do usuário

### Modificado
- Nome alterado de Chat Simples para Chat Bot
- Nome do bot pode ser Julia ou Regina
- Título da janela principal alterado para 'Chat Bot v2.1'
- Criado um novo ícone na tela principal
- Criada uma nova tela 'Sobre' com o novo ícone e novo versão
- Inclusão de novas mensagens e respostas para que o bot possa responder

### Corrigido
- Salvar a Conversa: Ajustado para que as quebras de linha, ao salvar os arquivos, funcionem no Linux e no Windows
- Tamanho da fonte: A fonte do texto aparece do mesmo tamanho no Linux e no Windows


## [2.0.0] - 2018-07-26
### Adicionado
- Imagens em execução no link: https://drive.google.com/open?id=1Nyxe98J1gQhYIIABGw4d4yhmSUr7ytw4
- Interface de Usuário Gráfica (Graphical User Interface - GUI) através do Swing
- Diálogo (janela) inicial perguntando o nome do usuário final
- Botão 'Salvar essa conversa' na interface que armazena a conversa em um arquivo de texto
- Botão 'Sobre' que mostra uma nova janela com o nome do sistema, empresa desenvolvedora, desenvolvedor e versão
- Teste evitando que o usuário envie uma mensagem em branco para o chat

### Modificado
- Inclusão de novas mensagens e respostas para que o bot possa responder

### Removido
- Mensagens de entrada no chat (cabeçalho) e de saída (rodapé)


## [1.1.0] - 2018-07-25
### Adicionado
- Mensagens do usuário final foram colocadas no arquivo 'mensagens.txt'
- Respostas do bot para as mensagens foram colocadas no arquivo 'respostas.txt'
- Mensagens de entrada no chat (cabeçalho) e de saída (rodapé)

### Modificado
- Inclusão de novas mensagens e respostas para que o bot possa responder


## [1.0.0] - 2018-07-24
### Adicionado
- Criado o bot que interage com o usuário através de mensagens de texto no terminal
- Classe Mensagem que armazena na memória gerencia mas mensagens enviadas pelo usuário final de uma forma tratada, na instância
da classe, sem acentos, espaços, caracteres especiais, pontuação e convertida para caracteres minúsculos.
- Mecanismo que interpreta as mensagens enviadas pelo usuário final, através de expressões regulares (regex), sendo que as mensagens e as respostas estão dentro da classe ProcessarMensagem no código em Java
- Nome do usuário final Diego e o nome do Bot é Julia