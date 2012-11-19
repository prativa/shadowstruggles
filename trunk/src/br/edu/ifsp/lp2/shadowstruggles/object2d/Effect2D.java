package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.data.Settings;
import br.edu.ifsp.lp2.shadowstruggles.model.Effect;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Effect2D extends Image {

	static final float SCALE_X = 72f / 64f;
	private Animation animation;
	private TextureRegion currentFrame;
	private TextureRegion animationSheet;
	private TextureRegion[] animationFrames;
	private Controller controller;
	private Effect effect;
	private float stateTime;
	
	static final int FRAME_COLS = 3;
	static final int FRAME_ROWS = 3;

	private Settings settings;
	
	public Effect2D(Effect effect, Controller controller) {
		super(new TextureRegion(Assets.effectAnimation.get(effect.getName()), 1, 1, 64, 64));
		this.effect = effect;

		this.controller = controller;
		this.settings = controller.getCurrentScreen().getGame().getManager().getSettings();
	}

	public void create() {
		animationSheet = new TextureRegion(Assets.effectAnimation.get(effect.getName()), 1, 1, 64 * 3, 64 * 3);

		TextureRegion[][] tmp =animationSheet.split(animationSheet.getRegionWidth()
				/ FRAME_COLS, animationSheet.getRegionHeight() / FRAME_ROWS);
		animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (effect.getDirection() == -1)
					tmp[i][j].flip(true, false);
				animationFrames[index++] = tmp[i][j];
			}
		}
		animation = new Animation(0.075f, animationFrames);
		stateTime = 0f;
		animationSheet = new TextureRegion(Assets.effectAnimation.get(effect.getName()), 1, 1, 64 * 3, 64 * 3);
		tmp = animationSheet.split(animationSheet.getRegionWidth()
				/ FRAME_COLS, animationSheet.getRegionHeight() / FRAME_ROWS);
		animationFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (effect.getDirection() == -1)
					tmp[i][j].flip(true, false);
				animationFrames[index++] = tmp[i][j];
			}
		}
		animation = new Animation(0.075f, animationFrames);
		this.x = settings.tileHeight * 2 + (effect.getTile())
				* settings.tileWidth;
		this.y = settings.screenHeight - settings.backgroundHeight
				+ settings.tileHeight + (this.effect.getLane())
				* settings.tileHeight * 3 / 2;
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
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
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
	
	public void render(){
		this.getEffect().getAction().update(this);
	}
	
	public void update() {
		effect.action(controller.getPlatform(),this);
	}
}
