package game;

public abstract class Creature extends Spell {

    private int power;
    private int toughness;
    private boolean summoningSickness;

    public Creature(Player owner, String name, int power, int toughness) {
        super(owner, name);
        this.power = power;
        this.toughness = toughness;
        this.summoningSickness = false;
        AddType(Type.Creature);
    }

    @Override
    public boolean HasSummoningSickness() { return summoningSickness; }

    @Override
    public void RemoveSummoningSickness() { summoningSickness = false; }

    @Override
    public int GetPower() { return power; }

    @Override
    public int GetToughness() { return toughness; }

    @Override
    public void Resolve() {
        summoningSickness = true;
        GetOwner().GetPermanents().add(this);
    }

    @Override
    public boolean CanAttack() {
        return !IsTapped()
                && Game.Get().GetPhase().GetName() == Phase.Name.Attack
                && !IsAttacking()
                && IsOnBattlefield()
                && !HasSummoningSickness();
    }
}
