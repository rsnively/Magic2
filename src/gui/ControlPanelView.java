package gui;

import game.Game;
import game.Phase;
import game.Stack;

import java.awt.Color;
import java.awt.Graphics2D;

public class ControlPanelView extends View {

    public ControlPanelView(Rect r) {
        super(r);
    }

    private String GetDialogString(Phase p) {
        if (Game.Get().CostsBeingPaid())
            return "Pay " + Game.Get().GetCostBeingPaid().GetMana().ToString();
        switch (p.GetName()) {
            case Upkeep:
            case Untap:
            case Draw:
            case BeginCombat:
            case Attack:
            case Block:
            case EndCombat:
            case End:
            case Cleanup: return PhaseView.GetPhaseNameString(p.GetName()) + ": Cast instants or activate abilities.";
            case Main1:
            case Main2: return PhaseView.GetPhaseNameString(p.GetName()) + (p.GetActivePlayer() && !Game.Get().GetStack().HasEffects() ? ": Play lands, cast spells, or activate abilities." : ": Cast instants or activate abilities.");
            default: return "???";
        }
    }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.MAGENTA.darker().darker());

        g.setColor(Color.BLACK);
        g.drawString("Player 1", GetRect().GetLeft(), GetRect().GetTop() + 20);
        g.drawString("========", GetRect().GetLeft(), GetRect().GetTop() + 40);
        g.drawString("P1 Life: " + Game.Get().GetPlayer1().GetLifeTotal(), GetRect().GetLeft(), GetRect().GetTop() + 60);
        g.drawString("P1 Cards in Hand: " + Game.Get().GetPlayer1().GetHand().size(), GetRect().GetLeft(), GetRect().GetTop() + 80);
        g.drawString("P1 Cards in Library: " + Game.Get().GetPlayer1().GetLibrary().size(), GetRect().GetLeft(), GetRect().GetTop() + 100);
        g.drawString("P1 Cards in Graveyard: " + Game.Get().GetPlayer1().GetGraveyard().size(),GetRect().GetLeft(), GetRect().GetTop() + 120);

        g.drawString("Player 2", GetRect().GetLeft(), GetRect().GetTop() + 160);
        g.drawString("========", GetRect().GetLeft(), GetRect().GetTop() + 180);
        g.drawString("P2 Life: " + Game.Get().GetPlayer2().GetLifeTotal(), GetRect().GetLeft(), GetRect().GetTop() + 200);
        g.drawString("P2 Cards in Hand: " + Game.Get().GetPlayer2().GetHand().size(), GetRect().GetLeft(), GetRect().GetTop() + 220);
        g.drawString("P2 Cards in Library: " + Game.Get().GetPlayer2().GetLibrary().size(), GetRect().GetLeft(), GetRect().GetTop() + 240);
        g.drawString("P2 Cards in Graveyard: " + Game.Get().GetPlayer2().GetGraveyard().size(),GetRect().GetLeft(), GetRect().GetTop() + 260);

        g.setColor(Color.BLACK);
        g.drawString(GetDialogString(Game.Get().GetPhase()), GetRect().GetLeft(), GetRect().GetCenterY());
        g.drawString("Click when done", GetRect().GetLeft(), GetRect().GetCenterY() + 20); //todo buttons for yes/no/okay
    }

    @Override
    protected void Clicked(Click c) {
        if (!Game.Get().GetStack().HasEffects() && !Game.Get().CostsBeingPaid())
            Game.Get().AdvancePhase();
    }
}
