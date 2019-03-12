package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;

import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.InputHandler;

public class Player extends Entity {

	private InputHandler inputHandler;
	private boolean flapKeyReleased;
	private int framesBetweenFlaps = 8, framesCounter = 8;
	
	public Player() {
		super();
	}

	public Player(int x, int y, int width, int height, Game game, InputHandler inputHandler) {
		super(x, y, width, height, game);
		this.inputHandler = inputHandler;
		this.flapKeyReleased = true;
		this.framesCounter = 0;
	}

	@Override
	public void tick() {
		
		if (inputHandler.up.down) {
			setySpeed(getySpeed() + 5);
		}
		
		// Check for a single flap (Z key = A button)
		if (inputHandler.z.down && isFlapKeyReleased()) {
			setySpeed(getySpeed() + 5);
			setFlapKeyReleased(false);
		}
		
		if (!inputHandler.z.down) {
			setFlapKeyReleased(true);
		}
		
		// Check for constant flapping (X key = B button)
		framesCounter++;
		if (inputHandler.x.down) {
			if (framesCounter > framesBetweenFlaps) {
				setySpeed(getySpeed() + 5);
				framesCounter = 0;
			}
		}
		
		if (inputHandler.down.down) {
			//setySpeed(getySpeed() - 3);
		}
		
		if (inputHandler.left.down) {
			setxSpeed(-4);
		}
		
		if (inputHandler.right.down) {
			setxSpeed(4);
		}
		
		if (!inputHandler.left.down && !inputHandler.right.down) {
			setxSpeed(0);
		}
		
		// Gravity pull
		if (!isGrounded())
			setySpeed(getySpeed() - GRAVITY);
		
		// Move the player
		setX((int)Math.floor(getX() + getxSpeed()));
		setY((int)Math.floor(getY() - getySpeed()));
		
		System.out.println(getySpeed());
		
		// Go to the other side if the limit is crossed
		if (getX() <= -1 * getWidth() / 2) {
			setX(game.getWidth() - getWidth() / 2 - 1);
		} else if (getX() >= game.getWidth() - getWidth() / 2) {
			setX(-1 * getWidth() / 2 + 1);
		}
		
		// Prevent the player from going above or below the screen
		if (getY() < -1 * getHeight() / 3) {
			// Ceiling
			setY(-1 * getHeight() / 3);
			setySpeed(getySpeed() * -0.3);
		} else if (getY() > game.getHeight() - getHeight()) {
			// Ground
			setY(game.getHeight() - getHeight());
			setySpeed(0);
		} else {
			// Mid-air
			setGrounded(false);
			
			// If the player is just above the ground limit, mark it as grounded and set ySpeed = 0.
			if (getY() >= game.getHeight() - getHeight()) {
				setGrounded(true);
				setySpeed(0);
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		if (isVisible())
			g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
	}

	/**
	 * @return the flapKeyReleased
	 */
	public boolean isFlapKeyReleased() {
		return flapKeyReleased;
	}

	/**
	 * @param flapKeyReleased the flapKeyReleased to set
	 */
	public void setFlapKeyReleased(boolean flapKeyReleased) {
		this.flapKeyReleased = flapKeyReleased;
	}
	
}
