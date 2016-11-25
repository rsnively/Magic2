package gui;

import game.Game;
import game.Phase;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ControlPanelView extends View {

    private GameView gameView;
    private GraveyardButton player1GraveyardButton;
    private GraveyardButton player2GraveyardButton;
    private UndoButtonView undoButton;

    public ControlPanelView(Rect r, GameView gameView) {
        super(r);
        this.gameView = gameView;
        player1GraveyardButton = new GraveyardButton(GetPlayer1GraveyardButtonRect(), 1, gameView);
        player2GraveyardButton = new GraveyardButton(GetPlayer2GraveyardButtonRect(), 2, gameView);
        undoButton = new UndoButtonView(GetUndoButtonRect());
    }

    private Rect GetPlayer1GraveyardButtonRect() {
        Size s = new Size(GetRect().GetWidth() * 0.9, 30);
        Point center = new Point(GetRect().GetCenterX(), GetRect().GetHeight() * 0.7);
        return Rect.RectCenter(center, s);
    }

    private Rect GetPlayer2GraveyardButtonRect() {
        Size s = new Size(GetRect().GetWidth() * 0.9, 30);
        Point center = new Point(GetRect().GetCenterX(), GetRect().GetHeight() * 0.8);
        return Rect.RectCenter(center, s);
    }

    private Rect GetUndoButtonRect() {
        Point bottomCenter = new Point(GetRect().GetCenterX(), GetRect().GetHeight() * 0.9);
        Size s = new Size(50, 30);
        return Rect.RectBottomCenter(bottomCenter, s);
    }

    private String GetDialogString(Phase p) {
        if (Game.Get().CostsBeingPaid())
            return "Pay " + Game.Get().GetCostBeingPaid().GetManaCost().ToString();
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

        super.Render(g);
    }

    @Override
    protected void Clicked(Click c) {
        if (Game.Get().CostsBeingPaid() || player1GraveyardButton.OwnsClick(c) || player2GraveyardButton.OwnsClick(c))
            super.Clicked(c);
        else if (!Game.Get().GetStack().HasEffects() && !Game.Get().CostsBeingPaid())
            Game.Get().AdvancePhase();
    }

    @Override
    protected ArrayList<View> GetSubviews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(player1GraveyardButton);
        views.add(player2GraveyardButton);
        if (Game.Get().CostsBeingPaid()) views.add(undoButton);
        return views;
    }
}
