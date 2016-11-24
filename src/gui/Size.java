package gui;


public class Size {
    private int width;
    private int height;

    public Size(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public Size(double w, int h) { this((int) w, h); }
    public Size(int w, double h) { this(w, (int) h); }
    public Size(double w, double h) { this((int) w, (int) h); }

    public int GetWidth() { return width; }
    public int GetHeight() { return height; }
}
