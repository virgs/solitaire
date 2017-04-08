package br.com.guigasgame.solitaire.solitaire.stack;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.FloatRect;
import org.jsfml.window.Mouse.Button;

import br.com.guigasgame.solitaire.card.Rank;
import br.com.guigasgame.solitaire.input.InputEvent;
import br.com.guigasgame.solitaire.input.InputEventType;
import br.com.guigasgame.solitaire.input.InputListener;
import br.com.guigasgame.solitaire.input.MouseEvent;
import br.com.guigasgame.solitaire.position.PositionComponent;
import br.com.guigasgame.solitaire.solitaire.card.CardManager;
import br.com.guigasgame.solitaire.solitaire.card.CardSolitaire;

public class TableauCardStack extends SolitaireCardStack implements InputListener
{
	
	private FloatRect stackArea;

	public TableauCardStack(List<CardManager> cards)
	{
		super(SolitaireCardStackType.tableau);
		
		cards.stream().forEach(card ->
		{
			addCard(card);
//			card.revealCard();
		});
		
		stackArea = cards.get(0).getDrawableCard().getSize();
		
		cards.get(cards.size() - 1).revealCard();
	}

	@Override
	public boolean canAddCards(List<CardManager> cards)
	{
		CardManager card = cards.get(0);
		if (cards.isEmpty())
		{
			return card.getCard().getRank() == Rank.king;
		}
		
		CardSolitaire cardAtTop = getSolitaireTop().getCard();
		if (card.getCard().getRank().getValue() == cardAtTop.getRank().getValue() - 1)
		{
			return card.getCard().getSuit().getSuitColor() != cardAtTop.getSuit().getSuitColor();
		}
		return false;
	}
	
	public void inputPressed(InputEvent inputValue)
	{
		List<CardManager> cardsToRemove = new ArrayList<>();
		boolean unselecting = false;
		for (int i = cards.size() - 1; i >= 0; --i)
		{
			CardManager cardManager = cards.get(i);
			boolean wasSelected = cardManager.getCard().isSelected();
			if (unselecting)
			{
				if (!wasSelected && !cardManager.getCard().isSelected())
					break;
				cardManager.unselectCard();
				cardsToRemove.add(0, cardManager);
			}
			else if (true)
			{
				cardManager.inputPressed(inputValue);
				if (cardManager.getCard().isSelected() && cardManager.hasReactedToInput())
				{
					selectFromIndexToTop(i);
					unselecting = true;
				}
				else if (!wasSelected && !cardManager.getCard().isSelected())
					continue;
				else if (null != transactionManager && cardManager.hasReactedToInput())
					cardsToRemove.add(0, cardManager);
			}
			cardManager.clearInputReaction();
		}
		if (inputValue.getInputEventType() == InputEventType.mouse)
		{
			MouseEvent mouseEvent = (MouseEvent) inputValue;
			if (stackArea.contains(mouseEvent.getPosition().x, mouseEvent.getPosition().y))
			{
				if (mouseEvent.getMouseButton() == Button.LEFT)
				{
					System.out.println("Stack clicked");
				}
			}
		}

			
		if (null != transactionManager && !cardsToRemove.isEmpty())
			transactionManager.removeCardToSelection(cardsToRemove);
	}

	private void selectFromIndexToTop(int initialIndex)
	{
		cards.stream().forEach(card -> System.out.print(card.getCard() + "; "));
		System.out.println();
		List<CardManager> cardsToAdd = new ArrayList<>();
		for (int i = initialIndex; i < cards.size(); ++i)
		{
			cards.get(i).selectCard();
			cardsToAdd.add(cards.get(i));
		}
		if (null != transactionManager)
			transactionManager.addCardToSelection(cardsToAdd);
	}

	public void inputReleased(InputEvent inputValue)
	{
		cards.stream().forEach(cardManager -> cardManager.inputReleased(inputValue));
	}
	
	@Override
	public void setCenter(PositionComponent center)
	{
		super.setCenter(center);
		stackArea = new FloatRect(center.getX() - stackArea.width/2, center.getY() - stackArea.height/2, stackArea.width, stackArea.height*4);
	}
}
