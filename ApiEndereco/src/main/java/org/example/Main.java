package org.example;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Por favor, forneça os argumentos: logradouro, cidade, id_pessoa");
            return;
        }

        try {
            String logradouro = args[0];
            String cidade = args[1];
            String idPessoa = args[2];

            Unirest.config().connectTimeout(10000).socketTimeout(60000);
            HttpResponse<String> response = Unirest.post("https://dlrwxnowidaglcoubwwb.supabase.co/rest/v1/Endereco")
                    .header("apikey", "KEY SUPABASE")
                    .header("Authorization", "KEY SUPABASE")
                    .header("Content-Type", "application/json")
                    .body(String.format("{\"logradouro\": \"%s\", \"cidade\": \"%s\", \"id_pessoa\": %s}", logradouro, cidade, idPessoa))
                    .asString();

            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
