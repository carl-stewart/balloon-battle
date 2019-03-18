package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;

import dev.marianoalipi.balloonbattle.Animation;
import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;

public class Enemy extends Entity {

	public static enum EnemyColor {PINK, GREEN, YELLOW};
	private EnemyColor color;
	Animation flapLeftAnim, flapRightAnim;
	
	public Enemy() {
		super();
	}
	
	public Enemy(int x, int y, int width, int height, Game game, EnemyColor color) {
		super(x, y, width, height, game);
		this.color = color;
		this.flapLeftAnim = new Animation(Assets.enemyFlapLeft, 80);
		this.flapRightAnim = new Animation(Assets.enemyFlapRight, 80);
	}
	
	@Override
	public void tick() {
		
		// Make the enemy flap towards the middle of the screen.
		if (getY() > game.getHeight() * 0.4) {
			setySpeed(getySpeed() + 5);
			setAnimation( getDirection() == Direction.LEFT ? flapLeftAnim : flapRightAnim );
		} else if (getY() < game.getHeight() * 0.33) {
			setSprite(getDirection() == Direction.LEFT ? Assets.enemyFlapLeft[0] : Assets.enemyFlapRight[0]);
		} else {
			setSprite(getDirection() == Direction.LEFT ? Assets.enemyFlapLeft[0] : Assets.enemyFlapRight[0]);
		}
		
		// Gravity pull and friction.
		if (!isGrounded()) {
			setySpeed(getySpeed() - GRAVITY);
			if (getxSpeed() > 0.3 || getxSpeed() < -0.3)
				setxSpeed((getxSpeed() > 0 ? 1 : -1) * (Math.abs(getxSpeed()) - 0.0025));
			else
				setxSpeed(0);
		} else {
			if (getxSpeed() > 0.3 || getxSpeed() < -0.3)
				setxSpeed((getxSpeed() > 0 ? 1 : -1) * (Math.abs(getxSpeed()) - 0.1));
			else {
				setxSpeed(0);
			}
		}
		
		// Move the player
		setX((int)Math.floor(getX() + getxSpeed()));
		setY((int)Math.floor(getY() - getySpeed()));
		
		// Go to the other side if the limit is crossed
		if (getX() <= -1 * getWidth() / 2) {
			setX(game.getWidth() - getWidth() / 2 - 1);
		} else if (getX() >= game.getWidth() - getWidth() / 2) {
			setX(-1 * getWidth() / 2 + 1);
		}
		
		// Prevent the enemy  from going above or below the screen
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
			
			// If the enemy is just above the ground limit, mark it as grounded and set ySpeed = 0.
			if (getY() >= game.getHeight() - getHeight()) {
				setGrounded(true);
				setySpeed(0);
			}			
		}
		
		// Tick the animation and get the current sprite.
		if (getAnimation() != null) {
			getAnimation().tick();
			setSprite(getAnimation().getCurrentFrame());
		}		

		// Relocate hitbox
		getHitbox().setLocation(getX(), getY());
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
	}

	/**
	 * @return the color
	 */
	public EnemyColor getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(EnemyColor color) {
		this.color = color;
	}

}
