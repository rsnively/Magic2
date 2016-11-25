package game;

import java.util.HashMap;
import java.util.Map;

public class Mana {

    public enum Color { White, Blue, Black, Red, Green }

    private Map<Color, Integer> colors;

    public Mana(String mana) {
        colors = new HashMap<>();
        for (Color c : Color.values())
            colors.put(c, 0);

        mana = mana.toUpperCase();
        for (int i = 0; i < mana.length(); i++) {
            switch (mana.charAt(i)) {
                case 'W':
                    Add(Color.White);
                    break;
                case 'U':
                    Add(Color.Blue);
                    break;
                case 'B':
                    Add(Color.Black);
                    break;
                case 'R':
                    Add(Color.Red);
                    break;
                case 'G':
                    Add(Color.Green);
                    break;
            }
        }
    }

    public Mana() {
        this("");
    }

    public Mana(Color c) {
        this("");
        Add(c);
    }

    public int Get(Color c) { return colors.get(c); }
    public boolean Has(Color c) { return colors.get(c) > 0; }

    public int Convert() {
        int sum = 0;
        for (Color c : Color.values())
            sum += Get(c);
        return sum;
    }

    public static String GetString(Mana.Color c) {
        switch (c) {
            case White: return "W";
            case Blue: return "U";
            case Black: return "B";
            case Red: return "R";
            case Green: return "G";
        }
        return "????";
    }

    public String ToString() {
        String s = "";
        for (Color c : Color.values())
            for (int i = 0; i < Get(c); i++)
                s += GetString(c);

        if (s.isEmpty())
            return "0";
        return s;
    }

    public void Add(Mana.Color color) { colors.replace(color, colors.get(color) + 1); }
    public void Remove(Mana.Color color) { colors.replace(color, colors.get(color) - 1); }
    public void Add(Mana other) { for (Color c : Color.values()) colors.replace(c, colors.get(c) + other.Get(c));}
    public void Remove(Mana other) { for (Color c : Color.values()) colors.replace(c, colors.get(c) - other.Get(c)); }

    public void AddMana(String mana) {
        Mana other = new Mana(mana);
        this.Add(other);
    }

    public void Remove(String mana) {
        Mana other = new Mana(mana);
        this.Remove(other);
    }

    public boolean CouldUse(Mana other) {
        for (Color c : Color.values())
            if (Get(c) > 0 && other.Get(c) > 0)
                return true;
        return false;
    }

    public boolean Empty() { return this.Convert() == 0; }

    public static int Convert(String mana) { return (new Mana(mana)).Convert(); }
}
