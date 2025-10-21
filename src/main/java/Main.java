import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // IMPORTANT: Replace this with your actual Polygon.io API key.
        String apiKey = "yp6s6jV7pkarYtIzWzlkF68pn3Jtzi57";
        StockApiService apiService = new StockApiService(apiKey);
        Portfolio portfolio = new Portfolio();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name of a stock:");
            String companyName = scanner.nextLine();

            try {
                System.out.println("Searching for ticker...");
                String ticker = apiService.findTicker(companyName);

                if (ticker == null) {
                    System.out.println("Could not find a ticker for '" + companyName + "'.");
                    return;
                }

                System.out.println("Found ticker: " + ticker);

                System.out.println("Fetching latest price...");
                double price = apiService.getLatestPrice(ticker);

                if (price < 0) {
                    System.out.println("Could not fetch price for ticker '" + ticker + "'.");
                    return;
                }

                Stock stock = new Stock(companyName, price, ticker);

                System.out.println("\n--- Stock Information ---");
                System.out.println(stock + "\n");
                portfolio.addStockToPortfolio(stock);
                portfolio.printPortfolio();

            } catch (IOException | InterruptedException e) {
                System.err.println("An error occurred while connecting to the API.");
            }
        }
    }
}
