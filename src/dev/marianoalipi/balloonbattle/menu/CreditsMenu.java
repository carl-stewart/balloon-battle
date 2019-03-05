package dev.marianoalipi.balloonbattle.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.InputHandler;

public class CreditsMenu extends Menu {

	private ArrayList<String> options;
	
	public CreditsMenu(Game game, InputHandler inputHandler) {
		super(game, inputHandler);
		
		options = new ArrayList<String>();
		options.add("Go back");	
	}
	
	public void tick() {
		
		if (inputHandler.enter.clicked || inputHandler.z.clicked || inputHandler.x.clicked) {
			//inputHandler.enter.clicked = false;
			//inputHandler.z.clicked = false;
			//inputHandler.x.clicked = false;
			game.setMenu(new MainMenu(game, game.getInputHandler()));
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        g.drawImage(Assets.github, 179, game.getHeight() - 50, 443, 31, null);
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.drawString("Go back", 350, game.getHeight() - 80);
        
        // Draw a balloon to indicate the cursor
        g.drawImage(Assets.balloon, 310, game.getHeight() - 80 - 30, 32, 32, null);
	}
	
	
	
}
