package gui;

import game.Game;

import java.util.ArrayList;

public class GameView extends View
{
    private ControlPanelView controlPanel;
    private PlayerHandView playerHandView;
    private PhaseView phaseView;
    private BattlefieldView battlefieldView;
    private GraveyardView player1GraveyardView, player2GraveyardView;
    private boolean player1GraveyardVisible, player2GraveyardVisible;
    private ManaPoolView manaPoolView;
    private StackView stackView;
    private GameOverView gameOverView;

    public GameView(Rect r) {
        super(r);
        player1GraveyardVisible = false;
        player2GraveyardVisible = false;
        SetViews();
    }

    public void SetViews() {
        controlPanel = new ControlPanelView(GetControlPanelRect(), this);
        playerHandView = new PlayerHandView(GetPlayerHandRect(), Game.Get().GetPlayer1().GetHand());
        phaseView = new PhaseView(GetPhaseViewRect(), Game.Get().GetPhase());
        battlefieldView = new BattlefieldView(GetBattlefieldRect(), Game.Get().GetPlayer1().GetPermanents(), Game.Get().GetPlayer2().GetPermanents());
        player1GraveyardView = new GraveyardView(GetGraveyardRect(0), Game.Get().GetPlayer1().GetGraveyard());
        player2GraveyardView = new GraveyardView(GetGraveyardRect(player1GraveyardVisible ? 1 : 0), Game.Get().GetPlayer2().GetGraveyard());
        manaPoolView = new ManaPoolView(GetManaPoolRect(), Game.Get().GetPlayer1().GetManaPool());
        stackView = new StackView(GetStackViewRect(), Game.Get().GetStack());
        if (Game.Get().IsOver())
            gameOverView = new GameOverView(GetGameOverRect(), Game.Get().GetPlayer1().LostGame() ? false : true);
    }

    private Rect GetControlPanelRect() {
        return Rect.RectTopLeft(GetRect().GetTopLeft(), new Size(GetRect().GetWidth() * 0.16, GetRect().GetHeight()));
    }

    private Rect GetPlayerHandRect() {
        Point bottomRight = new Point(GetRect().GetRight(), GetRect().GetBottom());
        Size size = new Size(GetRect().GetWidth() - GetControlPanelRect().GetWidth(), GetRect().GetHeight() * 0.32);
        return Rect.RectBottomRight(bottomRight, size);
    }

    private Rect GetPhaseViewRect() {
        Point bottomRight = new Point(GetRect().GetRight(), GetPlayerHandRect().GetTop());
        Size size = new Size(GetRect().GetWidth() - GetControlPanelRect().GetWidth(), GetRect().GetHeight() * 0.04);
        return Rect.RectBottomRight(bottomRight, size);
    }

    private Rect GetBattlefieldRect() {
        Point topRight = new Point(GetRect().GetRight(), GetRect().GetTop());
        int width = GetRect().GetWidth() - GetControlPanelRect().GetWidth();
        if (player1GraveyardVisible) width -= GetGraveyardRect(0).GetWidth();
        if (player2GraveyardVisible) width -= GetGraveyardRect(1).GetWidth();

        Size size = new Size(width, GetRect().GetHeight() - GetPlayerHandRect().GetHeight() - GetPhaseViewRect().GetHeight());
        return Rect.RectTopRight(topRight, size);
    }

    private Rect GetGraveyardRect(int index) {
        Size s = new Size(GetRect().GetWidth() * 0.16, GetRect().GetHeight() - GetPhaseViewRect().GetHeight() - GetPlayerHandRect().GetHeight());
        Point topLeft = new Point(GetControlPanelRect().GetRight() + s.GetWidth() * index, GetRect().GetTop());
        return Rect.RectTopLeft(topLeft, s);
    }

    private Rect GetManaPoolRect() {
        Point bottomRight = new Point(GetRect().GetRight(), GetPhaseViewRect().GetTop());
        Size s = new Size((int) ((GetRect().GetWidth() - GetControlPanelRect().GetWidth()) * 0.1), (int) (GetRect().GetHeight() * 0.5));
        return Rect.RectBottomRight(bottomRight, s);
    }

    private Rect GetStackViewRect() {
        Point topRight = new Point(GetRect().GetRight(), GetRect().GetTop());
        Size size = new Size((int) (GetRect().GetWidth() * 0.25), (int) (GetRect().GetHeight() * 0.25));
        return Rect.RectTopRight(topRight, size);
    }

    private Rect GetGameOverRect() {
        Size s = new Size(200, 100);
        return Rect.RectCenter(GetRect().GetCenter(), s);
    }

    public boolean GetGraveyardVisible(int player) {
        if (player == 1)
            return player1GraveyardVisible;
        return player2GraveyardVisible;
    }

    public void SetGraveyardVisible(int player, boolean visible) {
        if (player == 1)
            player1GraveyardVisible = visible;
        else
            player2GraveyardVisible = visible;
    }

    @Override
    protected ArrayList<View> GetSubviews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(battlefieldView);
        views.add(playerHandView);
        views.add(phaseView);
        views.add(controlPanel);
        if (player1GraveyardVisible) views.add(player1GraveyardView);
        if (player2GraveyardVisible) views.add(player2GraveyardView);
        if (!Game.Get().GetPlayer1().GetManaPool().Empty()) views.add(manaPoolView);
        views.add(stackView);
        if (Game.Get().IsOver()) views.add(gameOverView);
        return views;
    }
}