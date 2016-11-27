package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainView extends View implements MouseListener {
    private static MainView instance;

    public static void Init(int x, int y, int w, int h) {
        instance = new MainView(x, y, w, h);
    }

    public static MainView Get() {
        return instance;
    }

    public static void Update() {
        if (instance != null) {
            instance.gameView.SetViews();
            instance.repaint();
        }
    }

    private GameView gameView;


    private MainView(int x, int y, int w, int h) {
        super(x, y, w, h);
        gameView = new GameView(GetGameViewRect());

        addMouseListener(this);
    }

    private Rect GetGameViewRect() {
        return Rect.RectTopLeft(GetRect().GetTopLeft(), new Size(GetRect().GetWidth(), GetRect().GetHeight() - 29));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.Clicked(new Click(new Point(e.getX(), e.getY())));
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}

    @Override
    protected ArrayList<View> GetSubviews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(gameView);
        return views;
    }
}
