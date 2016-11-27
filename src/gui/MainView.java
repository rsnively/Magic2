package gui;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainView extends View implements MouseListener, ComponentListener {
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
        addComponentListener(this);
    }

    private Rect GetGameViewRect() {
        return GetRect();
    }

    // MouseListener overrides
    @Override
    public void mousePressed(MouseEvent e) {
        super.Clicked(new Click(new Point(e.getX(), e.getY())));
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}

    // ComponentListener overrides
    @Override public void componentResized(ComponentEvent e) {
        Dimension newSize = e.getComponent().getBounds().getSize();
        SetRect(Rect.RectTopLeft(GetRect().GetTopLeft(), new Size(newSize.getWidth(), newSize.getHeight())));
        gameView.SetRect(GetGameViewRect());
    }

    @Override public void componentHidden(ComponentEvent e) {}
    @Override public void componentMoved(ComponentEvent e) {}
    @Override public void componentShown(ComponentEvent e) {}


    @Override
    protected ArrayList<View> GetSubviews() {
        ArrayList<View> views = new ArrayList<>();
        views.add(gameView);
        return views;
    }
}
