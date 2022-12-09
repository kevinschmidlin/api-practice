package org.example;

import java.net.URLEncoder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) throws Exception {

        String host = "https://movie-database-alternative.p.rapidapi.com/"; // Host URL
        String charset = "UTF-8";

        // Headers for request
        String x_rapidapi_host = "movie-database-alternative.p.rapidapi.com";
        String x_rapidapi_key = "4433791c03msh107ae744fef2135p1b458ajsna82004c66e46";

        // Required parameters
        String s = "Pulp";

        // Format query to prevent encoding problems
        String query = String.format("s=%s", URLEncoder.encode(s, charset));

        HttpResponse<JsonNode> response = Unirest.get(host + "?" + query)
                .header("x-rapidapi-host", x_rapidapi_host)
                .header("x-rapidapi-key", x_rapidapi_key)
                .asJson();

        //System.out.println(response.getStatus());
        //System.out.println(response.getHeaders().get("Content-Type"));

        // Prettify
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);
    }
}