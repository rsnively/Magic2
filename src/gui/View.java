package gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class View extends JPanel
{
    private Rect rect;

    public View(Rect r) {
        this.rect = r;
    }

    public View(int x, int y, int w, int h) {
        this(new Rect(x, y, w, h));
    }

    public Rect GetRect() {
        return rect;
    }

    public void SetRect(Rect r) {
        this.rect = r;
    }

    protected void MakeDirty() {
        MainView.Update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Render(g2d);
    }

    protected void Render(Graphics2D g) {
        for (View v : GetSubviews())
            v.Render(g);
    }

    protected boolean OwnsClick(Click c) { return GetRect().Contains(c.GetPoint()); }

    protected void Clicked(Click c) {
        for (int i = GetSubviews().size() - 1; i >= 0; i--) {
            if (GetSubviews().get(i).OwnsClick(c)) {
                GetSubviews().get(i).Clicked(c);
                return;
            }
        }
    }

    protected void SetBackground(Graphics2D g, Color c) {
        g.setColor(c);
        g.fillRect(GetRect().GetLeft(), GetRect().GetTop(), GetRect().GetWidth(), GetRect().GetHeight());
    }

    protected void DrawBorder(Graphics2D g, Color c) {
        g.setColor(c);
        g.drawRect(GetRect().GetLeft(), GetRect().GetTop(), GetRect().GetWidth(), GetRect().GetHeight());
    }

    protected void DrawStringCentered(Graphics2D g, String s, Rect r) {
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D bounds = fm.getStringBounds(s, g);
        int x = r.GetLeft() + (r.GetWidth() - (int) bounds.getWidth()) / 2;
        int y = r.GetTop() + (r.GetHeight() - (int) bounds.getHeight()) / 2 + fm.getAscent();
        g.drawString(s, x, y);
    }

    protected void DrawStringCentered(Graphics2D g, String s) {
        DrawStringCentered(g, s, GetRect());
    }

    protected void DrawStringInBounds(Graphics2D g, String s, Rect bounds, boolean lineWrap) {
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D unbounded = fm.getStringBounds(s, g);

        double widthRatio = bounds.GetWidth() / unbounded.getWidth();
        if (widthRatio < 1) {
            boolean willLineWrap = lineWrap && bounds.GetHeight() > unbounded.getHeight() * 2;
            int splitIndex = (int) (s.length() * widthRatio);
            if (!willLineWrap) splitIndex -= 3;

            String tail = s.substring(splitIndex);
            s = s.substring(0, splitIndex);
            if (!willLineWrap) s += "...";

            if (willLineWrap) {
                Rect newBounds = Rect.RectTopLeft(new Point(bounds.GetLeft(), bounds.GetTop() + unbounded.getHeight()),
                                                  new Size(bounds.GetWidth(), bounds.GetHeight() - unbounded.getHeight()));
                DrawStringInBounds(g, tail, newBounds, lineWrap);
            }
        }
        g.drawString(s, bounds.GetLeft(), bounds.GetTop() + (int) unbounded.getHeight());
    }

    protected ArrayList<View> GetSubviews() {
        return new ArrayList<>();
    }
}