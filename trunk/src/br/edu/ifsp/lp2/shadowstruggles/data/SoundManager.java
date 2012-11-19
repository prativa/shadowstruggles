package br.edu.ifsp.lp2.shadowstruggles.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Class for encapsulating the sound management within the application.
 */

public class SoundManager {
	private Music music;
	private Sound sound;
	private float volume;
	private String musicName;

	public SoundManager() {
		this.volume=0.3f;
	}

	public void setMusic(String musicName) {
		
		this.musicName=musicName;
		this.music = Gdx.audio.newMusic(Gdx.files.internal("data/audio/"
				+ musicName + ".ogg"));
		music.setLooping(true);
		music.setVolume(volume);

		music.play();
	}

	public void stop() {
		music.stop();
	}

	public void playSound(String soundName) {
		this.sound = Gdx.audio.newSound(Gdx.files.internal("data/audio/"
				+ soundName + ".ogg"));
		
		sound.play(1);
	}

	public void setVolume(float newVolume) {
		this.volume += newVolume;

		if (volume > 1)
			volume = 1;
		if (volume < 0)
			volume = 0;

		music.setVolume(volume);
	}

	public float getVolume() {
		return volume;

	}
	public String getVolumeNumber(){
		int volume = (int)(this.volume*100);
		return String.valueOf(volume);
	}

	public Music getMusic() {
		return music;
	}
	
	public String getMusicName(){
		return musicName;
	}

}
