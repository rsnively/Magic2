package game;

public class ManaAbility extends ActivatedAbility {

    private Mana mana;
    private Card source;
    private Player controller;

    public ManaAbility(Cost c, Mana mana, Card source, Player controller) {
        super(c);
        this.mana = mana;
        this.source = source;
        this.controller = controller;
    }

    @Override
    public boolean IsManaAbility() { return true; }

    @Override
    public boolean UsesStack() { return false; }

    @Override
    public boolean CanActivate() { return true; }

    @Override
    public void Activate() {
        cost.Pay();
    }

    @Override
    public String GetEffectDescription() { return ""; }

    @Override
    public void Resolve() {
        controller.AddMana(mana ,source);
    }

    // todo deactivate to untap and remove mana

}
