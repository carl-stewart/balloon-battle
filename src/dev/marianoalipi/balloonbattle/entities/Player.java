package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;

import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.InputHandler;

public class Player extends Entity {

	private InputHandler inputHandler;
	
	public Player() {
		super();
	}

	public Player(int x, int y, int width, int height, Game game, InputHandler inputHandler) {
		super(x, y, width, height, game);
		this.inputHandler = inputHandler;
	}

	@Override
	public void tick() {
		if (inputHandler.up.down) {
			setY(getY() - 3);
		}
		
		if (inputHandler.down.down) {
			setY(getY() + 3);
		}
		
		if (inputHandler.left.down) {
			setX(getX() - 3);
		}
		
		if (inputHandler.right.down) {
			setX(getX() + 3);
		}
	}
	
	@Override
	public void render(Graphics g) {
		if (isVisible())
			g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
	}
	
}
