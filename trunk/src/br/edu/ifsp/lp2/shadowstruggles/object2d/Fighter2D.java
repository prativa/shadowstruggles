package br.edu.ifsp.lp2.shadowstruggles.object2d;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.data.Settings;
import br.edu.ifsp.lp2.shadowstruggles.model.Fighter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Fighter2D extends Image implements ApplicationListener {

	static final float SCALE_X = 72f / 64f;
	static final int FRAME_COLS = 3;
	static final int FRAME_ROWS = 3;

	private Settings settings;

	private Animation walkAnimation;
	private Animation attackAnimation;
	private TextureRegion currentFrame;
	private TextureRegion walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion attackSheet;
	private TextureRegion[] attackFrames;
	private boolean attacking = false;
	private Controller controller;
	private Fighter fighter;
	private float stateTime;

	public Fighter2D(Fighter fighter, Controller controller) {
		super(new TextureRegion(Assets.fighterWalk.get(fighter.getName()), 1, 1, 64, 64));
		this.fighter = fighter;

		this.controller = controller;
		this.settings = controller.getCurrentScreen().getGame().getManager().getSettings();
		
		if(fighter.getSize().equals(Fighter.SIZE_SMALL)) {
			this.scaleY = 0.8f;
		} else if(fighter.getSize().equals(Fighter.SIZE_MEDIUM)) {
			this.scaleY = 1.0f;
		} else if(fighter.getSize().equals(Fighter.SIZE_LARGE)) {
			this.scaleY = 1.5f;
		} 
	}

	@Override
	public void create() {
		walkSheet = new TextureRegion(Assets.fighterWalk.get(fighter.getName()), 1, 1, 64 * 3, 64 * 3);

		TextureRegion[][] tmp = walkSheet.split(walkSheet.getRegionWidth()
				/ FRAME_COLS, walkSheet.getRegionHeight() / FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (fighter.getDirection() == -1)
					tmp[i][j].flip(true, false);
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.075f, walkFrames);
		stateTime = 0f;
		attackSheet = new TextureRegion(Assets.fighterAttack.get(fighter.getName()), 1, 1, 64 * 3, 64 * 3);
		tmp = attackSheet.split(attackSheet.getRegionWidth()
				/ FRAME_COLS, attackSheet.getRegionHeight() / FRAME_ROWS);
		attackFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				if (fighter.getDirection() == -1)
					tmp[i][j].flip(true, false);
				attackFrames[index++] = tmp[i][j];
			}
		}
		attackAnimation = new Animation(0.075f, attackFrames);
		this.x = settings.tileHeight * 2 + (fighter.getTile())
				* settings.tileWidth;
		this.y = settings.screenHeight - settings.backgroundHeight
				+ settings.tileHeight + (this.fighter.getLane())
				* settings.tileHeight * 3 / 2;
	}

	@Override
	public void resize(int width, int height) {
	}

	
	public void update() {
		fighter.action(controller.getPlatform(),this);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Animation getWalkAnimation() {
		return walkAnimation;
	}

	public void setWalkAnimation(Animation walkAnimation) {
		this.walkAnimation = walkAnimation;
	}

	public Animation getAttackAnimation() {
		return attackAnimation;
	}

	public void setAttackAnimation(Animation attackAnimation) {
		this.attackAnimation = attackAnimation;
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}

	public TextureRegion getWalkSheet() {
		return walkSheet;
	}

	public void setWalkSheet(TextureRegion walkSheet) {
		this.walkSheet = walkSheet;
	}

	public TextureRegion[] getWalkFrames() {
		return walkFrames;
	}

	public void setWalkFrames(TextureRegion[] walkFrames) {
		this.walkFrames = walkFrames;
	}

	public TextureRegion getAttackSheet() {
		return attackSheet;
	}

	public void setAttackSheet(TextureRegion attackSheet) {
		this.attackSheet = attackSheet;
	}

	public TextureRegion[] getAttackFrames() {
		return attackFrames;
	}

	public void setAttackFrames(TextureRegion[] attackFrames) {
		this.attackFrames = attackFrames;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public static float getScaleX() {
		return SCALE_X;
	}

	public static int getFrameCols() {
		return FRAME_COLS;
	}

	public static int getFrameRows() {
		return FRAME_ROWS;
	}
	
	@Override
	public void render(){
		this.getFighter().getAction().update(this);
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	
}