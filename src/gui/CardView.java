package gui;

import game.Card;
import game.Game;
import game.Mana;
import game.Phase;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class CardView extends View {

    public static final double WHRatio = 0.71;

    private Card card;

    public CardView(Rect r, Card c) {
        super(r);
        this.card = c;
    }

    public Card GetCard() { return card; }

    private Color GetBackgroundColor() {
        if (card.Is(Mana.Color.White) || card.Is(Card.Subtype.Plains)) return Color.WHITE;
        if (card.Is(Mana.Color.Blue) || card.Is(Card.Subtype.Island)) return Color.CYAN.darker();
        if (card.Is(Mana.Color.Black) || card.Is(Card.Subtype.Swamp)) return Color.GRAY;
        if (card.Is(Mana.Color.Red) || card.Is(Card.Subtype.Mountain)) return Color.RED.darker();
        if (card.Is(Mana.Color.Green) || card.Is(Card.Subtype.Forest)) return Color.GREEN.darker();
        return Color.GRAY.brighter().brighter();
    }

    private String GetTypelineString() {
        String typeString = "";
        for (Card.Supertype t : card.GetSupertypes())
            typeString += t.toString() + " ";
        for (Card.Type t : card.GetTypes())
            typeString += t.toString() + " ";
        if (!card.GetSubtypes().isEmpty())
            typeString += "- ";
        for (Card.Subtype t : card.GetSubtypes())
            typeString += t.toString() + " ";
        return typeString;
    }

    @Override
    protected void Render(Graphics2D g) {
        if (card.CanPlay() || card.CanAttack()) {
            g.setColor(Color.ORANGE.darker());
            g.fillRect(GetRect().GetLeft() - 1, GetRect().GetTop() - 1, GetRect().GetWidth() + 3, GetRect().GetHeight() + 3);
        }
        if (card.IsAttacking()) {
            g.setColor(Color.RED);
            g.fillRect(GetRect().GetLeft() - 1, GetRect().GetTop() - 1, GetRect().GetWidth() + 3, GetRect().GetHeight() + 3);
        }

        SetBackground(g, GetBackgroundColor());
        DrawBorder(g, Color.BLACK);

        g.setColor(Color.BLACK);
        g.drawString(card.GetName(), GetRect().GetLeft() + 5, GetRect().GetTop()+12);
        if (!card.IsLand())
            g.drawString(card.GetCost().GetMana().ToString(), GetRect().GetRight() - 20, GetRect().GetTop() + 12);

        Image art = new ImageIcon(card.GetCardArtImageFile()).getImage();
        double imageWidth = GetRect().GetWidth() * 0.95;
        double imageHeight = imageWidth * CardView.WHRatio;
        double imageX = GetRect().GetLeft() + (GetRect().GetWidth() - imageWidth) / 2;
        double imageY = GetRect().GetTop() + GetRect().GetHeight() * 0.05;
        g.drawImage(art, (int)imageX, (int)imageY, (int)imageWidth, (int)imageHeight,null);

        g.drawString(GetTypelineString(), GetRect().GetLeft() + 5,(int) (imageY + imageHeight + 15));

        if (card.IsCreature())
            g.drawString(Integer.toString(card.GetPower()) + "/" + Integer.toString(card.GetToughness()), GetRect().GetRight() - 25, GetRect().GetBottom() - 20);

    }

    @Override
    protected void Clicked(Click c) {
        if (card.CanPlay() && card.GetOwner().CanAfford(card)) {
            Game.Get().PlayCard(card);
        }
        else if (card.CanActivate()) {
            // todo popup for multiple abilities
            card.ActivateAbility(0);
        }
        else if (card.CanAttack()) {
            card.SetAttacking(true);
            MakeDirty();
        }
        else if (card.IsAttacking() && Game.Get().GetPhase().GetName() == Phase.Name.Attack) {
            card.SetAttacking(false);
            MakeDirty();
        }
    }
}
