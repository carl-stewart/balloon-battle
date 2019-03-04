package dev.marianoalipi.balloonfight.menu;

import java.awt.Graphics;

import dev.marianoalipi.balloonfight.Game;
import dev.marianoalipi.balloonfight.KeyManager;

/**
 * 
 * @author MarianoAlipi
 *
 */

public class Menu {

	protected Game game;
	protected KeyManager keyManager;
	
	public Menu (Game game, KeyManager keyManager) {
		this.game = game;
		this.keyManager = keyManager;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
}
