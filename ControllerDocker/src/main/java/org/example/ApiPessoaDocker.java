package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApiPessoaDocker {

    public static String[] montarComandoDocker(String id, String nome, String email, String dataNascimento) {
        return new String[]{
                "docker", "run", "--rm", "api-pessoa-app",
                id,
                nome,
                email,
                dataNascimento
        };
    }

    public static void executarComando(String id, String nome, String email, String dataNascimento) {
        String[] comando = montarComandoDocker(id, nome, email, dataNascimento);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(comando);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            System.out.println("DOCKER COMMAND >> docker run --rm api-pessoa-app << NOME :"+nome+" EMAIL :"+email+" DATA NASCIMENTO :"+dataNascimento);

            try (InputStream inputStream = process.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            System.out.print("Código de saída: " + exitCode+" / ");
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao executar o comando Docker: " + e.getMessage());
        }
    }
}
