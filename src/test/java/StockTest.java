import org.junit.jupiter.api.Test;

public class StockTest {
    @Test
    public void testIfGetPriceWorks(){
        Stock stock = new Stock("Apple",260, "APPL");
        System.out.println(stock.getStockPrice());
    }
    public void testIfGetTickerWorks(){
        Stock stock = new Stock("Apple", 260, "APPL");
        System.out.println(stock.getTickerName());
    }
}
