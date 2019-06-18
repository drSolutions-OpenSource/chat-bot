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

package br.com.drsolutions.chatbot.nomes;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Gerenciar o nome e o sexo do usuário, recebendo estas informações através
 * de janelas. 
 * O nome do usuário é tratado, sendo removidos os caracteres especiais, 
 * espaços em branco, além de deixar o primeiro caractere em letra maiúscula.
 * O sexo do usuário é identificado, através de uma lista de nomes configurados
 * na instância da classe. Quando o sexo não é identificado automaticamente,
 * uma janela é aberta, solicitando essa informação do usuário final.
 * 
 * @author Diego Mendes Rodrigues
 * @version 2.1
 */
public class NomeDoUsuario {
    private final ArrayList<String> nomesMasculinos = new ArrayList<>();
    private final ArrayList<String> nomesFemininos = new ArrayList<>();
    
    private int sexo;
    private String nomeUsuario;

    /** 
     * Padrão utilizando para remover os acentos dos nomes através de regex
     */
    public static final Pattern CARACTERES = Pattern.compile(
            "[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");
    
    /**
     * Inicializar as variáveis da instância desta classe.
     */
    public NomeDoUsuario() {
        /* Nomes masculinos */
        String[] nomesHomens = new String[] {"aldo", "alicino", "andre", 
            "anthony", "anthony gabriel", "antonio", "arthur", "arthur gabriel", 
            "arthur henrique", "arthur miguel", "augusto", "benjamin", "bento", 
            "benicio", "bernardo", "breno", "bruno", "bryan", "caio", "caleb", 
            "carlos eduardo", "caua", "daniel", "danilo", "davi", "davi lucas", 
            "davi lucca", "davi luiz", "davi miguel", "david", "diego", "diogo", 
            "eduardo", "emanuel", "enrico", "enzo", "enzo gabriel", 
            "enzo miguel", "erick", "felipe", "fernando", "francisco", 
            "gabriel", "gael", "guilherme", "gustavo", "heitor", "henrique", 
            "henry", "ian", "isaac", "joaquim", "jose", "joao", "joao gabriel", 
            "joao guilherme", "joao lucas", "joao miguel", "joao pedro", 
            "joao victor", "juan", "kaique", "kaue", "leonardo", "levi", 
            "lorenzo", "luan", "lucas", "lucas gabriel", "lucca", "luis", 
            "luiz", "luiz felipe", "luiz gustavo", "luiz henrique", 
            "luiz miguel", "luiz otávio", "martin", "mateus", "matheus", 
            "miguel", "miguel henrique", "murilo", "nathan", "neymar", 
            "nicolas", "noah", "oliver", "otavio", "pedro", "pedro henrique", 
            "pedro lucas", "pedro miguel", "pietro", "rafael", "raul", 
            "rodrigo", "ruan", "ryan", "samuel", "sergio", "thiago", "thomas", 
            "theo", "tomas", "vicente", "victor", "victor hugo", "vinicius", 
            "yago", "yuri", "ricardo", "wellington", "agildo", "agnaldo",
            "alexandre", "andreazi", "andrey", "bruno", "carlos", "cassio", 
            "cesar", "chrystian", "cristian", "claudio", "daniel", "hallin", 
            "luciano", "pedro", "edgard", "edson", "egidio", "erik", "fabiano", 
            "felipe", "flavio", "frederico", "gabriel", "elzio", "giovanni", 
            "gregory", "guilherme", "helder", "henrique", "heraldo", "igor", 
            "ismar", "jaime", "jairo", "jerome", "jonathan", "jose", "juan", 
            "julio cesar", "judio dario", "junior", "leonardo", "leonel", 
            "lucas", "lucio", "luis carlos", "luis marcelo", "marcelo", 
            "mathias", "mauricio", "maycon", "albi", "anderson", "juliano", 
            "renato", "meyer", "michel", "moacir", "noel", "oliveira", "omar", 
            "osvaldo", "paulo", "petry", "ricardo", "robison", "rodrigo", 
            "ruben", "tiego", "vicente", "vitor", "michael", "michael jackson", 
            "justin", "justin bieber", "chris brown", "drake", "elton", 
            "elton john", "elvis", "elvis presley", "caetano", "caetano veloso", 
            "milton", "milton nascimento", "francisco", "chico", 
            "chico buarque", "pedro", "lanes", "Wendril", "ernesto", "marcos", 
            "clovis", "carlos", "guilherme", "gabriel", "marlon", "peterson", 
            "roberto", "bruno", "wester", "douglas", "emanuele", "rogerio", 
            "vanderlei", "vinicius", "douglas", "mario", "raimundo", "caio", 
            "amauri", "elon", "claudio", "hilton", "rildo", "mariano", 
            "humberto", "silas", "emerson", "cicero", "alberto", "octavio", 
            "otavio", "emilio", "hernami", "alvaro", "marcos", "guilherme", 
            "claudemir", "lucas", "kevin", "roberto", "valdir", "waldir", 
            "gilmar", "fabricio", "sidnei", "alfredo", "fabricio", "mauro", 
            "ubiratan", "neto", "vinicius", "christian", "jaime", "paulo", 
            "elton", "joarcy", "henrique", "cristiano", "ivan", "victor", 
            "vitor", "omayr", "tony", "ryan", "robson", "ernesto", "jaco", 
            "hugo", "clerson", "emanuel", "raphael", "giovanni", "izaias", 
            "cesar", "cezar", "elias", "wissan", "lourenco", "cristian", 
            "jones", "audrey", "benoit", "jair", "bolsonaro", "hermogenes", 
            "adriano", "rodrigo", "reinaldo", "gilmar", "fernando", "alexandre", 
            "fabiano", "denis", "silvio", "emilio", "ronaldo", "atilla", 
            "atila", "joao victor", "joao vitor", "ragnar", "rollo", "floki", 
            "alisson", "maciel", "luan santana", "santana", "juliano", "cassio", 
            "cassiano", "milosh", "maciel", "robson", "eric", "helder", "ismar", 
            "ismael", "silas", "markos", "tasso", "felipe", "renato", 
            "leonardo", "flavio", "hugo", "wando", "jones", "rafael", 
            "juanitho", "tales", "wanderley", "davinir", "nathan", "fellipe", 
            "yusif", "felipe", "evaldo", "erico", "mariano", "jean", "tarcisio", 
            "gilson", "anderson", "marcus", "shafick", "raavisson", "ravisson", 
            "wilson", "miguel", "luis miguel", "luiz miguel", "marcello", 
            "saulo", "rockefeller", "moises", "hudson", "augusto", "luciano"};
        
        List<String> listaHomens = Arrays.asList(nomesHomens);
        Collections.sort(listaHomens);
        nomesMasculinos.addAll(Arrays.asList(nomesHomens));
        
        /* Nomes femininos */
        String[] nomesMulheres = new String[] {"adriana", "agatha", "alice", 
            "anala", "allana", "alicia", "amanda", "ana", "ana beatriz", 
            "ana cecilia", "ana clara", "ana julia", "ana laura", "ana liz", 
            "ana luiza", "ana livia", "ana sophia", "ana vitoria", "analu", 
            "antonella", "antonia", "aurora", "ayla", "beatriz", "bianca", 
            "bruna", "carolina", "catarina", "cecilia", "clara", "clarice", 
            "eduarda", "elisa", "eloa", "emanuelly", "emilly", "esther", 
            "fernanda", "gabriela", "gabrielly", "giovanna", "helena", 
            "heloise", "heloisa", "herminia", "ingrid", "isabel", "isabella", 
            "isabelly", "isadora", "isis", "joana", "julia", "lara", 
            "larissa", "laura", "lavinia", "lais", "leticia", "liz", "lorena", 
            "louise", "luana", "luciana", "luiza", "luna", "livia", "maite", 
            "malu", "manuela", "maria", "maria alice", "maria cecilia", 
            "maria clara", "maria eduarda", "maria fernanda", "maria flor", 
            "maria helena", "maria heloisa", "maria isis", "maria julia", 
            "maria laura", "maria luiza", "maria sophia", "maria valentina", 
            "maria vitoria", "mariah", "mariana", "marina", "maya", "melissa", 
            "milena", "mirella", "natalia", "nicole", "olivia", "pietra", 
            "rafaela", "rebeca", "regina", "sarah", "sophia", "sophie", 
            "stella", "valentina", "vania", "vitoria", "yasmin", "nadia", 
            "iara", "estela", "rosy", "rosy may", "elaine", "alessandra", 
            "aline", "amanda", "andrea", "beatrice", "celia", "claudia", 
            "dirce", "elaine", "elizete", "gabrielle", "irene", "jacira", 
            "kelly", "leidy", "linda", "marcia", "mariana", "marina", "martha", 
            "carol", "denise", "katia", "malu", "sinara", "mile", "milena", 
            "pamela", "rita", "maria", "silvana", "silvia", "tarcila", "melina", 
            "thaynara", "vania", "vera", "thais", "tatiana", "tatiane", 
            "alana", "janis", "madonna","barbra", "mariah", "celine", 
            "britney spears", "britney", "cher", "ayumi", "rihanna", "beyonce", 
            "giselle", "janet", "britney", "katy", "taylor", "lady", 
            "lady gaga", "adele", "demi lovatto", "whitney", "sharika", "miley", 
            "alicia", "alicia keys", "nicki", "nick minaj", "jennifer", 
            "jennifer lopez", "jenifer", "selena", "selena gomez", "christina", 
            "christina aguilera", "avril", "amy", "amy winehouse", "anitta", 
            "ariana", "ariana grande", "jessie", "janet", "ivete", 
            "ivete sangalo", "fergie", "gwen", "gwen stefani", "lana", "shania", 
            "toni braxton", "dua lipa", "amy lee", "jordin sparks", "nelly", 
            "nelly furtado", "edith", "camila", "jennifer", "simone", "simara", 
            "sacha", "sandy", "katniss", "alice", "ripley", "beatrix", 
            "beatrix kidoo", "leia", "angelica", "lenice", "carla", "luciene", 
            "fabielly", "tania", "sonia", "suzeli", "silvia", "susan", "evelyn", 
            "cristiane", "luciana", "angela", "thaise", "nelci", "ligia", 
            "sirlene", "mirian", "claudia", "fatima", "marta", "maria", 
            "giovana", "mirna", "luana", "marisa", "nilza", "mariana", 
            "priscila", "aurea", "celina", "joilma", "joelma", "manuella", 
            "manuela", "andressa", "emilia", "luzia", "erika", "solange", 
            "vera", "rosa", "lais", "eloa", "patricia", "nathalia", "michelle", 
            "daniele", "ines", "virginia", "gisela", "elis", "tamiris", 
            "tamires", "jaquecele", "jaqueline", "fatima", "patricia", 
            "giandri", "giovanni", "steve", "silvio", "alvaro", "renato", 
            "sandra", "raquel", "francielly", "vaina", "elizabete", "elizete", 
            "janaina", "raissa", "ruth", "dilma", "noemy", "ruth", "marilia", 
            "elizabeth", "emilia", "mariana", "barbara", "caroline", 
            "caroliana", "renata", "paula", "ana paula", "monica", "magali", 
            "lilian", "lurdina", "aldeilza", "maria julia", "maju", "sandy", 
            "jussara", "iascara", "alcineide", "criatiane", "cristiana", 
            "maria aparecida", "maria almeida", "rita", "daniela", "daniele", 
            "luisa", "carla", "ivana", "valeria", "marlei", "helena", "nayara", 
            "janaina", "nilva", "marli", "melissa", "moira", "daylly", "camila", 
            "cristina", "sandra", "gilsimara", "silvana", "daiane", "rejane", 
            "alba", "camille", "kennerly", "teresa", "theresa", "thaise", 
            "taise", "maristela", "kedma", "emilly", "mara lucia", 
            "maria silvia", "alice", "larissa", "marli", "ellanine", "elidia", 
            "rosa", "ana carolina", "nayara", "beatriz", "genilda", "fernanda", 
            "luciana", "jessica", "fabiana", "silvia", "emilia", "thaissa", 
            "aline", "daylly", "gabrielle", "juliana", "isadora", "jaqueline", 
            "mariseli", "fatima", "priscila", "elaine", "renata", "francielly", 
            "ericca", "nicole", "nicoly", "michelle", "brenda", "denise", 
            "alessandra", "vauzedina", "viviane", "valesca", "rosely", 
            "angelisa", "tereza", "terezinha", "kellen", "maria angela", 
            "angela", "walkyria", "valkyria", "walkiria", "valkiria", "mysha", 
            "patty", "sueli", "andreza", "samara", "estefania", "thalita", 
            "lourdes", "gisele", "anna", "giselli", "mariana", "gleice", 
            "angelica", "stephani", "gilsimara", "marcelle", "roberta", 
            "clarisse", "silvana", "ekaterina", "karla", "cecilia", "yasmin", 
            "leila", "luciana", "karina","silvia", "carina", "karina", "renata"};

        List<String> listaMulheres = Arrays.asList(nomesMulheres);
        Collections.sort(listaMulheres);
        nomesFemininos.addAll(Arrays.asList(nomesMulheres));
        
        /* Sexo do usuário */
        sexo = 0;
    }
    
