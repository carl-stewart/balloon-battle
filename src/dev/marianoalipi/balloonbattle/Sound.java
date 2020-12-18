package dev.marianoalipi.balloonbattle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static final Sound flap		= new Sound("assets/sounds/flap.wav"),
							  hit		= new Sound("assets/sounds/hit.wav"),
							  navigate	= new Sound("assets/sounds/menuNavigate.wav"),
							  silence	= new Sound("assets/sounds/silence.wav");

	private Clip clip;

	private Sound(String path) {
		try {
			
			AudioInputStream sound = AudioSystem.getAudioInputStream(Sound.class.getResource(path));
			clip = AudioSystem.getClip();
			clip.open(sound);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}
}