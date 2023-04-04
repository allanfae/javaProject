import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var client = HttpClient.newHttpClient();
        URI adress = URI.create(url);
        var request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
       // System.out.println(body);

        var parser  = new JsonParser();

        List<Map<String, String>> moovieList = parser.parse(body);

        for (Map<String,String> filme : moovieList) {

            double rating =  Double.valueOf(filme.get("imDbRating"));
            
            int intRating = (int)rating;    

            System.out.println("\u001B[41m" + "\u001B[33m" + filme.get("title") + "\u001B[0m");
            System.out.println(filme.get("image"));           
            System.out.println("[\u2665]".repeat(intRating));
            System.out.println();
        }
    }
}
