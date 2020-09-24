package sk.constants;

public class FeatureType {
    private FeatureType() {
        throw new AssertionError("FeatureType class must not be instantiated");
    }

    public static final String TC_1 = "TC-1: Guest cannot create article.";
    public static final String TC_2 = "TC-2: Verify Main menu items for Writer";
    public static final String TC_3 = "TC-2: Writer can create a new article.";
    public static final String TC_4 = "TC-4: Pagination 10 feed per page for Writer";
    public static final String TC_5 = "TC-4: Pagination 10 feed per page for Guest";
}
