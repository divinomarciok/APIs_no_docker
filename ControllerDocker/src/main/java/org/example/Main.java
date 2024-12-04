package org.example;

import org.example.ApiPessoaDocker;
import org.example.ApiEnderecoDocker;
import org.example.ApiTelefoneDocker;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String caminhoArquivoJson = "D:\\Faculdade\\5° Semestre\\Sistemas Distribuidos\\Trabalho N3\\JsonPessoa.json";

        try {

            String conteudoJson = new String(Files.readAllBytes(Paths.get(caminhoArquivoJson)));
            JSONArray jsonArray = new JSONArray(conteudoJson);

            Scanner scanner = new Scanner(System.in);

            // Solicitando ao usuário os índices de início e fim
            System.out.print("Digite o índice inicial: ");
            int indiceInicial = scanner.nextInt();
            System.out.print("Digite o índice final: ");
            int indiceFinal = scanner.nextInt();


            if (indiceInicial < 0 || indiceFinal >= jsonArray.length() || indiceInicial > indiceFinal) {
                System.err.println("Índices inválidos. Certifique-se de que estão dentro do intervalo correto e que o índice inicial seja menor ou igual ao índice final.");
                return;
            }

            for (int i = indiceInicial; i <= indiceFinal; i++) {
                JSONObject pessoa = jsonArray.getJSONObject(i);

                String id = Integer.toString(pessoa.getInt("id")); // Convertendo "id" para string
                String nome = pessoa.getString("nome");
                String email = pessoa.getString("email");
                String dataNascimento = pessoa.getString("data_nascimento");
                String logradouro = pessoa.optString("logradouro");
                String cidade = pessoa.optString("cidade");
                String numero = pessoa.optString("numero");
                String tipo = Integer.toString(pessoa.getInt("tipo"));


                ApiPessoaDocker.executarComando(id, nome, email, dataNascimento);
                ApiEnderecoDocker.executarComando(logradouro, cidade, id);
                ApiTelefoneDocker.executarComando(numero, tipo, id);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo JSON: " + e.getMessage());
        }
    }
}
