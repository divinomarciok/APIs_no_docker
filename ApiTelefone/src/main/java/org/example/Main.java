package org.example;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Por favor, forneça os argumentos: numero, tipo, id_pessoa");
            return;
        }

        try {
            String numero = args[0];
            String tipo = args[1];
            String idPessoa = args[2];

            Unirest.config().connectTimeout(10000).socketTimeout(60000);
            HttpResponse<String> response = Unirest.post("https://dlrwxnowidaglcoubwwb.supabase.co/rest/v1/Telefone")
                    .header("apikey", "KEY supabase")
                    .header("Authorization", "Bearer key supabase")
                    .header("Content-Type", "application/json")
                    .body(String.format("{\"numero\": \"%s\", \"tipo\": \"%s\", \"id_pessoa\": %s}", numero, tipo, idPessoa))
                    .asString();

            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
