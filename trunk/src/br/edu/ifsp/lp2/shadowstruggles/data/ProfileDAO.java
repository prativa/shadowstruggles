package br.edu.ifsp.lp2.shadowstruggles.data;

import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.lp2.shadowstruggles.model.Profile;


/**
 * This class manages the interpretation of a profile from the DataManager
 */


public class ProfileDAO {
	
	public static Profile getProfile(int id, DataManager manager) {
		Profile profile = null;

		Array<Profile> profiles = manager.retrieveProfiles();
		for (Profile p : profiles) {
			if (p.getId() == id)
				profile = p;
		}

		return profile;
	}

	public static Array<Profile> getProfiles(DataManager manager) {
		Array<Profile> orderedProfiles = manager.retrieveProfiles();
		orderedProfiles.sort();
		return orderedProfiles;
	}

	public static void createProfile(Profile profile, DataManager manager) {
		manager.writeProfile(profile);
	}
}
