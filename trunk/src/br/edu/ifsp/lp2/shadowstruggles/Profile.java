package br.edu.ifsp.lp2.shadowstruggles;


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
	
	public Profile(int id, Scene currentScene) {
		this.id = id;
		this.currentScene = currentScene;
	}
	
	public Profile() {
		this.id = 1;
		this.currentScene = Scene.FIRST_SCENE;
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

	public void setCurrentScene(Scene currentScene) {
		this.currentScene = currentScene;
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("currentScene", this.currentScene);
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.currentScene = json.readValue("currentScene", Scene.class, jsonData);
	}
	
	

}
