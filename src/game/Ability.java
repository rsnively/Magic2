package game;

public abstract class Ability implements Effect {

    public boolean IsTriggeredAbility() { return false; }
    public boolean IsActivatedAbility() { return false; }
    public boolean IsStaticAbility() { return false; }
    public boolean IsManaAbility() { return false; }

    public String GetEffectImageFile() { return ""; }
    public boolean CanActivate() { return false; }
    public boolean UsesStack() { return false; }
    public void Activate() {}
}