    /**
     * Receber o nome do usuário final através de uma janela.
     * 
     * @return String sendo o nome do usuário
     */
    public String receberNomeUsuario() {
        String nome;
        
        nome = JOptionPane.showInputDialog(null, 
                "Qual o seu nome?", "Nome", JOptionPane.PLAIN_MESSAGE);
        
        if (nome.length() == 0)
            nome = "Diego";

        String novoNome = nome.trim();
        
        // Removendo dígitos, potuação, caracteres especiais
        novoNome = novoNome.replaceAll("[!@#$%&*_+=|\\\\/?:;><,.]","");
        
        // Removendo espaços em branco 
        novoNome = novoNome.replaceAll("\\s+", " ");
        
        /* Primeira letra do nome convertida para maiúscula */
        novoNome = novoNome.substring(0,1).toUpperCase() + novoNome.substring(1);
        nomeUsuario = novoNome;
        
        /* Definindo o sexo do usuário */
        sexo = verificarSexo(novoNome);
        if (sexo == 0)
            receberSexo();
        
        return novoNome;
    }
    
    /**
     * Receber o sexo do usuário final através de uma janela.
     */
    public void receberSexo() {
        // display the showOptionDialog
        Object[] options = { "Homem", "Mulher" };
        
        int escolha = JOptionPane.showOptionDialog(null, 
            nomeUsuario + ", você é homem ou mulher?", 
            "Sexo", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]);

