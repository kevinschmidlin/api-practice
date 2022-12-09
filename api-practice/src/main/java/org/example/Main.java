package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.net.URLEncoder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String host = "https://movie-database-alternative.p.rapidapi.com/"; // Host URL
        String charset = "UTF-8";

        // Headers for request
        String xRapidAPIHost = "movie-database-alternative.p.rapidapi.com";
        String xRapidAPIKey = "4433791c03msh107ae744fef2135p1b458ajsna82004c66e46";

        boolean isValid = false;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("1. By Search");
            System.out.println("2. Quit");

            int option = Integer.parseInt(input.nextLine());

            switch (option) {
                case 1:
                    System.out.println("Search: ");

                    // Required parameters
                    String s = input.nextLine();

                    // Format query to prevent encoding problems
                    String query = String.format("s=%s", URLEncoder.encode(s, charset));

                    HttpResponse<JsonNode> response = Unirest.get(host + "?" + query)
                            .header("x-rapidapi-host", xRapidAPIHost)
                            .header("x-rapidapi-key", xRapidAPIKey)
                            .asJson();

                    // Prettify
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonParser jp = new JsonParser();
                    JsonElement je = jp.parse(response.getBody().toString());
                    String prettyJsonString = gson.toJson(je);
                    System.out.println(prettyJsonString);
                    break;
                case 2:
                    isValid = true;
                    break;
            }
        } while (!isValid);

        System.out.println("Goodbye.");
    }
}