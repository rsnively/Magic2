package gui;

public class Point
{
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int GetX() { return x; }
    public int GetY() { return y; }

    public Point Add(Point other) { return new Point(this.x + other.x, this.y + other.y); }
}