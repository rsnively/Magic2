package gui;

import game.Game;
import game.Phase;

import java.awt.Color;
import java.awt.Graphics2D;

// todo split into singlephaseviews
// todo click to add phase stop
public class PhaseView extends View {

    private Phase currentPhase;

    public PhaseView(Rect r, Phase phase) {
        super(r);
        this.currentPhase = phase;
    }

    public static String GetPhaseNameString(Phase.Name name) {
        switch (name) {
            case Untap: return "Untap";
            case Upkeep: return "Upkeep";
            case Draw: return "Draw";
            case Main1: return "Main";
            case BeginCombat: return "Begin Combat";
            case Attack: return "Attack";
            case Block: return "Block";
            case Damage: return "Damage";
            case EndCombat: return "End Combat";
            case Main2: return "Main";
            case End: return "End";
            case Cleanup: return "Cleanup";
            default: return "???";
        }
    }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.YELLOW);
        DrawBorder(g, Color.BLACK);

        for (int i = 0; i < Phase.Name.values().length; i++) {
            Phase.Name phaseName = Phase.Name.values()[i];
            if (phaseName == currentPhase.GetName()) {
                g.setColor(Color.MAGENTA);
                g.drawRect(GetPhaseX(i), GetRect().GetTop(), GetPhaseWidth(), GetRect().GetHeight());
            }

            g.setColor(Color.BLACK);
            DrawStringCentered(g, GetPhaseNameString(phaseName), GetPhaseRect(i));

            g.setColor(Color.BLUE);
            if (Game.Get().HasPhaseStop(phaseName, true))
                g.fillRect(GetPhaseX(i), GetRect().GetBottom() - 5, GetPhaseWidth(), 5);

            if (Game.Get().HasPhaseStop(phaseName, false))
                g.fillRect(GetPhaseX(i), GetRect().GetTop(), GetPhaseWidth(), 5);
        }

        g.setColor(Color.BLACK);
        g.drawString("Turn " + currentPhase.GetTurnNumber() + ": " + (currentPhase.GetActivePlayer() ? "Player 1" : "Player 2"), GetRect().GetLeft(), GetRect().GetTop() + 15);
    }

    private int GetPhaseWidth() {
        return GetRect().GetWidth() / Phase.Name.values().length;
    }

    private int GetPhaseX(int i) {
        return GetRect().GetLeft() + i * GetPhaseWidth();
    }

    private Rect GetPhaseRect(int i) {
        return Rect.RectTopLeft(new Point(GetPhaseX(i), GetRect().GetTop()), new Size(GetPhaseWidth(), GetRect().GetHeight()));
    }
}
