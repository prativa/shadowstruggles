package com.shadowstruggles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.shadowstruggles.Controller;
import com.shadowstruggles.ShadowStruggles;

public abstract class BaseScreen implements Screen {
	protected final ShadowStruggles game;
	protected final Stage stage;
	private BitmapFont font;
	private SpriteBatch batch;
	private Skin skin;
	private TextureAtlas atlas;
	protected Controller controller;
	protected int height;
	protected int width;

	public BaseScreen(ShadowStruggles game, Controller controller) {
		this.game = game;
		this.controller = controller;
		this.stage = new Stage(0, 0, true);
	}

	public Skin getSkin() {
		if (skin == null) {
			skin = new Skin(Gdx.files.internal("data/uiskin.json"),
					Gdx.files.internal("data/images/controls/botao.png"));
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
		Gdx.app.log(ShadowStruggles.LOG, "Game Paused");
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
		Gdx.app.log(ShadowStruggles.LOG, "Resized to " + width + "x" + height);
		stage.setViewport(width, height, true);
		stage.clear();
	}

	@Override
	public void resume() {
		Gdx.app.log(ShadowStruggles.LOG, "Resuming screen: " + getName());
	}

	@Override
	public void show() {

		Gdx.app.log(ShadowStruggles.LOG, "Showing screen: " + getName());
		Gdx.input.setInputProcessor(stage);
	}

	public ShadowStruggles getGame() {
		return game;
	}

}
