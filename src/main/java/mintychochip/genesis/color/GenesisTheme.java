package mintychochip.genesis.color;

public class GenesisTheme {

    private static final char defaultColor = '7';
    private char headerColor;
    private char secondaryHeaderColor;
    private char bodyColor;
    private char accents;
    private char bullets;

    public GenesisTheme(char[] colors) {
        headerColor = secondaryHeaderColor = bodyColor = accents = bullets = defaultColor;
        if (colors != null) {
            for (int i = 0; i < colors.length; i++) {
                char colorCode = colors[i];
                if (colorCode == ':') { //still have to fix this
                    return;
                }
                switch (i) {
                    case 0:
                        this.setHeaderColor(colorCode);
                    case 1:
                        this.setSecondaryHeaderColor(colorCode);
                    case 2:
                        this.setBodyColor(colorCode);
                    case 3:
                        this.setAccents(colorCode);
                    case 4:
                        this.setBullets(colorCode);
                }
            }
        }
    }

    public char getHeaderColor() {
        return headerColor;
    }

    public void setHeaderColor(char headerColor) {
        this.headerColor = headerColor;
    }

    public char getSecondaryHeaderColor() {
        return secondaryHeaderColor;
    }

    public void setSecondaryHeaderColor(char secondaryHeaderColor) {
        this.secondaryHeaderColor = secondaryHeaderColor;
    }

    public char getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(char bodyColor) {
        this.bodyColor = bodyColor;
    }

    public char getAccents() {
        return accents;
    }

    public void setAccents(char accents) {
        this.accents = accents;
    }

    public char getBullets() {
        return bullets;
    }

    public void setBullets(char bullets) {
        this.bullets = bullets;
    }


}
