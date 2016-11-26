package game;

public abstract class Spell extends Card {

    public Spell(String set, Player owner, String name, Cost cost) {
        super(set, owner, name, cost);
    }

    @Override
    public boolean Is(Mana.Color c) {
        return GetCost().GetManaCost().Has(c);
    }

    @Override
    public boolean UsesStack() { return true; }

    @Override
    public boolean CanPlay() {
        return GetOwner().IsActivePlayer()
                && GetZone() == CardZone.Hand
                && (Game.Get().GetPhase().GetName() == Phase.Name.Main1 ||  Game.Get().GetPhase().GetName() == Phase.Name.Main2)
                && !Game.Get().CostsBeingPaid()
                && !Game.Get().GetStack().HasEffects();
    }

    @Override
    public void Resolve() {
        SetZone(CardZone.Graveyard);
        GetOwner().GetGraveyard().add(this);
    }
}
