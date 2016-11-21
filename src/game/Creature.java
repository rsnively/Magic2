package game;

public abstract class Creature extends Spell {

    private int power;
    private int toughness;

    public Creature(Player owner, String name, int power, int toughness) {
        super(owner, name);
        this.power = power;
        this.toughness = toughness;
        AddType(Type.Creature);
    }

    @Override
    public void Resolve() {
        GetOwner().GetPermanents().add(this);
    }
}
