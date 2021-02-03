package com.example.restservice;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class MainClient {
    public static void main(String[] args) throws IOException {
        URL url = new URL ("http://localhost:8080/loadLogs");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        StringBuilder jsonInputString =new StringBuilder();
        File file = new File("log.json");
        Scanner scanner = new Scanner(file);
        while ((scanner.hasNextLine())){
            jsonInputString.append(scanner.nextLine());
        }
        System.out.println(jsonInputString.toString());
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
         System.out.println(connection.getResponseCode());
        }
    }
}
