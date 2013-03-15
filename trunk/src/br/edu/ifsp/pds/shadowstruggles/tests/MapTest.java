package br.edu.ifsp.pds.shadowstruggles.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.model.Card;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
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
		Map map = new Map("teste");
		Fighter fighter = new Fighter("DR002", "DR002", 15, "", 14, new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter.setDirection(1);
		map.addCard(fighter, 40, 1);
		assertEquals("Result:",1, map.enemyBaseAttacked());
	}

	@Test
	public void testPlayerBaseAttacked() {
		Map map = new Map("teste");
		Fighter fighter = new Fighter("DR002", "DR002", 15, "", 14, new DefaultAction(), 15, 15, 15.0f, 1, false, "", 0, null);
		fighter.setDirection(-1);
		map.addCard(fighter, 1, 1);
		assertEquals("Result:",1, map.enemyBaseAttacked());
	}

	@Test
	public void testNextAvailableLane() {
		Map map = new Map("teste");
		Card card1 = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card1.setDirection(1);
		map.addCard(card1, 1, 0);
		Card card2 = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card2.setDirection(1);
		map.addCard(card2, 2, 0);
		Card card3 = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card3.setDirection(1);
		map.addCard(card3, 3, 0);
		Card card4 = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card4.setDirection(1);
		map.addCard(card4, 4, 0);
		Card card5 = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card5.setDirection(1);
		map.addCard(card5, 5, 0);
		assertEquals("Result:", 1, map.nextAvailableLane(1));
	}

	@Test
	public void testNextAvailableTile() {
		Map map = new Map("teste");
		Card card1 = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card1.setDirection(1);
		map.addCard(card1, 0, 0);
		Card card2 = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card2.setDirection(1);
		map.addCard(card2, 1, 0);
		Card card3 = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card3.setDirection(1);
		map.addCard(card3, 2, 0);
		Card card4 = new Card("DR-002", "DR-002", 15, "description", 100, new DefaultAction());
		card4.setDirection(1);
		map.addCard(card4, 3, 0);
		assertEquals("Result:", 4, map.nextAvailableTile(0,1));
		}

	@Test
	public void testLaneWithMoreEnemies() {
//		assertEquals("Result:", aaa, aaa);
		fail("Not yet implemented");
		}

	@Test
	public void testLaneWithLessAllies() {
//		assertEquals("Result:", aaa, aaa);
		fail("Not yet implemented");
		}

	@Test
	public void testLaneWithMoreEnemiesInvading() {
//		assertEquals("Result:", aaa, aaa);
		fail("Not yet implemented");
		}

	@Test
	public void testTileClosestToBase() {
//		assertEquals("Result:", aaa, aaa);
		fail("Not yet implemented");
		}

	@Test
	public void testTileFurthestFromBase() {
//		assertEquals("Result:", aaa, aaa);
		fail("Not yet implemented");
		}

	@Test
	public void testAddCard() {
//		assertEquals("Result:", aaa, aaa);
		fail("Not yet implemented");
	}

}
