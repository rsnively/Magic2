package game;

public class Mana {

    public enum Color { White, Blue, Black, Red, Green, Colorless }

    private int white, blue, black, red, green, colorless;

    public Mana(String cost) {
        white = 0;
        blue = 0;
        black = 0;
        red = 0;
        green = 0;
        colorless = 0;

        cost = cost.toUpperCase();
        for (int i = 0; i < cost.length(); i++) {
            char c = cost.charAt(i);

            if (Character.isDigit(c)) {
                colorless *= 10;
                colorless += Character.getNumericValue(c);
            }
            else if (c == 'W') white++;
            else if (c == 'U') blue++;
            else if (c == 'B') black++;
            else if (c == 'R') red++;
            else if (c == 'G') green++;
        }
    }

    public Mana() {
        this("");
    }

    public Mana(Color c) {
        this("");
        switch (c) {
            case White: white++; break;
            case Blue: blue++; break;
            case Black: black++; break;
            case Red: red++; break;
            case Green: green++; break;
            case Colorless: colorless++; break;
            default: System.out.println("Don't recognize mana color " + c.toString());
        }
    }

    public int GetWhite() { return white; }
    public int GetBlue() { return blue; }
    public int GetBlack() { return black; }
    public int GetRed() { return red; }
    public int GetGreen() { return green; }
    public int GetColorless() { return colorless; }

    public int Convert() {
        return white + blue + black + red + green + colorless;
    }

    public String ToString() {
        String s = "";
        if (colorless > 0) s += Integer.toString(colorless);
        if (white > 0) s += "W";
        if (blue > 0) s += "U";
        if (black > 0) s += "B";
        if (red > 0) s += "R";
        if (green > 0) s += "G";

        if (s.isEmpty())
            return "0";
        return s;
    }

    public void Add(Mana other) {
        this.white += other.white;
        this.blue += other.blue;
        this.black += other.black;
        this.red += other.red;
        this.green += other.green;
        this.colorless += other.colorless;
    }

    public void Remove(Mana other) {
        this.white -= other.white;
        this.blue -= other.blue;
        this.black -= other.black;
        this.red -= other.red;
        this.green -= other.green;

        // todo let player choose
        while (other.colorless > 0) {
            if (this.colorless > 0) {
                this.colorless--;
                other.colorless--;
            }
            else if (this.white > 0) {
                this.white--;
                other.colorless--;
            }
            else if (this.blue > 0) {
                this.blue--;
                other.colorless--;
            }
            else if (this.black > 0) {
                this.black--;
                other.colorless--;
            }
            else if (this.red > 0) {
                this.red--;
                other.colorless--;
            }
            else if (this.green > 0) {
                this.green--;
                other.colorless--;
            }
        }
    }

    public void AddMana(String mana) {
        Mana other = new Mana(mana);
        this.Add(other);
    }

    public void Remove(String mana) {
        Mana other = new Mana(mana);
        this.Remove(other);
    }

    public boolean Covers(Mana other) {
        return this.white >= other.white
                && this.blue >= other.blue
                && this.black >= other.black
                && this.red >= other.red
                && this.green >= other.green
                && this.Convert() >= other.Convert();
    }

    public boolean Empty() { return this.Convert() == 0; }

    public static int Convert(String mana) { return (new Mana(mana)).Convert(); }
}
