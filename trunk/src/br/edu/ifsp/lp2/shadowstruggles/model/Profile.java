package br.edu.ifsp.lp2.shadowstruggles.model;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * A class representing the player's data. There are multiple profiles
 * available, each for a different player or playthrough.
 */
public class Profile implements Serializable {

	private int id;
	private Scene currentScene;
	private String language;
	private int light;
	private int dark;
	private String path;
	private Array<Integer> battlesFought;

	public Profile(int id, Scene currentScene, String language, int light,
			int dark, String path) {
		this.id = id;
		this.currentScene = currentScene;
		this.language = language;
		this.light = light;
		this.dark = dark;
		this.path = path;
		this.setBattlesFought(new Array<Integer>());
	}

	public Profile() {
		this.id = 1;
		this.currentScene = Scene.FIRST_SCENE;
		this.language = "en_us";
		this.dark = 0;
		this.light = 0;
		this.setBattlesFought(new Array<Integer>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setCurrentScene(Scene currentScene) {
		this.currentScene = currentScene;
	}
	
	public Array<Integer> getBattlesFought() {
		return battlesFought;
	}

	public void setBattlesFought(Array<Integer> battlesFought) {
		this.battlesFought = battlesFought;
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("currentScene", this.currentScene);
		json.writeValue("language", this.language);
		json.writeValue("light", this.light);
		json.writeValue("dark", this.dark);
		json.writeValue("path", this.path);
		json.writeValue("battlesFought", this.battlesFought);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.currentScene = json.readValue("currentScene", Scene.class,
				jsonData);
		this.language = json.readValue("language", String.class, jsonData);
		this.light = json.readValue("light", Integer.class, jsonData);
		this.dark = json.readValue("dark", Integer.class, jsonData);
		this.path = json.readValue("path", String.class, jsonData);
		this.battlesFought = json.readValue("battlesFought", Array.class, jsonData);
	}



}
