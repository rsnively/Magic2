package game;

public abstract class ActivatedAbility extends Ability {

    public Cost cost;

    public ActivatedAbility(Cost c) {
        this.cost = c;
    }

    @Override
    public boolean CanActivate() {
        return cost.CouldPay();
    }

    @Override
    public boolean UsesStack() { return true; }
}
