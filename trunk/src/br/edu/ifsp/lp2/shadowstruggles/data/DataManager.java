package br.edu.ifsp.lp2.shadowstruggles.data;


import br.edu.ifsp.lp2.shadowstruggles.Profile;
import br.edu.ifsp.lp2.shadowstruggles.Scene;
import br.edu.ifsp.lp2.shadowstruggles.battle.Deck;
import br.edu.ifsp.lp2.shadowstruggles.battle.Effect;
import br.edu.ifsp.lp2.shadowstruggles.battle.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.battle.Trap;
import br.edu.ifsp.lp2.shadowstruggles.scripts.DefaultAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;

/**
 * The main class for dealing with I/O operations, including i18n. It uses
 * JSON as its data notation.
 */

public class DataManager {

	private Json json;

	private FileHandle gameSettings;
	private FileHandle profiles;
	private FileHandle scenes;
	private FileHandle decks;
	private FileHandle fighters;
	private FileHandle effects;
	private FileHandle traps;
	private FileHandle menuTextFile;

	private Array<Fighter> fighterList;
	private Array<Effect> effectList;
	private Array<Trap> trapList;
	private MenuText menuText;

	public DataManager() {
		this("en_us");
	}

	public DataManager(String language) {
		this.json = new Json();
		
		this.gameSettings = Gdx.files.internal("data/game_settings.json");
		this.profiles = Gdx.files.local("data/data_profiles.json");
		this.scenes = Gdx.files.internal("data/script/" + language + ".json");
		this.decks = Gdx.files.internal("data/decks.json");
		this.fighters = Gdx.files.internal("data/fighters_" + language + ".json");
		this.effects = Gdx.files.internal("data/effects_" + language + ".json");
		this.traps = Gdx.files.internal("data/traps_" + language + ".json");
		this.menuTextFile = Gdx.files.internal("data/menu/text_" + language + ".json");

		this.createFighters();
		this.createEffects();
		this.createTraps();
		this.createMenuText();
	}

	/**
	 * Populates the Array of fighters in the game with data
	 * from file, attributing to each fighter its respective battleAction.
	 * This method should be invoked only once in the entire application.
	 */
	//TODO: Popular a array com todos os lutadores do jogo.
	private void createFighters() {
		fighterList = new Array<Fighter>();
		Array<Fighter> retrievedFighters = retrieveFighters();
		
		for (Fighter f : retrievedFighters) {
			if (f.getName().equals("DR-002") || f.getName().equals("DR-004")) {
				f.action = new DefaultAction();
			}
			fighterList.add(f);
		}
	}

	/**
	 * Populates the Array of effects in the game with data
	 * from file, attributing to each effect its respective battleAction.
	 * This method should be invoked only once in the entire application.
	 */
	//TODO: Popular a array com todos os efeitos do jogo.
	private void createEffects() {
		effectList = new Array<Effect>();
		Array<Effect> retrievedEffects = retrieveEffects();
		
		for(Effect e : retrievedEffects) {
			
		}
	}

	/**
	 * Populates the Array of traps in the game with data
	 * from file, attributing to each trap its respective battleAction.
	 * This method should be invoked only once in the entire application.
	 */
	//TODO: Popular a array com todas as armadilhas do jogo.
	private void createTraps() {
		trapList = new Array<Trap>();
		Array<Trap> retrievedTraps = retrieveTraps();
		
		for(Trap t : retrievedTraps) {
			
		}
	}
	
	private void createMenuText() {
		if(this.menuTextFile.exists()) {
			menuText = json.fromJson(MenuText.class, menuTextFile);
		} else {
			menuText = new MenuText();
			json.toJson(menuText, menuTextFile);
		}
	}

