package br.edu.ifsp.lp2.shadowstruggles.screens;

import br.edu.ifsp.lp2.shadowstruggles.Controller;
import br.edu.ifsp.lp2.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.lp2.shadowstruggles.data.Assets;
import br.edu.ifsp.lp2.shadowstruggles.model.Card;
import br.edu.ifsp.lp2.shadowstruggles.object2d.TransitionControl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

public class CheckCardsScreen extends BaseScreen{
	
	
	private InGameMenu menu;
	private Image selectedImage;	
	private Image background;
	private Label name;
	private Label description;
	private TransitionControl right;
	private TransitionControl left;
	private Image exit;
	private Image box;
	
	private Card selectedCard;	
	private Array<Card> cards;
	private Array<Image> cardImages;
	

	public CheckCardsScreen(ShadowStruggles game, Controller controller, InGameMenu menu) {
		super(game, controller);
		this.menu=menu;
		initComponents();
	}
	private void initComponents(){
		final InGameMenu menu = this.menu;	
		background = new Image(new TextureRegion(Assets.mainBackground, 512, 380));
		background.scaleX = (960f / 512f);
		background.scaleY = (640f / 380f);
		
		
		name = new Label(super.getSkin());
		name.x=410;		
		name.width=500;	
		name.height=50;
		name.setWrap(true);
		name.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		description = new Label(super.getSkin());
		description.x=410;		
		description.width=500;		
		description.setWrap(true);
		description.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.BLACK));
		
		box = new Image(new Texture(
				Gdx.files.internal("data/images/objects/box.png")));
		box.width = 600;
		box.height = 600;
		box.x = 390;
		box.y = 177;
		box.scaleX=0.9f;
		box.scaleY=0.76f;
		
		
		right = new TransitionControl(1);
		right.y=20;
		right.x=900;
		right.scaleY=4f;
		right.scaleX=1.5f;
		right.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				moveCards(1);
				
			}
		});
		left = new TransitionControl(-1);
		left.y=20;
		left.x=120;
		left.scaleY=4f;
		left.scaleX=1.5f;
		left.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				moveCards(-1);
				
			}
		});
		
		exit = new Image(new TextureRegion(Assets.mute));
		exit.x=10;
		exit.y=10;
		exit.scaleX=1.5f;
		exit.scaleY=1.5f;
		exit.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				game.setScreenWithTransition(menu);	
				
			}
		});
			
		
		stage.addActor(background);
		stage.addActor(box);
		stage.addActor(name);
		stage.addActor(description);
		stage.addActor(exit);
		stage.addActor(right);
		
		this.cards= new Array<Card>();
		this.cards.addAll(game.getManager().getFighterList());
		this.cards.addAll(game.getManager().getEffectList());
		this.cards.addAll(game.getManager().getTrapList());
		for(Card card:cards)
			for(Card nextCard: cards)if(card.name.equals(nextCard.name)&& !card.equals(nextCard))
				cards.removeValue(nextCard, true);
		int count=0;
		cardImages= new Array<Image>();
		for(Card card : cards){
			Image cardImage = new Image(new TextureRegion(Assets.handCards.get(card.name), 360,480));				
			cardImage.y=5;
			cardImage.x=180+count*120;			
			cardImage.scaleX=0.3f;
			cardImage.scaleY=0.3f;
			final Card card2 = card;
			cardImage.setClickListener(new ClickListener() {
				
				@Override
				public void click(Actor actor, float x, float y) {
					changeCard(card2);					
				}
			});
			cardImages.add(cardImage);
			if(cardImage.x>=180 && cardImage.x <900)
			stage.addActor(cardImage);
			count++;
		}	
		changeCard(cards.get(0));	
		stage.addActor(right);
		stage.addActor(left);
		for(Image card: cardImages){
			System.out.println(card.name + " - "+ card.x);
		}
	}
	
	private void moveCards(int side) {
		System.out.println("clicou");
		boolean movableToRight=false, movableToLeft=false;		
		for(Image card : cardImages){
			if(card.x<180)movableToRight=true;
			if(card.x>780) movableToLeft=true;			
		}
			
		if((side>0 && movableToLeft) || (side<0 &&movableToRight))
			for(Image card : cardImages){
				card.x-=120*side;
				if(card.x>=120 && card.x<900)stage.addActor(card);
				else{
					try{
						stage.removeActor(card);
						
					}catch(Exception e){}
				}
			}
		for(Image card: cardImages){
			System.out.println(card.name + " - "+ card.x);
		}

	}
	
	
	private void changeCard(Card card){
		this.selectedCard=card;
		description.setText(card.description);
		description.height=description.getPrefHeight();
		description.y= 590- description.height;
		try{
			stage.removeActor(selectedImage);
		}catch(Exception e){}
		selectedImage=new Image(new TextureRegion(Assets.handCards.get(card.name),360,480));
		selectedImage.x=20;
		selectedImage.y=160;
		name.setText(selectedCard.nameVisualization);
		name.y=590;
		stage.addActor(selectedImage);
	}
	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 200);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

}
