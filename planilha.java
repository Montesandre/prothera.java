//  Aqui iremos criar um programinha em Java que trabalha com pessoas e documentos
import java.util.*;

// Classe que representa um Documento (tipo RG, CPF, etc)
class Documento {
    String tipo;
    String numero;
    String descricao;

    Documento(String tipo, String numero, String descricao) {
        this.tipo = tipo;
        this.numero = numero;
        this.descricao = descricao;
    }
}

// Classe que representa uma Pessoa com seus documentos
class Pessoa {
    int id;
    String nome;
    int idade;
    List<Documento> documentos;

    Pessoa(int id, String nome, int idade, List<Documento> documentos) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.documentos = documentos;
    }

    boolean temCPF() {
        for (Documento doc : documentos) {
            if (doc.tipo.equals("CPF")) {
                return true;
            }
        }
        return false;
    }
}

public class CadastroPessoas {
    public static void main(String[] args) {

        // Criando a listagem de pessoas
        List<Pessoa> pessoas = new ArrayList<>();

        pessoas.add(new Pessoa(1, "Luiz Roberto", 33, Arrays.asList(
            new Documento("CNH", "74591035698445", "Carteira de Motorista"),
            new Documento("RG", "5200789", "Carteira de Identidade"),
            new Documento("CTPS", "4567898", "Carteira de Trabalho")
        )));

        pessoas.add(new Pessoa(2, "Raimundo Soares", 51, Arrays.asList(
            new Documento("CNH", "45963548565789", "Carteira de Motorista"),
            new Documento("RG", "5200785", "Carteira de Identidade"),
            new Documento("CTPS", "7891237", "Carteira de Trabalho")
        )));

        pessoas.add(new Pessoa(3, "Ana Tavares", 24, Arrays.asList(
            new Documento("CNH", "65236525662159", "Carteira de Motorista"),
            new Documento("CPF", "412454125147", "Cadastro de Pessoa Física")
        )));

        pessoas.add(new Pessoa(4, "Janete Niebues", 28, Arrays.asList(
            new Documento("CPF", "65236525662", "Cadastro de Pessoa Física"),
            new Documento("RG", "3300785", "Carteira de Identidade"),
            new Documento("SUS", "784512", "Sistema Único de Saúde")
        )));

        pessoas.add(new Pessoa(5, "Paulo da Rosa", 74, Arrays.asList(
            new Documento("RG", "5200785", "Carteira de Identidade"),
            new Documento("CPF", "456215812541", "Cadastro de Pessoa Física")
        )));

        pessoas.add(new Pessoa(6, "Wesley Soares", 60, Arrays.asList(
            new Documento("CNH", "91035698445963", "Carteira de Motorista"),
            new Documento("RG", "7415595", "Carteira de Identidade"),
            new Documento("CPF", "45618452136", "Cadastro de Pessoa Física"),
            new Documento("SUS", "2200789", "Sistema Único de Saúde")
        )));

        pessoas.add(new Pessoa(7, "Tamires Souza", 12, Arrays.asList(
            new Documento("RG", "7852123", "Carteira de Identidade")
        )));

        // 1. Pessoa com ID = 2
        System.out.println("\nPessoa com ID = 2:");
        pessoas.stream().filter(p -> p.id == 2).forEach(p -> System.out.println(p.nome));

        // 2. Listagem em ordem crescente de idade + CPF (se tiver)
        System.out.println("\nPessoas por idade (com CPF):");
        pessoas.stream().sorted(Comparator.comparingInt(p -> p.idade)).forEach(p -> {
            String cpf = p.documentos.stream()
                            .filter(doc -> doc.tipo.equals("CPF"))
                            .map(doc -> doc.numero)
                            .findFirst()
                            .orElse("(sem CPF)");
            System.out.println(p.nome + " - Idade: " + p.idade + ", CPF: " + cpf);
        });

        // 3. Pessoas com mais de 50 anos
        System.out.println("\nPessoas com idade acima de 50 anos:");
        pessoas.stream().filter(p -> p.idade > 50).forEach(p -> System.out.println(p.nome));

        // 4. Pessoas sem CPF
        System.out.println("\nPessoas sem CPF:");
        pessoas.stream().filter(p -> !p.temCPF()).forEach(p -> System.out.println(p.nome));

        // 5. Tipos de documentos (únicos)
        System.out.println("\nTipos de documentos:");
        pessoas.stream().flatMap(p -> p.documentos.stream())
            .map(doc -> doc.tipo).distinct()
            .forEach(System.out::println);
    }
}
