package game;

public abstract class Ability implements Effect {
    public String GetEffectImageFile() { return ""; }
    public boolean CanActivate() { return false; }
    public boolean UsesStack() { return false; }
    public void Activate() {}
}
