package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Settings;
import br.edu.ifsp.lp2.shadowstruggles.model.Trap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/***
 * A representation of a trap on the field. Becomes visible when activated.
 */

public class Trap2D extends Image {

	private static final int FRAME_COLS = 3;
	private static final int FRAME_ROWS = 3;

	private Animation animation;
	private TextureRegion currentFrame;
	private TextureRegion animationSheet;
	private TextureRegion[] animationFrames;
	private ShadowStruggles game;
	private Trap trap;
	private float stateTime;
	private Settings settings;

	public Trap2D(Trap trap, ShadowStruggles game) {
		super(new TextureRegion(game.getAssets().get("data/images/sprites/"
				+ trap.getName() + "/animation_sheet.png", Texture.class), 1, 1,
				64, 64));
		this.trap = trap;
		this.game = game;
		this.settings = game.getManager()
				.getSettings();
		this.setVisible(false);
	}

	public void create() {
		animationSheet = new TextureRegion(game.getAssets().get("data/images/sprites/"
				+ trap.getName() + "/animation_sheet.png", Texture.class), 1, 1, 64 * 3, 64 * 3);

		TextureRegion[][] tmp = animationSheet.split(
				animationSheet.getRegionWidth() / FRAME_COLS,
				animationSheet.getRegionHeight() / FRAME_ROWS);
		animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (trap.getDirection() == -1)
					tmp[i][j].flip(true, false);
				animationFrames[index++] = tmp[i][j];
			}
		}

		animation = new Animation(0.075f, animationFrames);
		stateTime = 0f;
		animationSheet = new TextureRegion(game.getAssets().get("data/images/sprites/"
				+ trap.getName() + "/animation_sheet.png", Texture.class), 1, 1, 64 * 3, 64 * 3);
		tmp = animationSheet.split(
				animationSheet.getRegionWidth() / FRAME_COLS,
				animationSheet.getRegionHeight() / FRAME_ROWS);
		animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (trap.getDirection() == -1)
					tmp[i][j].flip(true, false);
				animationFrames[index++] = tmp[i][j];
			}
		}
		animation = new Animation(0.075f, animationFrames);
		this.setX(settings.tileHeight * 2 + (trap.getTile())
				* settings.tileWidth);
		this.setY(settings.screenHeight - settings.backgroundHeight
				+ settings.tileHeight + (this.trap.getLane())
				* settings.tileHeight * 3 / 2);
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}

	public TextureRegion getAnimationSheet() {
		return animationSheet;
	}

	public void setAnimationSheet(TextureRegion animationSheet) {
		this.animationSheet = animationSheet;
	}

	public TextureRegion[] getAnimationFrames() {
		return animationFrames;
	}

	public void setAnimationFrames(TextureRegion[] animationFrames) {
		this.animationFrames = animationFrames;
	}

	public Controller getController() {
		return game.getController();
	}

	public void setController(Controller controller) {
		this.game.setController(controller);
	}

	public Trap getTrap() {
		return trap;
	}

	public void setTrap(Trap Trap) {
		this.trap = Trap;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public void render() {
		this.getTrap().getAction().update(this);
	}

	public void update(float delta) {
		trap.action(game.getController().getPlatform(), this,delta);
	}

}
