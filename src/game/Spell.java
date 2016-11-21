package game;

public abstract class Spell extends Card {

    public Spell(Player owner, String name) {
        super(owner, name);
    }

    @Override
    public boolean UsesStack() { return true; }

    @Override
    public boolean CanPlay() {
        return GetOwner().IsActivePlayer()
                && IsInHand()
                && (Game.Get().GetPhase().GetName() == Phase.Name.Main1 ||  Game.Get().GetPhase().GetName() == Phase.Name.Main2)
                && !Game.Get().GetStack().HasEffects();
    }
}
