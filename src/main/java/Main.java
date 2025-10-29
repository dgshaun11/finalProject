import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String apiKey = ConfigLoader.getApiKey();
        StockApiService apiService = new StockApiService(apiKey);
        Portfolio portfolio = new Portfolio();

        PrintStream console = System.out;

        PrintStream fileOut = new PrintStream(new FileOutputStream("portfolio.txt", true));

        PrintStream dualOutput = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                console.write(b);
                fileOut.write(b);
            }

            @Override
            public void flush() throws IOException {
                console.flush();
                fileOut.flush();
            }
        }, true);

        System.setOut(dualOutput);

        Scanner scanner;
        while (true) {
            scanner = new Scanner(System.in);
            System.out.print("Enter the name of a stock: (q to quit) ");
            String companyName = scanner.nextLine();
            if (companyName.equals("q")) {
                break;
            }
            System.out.print("Enter your average price: ");
            double averagePrice = scanner.nextDouble();

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

                Stock stock = new Stock(companyName, averagePrice, ticker, price);

                System.out.println("\n--- Stock Information ---");
                System.out.println(stock + "\n");
                portfolio.addStockToPortfolio(stock);
                portfolio.printPortfolio();

            } catch (IOException | InterruptedException e) {
                System.err.println("An error occurred while connecting to the API.");
            }
        }
        System.setOut(console);
        fileOut.close();
        scanner.close();
    }
}