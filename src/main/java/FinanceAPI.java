import com.github.oscerd.finnhub.client.FinnhubClient;
import com.github.oscerd.finnhub.models.CompanyProfile;
import com.github.oscerd.finnhub.models.CompanyProfile2;
import com.github.oscerd.finnhub.models.Quote;
import com.github.oscerd.finnhub.models.Quote;

public class FinanceAPI {
    public static void main(String[] args) {
        String API_KEY = "d3n92hpr01qk6516am30d3n92hpr01qk6516am3g";

        FinnhubClient client = new FinnhubClient(API_KEY);
        try {
            CompanyProfile2 profile = client.companyProfile("AAPL");
            System.out.println("Name: " + profile.getName());
            System.out.println("Currency: " + profile.getCurrency());
            System.out.println("IPO Date: " + profile.getIpo());
        } catch (Exception e){
            System.out.println("error");
    }
    }
}
