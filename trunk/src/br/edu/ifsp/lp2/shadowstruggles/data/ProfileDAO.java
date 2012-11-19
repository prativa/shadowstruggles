package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.model.Profile;

import com.badlogic.gdx.utils.Array;


public class ProfileDAO {
	
	public static Profile getProfile(int id) {
		Profile profile = null;
		
		Array<Profile> profiles = new DataManager().retrieveProfiles();
		for(Profile p : profiles) {
			if(p.getId() == id)
				profile = p;
		}
		
		return profile;
	}
	
	public static Array<Profile> getProfiles() {
		return new DataManager().retrieveProfiles();
	}
	
	public static void createProfile(Profile profile) {
		new DataManager().writeProfile(profile);
	}
}
