package dev.marianoalipi.balloonbattle.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.KeyManager;

public class MainMenu extends Menu {

	private ArrayList<String> options;
	private int selected = 0;
	private int inputInterval = 12;	// the number of frames between inputs
	private int intervalCounter = 0;
	
	public MainMenu(Game game, KeyManager keyManager) {
		super(game, keyManager);
		
		options = new ArrayList<String>();
		options.add("PLAY");
		options.add("CREDITS");
	}
	
	@Override
	public void tick() {
		
		if (intervalCounter >= inputInterval) {
			keyManager.tick();
		} else {
			intervalCounter++;
		}
		
		if ( (keyManager.enter || keyManager.z) && selected == 0) {
			System.out.println("Game start");
			keyManager.enter = false;
			keyManager.z = false;
			intervalCounter = 0;
		}
		
		if (keyManager.up) {
			selected--;
			keyManager.up = false;
			intervalCounter = 0;
		}
		if (keyManager.down) {
			selected++;
			keyManager.down = false;
			intervalCounter = 0;
		}
		
		if (selected < 0)
			selected = options.size() - 1;
		else if (selected  >= options.size())
			selected = 0;

	}
	
	@Override
	public void render (Graphics g) {
		g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        g.drawImage(Assets.title, 100, 20, 600, 250, null);
        g.drawImage(Assets.github, 179, game.getHeight() - 50, 443, 31, null);
        
        g.setColor(Color.white);
        for (int i = 0; i < options.size(); i++) {
        	//g.drawString(options.get(i), 350, 350 + i * 20);
        	g.drawImage(Assets.mainMenuOptions[i], 350, 320 + i * Assets.mainMenuOptions[i].getHeight() + i * 15, null);
        }
        
        // Draw a balloon to indicate the cursor
        g.drawImage(Assets.balloon, 300, 320 + selected * Assets.mainMenuOptionsImg.getHeight() / 2 + selected * 15 - 5, 32, 32, null);
	}

}
