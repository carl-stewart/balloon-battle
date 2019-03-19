package dev.marianoalipi.balloonbattle.menu;

import java.awt.Color;
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
        g.setFont(Assets.gameFont25B);
        g.drawString("Fonts:", 130, game.getHeight() / 8);
        g.setFont(Assets.gameFont25);
        g.drawString("\"Roses Are FF0000\" by AJ Paglia", 130, game.getHeight() / 7 + 30);
        g.drawString("www.ajpaglia.com", 130, game.getHeight() / 7 + 60);
        
        int index = 2;
        g.drawImage(Assets.mainMenuOptions[index], 350, game.getHeight() - 80 - Assets.textScale * Assets.mainMenuOptions[index].getHeight(), Assets.textScale * Assets.mainMenuOptions[index].getWidth(), Assets.textScale * Assets.mainMenuOptions[index].getHeight(),  null);
        
        // Draw a balloon to indicate the cursor
        g.drawImage(Assets.balloonsOne[0], 300, game.getHeight() - 80 - 32, (int)(Game.SCALE * 16), (int)(Game.SCALE * 12), null);
	}
	
	
	
}