	/***
	 * Gets the settings for the game from a file. If the configuration file
	 * ("data/game_settings.json") doesn't exist, it will create one.
	 * 
	 * @return A Settings object describing the game's configurations.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	public Settings retrieveSettings() throws SerializationException {
		Settings settings = null;

		if (this.gameSettings.exists()) {
			settings = json.fromJson(Settings.class, this.gameSettings);
		} else {
			settings = new Settings();
			json.toJson(settings, gameSettings);
		}

		return settings;
	}
	
	/***
	 * Gets the decks from a file. If the file
	 * ("data/decks.json") doesn't exist, it will create one.
	 * 
	 * @return An Array<Deck> object containing the players' possible decks.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	public Array<Deck> retrieveDecks() throws SerializationException {
		Array<Deck> deckList = new Array<Deck>();

		if (!this.decks.exists()) {
			deckList.add(new Deck());
			json.toJson(deckList, decks);
		} else {
			deckList.addAll(json.fromJson(Array.class, this.decks));
		}
		return deckList;
	}

	/***
	 * Gets the profiles from a file. If the file
	 * ("data/data_profiles.json") doesn't exist, it will create one.
	 * 
	 * @return An Array<Profile> object containing the saved profiles.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	public Array<Profile> retrieveProfiles() throws SerializationException {
		Array<Profile> profilesList = new Array<Profile>();

		if (!this.profiles.exists()) {
			profilesList.add(new Profile());
			json.toJson(profilesList, this.profiles);
		} else {
			profilesList.addAll((json.fromJson(Array.class, profiles)));
		}

		return profilesList;
	}
	
	/**
	 * Saves a profile to a file ("data/data_profiles.json").
	 * @param profile The profile to be saved.
	 * @throws SerializationException
	 * 			   An error while reading or writing an object.
	 */
	public void writeProfile(Profile profile) throws SerializationException {
		Array<Profile> allProfiles = this.retrieveProfiles();
		allProfiles.add(profile);
		json.toJson(allProfiles, this.profiles);
	}

	/***
	 * Gets the scenes from a file. If the file doesn't exist in the 
	 * data/script folder, it will create one.
	 * 
	 * @return An Array<Scene> object containing the scenes.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	public Array<Scene> retrieveScenes() throws SerializationException {
		Array<Scene> scenesList = new Array<Scene>();

		if (!this.scenes.exists()) {
			scenesList.add(new Scene());
			json.toJson(scenesList, this.scenes);
		} else {
			scenesList.addAll(json.fromJson(Array.class, this.scenes));
		}

		return scenesList;
	}

	public Array<Fighter> getFighterList() {
		return fighterList;
	}

	public Array<Effect> getEffectList() {
		return effectList;
	}

	public Array<Trap> getTrapList() {
		return trapList;
	}
	
	public MenuText getMenuText() {
		return menuText;
	}
	
	/**
	 * Gets all fighters from a file. This should only be invoked once, and by the object itself;
	 * other objects should use the {@link DataManager#getFighterList()} method instead.
	 * @return An Array<Fighter> object.
	 * @throws SerializationException
	 * 			   An error while reading or writing an object.
	 * @see DataManager#getFighterList().
	 */
	private Array<Fighter> retrieveFighters() throws SerializationException {
		Array<Fighter> retrievedFighters = new Array<Fighter>();

		if (!this.fighters.exists()) {
			json.toJson(retrievedFighters, this.fighters);
		} else {
			retrievedFighters.addAll(json.fromJson(Array.class, this.fighters));
		}
		return retrievedFighters;
	}

	/**
	 * Gets all effects from a file. This should only be invoked once, and by the object itself;
	 * other objects should use the {@link DataManager#getEffectList()} method instead.
	 * @return An Array<Effect> object.
	 * @throws SerializationException
	 * 			   An error while reading or writing an object.
	 * @see DataManager#getEffectList().
	 */
	private Array<Effect> retrieveEffects() throws SerializationException {
		Array<Effect> retrievedEffects = new Array<Effect>();

		if (!this.effects.exists()) {
			json.toJson(retrievedEffects, this.effects);
		} else {
			retrievedEffects.addAll(json.fromJson(Array.class, this.effects));
		}

		return retrievedEffects;
	}

	/**
	 * Gets all traps from a file. This should only be invoked once, and by the object itself;
	 * other objects should use the {@link DataManager#getTrapList()} method instead.
	 * @return An Array<Trap> object.
	 * @throws SerializationException
	 * 			   An error while reading or writing an object.
	 * @see DataManager#getTrapList().
	 */
	private Array<Trap> retrieveTraps() throws SerializationException {
		Array<Trap> retrievedTraps = new Array<Trap>();

		if (!this.traps.exists()) {
			json.toJson(retrievedTraps, this.traps);
		} else {
			retrievedTraps.addAll(json.fromJson(Array.class, this.traps));
		}

		return retrievedTraps;
	}

}
