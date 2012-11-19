package br.edu.ifsp.lp2.shadowstruggles.data;

import br.edu.ifsp.lp2.shadowstruggles.model.Deck;
import br.edu.ifsp.lp2.shadowstruggles.model.Effect;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.model.Profile;
import br.edu.ifsp.lp2.shadowstruggles.model.Scene;
import br.edu.ifsp.lp2.shadowstruggles.model.Trap;
import br.edu.ifsp.lp2.shadowstruggles.scripts.DefaultAction;
import br.edu.ifsp.lp2.shadowstruggles.scripts.MineralogyAction;
import br.edu.ifsp.lp2.shadowstruggles.scripts.RecoveryAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SerializationException;

/**
 * The main class for dealing with I/O operations, including i18n. It uses JSON
 * as its data notation.
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
	private Settings settings;
	private Array<Deck> decksList;
	private Array<Scene> scenesList;
	private Languages languages;

	private MenuText menuText;

	public DataManager() {
		this("en_us");
	}

	/**
	 * The constructor instantiates the FileHandle objects and retrieves all
	 * informations from the files, so that most of the processing can happen
	 * here. There should be only one instance of DataManager for the entire
	 * application.
	 * 
	 * @param language
	 *            The language to write/recover the files from. The currently
	 *            supported languages are en_us and pt_br.
	 */
	public DataManager(String language) {
		this.json = new Json();

		this.gameSettings = Gdx.files.internal("data/game_settings.json");
		this.profiles = Gdx.files.local("data/data_profiles.json");
		
		this.languages=json.fromJson(Languages.class, Gdx.files.internal("data/menu/languages.json"));
		this.decks = Gdx.files.internal("data/decks.json");
		this.createDecks();
		
		this.createSettings();
		
		this.changeLanguage(language);
	}
	
	public void changeLanguage(String language){
		this.scenes = Gdx.files.internal("data/script/" + language + ".json");
		this.fighters = Gdx.files.internal("data/fighters_" + language
				+ ".json");
		this.effects = Gdx.files.internal("data/effects_" + language + ".json");
		this.traps = Gdx.files.internal("data/traps_" + language + ".json");
		this.menuTextFile = Gdx.files.internal("data/menu/text_" + language
				+ ".json");
		
		this.createFighters();
		this.createEffects();
		this.createTraps();
		this.createMenuText();
		this.createScenes();
	}

	/**
	 * Populates the Array of fighters in the game with data from file,
	 * attributing to each fighter its respective battleAction. This method
	 * should be invoked only once in the entire application.
	 */
	// TODO: Popular a array com todos os lutadores do jogo.
	private void createFighters() {
		fighterList = new Array<Fighter>();
		Array<Fighter> retrievedFighters = retrieveFighters();

		for (Fighter f : retrievedFighters) {
			if (f.getName().equals("DR-002") || 
					f.getName().equals("DR-004")||
					f.getName().equals("DR-003")||
					f.getName().equals("DR-000")||
					f.getName().equals("Broomy")||
					f.getName().equals("Golem")||
					f.getName().equals("Scrap Boy")||
					f.getName().equals("Garoto Entulho")||
					f.getName().equals("RD-002")||
					f.getName().equals("RD-004")||
					f.getName().equals("RD-003")||
					f.getName().equals("RD-000")) {
				f.action = new DefaultAction();
			}
			fighterList.add(f);
		}
	}

	/**
	 * Populates the Array of effects in the game with data from file,
	 * attributing to each effect its respective battleAction. This method
	 * should be invoked only once in the entire application.
	 */
	// TODO: Popular a array com todos os efeitos do jogo.
	private void createEffects() {
		effectList = new Array<Effect>();
		Array<Effect> retrievedEffects = retrieveEffects();

		for (Effect effect : retrievedEffects) {
			if(effect.getName().equals("Reconnect Circuits")||effect.getName().equals("Reconectar Circuitos")){
				effect.action=new RecoveryAction();
			}else if(effect.getName().equals("Mineralogy")||effect.getName().equals("Mineralogia")){
				effect.action=new MineralogyAction();
			}
			effectList.add(effect);
		}
	}

	/**
	 * Populates the Array of traps in the game with data from file, attributing
	 * to each trap its respective battleAction. This method should be invoked
	 * only once in the entire application.
	 */
	// TODO: Popular a array com todas as armadilhas do jogo.
	private void createTraps() {
		trapList = new Array<Trap>();
		Array<Trap> retrievedTraps = retrieveTraps();

		for (Trap trap : retrievedTraps) {
			trapList.add(trap);
		}
	}

	private void createMenuText() throws SerializationException {
		if (this.menuTextFile.exists()) {
			menuText = json.fromJson(MenuText.class, menuTextFile);
		} else {
			menuText = new MenuText();
		}
	}

	/***
	 * Gets the settings for the game from a file and storages them in the
	 * variable settings.
	 * 
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	private void createSettings() throws SerializationException {

		if (this.gameSettings.exists()) {
			settings = json.fromJson(Settings.class, this.gameSettings);
		} else {
			settings = new Settings();
		}
	}

	/***
	 * Gets the decks from a file.
	 * 
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	private void createDecks() throws SerializationException {

		decksList = new Array<Deck>();

		if (!this.decks.exists()) {
			decksList.add(new Deck());
		} else {
			decksList.addAll(json.fromJson(Array.class, this.decks));
		}
	}
	
	/***
	 * Gets the scenes from a file.
	 * 
	 * @throws SerializationException
	 *             An error while reading an object.
	 */
	private void createScenes() throws SerializationException {
		scenesList = new Array<Scene>();

		if (!this.scenes.exists()) {
			scenesList.add(new Scene());
		} else {
			scenesList.addAll(json.fromJson(Array.class, this.scenes));
		}

	}

	/***
	 * Gets the profiles from a file. If the file doesn't exist, it will create
	 * one. Since profiles is the only dynamic data, that is, it is possible to
	 * modify and create profiles throughout the game, this method may be called
	 * multiple times.
	 * 
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
	 * 
	 * @param profile
	 *            The profile to be saved.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 */
	public void writeProfile(Profile newProfile) throws SerializationException {
		Array<Profile> allProfiles;
		try {
			allProfiles = this.retrieveProfiles();
		} catch (Exception ew) {
			allProfiles = new Array<Profile>();
		}
		for(Profile profile: allProfiles){
			if(profile.getId()==newProfile.getId())allProfiles.removeValue(profile, true);
		}
		allProfiles.add(newProfile);
		json.toJson(allProfiles, this.profiles);
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

	public Settings getSettings() {
		return settings;
	}

	public Array<Deck> getDecksList() {
		return decksList;
	}

	public Array<Scene> getScenesList() {
		return scenesList;
	}

	public ObjectMap<String,String> getLanguages(){
		return languages.getLanguages();
	}
	
	public boolean profileExists(){
		return this.profiles.exists();
	}
	/**
	 * Gets all fighters from a file. This should only be invoked once, and by
	 * the object itself; other objects should use the
	 * {@link DataManager#getFighterList()} method instead.
	 * 
	 * @return An Array<Fighter> object.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 * @see DataManager#getFighterList().
	 */
	private Array<Fighter> retrieveFighters() throws SerializationException {
		Array<Fighter> retrievedFighters = new Array<Fighter>();

		if (this.fighters.exists()) {
			retrievedFighters.addAll(json.fromJson(Array.class, this.fighters));
		}

		return retrievedFighters;
	}

	/**
	 * Gets all effects from a file. This should only be invoked once, and by
	 * the object itself; other objects should use the
	 * {@link DataManager#getEffectList()} method instead.
	 * 
	 * @return An Array<Effect> object.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 * @see DataManager#getEffectList().
	 */
	public Array<Effect> retrieveEffects() throws SerializationException {
		Array<Effect> retrievedEffects = new Array<Effect>();

		if (this.effects.exists()) {
			retrievedEffects.addAll(json.fromJson(Array.class, this.effects));
		}/*else {
			retrievedEffects.add(new Effect("Reconnect Circuits", 45, "", 0, null, 1, true));
			json.toJson(retrievedEffects, this.effects);
		}*/

		return retrievedEffects;
	}

	/**
	 * Gets all traps from a file. This should only be invoked once, and by the
	 * object itself; other objects should use the
	 * {@link DataManager#getTrapList()} method instead.
	 * 
	 * @return An Array<Trap> object.
	 * @throws SerializationException
	 *             An error while reading or writing an object.
	 * @see DataManager#getTrapList().
	 */
	private Array<Trap> retrieveTraps() throws SerializationException {
		Array<Trap> retrievedTraps = new Array<Trap>();

		if (this.traps.exists()) {
			retrievedTraps.addAll(json.fromJson(Array.class, this.traps));
		}

		return retrievedTraps;
	}

}
