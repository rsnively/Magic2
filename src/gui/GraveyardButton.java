package gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class GraveyardButton extends View {

    private GameView gameView;
    private int player;

    public GraveyardButton(Rect r, int player, GameView gameView) {
        super(r);
        this.gameView = gameView;
        this.player = player;
    }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.BLACK);
        g.setColor(Color.WHITE);
        DrawStringCentered(g, "View Player " + ((player == 1) ? "1" : "2") + " Graveyard");
    }

    @Override
    protected void Clicked(Click c) {
        gameView.SetGraveyardVisible(player, !gameView.GetGraveyardVisible(player));
        MakeDirty();
    }
}
