package game;

import java.util.HashMap;
import java.util.Map;

public class PhaseStops {

    private Map<Phase.Name, Boolean> p1Stops;
    private Map<Phase.Name, Boolean> p2Stops;

    public PhaseStops() {
        p1Stops = new HashMap<>();
        p2Stops = new HashMap<>();

        for (Phase.Name phaseName : Phase.Name.values()) {
            p1Stops.put(phaseName, false);
            p2Stops.put(phaseName, false);
        }

        p1Stops.put(Phase.Name.Main1, true);
        p1Stops.put(Phase.Name.Attack, true);
        p1Stops.put(Phase.Name.Block, true);
        p1Stops.put(Phase.Name.Main2, true);

        p2Stops.put(Phase.Name.BeginCombat, true);
        p2Stops.put(Phase.Name.Attack, true);
        p2Stops.put(Phase.Name.Block, true);
        p2Stops.put(Phase.Name.End, true);
    }

    public boolean Get(Phase.Name phaseName, boolean activePlayer) {
        return activePlayer ? p1Stops.get(phaseName) : p2Stops.get(phaseName);
    }
}
