package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApiTelefoneDocker {

    public static String[] montarComandoDocker(String numero, String tipo, String idPessoa) {
        return new String[]{
                "docker", "run", "--rm", "api-telefone",
                numero,
                tipo,
                idPessoa
        };
    }

    public static void executarComando(String numero, String tipo, String idPessoa) {
        String[] comando = montarComandoDocker(numero, tipo, idPessoa);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(comando);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            System.out.println("DOCKER COMMAND >> docker run --rm api-telefone <<  NUMERO:"+numero+" TIPO :"+tipo+" ID_PESSOA:"+idPessoa);

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
