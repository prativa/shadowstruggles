package br.edu.ifsp.lp2.shadowstruggles.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.OrderedMap;

import br.edu.ifsp.lp2.shadowstruggles.model.Effect;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;
import br.edu.ifsp.lp2.shadowstruggles.model.Trap;

/**
 * This class handles all resources from the game, providing efficient access to
 * them.
 */
public class Assets {

	public static Texture cameraController;
	public static Texture deckReady;
	public static Texture deckNotReady;
	public static Texture energyBar;
	public static Texture handBackground;
	public static Texture mainBackground;
	public static Texture lifeBar;
	public static Texture pentagram;
	public static Texture backcard;
	public static Texture minus;
	public static Texture plus;
	public static Texture mute;
	
	public static com.badlogic.gdx.audio.Sound introSound;
	public static com.badlogic.gdx.audio.Sound buttonSound1;
	public static com.badlogic.gdx.audio.Sound buttonSound2;
	public static com.badlogic.gdx.audio.Sound buttonSound3;
	public static com.badlogic.gdx.audio.Sound buttonSound4;
	public static com.badlogic.gdx.audio.Sound buttonSound5;
	public static com.badlogic.gdx.audio.Sound buttonSound6;
	public static com.badlogic.gdx.audio.Sound buttonSound7;
	public static com.badlogic.gdx.audio.Sound buttonSound8;

	public static OrderedMap<String, Texture> fighterWalk;
	public static OrderedMap<String, Texture> fighterAttack;
	public static OrderedMap<String, Texture> handCards;
	public static OrderedMap<String, Texture> effectAnimation;
	public static OrderedMap<String, Texture> trapAnimation;
	
	/**
	 * Loads all of the textures and sounds. It should be called exactly once in
	 * the application.
	 * 
	 * @param manager A previously instantiated DataManager object.
	 */
	public static void load(DataManager manager) {
		cameraController = new Texture(
				Gdx.files.internal("data/images/controls/right.png"));
		minus = new Texture(
				Gdx.files.internal("data/images/controls/Minus.png"));
		plus = new Texture(
				Gdx.files.internal("data/images/controls/Plus.png"));
		mute = new Texture(
				Gdx.files.internal("data/images/controls/Mute.png"));
		deckReady = new Texture(
				Gdx.files.internal("data/images/objects/deck.png"));
		deckNotReady = new Texture(
				Gdx.files.internal("data/images/objects/deckNotReady.png"));
		energyBar = new Texture(
				Gdx.files.internal("data/images/objects/energy100.png"));
		handBackground = new Texture(
				Gdx.files.internal("data/images/objects/background.png"));
		mainBackground = new Texture(
				Gdx.files.internal("data/images/objects/msbackground.png"));
		lifeBar = new Texture(
				Gdx.files.internal("data/images/objects/life100.png"));
		pentagram = new Texture(
				Gdx.files.internal("data/images/objects/pentagram.png"));
		backcard = new Texture(
				Gdx.files.internal("data/images/objects/back_card.png"));
		
		
		introSound = Gdx.audio.newSound(Gdx.files
				.internal("data/audio/scene1.ogg"));
		buttonSound1 = Gdx.audio.newSound(Gdx.files
				.internal("data/audio/a1.ogg"));
		buttonSound2 = Gdx.audio.newSound(Gdx.files
				.internal("data/audio/a2.ogg"));
		buttonSound3 = Gdx.audio.newSound(Gdx.files
				.internal("data/audio/a3.ogg"));
		buttonSound4 = Gdx.audio.newSound(Gdx.files
				.internal("data/audio/a4.ogg"));
		buttonSound5 = Gdx.audio.newSound(Gdx.files
				.internal("data/audio/a5.ogg"));
		buttonSound6 = Gdx.audio.newSound(Gdx.files
				.internal("data/audio/a6.ogg"));
		buttonSound7 = Gdx.audio.newSound(Gdx.files
				.internal("data/audio/a7.ogg"));
		buttonSound8 = Gdx.audio.newSound(Gdx.files
				.internal("data/audio/a8.ogg"));

		fighterWalk = new OrderedMap<String, Texture>();
		fighterAttack = new OrderedMap<String, Texture>();
		effectAnimation = new OrderedMap<String, Texture>();
		trapAnimation = new OrderedMap<String, Texture>();
		handCards = new OrderedMap<String, Texture>();
		for (Fighter f : manager.getFighterList()) {
			fighterWalk.put(
					f.getName(),
					new Texture(Gdx.files.internal("data/images/sprites/"
							+ f.getName() + "/walk/animation_sheet.png")));

			fighterAttack.put(
					f.getName(),
					new Texture(Gdx.files.internal("data/images/sprites/"
							+ f.getName() + "/attack/animation_sheet.png")));

			handCards.put(
					f.getName(),
					new Texture(Gdx.files.internal("data/images/sprites/"
							+ f.getName() + "/card.png")));
		}

		for (Effect e : manager.getEffectList()) {
			handCards.put(
					e.getName(),
					new Texture(Gdx.files.internal("data/images/sprites/"
							+ e.getName() + "/card.png")));
			effectAnimation.put(
					e.getName(),
					new Texture(Gdx.files.internal("data/images/sprites/"
							+ e.getName() + "/animation_sheet.png")));
		}

		for (Trap t : manager.getTrapList()) {
			handCards.put(
					t.getName(),
					new Texture(Gdx.files.internal("data/images/sprites/"
							+ t.getName() + "/card.png")));
			trapAnimation.put(
					t.getName(),
					new Texture(Gdx.files.internal("data/images/sprites/"
							+ t.getName() + "/animation_sheet.png")));
		}
	}
}
