public class Stock {
    private final String stockName;
    private final double userAveragePrice;
    private final String tickerName;
    private final double stockPrice;

    public Stock(String stockName, double userAveragePrice, String tickerName, double stockPrice) {
        this.stockName = stockName;
        this.userAveragePrice = userAveragePrice;
        this.tickerName = tickerName;
        this.stockPrice = stockPrice;
    }

    public String getStockName(){
        return stockName;
    }

    public String getTickerName(){
        return tickerName;
    }

    public double getUserAveragePrice(){
        return userAveragePrice;
    }

    public double getStockPrice(){return stockPrice; }

    public double getPercentChange(){
        double percentChange = stockPrice - userAveragePrice;
        return ((double) ((int) ((percentChange / userAveragePrice) * 100000)))/1000;
    }

    @Override
    public String toString() {
        return String.format(
                "Company: %s%nTicker: %s%nAverage Price: $%.2f%nCurrent Price: $%.2f%nPercent Change: %.2f%%",
                stockName, tickerName, userAveragePrice, stockPrice, getPercentChange()
        );
    }
}
