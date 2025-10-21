import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class StockApiService {
    private final String apiKey;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public StockApiService(String apiKey) {
        this.apiKey = apiKey;
    }

    public String findTicker(String companyName) throws IOException, InterruptedException {
        String encodedCompanyName = URLEncoder.encode(companyName, StandardCharsets.UTF_8);
        String url = String.format(
                "https://api.polygon.io/v3/reference/tickers?search=%s&market=stocks&type=CS&active=true&apiKey=%s",
                encodedCompanyName, apiKey);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode root = mapper.readTree(response.body());
        JsonNode results = root.path("results");

        if (results.isArray() && !results.isEmpty()) {
            return results.get(0).get("ticker").asText();
        }
        return null;
    }

    public double getLatestPrice(String ticker) throws IOException, InterruptedException {
        String url = String.format(
                "https://api.polygon.io/v2/aggs/ticker/%s/prev?adjusted=true&apiKey=%s",
                ticker, apiKey);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode root = mapper.readTree(response.body());
        JsonNode results = root.path("results");

        if (results.isArray() && !results.isEmpty()) {
            return results.get(0).get("c").asDouble();
        }
        return -1.0;
    }
}
