import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StockTest {
    @Test
    public void testIfGetPriceReturnsCorrectValue(){
        Stock stock = new Stock("Apple",260, "APPL", 400);
        Assertions.assertEquals(260, stock.getUserAveragePrice());
    }

    @Test
    public void testIfGetTickerReturnsCorrectValue(){
        Stock stock = new Stock("Apple", 260, "APPL", 400);
        Assertions.assertEquals("APPL", stock.getTickerName());
    }

    @Test
    public void testIfGetStockNameReturnsCorrectValue(){
        Stock stock = new Stock("Apple", 260, "APPL", 400);
        Assertions.assertEquals("Apple", stock.getStockName());
    }

    @Test
    public void testIfGetSAveragePriceReturnsCorrectValue(){
        Stock stock = new Stock("Apple", 260, "APPL", 400);
        Assertions.assertEquals(400, stock.getStockPrice());
    }

    @Test
    public void testGetPercentageChange(){
        Stock stock = new Stock("Apple", 260, "APPL", 400);
        Assertions.assertEquals(53.846, stock.getPercentChange());
    }
}
