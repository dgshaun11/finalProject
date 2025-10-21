public class Stock {
    private final String stockName;
    private final double stockPrice;
    private final String tickerName;

    public Stock(String stockName, double stockPrice, String tickerName){
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.tickerName = tickerName;
    }

    public String getStockName(){
        return stockName;
    }

    public String getTickerName(){
        return tickerName;
    }

    public double getStockPrice(){
        return stockPrice;
    }

    @Override
    public String toString(){
        return String.format("Company: %s%nTicker: %s%nCurrent Price: $%.2f", stockName, tickerName, stockPrice);
    }
}
