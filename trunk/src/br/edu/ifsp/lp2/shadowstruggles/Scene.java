package br.edu.ifsp.lp2.shadowstruggles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * A Scene is an object which represents a narrative sequence within the story.
 * Each Scene has some text, written in a specified language, and is displayed
 * on the screen on top of a background image.
 */
public class Scene implements Serializable {

	public static final Scene FIRST_SCENE = new Scene();
	private int id;
	private TextureRegion background;
	private String script;
	private String language;
	
	public Scene() {
		this.background = new TextureRegion();
		this.id = 1;
		this.script = "";
		this.setLanguage("pt_br");
	}
	
	public int getId() {
		return this.id;
	}
	
	public TextureRegion getBackground() {
		return this.background;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getScript() {
		return script;
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("background", this.background);
		json.writeValue("script", this.script);
		json.writeValue("language", this.language);
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.background = json.readValue("background", TextureRegion.class, jsonData);
		this.script = json.readValue("script", String.class, jsonData);
		this.language = json.readValue("language", String.class, jsonData);
	}
	
}
