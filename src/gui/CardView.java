package gui;

import game.Card;
import game.Game;
import game.Phase;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class CardView extends View {

    private Card card;

    public CardView(Rect r, Card c) {
        super(r);
        this.card = c;
    }

    public Card GetCard() { return card; }

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

        SetBackground(g, Color.GRAY);
        DrawBorder(g, Color.BLACK);

        g.setColor(Color.BLACK);
        g.drawString(card.GetName(), GetRect().GetLeft(), GetRect().GetTop()+12);
        if (!card.IsLand())
            g.drawString(card.GetCost().GetMana().ToString(), GetRect().GetRight() - 20, GetRect().GetTop() + 12);

        Image art = new ImageIcon(card.GetCardArtImageFile()).getImage();
        g.drawImage(art, GetRect().GetLeft() + (GetRect().GetWidth() - art.getWidth(null)) / 2 , GetRect().GetTop() + 20, null);

        if (card.IsCreature())
            g.drawString(Integer.toString(card.GetPower()) + "/" + Integer.toString(card.GetToughness()), GetRect().GetRight() - 25, GetRect().GetBottom() - 3);

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
