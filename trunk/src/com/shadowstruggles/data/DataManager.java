package com.shadowstruggles.data;



import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.shadowstruggles.Profile;
import com.shadowstruggles.battle.Card;

public class DataManager implements Serializable{
	private Json json;	
	private CardDAO cardDAO;
	private ProfileDAO profileDAO;
	private FileHandle profiles;
	private FileHandle cards;
	
	public DataManager() {
		this.json=new Json();		
		this.profiles=Gdx.files.local("data/profiles.json");
		this.cards=Gdx.files.local("data/cards.json");
		
		
	}
	
	
	//Executar apenas quando for gerar o arquivo, ou seja, 1 vez na vida
	//private void initJSON() {				
		//json.toJson(cardDAO, Gdx.files.local("data/cards.json"));
	//}	
	
	
	public Card getCard(String key) {		
		return cardDAO.getCard(key);
	}


	@Override
	public void write(Json json) {
		json.toJson(cardDAO, cards);
		json.toJson(profileDAO, profiles);
		
	}


	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		this.cardDAO = json.fromJson(CardDAO.class, cards);		
		this.profileDAO= json.fromJson(ProfileDAO.class, profiles);
	}
	
	//public Profile getProfile(String key){
	//	return profileDAO.getProfile(key);
	//}

}
