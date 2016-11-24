package gui;

import game.Effect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class EffectView extends View {

    private Effect effect;

    public EffectView(Rect r, Effect e) {
        super(r);
        this.effect = e;
    }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.MAGENTA);
        DrawBorder(g, Color.BLACK);

        g.setColor(Color.BLACK);
        g.drawString(effect.GetEffectDescription(), GetRect().GetLeft(), GetRect().GetTop()+12);

        Image image = new ImageIcon(effect.GetEffectImageFile()).getImage();
        double imageWidth = GetRect().GetWidth() * 0.95;
        double imageHeight = imageWidth * CardView.WHRatio;
        double imageX = GetRect().GetLeft() + (GetRect().GetWidth() - imageWidth) / 2;
        double imageY = GetRect().GetTop() + GetRect().GetHeight() * 0.1;
        g.drawImage(image, (int)imageX, (int)imageY, (int)imageWidth, (int)imageHeight,null);    }
}
