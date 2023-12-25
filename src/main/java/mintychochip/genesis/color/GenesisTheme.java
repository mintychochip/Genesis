package mintychochip.genesis.color;

public class GenesisTheme {

    private char headerColor; //also displayName
    private char secondaryHeaderColor;
    private char bodyColor; //lore
    private char accents;
    private char bullets;
    public GenesisTheme() {

    }
    public void setHeaderColor(char headerColor) {
        this.headerColor = headerColor;
    }

    public void setSecondaryHeaderColor(char secondaryHeaderColor) {
        this.secondaryHeaderColor = secondaryHeaderColor;
    }

    public void setBodyColor(char bodyColor) {
        this.bodyColor = bodyColor;
    }

    public void setAccents(char accents) {
        this.accents = accents;
    }

    public void setBullets(char bullets) {
        this.bullets = bullets;
    }

    public char getHeaderColor() {
        return headerColor;
    }

    public char getSecondaryHeaderColor() {
        return secondaryHeaderColor;
    }

    public char getBodyColor() {
        return bodyColor;
    }

    public char getAccents() {
        return accents;
    }

    public char getBullets() {
        return bullets;
    }
}
