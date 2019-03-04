package dev.marianoalipi.balloonfight.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import dev.marianoalipi.balloonfight.Assets;
import dev.marianoalipi.balloonfight.Game;
import dev.marianoalipi.balloonfight.KeyManager;

public class MainMenu extends Menu {

	private ArrayList<String> options; 
	
	public MainMenu(Game game, KeyManager keyManager) {
		super(game, keyManager);
		
		options = new ArrayList<String>();
		options.add("PLAY");
		options.add("CREDITS");
	}
	
	@Override
	public void tick() {
		keyManager.tick();

	}
	
	@Override
	public void render (Graphics g) {
		g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        g.drawImage(Assets.title, 100, 20, 600, 250, null);
        g.drawImage(Assets.github, 179, game.getHeight() - 50, 443, 31, null);
        
        g.setColor(Color.white);
        for (int i = 0; i < options.size(); i++) {
        	g.drawString(options.get(i), 350, 350 + i * 20);
        }
	}

}
