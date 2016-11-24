package gui;

public class Rect
{
    private Point origin;
    private Size size;

    public static Rect RectTopLeft(Point topLeft, Size s) { return new Rect(topLeft.GetX(), topLeft.GetY(), s.GetWidth(), s.GetHeight()); }
    public static Rect RectTopRight(Point topRight, Size s) { return new Rect(topRight.GetX() - s.GetWidth(), topRight.GetY(), s.GetWidth(), s.GetHeight()); }
    public static Rect RectTopCenter(Point topCenter, Size s) { return new Rect(topCenter.GetX() - s.GetWidth() / 2, topCenter.GetY(), s.GetWidth(), s.GetHeight()); }
    public static Rect RectCenter(Point center, Size s) { return new Rect(center.GetX() - s.GetWidth() / 2, center.GetY() - s.GetHeight() / 2, s.GetWidth(), s.GetHeight()); }
    public static Rect RectBottomLeft(Point bottomLeft, Size s) { return new Rect(bottomLeft.GetX(), bottomLeft.GetY() - s.GetHeight(), s.GetWidth(), s.GetHeight()); }
    public static Rect RectBottomCenter(Point bottomCenter, Size s) { return new Rect(bottomCenter.GetX() - s.GetWidth() / 2, bottomCenter.GetY() - s.GetHeight(), s.GetWidth(), s.GetHeight()); }
    public static Rect RectBottomRight(Point bottomRight, Size s) { return new Rect(bottomRight.GetX() - s.GetWidth(), bottomRight.GetY() - s.GetHeight(), s.GetWidth(), s.GetHeight()); }

    public Rect(int x, int y, int w, int h) {
        this.origin = new Point(x, y);
        this.size = new Size(w, h);
    }

    public Size GetSize() { return size; }
    public int GetWidth() { return size.GetWidth(); }
    public int GetHeight() { return size.GetHeight(); }
    public int GetLeft() { return origin.GetX(); }
    public int GetRight() { return origin.GetX() + GetWidth(); }
    public int GetTop() { return origin.GetY(); }
    public int GetBottom() { return GetTop() + GetHeight(); }
    public int GetCenterY() { return GetTop() + GetHeight() / 2; }
    public int GetCenterX() { return GetLeft() + GetWidth() / 2; }

    public Point GetTopLeft() { return new Point(GetLeft(), GetTop()); }
    public Point GetTopCenter() { return new Point(GetCenterX(), GetTop()); }
    public Point GetTopRight() { return new Point(GetRight(), GetTop()); }
    public Point GetLeftCenter() { return new Point(GetLeft(), GetCenterY()); }
    public Point GetCenter() { return new Point(GetCenterX(), GetCenterY()); }
    public Point GetRightCenter() { return new Point(GetRight(), GetCenterY()); }
    public Point GetBottomLeft() { return new Point(GetLeft(), GetBottom()); }
    public Point GetBottomCenter() { return new Point(GetCenterX(), GetBottom()); }
    public Point GetBottomRight() { return new Point(GetRight(), GetBottom()); }

    public boolean Contains(Point p) {
        return p.GetX() > GetLeft()
                && p.GetX() < GetRight()
                && p.GetY() > GetTop()
                && p.GetY() < GetBottom();
    }
}