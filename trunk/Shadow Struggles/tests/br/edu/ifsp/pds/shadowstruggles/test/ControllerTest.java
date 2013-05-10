package br.edu.ifsp.pds.shadowstruggles.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.DefaultRules;

public class ControllerTest {

	@Test
	public void testBackCardClicked() {
		fail("Not yet implemented"); //TODO: Implementar teste.
		Card card = new Card("Teste", "CartaTeste", 10, "Descrição", 0, null);
		
	}

	@Test
	public void testHexagramClicked() {
		fail("Not yet implemented"); //TODO: Implementar teste.
	}

	@Test
	public void testHandCardClicked() {
		//fail("Not yet implemented"); //TODO: Implementar teste.
		Controller control = Controller.getInstance();
		Card card = new Card("Teste", "CartaTeste", 10, "Descrição", 0, null);
		BattlePlatform platform = new BattlePlatform(new Deck(), new Deck(), 
				new BattleMap(""), new DefaultRules());
		platform.addPlayerHandCard(card);
		platform.addPlayerHandCard(card);
		platform.addPlayerHandCard(card);
		platform.addPlayerHandCard(card);
		platform.addPlayerHandCard(card);
		control.handCardClicked(card, true);
		assertEquals(card, platform.getSelectedCard());
	}

	@Test
	public void testDeckClicked() {
		fail("Not yet implemented"); //TODO: Implementar teste.
	}

	@Test
	public void testTileChanged() {
		fail("Not yet implemented"); //TODO: Implementar teste.
	}

	@Test
	public void testPlayCard() {
		Card cartaTest = new Card("Teste", "CartaTeste", 10, "Descrição", 0,
				null);
		int laneTest = 10;
		int tileTest = 20;

		Controller control = Controller.getInstance();

		control.playCard(cartaTest, laneTest, tileTest);

	}

	@Test
	public void testAddCardToMap() {
		Card cartaTest = new Card("Teste", "CartaTeste", 10, "Descrição", 1,
				null);
		int laneTest = 10;
		int tileTest = 20;
		Controller control = Controller.getInstance();
		Image cardImage = new Image();

		control.addCardToMap(cartaTest, cardImage, tileTest, laneTest);
	}

	@Test
	public void testRemoveCard() {
		//fail("Not yet implemented"); //TODO: Implementar teste.
		Controller control = Controller.getInstance();
		Card card = new Card("Teste", "CartaTeste", 10, "Descrição", 0, null);
		BattlePlatform platform = new BattlePlatform(new Deck(), new Deck(), 
				new BattleMap(""), new DefaultRules());
		
		
		
		
	}

	@Test
	public void testVerifyValueChange() {
		Controller controlTest = Controller.getInstance();
		int value1 = 200;
		int valueMax = 300;

		int result = controlTest.verifyValueChange(value1, valueMax);

		assertEquals(value1, result);

	}

}
