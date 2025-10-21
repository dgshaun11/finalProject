import java.util.Scanner;

public class UserInput {
    public Stock getUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your stock name.");
        String stockName = scanner.nextLine();
        Stock stock = new Stock (stockName, stock.getTicker(), stock.getPrice());
        return stock;
    }
}
