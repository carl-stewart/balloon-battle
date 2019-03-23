package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.marianoalipi.balloonbattle.Animation;
import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;

public class Enemy extends Entity {

	public static enum EnemyColor {PINK, GREEN, YELLOW};
	private EnemyColor color;
	private Balloon balloons;
	protected static Animation flapLeftAnim, flapRightAnim, fallingAnim;
	
	public Enemy() {
		super();
	}
	
	public Enemy(int x, int y, int width, int height, Game game, EnemyColor color) {
		super(x, y, width, height, game);
		this.color = color;
		this.hitbox = new Rectangle(x, y, (int)(getWidth() * 0.9), getHeight());
		
		this.balloons = new Balloon(getX(), (int)(getY() - Game.SCALE * 12), (int)(Game.SCALE * 16), (int)(Game.SCALE * 12), 1, Balloon.BalloonColor.PINK, game, this);
		flapLeftAnim = new Animation(Assets.enemyFlapLeft, 80);
		flapRightAnim = new Animation(Assets.enemyFlapRight, 80);
		fallingAnim = new Animation(Assets.enemyFalling, 50);
	}
	
	@Override
	public void tick() {
		
		if (balloons.getBalloonsAmount() > 0) {
			
			Balloon pBalloons = game.getPlayer().getBalloons();
			// Check for collision with player's balloons
			if (getHitbox().intersects(pBalloons.getHitbox())) {
				// Remove one balloon
				if (!pBalloons.isInvincible()) {
					pBalloons.setBalloonsAmount(pBalloons.getBalloonsAmount() - 1);
				}
				
				// Make the enemy bounce a little
				double hitboxCenterX = getHitbox().getX() + getHitbox().getWidth() / 2,
						pBalloonsCenter = pBalloons.getHitbox().getX() + pBalloons.getHitbox().getWidth() / 2;
				if (pBalloonsCenter <= hitboxCenterX) {
					setxSpeed(Math.abs(getxSpeed()) * -1);
					if (!pBalloons.isInvincible())
						game.getPlayer().setxSpeed(Math.abs(game.getPlayer().getxSpeed()));
				}
				else {
					setxSpeed(Math.abs(getxSpeed()));
					if (!pBalloons.isInvincible())
						game.getPlayer().setxSpeed(-1 * Math.abs(game.getPlayer().getxSpeed()));
				}
				
				setySpeed(0.5 * Math.abs(getySpeed()));

				if (pBalloons.getBalloonsAmount() > 0)
					game.getPlayer().setySpeed(-5);
				else
					game.getPlayer().setySpeed(10);
				
				pBalloons.setInvincible(true);
			}
			
			// Make the enemy flap towards the middle of the screen.
			if (getY() > game.getHeight() * 0.4) {
				setySpeed(getySpeed() + 5);
				setAnimation( getDirection() == Direction.LEFT ? flapLeftAnim : flapRightAnim );
			} else if (getY() < game.getHeight() * 0.33) {
				setSprite(getDirection() == Direction.LEFT ? Assets.enemyFlapLeft[0] : Assets.enemyFlapRight[0]);
			} else {
				setSprite(getDirection() == Direction.LEFT ? Assets.enemyFlapLeft[0] : Assets.enemyFlapRight[0]);
			}
		} else {
			if (!isGrounded()) {
				// No balloons: enemy is falling.
				setAnimation(fallingAnim);
				setxSpeed(0);
			} else {
				setAnimation(null);
				setSprite(Assets.enemyIdle[getDirection() == Direction.LEFT ? 0 : 1]);
			}
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
		
		// Move the enemies
		setX((int)Math.floor(getX() + getxSpeed()));
		setY((int)Math.floor(getY() - getySpeed()));
	
		// Relocate hitbox
		getHitbox().setLocation(getX(), getY());
		
		// Tick balloons
		balloons.tick();
		
		// Tick the animation and get the current sprite.
		if (getAnimation() != null) {
			getAnimation().tick();
			setSprite(getAnimation().getCurrentFrame());
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
		
		// Render balloons
		balloons.render(g);
		
		// Draw hitbox (for debugging)
		//g.setColor(Color.red);
		//g.drawRect((int)hitbox.getX(), (int)hitbox.getY(), (int)hitbox.getWidth(), (int)hitbox.getHeight());

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
