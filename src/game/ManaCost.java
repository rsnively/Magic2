package game;

public class ManaCost {
    private Mana mana;
    private int generic;

    public ManaCost(String s) {
        mana = new Mana();

        generic = 0;
        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'W': mana.Add(Mana.Color.White); break;
                case 'U': mana.Add(Mana.Color.Blue); break;
                case 'B': mana.Add(Mana.Color.Black); break;
                case 'R': mana.Add(Mana.Color.Red); break;
                case 'G': mana.Add(Mana.Color.Green); break;
                default: generic *= 10; generic += Character.getNumericValue(s.charAt(i));
            }
        }
    }

    public ManaCost() {
        this("");
    }

    public ManaCost(ManaCost other) {
        this(other.ToString());
    }

    public boolean Has(Mana.Color color) { return mana.Has(color); }
    public int Get(Mana.Color color) { return mana.Get(color); }
    public int GetGeneric() { return generic; }

    public void Pay(Mana.Color color) {
        if (mana.Has(color))
            mana.Remove(color);
        else if (generic > 0)
            generic--;
    }

    public void Pay(Mana m) {
        for (Mana.Color c : Mana.Color.values())
            for (int i = m.Get(c); i > 0; i--)
                Pay(c);
    }

    public boolean Paid() {
        return generic == 0 && mana.Convert() == 0;
    }

    public boolean CouldUse(Mana m) {
        return generic > 0 || mana.CouldUse(m);
    }

    public String ToString() { return (generic > 0 ? Integer.toString(generic) : "") + (mana.Empty() ? "" : mana.ToString()); }
    public int Convert() { return generic + mana.Convert(); }
}
