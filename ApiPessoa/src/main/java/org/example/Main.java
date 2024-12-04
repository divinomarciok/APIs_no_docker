package org.example;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Main {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.err.println("Por favor, forneça os argumentos: id, nome, email, data_nascimento");
            return;
        }

        try {
            String id = args[0];
            String nome = args[1];
            String email = args[2];
            String dataNascimento = args[3];

            Unirest.config().connectTimeout(10000).socketTimeout(60000);
            HttpResponse<String> response = Unirest.post("https://dlrwxnowidaglcoubwwb.supabase.co/rest/v1/Pessoa")
                    .header("apikey", "key supabase")
                    .header("Authorization", "key supabase")
                    .header("Content-Type", "application/json")
                    .body(String.format("{\"id\": \"%s\", \"nome\": \"%s\", \"email\": \"%s\", \"data_nascimento\": \"%s\"}", id, nome, email, dataNascimento))
                    .asString();

            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
