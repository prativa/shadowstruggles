package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.MyStage;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Settings;
import br.edu.ifsp.lp2.shadowstruggles.object2d.Fighter2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class BaseScreen implements Screen {
	protected final ShadowStruggles game;
	protected final MyStage stage;
	private BitmapFont font;
	private SpriteBatch batch;
	private Skin skin;
	private TextureAtlas atlas;
	protected Controller controller;
	protected int height;
	protected int width;
	protected static final int BACKGROUND_Y = 160;
	protected static final int CAMERA_INITIAL_X = 480;
	protected OrthographicCamera camera;
	protected Settings settings;

	public BaseScreen(ShadowStruggles game, Controller controller) {
		this.game = game;
		this.controller = controller;
		settings = game.getManager().retrieveSettings();
		this.stage = new MyStage(0, 0, true);
		this.camera = new OrthographicCamera(this.width, this.height);
		this.camera.position.set(CAMERA_INITIAL_X, 160, 0);
		float zoom = ((float) 960 / (float) width);
		camera.zoom = zoom;
		camera.position.x = CAMERA_INITIAL_X;
		stage.setCamera(camera);
	}

	public Skin getSkin() {
		if (skin == null) {
			skin = new Skin(Gdx.files.internal("data/skin.json"),
					Gdx.files.internal("data/images/controls/botaohd.png"));
		}

		return skin;
	}

	public SpriteBatch getBatch() {
		if (batch == null) {
			batch = new SpriteBatch();
		}
		return batch;
	}

	public BitmapFont getFont() {
		if (font == null) {
			font = new BitmapFont();
		}
		return font;
	}

	public TextureAtlas getAtlas() {
		if (atlas == null) {
			atlas = new TextureAtlas(
					Gdx.files.internal("image-atlases/pages-info"));
		}
		return atlas;
	}

	protected String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public void dispose() {
		stage.dispose();
		if (font != null)
			font.dispose();
		if (batch != null)
			batch.dispose();
		if (skin != null)
			skin.dispose();
		if (atlas != null)
			atlas.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

		this.width = width;
		this.height = height;
		camera.viewportHeight = this.height;
		camera.viewportWidth = this.width;
		stage.setViewport(width, height, false);
		stage.clear();
		float zoom;
		if (((float) 960 / (float) width) > ((float) 640 / (float) height)) {
			zoom = ((float) 960 / (float) width);
		} else {
			zoom = ((float) 640 / (float) height);
		}
		camera.zoom = zoom;
		camera.position.x = CAMERA_INITIAL_X;
		camera.position.y = 320;
		for (Actor a : stage.getCurrentActor()) {
			stage.insertActor(a);
		}

	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {

		Gdx.input.setInputProcessor(stage);
	}

	public ShadowStruggles getGame() {
		return game;
	}

	public Stage getStage() {
		return stage;
	}

	public void addGameObject(Fighter2D f2d) {
		stage.addActor(f2d);
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

}
