package game;

public abstract class Ability implements Effect {

    public boolean CanActivate() { return false; }
    public boolean UsesStack() { return false; }
    public void Activate() {}
}
