package com.shadowstruggles;

import com.shadowstruggles.battle.BattleLogic;
import com.shadowstruggles.battle.BattlePlatform;
import com.shadowstruggles.battle.Fighter;
import com.shadowstruggles.battle.Map;
import com.shadowstruggles.object2d.HandCard;
import com.shadowstruggles.screens.BaseScreen;

public class Controller {
	private BaseScreen currentscreen;
	private Map map;
	private BattlePlatform platform;
	private BattleLogic logic;
	
	public Controller() {
		// TODO Auto-generated constructor stub
	}
	
	public void mapClicked(float x, float y) {	
		
		if(platform.getSelectedCard()!=null){
			HandCard hc = platform.getSelectedCard();
			switch(hc.getType()){
			case 1:map.addCard(new Fighter(logic, 1, 1,hc.getSide() , hc.getName()), 1, 1);
			}
		}
	}
	
	public void setLogic(BattleLogic logic) {
		this.logic = logic;
		this.platform=logic.getPlatform();
	}
	
	public void handCardClicked(HandCard handCard, boolean isSelected) {
		if(isSelected)platform.setSelectedCard(handCard);
	}
	
	
	
	public void initMap(){
		this.map= new Map();
	}
	
	public void deckClicked(){
		
	}
	
	
	public void setCurrentscreen(BaseScreen currentscreen) {
		this.currentscreen = currentscreen;
	}
	
	
}
