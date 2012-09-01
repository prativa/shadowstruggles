package com.shadowstruggles.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.shadowstruggles.Profile;


public class ProfileDAO {
	private ObjectMap<String, Profile> profiles;
	public ProfileDAO() {
		this.profiles=new ObjectMap<String, Profile>();
	}
	
	public Profile getProfile(String key){
		return profiles.get(key);
	}
	
	private void init() {		

	}

	
	
}
