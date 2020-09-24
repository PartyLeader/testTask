package sk.constants;

public enum FeedItems {
    YOUR_FEED("Your Feed"),
    GLOBAL_FEED("Global Feed");

    private String displayText;

    FeedItems(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
