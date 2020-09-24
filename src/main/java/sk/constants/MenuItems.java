package sk.constants;

public enum MenuItems {
    NEW_POST("New Post"),
    HOME("Home"),
    SIGN_IN("Sign in"),
    SIGN_UP("Sign up"),
    SETTINGS("Settings");

    private String displayText;

    MenuItems(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
