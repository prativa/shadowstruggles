package br.edu.ifsp.lp2.shadowstruggles.battle;

import com.badlogic.gdx.utils.Array;

/**
 * A Map object is a three-dimensional Array containing the cards
 * placed on the field. A card is inside an Array of cards, which is
 * inside a tile, which is inside an Array of tiles. Thus, it is possible
 * to have multiple cards on each tile.
 */

public class Map {
	private Array<Array<Array<Card>>> tiles;
	private String name;

	public Map(String name) {
		this.name = name;
		this.tiles = new Array<Array<Array<Card>>>();
		for (int i = 0; i < 50; i++) {
			tiles.add(new Array<Array<Card>>());
			for (int j = 0; j < 4; j++) {
				tiles.get(i).add(new Array<Card>());
			}
		}

	}

	public Array<Array<Array<Card>>> getTiles() {
		return tiles;
	}

	public void setTiles(Array<Array<Array<Card>>> tiles) {
		this.tiles = tiles;
	}

	public void addCard(Card card, int tile, int lane) {

		tiles.get(tile).get(lane).add(card);
	}

	public String getName() {
		return name;
	}

}
