package com.shadowstruggles.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class BaseCharacter extends Image {
	private int atk;
	private int def;
	private float vel; // 0=slowest 1 = slow 2 =regular 3=fast 4=faster
						// 5=fastest
	private int side; // aliens=0 alquimistas=1
	private int hp;
	private Texture[] walk;
	private Texture[] attack;
	private Texture card;
	private int dir = 1;
	private int nWalk = 2;
	private int nAttack = 0;
	private boolean walking = false;
	private boolean attacking = false;
	private float time = 60;
	private final static float FPS = 30;
	private BaseCharacter t = this;
	private int cardID;
	private String name;

	public BaseCharacter(int side, String name, int atk, int def, int hp,
			float vel, int id) {
		loadCharacter(id);
		this.name=name;		
		this.walk = new Texture[10];
		this.attack = new Texture[8];
		this.side = side;
		for (int i = 0; i <= 9; i++)
			walk[i] = new Texture(Gdx.files.internal("data/images/sprites/"
					+ name + "/Walk/000" + (i + 1) + ".png"));
		for (int i = 0; i <= 7; i++)
			attack[i] = new Texture(Gdx.files.internal("data/images/sprites/"
					+ name + "/Attack/000" + (i + 1) + ".png"));
		this.card=new Texture(Gdx.files.internal("data/images/sprites/"+name+"/Card.png"));
		this.setRegion(new TextureRegion(card));
		t.height = 256;
		t.width = 256;
		this.scaleX = 0.5f;
		this.scaleY = 0.5f;

	}
	
	public void loadCharacter(int id){//Carregar informações da carta através do arquivo de dados
		
	}

	public void walk() {
		walking = true;
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// super.run();
				while (walking) {
					if (time >= 1 / FPS) {
						time = 0;
						float x = t.x;
						float y = t.y;
						if (nWalk < 9)
							nWalk++;
						else
							nWalk = 0;
						t.setRegion(new TextureRegion(walk[nWalk]));
						t.x = x;
						t.y = y;
						t.height = 256;
						t.width = 256;
						t.scaleX = 0.6f;
						t.scaleY = 0.6f;
						// t.x = t.x + 0.5f + (t.getVel() * 0.25f);
					} else
						time += Gdx.graphics.getDeltaTime();
				}
			}
		}.start();

	}

	public void stopWalking() {
		walking = false;
	}

	public void attack() {
		if (nAttack < 7)
			nAttack++;
		else
			nAttack = 0;
		this.setRegion(new TextureRegion(walk[nWalk]));
	}

	public int getDir() {
		return dir;
	}

	public void changeDir() {
		dir *= (-1);
		this.scaleX = -0.6f;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public float getVel() {
		return vel;
	}

	public void setVel(float vel) {
		this.vel = vel;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

}
