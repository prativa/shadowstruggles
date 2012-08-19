package com.shadowstruggles.screens;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.shadowstruggles.Controller;
import com.shadowstruggles.Profile;
import com.shadowstruggles.ShadowStruggles;
import com.shadowstruggles.battle.BattleLogic;
import com.shadowstruggles.battle.BattlePlatform;
import com.shadowstruggles.battle.Enemy;
import com.shadowstruggles.characters.BaseCharacter;

public class BattleScreen extends BaseScreen {
	/*private Image background;
	private Profile player;
	private ArrayList<BaseCharacter> playerDeck;
	private ArrayList<BaseCharacter> handCards;
	private OrthographicCamera camera;
	private long lastRender;
	private int lastTouchedX;
	private int lastTouchedY;*/
	static final int WIDTH  = 960;
    static final int HEIGHT = 480;
    private TextureRegion test= new TextureRegion(new Texture("data/images/sprites/1.png"),0,0,64,64);
    private int x = 140;
    private OrthographicCamera      cam;
    private TextureRegion                         texture;
    private Mesh                            mesh;
    private Rectangle                       glViewport;
    private float                           rotationSpeed;
    private SpriteBatch batch = new SpriteBatch(10000);
    private BattleLogic bl;
	
	
	public BattleScreen(ShadowStruggles game, String BG, Profile player, Controller controlle) {
		super(null,null);
		//bl = new BattleLogic(new BattlePlatform(new Enemy(),new Deck(0,30,new Array<card>)));
		//this.camera= new OrthographicCamera(width, height);
		//camera.position.set(0, 0, 0);
		//this.player = player;
		//this.playerDeck=player.getDeck();
		//Collections.shuffle(playerDeck);
		//this.background = new Image(new TextureRegion(new Texture(
				//Gdx.files.internal(BG)), 0, 0, 1920, 480));
		//lastRender = System.nanoTime();*/
		this.cam = new OrthographicCamera(960, 640);
		this.cam.position.set(480, 160,0);
		/*rotationSpeed = 0.5f;
        mesh = new Mesh(true, 4, 6,
                        new VertexAttribute(VertexAttributes.Usage.Position, 3,"attr_Position"),
                        new VertexAttribute(Usage.TextureCoordinates, 2, "attr_texCoords"));
        */texture = new TextureRegion(new Texture(
				Gdx.files.internal(BG)),1920,480);/*
        mesh.setVertices(new float[] { 
                               0f, 0f, 0, 0, 1,
                             960f, 0f, 0, 1, 1,
                          960f,  480f, 0, 1, 0,
                            0f,  480f, 0, 0, 0
        });
        mesh.setIndices(new short[] { 0, 1, 2, 2, 3, 0 });

        cam = new OrthographicCamera(WIDTH, HEIGHT);            
        cam.position.set(0, 0, 0);

        glViewport = new Rectangle(0, 0, WIDTH, HEIGHT);*/
	}
	
	public void drawCard(ArrayList<BaseCharacter> playerDeck, ArrayList<BaseCharacter> handCards){
		
	}

	@Override
	public void resize(int width, int height) {
		/*super.resize(width, height);
		initComponents();*/
	}
	
	
	@Override
	public void render(float delta) {		
		super.render(delta);
		/*long now = System.nanoTime();		
		if (Gdx.input.justTouched()) {
			lastTouchedX = Gdx.input.getX();
			lastTouchedY = Gdx.input.getY();
		} else if (Gdx.input.isTouched()) {
			camera.position.x += lastTouchedX
					- Gdx.input.getX();

			
			 // Camera y is opposite of Gdx.input y, so the subtraction is
			  //swapped.
			 
			camera.position.y += Gdx.input.getY()
					- lastTouchedY;

			lastTouchedX = Gdx.input.getX();
			lastTouchedY = Gdx.input.getY();
		}

		
		// Ensure that the camera is only showing the map, nothing outside.
		
		if (camera.position.x < width / 2) {
			camera.position.x = width / 2;
		}
		if (camera.position.x >= background.width
				- width / 2) {
			camera.position.x = background.width
					- width / 2;
		}

		if (camera.position.y < height / 2) {
			camera.position.y = height / 2;
		}
		if (camera.position.y >= background.height
				- height / 2) {
			camera.position.y = background.height
					- height / 2;
		}

		camera.update();
		now = System.nanoTime();
		if (now - lastRender < 30000000) { // 30 ms, ~33FPS
			try {
				Thread.sleep(30 - (now - lastRender) / 1000000);
			} catch (InterruptedException e) {
			}
		}

		lastRender = now;*/
		
		handleInput();
		//ExecutarLogica();
        GL10 gl = Gdx.graphics.getGL10();
        
        // Camera --------------------- /
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //gl.glViewport((int) glViewport.x, (int) glViewport.y,
                        //(int) glViewport.width, (int) glViewport.height);
        
        batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(texture,0,0);
		batch.draw(test,x,150);
		x++;
		batch.end();
		cam.update();
        // Texturing --------------------- /
        //gl.glActiveTexture(GL10.GL_TEXTURE0);
        //gl.glEnable(GL10.GL_TEXTURE_2D);
        //texture.bind();
        
        //mesh.render(GL10.GL_TRIANGLES);
	}
	
	private void handleInput() {
		/*if(Gdx.input.isTouched()){
			System.out.println(Gdx.input.getX());
			System.out.println(Gdx.input.getY());
		}*/
       
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if (cam.position.x > 480)
                        cam.translate(-3, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if (cam.position.x < 1440)
                        cam.translate(3, 0, 0);
        }
}
	
	
	/*private void initComponents() {
		background.width = 1920;
		background.height = 480;
		background.x = 0;
		background.y = 160;
		
		stage.addActor(background);
	}*/
	
	private void cardClicked() {
		// TODO Auto-generated method stub

	}
	
	private void deckClicked() {
		// TODO Auto-generated method stub

	}
	private void menuClicked() {
		// TODO Auto-generated method stub

	}
	private void pauseClicked() {
		// TODO Auto-generated method stub

	}
	private void muteDesmuteClicked() {
		// TODO Auto-generated method stub

	}
	private void cardDragged() {
		// TODO Auto-generated method stub

	}
	private void cardDropped() {
		// TODO Auto-generated method stub

	}
	private void tileClicked() {
		// TODO Auto-generated method stub

	}

}