        // interpret the user's choice
        if (escolha == JOptionPane.YES_OPTION)
            sexo = 1;
        else
            sexo = 2;
    }
    
    /**
     * Verificar se o nome é masculino, feminino, ou se não foi possível 
     * classificar.
     * 
     * @param nome String sendo o nome que será verificado
     * @return int sendo 0 para não identificado, 1 para masculino e
     *             2 para feminino
     */
    public int verificarSexo(String nome) {
        String novoNome = nome.toLowerCase().trim();
        
        // Removendo acentos
        novoNome = stripDiacritics(novoNome);

        // Removendo dígitos, potuação, carateres especiais, etc
        novoNome = novoNome.replaceAll("[^a-zA-Z0-9\\s]+","");
        
        // Removendo espaços em branco 
        novoNome = novoNome.replaceAll("\\s+", " ");
        
        if (nomesMasculinos.contains(novoNome))
            return 1;
        else if (nomesFemininos.contains(novoNome))
            return 2;
        else
            return 0;
    }
    
    /**
     * Método privado - Remover os acentos dos nomes.
     * 
     * @param str String sendo o nome do usuário
     * @return String sendo o nome sem acentos
     */
    private static String stripDiacritics(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = CARACTERES.matcher(str).replaceAll("");
        return str;
    }

    public int getSexo() {
        return sexo;
    }
}