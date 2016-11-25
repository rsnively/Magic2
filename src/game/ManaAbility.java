package game;

public class ManaAbility extends ActivatedAbility {

    public Mana mana;
    public Player controller;

    public ManaAbility(Cost c, Mana mana, Player controller) {
        super(c);
        this.mana = mana;
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
        controller.AddMana(mana);
    }

    // todo deactivate to untap and remove mana

}
