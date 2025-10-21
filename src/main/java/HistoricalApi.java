import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HistoricalApi {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apiKey = "Szoc9xAzpYbVQ62qll_DXS8kA_Y6pRXI"; // ← Replace with your Polygon.io key
        String symbol = "AAPL";         // ← Change ticker if needed

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);

        String url = String.format(
                "https://api.polygon.io/v2/aggs/ticker/%s/range/1/day/%s/%s?adjusted=true&sort=asc&apiKey=%s",
                symbol,
                startDate.format(DateTimeFormatter.ISO_DATE),
                endDate.format(DateTimeFormatter.ISO_DATE),
                apiKey);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());
        JsonNode results = root.get("results");

        if (results == null) {
            System.out.println("No data returned. Check your API key or ticker symbol.");
            return;
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("Date\t\tClose");
        for (JsonNode day : results) {
            long timestamp = day.get("t").asLong();
            double close = day.get("c").asDouble();

            LocalDate date = LocalDate.ofEpochDay(timestamp / 86_400_000L);

            System.out.printf("%s\t%.2f%n", dateFormatter.format(date), close);
        }
    }
}
