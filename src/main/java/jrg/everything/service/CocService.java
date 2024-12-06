package jrg.everything.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

// Clase que se encarga de realizar peticiones a la API de Clash of Clans.
@Service
public class CocService {

    private static final String BASE_URL = "https://api.clashofclans.com/v1";

    public String getPlayerInfo(String tag, String token) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/players/%23" + tag))
                    .header("Authorization", "Bearer " + token)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return null;
            }
            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
}
