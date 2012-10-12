package br.edu.ifsp.lp2.shadowstruggles.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Class for storage of all text shown in the GUI, for purposes of
 * i18n.
 */
public class MenuText implements Serializable {

	// Start Screen
	public String continueGame = "";
	public String newGame = "";
	
	// Select Profile
	public String returnToStart = "";
	public String profile = "";
	
	// Error Screen
	public String terminate = "";
	public String details = "";
	
	@Override
	public void write(Json json) {
		json.writeValue("continueGame", continueGame);
		json.writeValue("newGame", newGame);
		json.writeValue("returnToStart", returnToStart);
		json.writeValue("profile", profile);
		json.writeValue("terminate", terminate);
		json.writeValue("details", details);
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		continueGame = json.readValue("continueGame", String.class, jsonData);
		newGame = json.readValue("newGame", String.class, jsonData);
		returnToStart = json.readValue("returnToStart", String.class, jsonData);
		profile = json.readValue("profile", String.class, jsonData);
		terminate = json.readValue("terminate", String.class, jsonData);
		details = json.readValue("details", String.class, jsonData);
	}
	
}
