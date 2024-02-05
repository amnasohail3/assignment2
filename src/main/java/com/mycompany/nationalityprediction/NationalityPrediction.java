

package com.mycompany.nationalityprediction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NationalityPrediction {
    public static void main(String[] args) {
        String name = "Johnson";

        try {
            URL url = new URL("https://api.nationalize.io?name=" + name);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.readLine();
                String firstNationality = parseFirstNationality(response);

                System.out.println("Predicted nationality for " + name + ": " + firstNationality);
            }

        } catch (IOException e) {
        }
    }

    private static String parseFirstNationality(String responseData) {
        int start = responseData.indexOf("\"country_id\":\"") + 14;
        int end = responseData.indexOf("\"", start);
        return responseData.substring(start, end);
    }
}