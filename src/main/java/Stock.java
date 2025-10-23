public class Stock {
    private final String stockName;
    private final double stockPrice;
    private final String tickerName;
    private final double averagePrice;

    public Stock(String stockName, double stockPrice, String tickerName, double averagePrice){
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.tickerName = tickerName;
        this.averagePrice = averagePrice;
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

    public double getAveragePrice(){return averagePrice; }

    @Override
    public String toString(){
        return String.format("Company: %s%nTicker: %s%nAverage Price: $%.2f%nCurrent Price: $%.2f",
                stockName, tickerName, stockPrice, averagePrice);
    }
}
