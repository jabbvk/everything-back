package jrg.everything.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class CocService {

    public String getPlayerInfo(String tag) {
        try {
            String url = "https://api.clashofclans.com/v1/players/%23" + tag;
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjU4ZTU5ZTUzLWZiMTEtNDc4Mi05NWFmLTkyMzJlMTgzOGE2OSIsImlhdCI6MTczMTk1MjAzNSwic3ViIjoiZGV2ZWxvcGVyLzQxNDE4N2ZmLWY1OTYtMzg5NC0zYjRjLTA4MDcyNGMwMmM0MCIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjE4OC4xMjcuMTcxLjQwIl0sInR5cGUiOiJjbGllbnQifV19.h2sHZERvIkBay3otu5GIuZKK1HsZ5YkZc0Xfj4hWlLf0Z3B7z2O4jpEkg5H_Z_Cpmbs_N2kLEYrODZpU58rEQw";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + token)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body(); // Devolver el cuerpo de la respuesta

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Devolver null o manejar el error de una mejor forma
        }
    }
}
