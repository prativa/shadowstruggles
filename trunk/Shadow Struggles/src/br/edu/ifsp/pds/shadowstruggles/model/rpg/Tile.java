package br.edu.ifsp.pds.shadowstruggles.model.rpg;

public class Tile {
	private Character character;
	private Item item;
	
	public Tile() {
		// TODO Auto-generated constructor stub
	}
	
	public Character hasCharacter(){return character;}
	
	public Item hasItem(){return item;}
	
	public void removeCharacer(){
		this.character=null;
	}
	
	public void insertCharacter(Character character){
		this.character=character;
	}
	

}