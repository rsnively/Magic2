package game;

public abstract class Land extends Card {

    public Land(String set, Player owner, String name, Cost cost) {
        super(set, owner, name, cost);

        AddAbility(new ManaAbility(new Cost(this), new Mana(ColorProduced()), this, owner));
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
                && GetZone() == CardZone.Hand
                && GetOwner().LandsPlayedThisTurn() == 0
                && (Game.Get().GetPhase().GetName() == Phase.Name.Main1
                        || Game.Get().GetPhase().GetName() == Phase.Name.Main2)
                && !Game.Get().CostsBeingPaid();
    }

    @Override
    public void Resolve() {
        GetOwner().GetPermanents().add(this);
        SetZone(CardZone.Battlefield);
        GetOwner().IncrementLandsPlayed();
    }

}
