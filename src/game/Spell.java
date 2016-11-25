package game;

public abstract class Spell extends Card {

    public Spell(String set, Player owner, String name) {
        super(set, owner, name);
    }

    @Override
    public boolean Is(Mana.Color c) {
        return GetCost().GetMana().HasColor(c);
    }

    @Override
    public boolean UsesStack() { return true; }

    @Override
    public boolean CanPlay() {
        return GetOwner().IsActivePlayer()
                && IsInHand()
                && (Game.Get().GetPhase().GetName() == Phase.Name.Main1 ||  Game.Get().GetPhase().GetName() == Phase.Name.Main2)
                && !Game.Get().CostsBeingPaid()
                && !Game.Get().GetStack().HasEffects();
    }
}
