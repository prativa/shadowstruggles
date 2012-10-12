package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.battle.Card;
import br.edu.ifsp.lp2.shadowstruggles.battle.Deck;
import br.edu.ifsp.lp2.shadowstruggles.battle.Effect;
import br.edu.ifsp.lp2.shadowstruggles.battle.Fighter;

import com.badlogic.gdx.utils.Array;

public class DeckDAO {

	public static Deck getDeck(String key) {
		Deck deck = null;

		Array<Deck> decks = new DataManager().retrieveDecks();
		for (Deck d : decks) {
			if (d.getName().equals(key))
				deck = d;
		}

		Array<Card> copiaCards = new Array<Card>();

		for (Card card : deck.getCards()) {

			if (card.getClass().equals(Fighter.class))
				card = FighterDAO.getFighter(card.getName());
			else if (card.getClass().equals(Effect.class))
				card = EffectDAO.getEffect(card.getName());
			copiaCards.add(card);
		}

		deck.setCards(copiaCards);

		return deck;
	}

	public static Array<Deck> getDecks() {
		return new DataManager().retrieveDecks();
	}
}
