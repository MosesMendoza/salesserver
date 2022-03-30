package salesserver;
import java.util.Map;
import java.util.HashMap;

public class Sale {
    private String amount;
    private String state;

    public Sale(String saleAmount, String saleState) {
        amount = saleAmount;
        state = saleState;
    }

    public Map<String, String> toMap() {
        // leave these as strings since we're just putting them on kafka as strings for nwo
        final Map<String, String> sale = new HashMap<String, String>();

        sale.put("amount", amount);
        sale.put("state_abbreviation", state);

        return sale;
    }
}
