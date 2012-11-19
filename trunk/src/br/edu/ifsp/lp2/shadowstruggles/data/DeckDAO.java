package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.model.Deck;
import br.edu.ifsp.lp2.shadowstruggles.model.Effect;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;

import com.badlogic.gdx.utils.Array;

public class DeckDAO {

	public static Deck getDeck(String key, DataManager manager) {
		Deck deck = new Deck();

		Array<Deck> decks = manager.getDecksList();
		for (Deck d : decks) {
			if (d.getName().equals(key)) {
				/* This makes a deep copy of the deck so that similar decks
				don't share the same memory blocks. */
				deck.getCards().addAll(d.getCards());
				deck.setDeckImage(d.getDeckImage());
				deck.setMaxCapacity(d.getMaxCapacity());
				deck.setMinCapacity(d.getMinCapacity());
				deck.setName(d.getName());
			}
		}

		Array<Card> copiaCards = new Array<Card>();

		for (Card card : deck.getCards()) {

			if (card.getClass().equals(Fighter.class)) {
				card = FighterDAO.getFighter(card.getName(), manager);
				card.setAction(FighterDAO.getFighter(card.getName(), manager)
						.getAction());
			} else if (card.getClass().equals(Effect.class))
				card = EffectDAO.getEffect(card.getName(), manager);
			copiaCards.add(card);
		}

		deck.setCards(copiaCards);

		return deck;
	}

	public static Array<Deck> getDecks(DataManager manager) {
		return manager.getDecksList();
	}
}
