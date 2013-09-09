package br.edu.ifsp.pds.shadowstruggles.model.events;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.dao.SettingsDAO;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.Character;
import br.edu.ifsp.pds.shadowstruggles.model.rpg.RpgMap;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.RpgScreen;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Sends an event or the player to another location, updating the current screen
 * when necessary.
 */
public class WarpAction extends EventAction {
	private int destinyX, destinyY;
	private String destinyMap;
	private String destinyLayer;
	private Event target;

	public WarpAction() {
		this.destinyX = 0;
		this.destinyY = 0;
		this.destinyMap = "";
		this.destinyLayer = "";
		this.target = null;
	}

	@Override
	public void act() {
		if (target != null) {
			EventInGame eventInGame = ShadowStruggles.getInstance()
					.getProfile().getEvent(target.getId());
			eventInGame.setMap(destinyMap);
			eventInGame.setLayer(destinyLayer);
			eventInGame.getCharacter().setPosition(destinyX, destinyY);
		} else {
			Character playerChar = ShadowStruggles.getInstance().getProfile()
					.getCharacter();
			RpgMap newMap = new RpgMap(ShadowStruggles.getInstance(),
					destinyMap, destinyLayer,
					SettingsDAO.getSettings().defaultTileLayer);
			playerChar.setPosition(destinyX, destinyY);
			playerChar.setCurrentMap(newMap);
		}

		if (this.update()) {
			RpgScreen currentScreen = (RpgScreen) ShadowStruggles.getInstance()
					.getScreen();
			ShadowStruggles game = currentScreen.getGame();
			Controller controller = currentScreen.getController();
			RpgController rpgController = currentScreen.getRpgController();

			ShadowStruggles.getInstance().setScreenWithTransition(
					new RpgScreen(game, controller, rpgController));
		}
	}

	/**
	 * Returns whether or not it's necessary to update the screen after the
	 * WarpAction has been applied. The screen is updated if 1) The event was
	 * previously on the same map and layer as the player's character (even if
	 * it remains on the same layer/map after the action); 2) The event will now
	 * be on the same map and layer as the player's character; or 3) The
	 * WarpAction will affect the player's character.
	 */
	private boolean update() {
		if (target == null)
			return true;

		EventInGame event = ShadowStruggles.getInstance().getProfile()
				.getEvent(target.getId());
		Character playerChar = ShadowStruggles.getInstance().getProfile()
				.getCharacter();

		String playerLayer = playerChar.getCurrentMap().getObjectLayer();
		String playerMap = playerChar.getCurrentMap().getMapName();
		return ((playerMap.equals(event.getMap()) && playerLayer.equals(event
				.getLayer())) || (playerMap.equals(destinyMap) && playerLayer
				.equals(destinyLayer)));
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.destinyX = json.readValue("destinyX", Integer.class, jsonData);
		this.destinyY = json.readValue("destinyY", Integer.class, jsonData);
		this.destinyMap = json.readValue("destinyMap", String.class, jsonData);
		this.destinyLayer = json.readValue("destinyLayer", String.class,
				jsonData);
		this.target = json.readValue("target", Event.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("destinyX", this.destinyX);
		json.writeValue("destinyY", this.destinyY);
		json.writeValue("destinyMap", this.destinyMap);
		json.writeValue("destinyLayer", this.destinyLayer);
		json.writeValue("target", this.target);
	}
}
