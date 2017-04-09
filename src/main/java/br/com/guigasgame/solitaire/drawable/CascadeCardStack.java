package br.com.guigasgame.solitaire.drawable;

import java.util.Random;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

import br.com.guigasgame.solitaire.position.PositionComponent;
import br.com.guigasgame.solitaire.solitaire.card.CardManager;
import br.com.guigasgame.solitaire.solitaire.stack.SolitaireCardStack;

public class CascadeCardStack extends StackDrawable
{
	private PositionComponent drawingOffset;
	private PositionComponent center;
	private EmptyStackCardSprite emptyStackCardSprite;
	private PositionComponent proportion;
	private FloatRect visibleArea;

	public CascadeCardStack(SolitaireCardStack cardStack, PositionComponent windowSize, PositionComponent proportion, PositionComponent drawingOffset, boolean drawEmptyStack)
	{
		super(cardStack);
		this.proportion = proportion;
		this.visibleArea = new FloatRect(0, 0, windowSize.getX(), windowSize.getY());
		this.center = new PositionComponent(windowSize.getX() * proportion.getX(), windowSize.getY()* proportion.getY());
		cardStack.setCenter(center);
		this.drawingOffset = drawingOffset;
		readjustAllCards(drawEmptyStack);
	}

	private void readjustAllCards(boolean drawEmptyStack)
	{
		for (int i = 0; i < cards.size(); ++i)
		{
			cards.get(i).getDrawableCard().moveTo(getCardPosition(i));
		}
		if (drawEmptyStack)
		{
			emptyStackCardSprite = new EmptyStackCardSprite();
			emptyStackCardSprite.moveTo(center);
		}
	}

	public CascadeCardStack(SolitaireCardStack cardStack, PositionComponent windowSize, PositionComponent proportion, PositionComponent drawingOffset)
	{
		this(cardStack, windowSize, proportion, drawingOffset, false);
	}
	
	@Override
	public PositionComponent getCardPosition(int index)
	{
		CardDrawable card = cards.get(index).getDrawableCard();
		PositionComponent initial = new PositionComponent(center.getX(), center.getY());
		initial.add(new PositionComponent(
										drawingOffset.getX() * card.getSize().width * index, 
										drawingOffset.getY() * card.getSize().height * index));
		return initial;
	}

	@Override
	public void cardRemoved(CardManager card)
	{
		cards.remove(card);
	}

	@Override
	public void cardAdded(CardManager card)
	{
		cards.add(card);
		int index= cards.size() - 1;
		cards.get(index).getDrawableCard().moveTo(getCardPosition(index));
	}
	
	@Override
	public void draw(RenderTarget renderTarget)
	{
		if (cards.size() <= 1 && null != emptyStackCardSprite)
			emptyStackCardSprite.draw(renderTarget);	
		super.draw(renderTarget);
	}

	public void readjustToSize(FloatRect visibleArea)
	{
		this.visibleArea = visibleArea;
		this.center = new PositionComponent(visibleArea.width * proportion.getX(), visibleArea.height * proportion.getY());
		cardStack.setCenter(center);
		readjustAllCards(null != emptyStackCardSprite);
	}

	public void sendThemAllToRandomPositions()
	{
		float visibleAreaDiagonal = (float) Math.sqrt(visibleArea.width*visibleArea.width + visibleArea.height*visibleArea.height)/2;
		Random r = new Random();
		for (CardManager cardManager : cards)
		{
			double angle = r.nextDouble()*Math.PI*2;
			PositionComponent newPosition = new PositionComponent(Vector2f.mul(new Vector2f((float)Math.sin(angle), (float)Math.cos(angle)),
					visibleAreaDiagonal));
			cardManager.getDrawableCard().moveTo(newPosition );
		}
	}

}
