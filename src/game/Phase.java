package game;

public class Phase {

    public enum Name {
        Untap, Upkeep, Draw, Main1, BeginCombat, Attack, Block, Damage, EndCombat, Main2, End, Cleanup {
            @Override
            public Name next() {
                return Untap;
            }
        };

        public Name next() {
            return values()[ordinal() + 1];
        }
    }

    private Name name;
    private boolean activePlayer;
    private int turnNumber;

    public Phase(boolean ap) {
        name = Name.Untap;
        activePlayer = ap;
        turnNumber = 1;
    }

    public Name GetName() { return name; }
    public boolean GetActivePlayer() { return activePlayer; }
    public int GetTurnNumber() { return turnNumber; }

    public void NextPhase() {
        if (name == Name.Cleanup) {
            activePlayer = !activePlayer;
            if (activePlayer)
                turnNumber++;
        }
        name = name.next();
    }
}
