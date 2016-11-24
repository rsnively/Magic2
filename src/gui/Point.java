package gui;

public class Point
{
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, double y) { this(x, (int) y); }
    public Point(double x, int y) { this((int) x, y); }
    public Point(double x, double y) { this((int) x, (int) y); }

    public int GetX() { return x; }
    public int GetY() { return y; }

    public Point Add(Point other) { return new Point(this.x + other.x, this.y + other.y); }
}