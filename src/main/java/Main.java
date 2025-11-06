import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String apiKey = ConfigLoader.getApiKey();
        StockApiService apiService = new StockApiService(apiKey);
        Portfolio portfolio = new Portfolio();

        Scanner scanner = new Scanner(System.in);
        PrintWriter fileOut = new PrintWriter(new FileOutputStream("portfolio.txt", true)); // append mode

        while (true) {
            System.out.print("Enter the name of a stock: (q to quit) ");
            String companyName = scanner.nextLine();
            if (companyName.equalsIgnoreCase("q")) {
                break;
            }

            System.out.print("Enter your average price: ");
            double averagePrice = scanner.nextDouble();
            scanner.nextLine();

            try {
                System.out.println("Searching for ticker...");
                String ticker = apiService.findTicker(companyName);

                if (ticker == null) {
                    System.out.println("Could not find a ticker for '" + companyName + "'.");
                    continue;
                }

                System.out.println("Found ticker: " + ticker);

                System.out.println("Fetching latest price...");
                double price = apiService.getLatestPrice(ticker);

                if (price < 0) {
                    System.out.println("Could not fetch price for ticker '" + ticker + "'.");
                    continue;
                }

                Stock stock = new Stock(companyName, averagePrice, ticker, price);

                System.out.println("\n--- Stock Information ---");
                System.out.println(stock + "\n");
                portfolio.addStockToPortfolio(stock);
                portfolio.printPortfolio();


                fileOut.printf("Ticker: %s | Price: %.2f%n", ticker, averagePrice);
                fileOut.flush();

            } catch (IOException | InterruptedException e) {
                System.err.println("An error occurred while connecting to the API.");
            }
        }

        fileOut.close();
        scanner.close();
    }
}