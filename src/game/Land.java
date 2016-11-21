package game;

public abstract class Land extends Card {

    public Land(String set, Player owner, String name) {
        super(set, owner, name);

        AddAbility(new ManaAbility(new Cost(this), new Mana(ColorProduced()), owner));
        AddType(Type.Land);
    }

    public abstract Mana.Color ColorProduced();

    @Override
    public Cost GetCost() { return new Cost(); }

    @Override
    public boolean UsesStack() { return false; }

    @Override
    public boolean CanPlay() {
        return GetOwner().IsActivePlayer()
                && IsInHand()
                && GetOwner().LandsPlayedThisTurn() == 0
                && (Game.Get().GetPhase().GetName() == Phase.Name.Main1
                        || Game.Get().GetPhase().GetName() == Phase.Name.Main2);
    }

    @Override
    public void Resolve() {
        GetOwner().GetPermanents().add(this);
        GetOwner().IncrementLandsPlayed();
    }

}
