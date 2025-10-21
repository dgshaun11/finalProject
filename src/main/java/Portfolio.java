import java.util.ArrayList;

public class Portfolio {
    ArrayList<Stock> portfolio = new ArrayList<>();

    public void addStockToPortfolio(Stock stock){
        portfolio.add(stock);
    }

    public void printPortfolio(){
        System.out.println("------ Portfolio ------");
        for (Stock stock: portfolio){
            System.out.println(stock.toString());
        }
    }

}
