package br.edu.ifsp.pds.shadowstruggles.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Map;
import br.edu.ifsp.pds.shadowstruggles.scripts.DefaultAction;

public class MapTest {

	@Test
	public void testCardOnMap() {
		Map map = new Map("teste");
		Card card = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card.setDirection(1);
		map.addCard(card, 4, 1);
		assertEquals("Result:",true, map.cardOnMap(card, -1, 1));
	}

	@Test
	public void testEnemyBaseAttacked() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayerBaseAttacked() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEnemyCards() {
		fail("Not yet implemented");
	}

	@Test
	public void testNextAvailableLane() {
		fail("Not yet implemented");
	}

	@Test
	public void testNextAvailableTile() {
		fail("Not yet implemented");
	}

	@Test
	public void testLaneWithMoreEnemies() {
		fail("Not yet implemented");
	}

	@Test
	public void testLaneWithLessAllies() {
		fail("Not yet implemented");
	}

	@Test
	public void testLaneWithMoreEnemiesInvading() {
		fail("Not yet implemented");
	}

	@Test
	public void testTileClosestToBase() {
		fail("Not yet implemented");
	}

	@Test
	public void testTileFurthestFromBase() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTiles() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTiles() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

}
