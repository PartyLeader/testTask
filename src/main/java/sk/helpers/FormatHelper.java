package sk.helpers;

public class FormatHelper {
    public static String toAmount(Double amount) {
        return String.format("$%.2f", amount);
    }

    public static Double fromAmount(String price) {
        return Double.valueOf(price.replace("$",""));

    }
}
