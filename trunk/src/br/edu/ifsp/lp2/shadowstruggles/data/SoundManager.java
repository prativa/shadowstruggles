package br.edu.ifsp.lp2.shadowstruggles.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	private Music music;
	private Sound sound;
	public SoundManager() {
		
	}
	
	public void setMusic(String musicName){
		this.music= Gdx.audio.newMusic(Gdx.files.internal("data/music/"+musicName+".mid"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
	}
	
	public void playSound(String soundName){
		this.sound= Gdx.audio.newSound(Gdx.files.internal("data/music/"+soundName+".mid"));
		sound.play();
	}
	
	

	
}
