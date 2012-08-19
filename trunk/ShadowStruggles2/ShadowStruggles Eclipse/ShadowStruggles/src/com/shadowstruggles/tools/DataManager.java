package com.shadowstruggles.tools;



import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.utils.Json;
import com.shadowstruggles.Profile;
import com.shadowstruggles.battle.Card;

public class DataManager {
	private Json json;	
	private CardDAO cardDAO;
	//private ProfileDAO profileDAO;
	
	public DataManager() {
		this.json=new Json();		
		//this.profileDAO= json.fromJson(ProfileDAO.class, Gdx.files.local("data/profiles.json"));
		this.cardDAO = json.fromJson(CardDAO.class, Gdx.files.local("data/cards.json"));		
		
	}
	
	
	//Executar apenas quando for gerar o arquivo, ou seja, 1 vez na vida
	//private void initJSON() {				
		//json.toJson(cardDAO, Gdx.files.local("data/cards.json"));
	//}	
	
	
	public Card getCard(String key) {		
		return cardDAO.getCard(key);
	}
	
	//public Profile getProfile(String key){
	//	return profileDAO.getProfile(key);
	//}

}
