package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;
import dev.marianoalipi.balloonbattle.Animation;
import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.InputHandler;

public class Player extends Entity {

	private InputHandler inputHandler;
	private boolean flapKeyReleased;
	private int framesBetweenFlaps = 8, framesCounter = 8;
	private Animation animation;
	
	private enum State {IDLE, FLY, WALK};
	
	public Player() {
		super();
	}

	public Player(int x, int y, int width, int height, Game game, InputHandler inputHandler) {
		super(x, y, width, height, game);
		this.inputHandler = inputHandler;
		this.flapKeyReleased = true;
		this.framesCounter = 0;
		this.setAnimation(new Animation(Assets.balloonsTwo, 500));
		this.sprite = Assets.playerFly[0];
		this.direction = Direction.LEFT;
	}

	@Override
	public void tick() {
		
		// Check for a single flap (Z key = A button)
		if (inputHandler.z.down && isFlapKeyReleased()) {
			setySpeed(getySpeed() + 5);
			setFlapKeyReleased(false);
			setSpriteAuto(State.FLY, true);
			
			if (inputHandler.left.down)
				setxSpeed(getxSpeed() - 2);
			if (inputHandler.right.down)
				setxSpeed(getxSpeed() + 2);
		}
		
		// To check if Z key has been released.
		if (!inputHandler.z.down) {
			setFlapKeyReleased(true);
			if (!isGrounded())
				setSpriteAuto(State.FLY, false);
		}
		
		// Check for constant flapping (X key = B button)
		framesCounter++;
		if (inputHandler.x.down) {
			if (framesCounter > framesBetweenFlaps) {
				setySpeed(getySpeed() + 5);
				framesCounter = 0;
				setSpriteAuto(State.FLY, true);
				
				if (inputHandler.left.down)
					setxSpeed(getxSpeed() - 3);
				if (inputHandler.right.down)
					setxSpeed(getxSpeed() + 3);
			} else
				setSpriteAuto(State.FLY, false);
		}
		
		if (inputHandler.left.down) {
			setDirection(Direction.LEFT);
			if (isGrounded()) {
				setxSpeed(-4);
				setSpriteAuto(State.WALK, false);
			}
		}
		
		if (inputHandler.right.down) {
			setDirection(Direction.RIGHT);
			if (isGrounded()) {
				setxSpeed(4);
				setSpriteAuto(State.WALK, false);
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
		
		// Idle animation
		if (isGrounded() && getxSpeed() == 0 && getySpeed() == 0)
			setSpriteAuto(State.IDLE, false);
		
		// Move the player
		setX((int)Math.floor(getX() + getxSpeed()));
		setY((int)Math.floor(getY() - getySpeed()));
		
		System.out.println("xSpeed = " + getxSpeed() + ", ySpeed = " + getySpeed());
		
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
		if (isVisible()) {
			getAnimation().tick();
			g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);

			// Draw hitbox (for debugging)
			// g.setColor(Color.red);
			// g.drawRect(getX(), getY(), getWidth(), getHeight());
		}
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

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @param animation the animation to set
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	/**
	 * This method sets the sprite of the player according to its state (flying, idle, walking). It detects if it's facing left or right and sets its sprite to not-flapping or flapping, depending on the <code>flap</code> parameter.
	 */
	private void setSpriteAuto(State state, boolean flap) {
		switch (getDirection()) {
			case LEFT:
				switch (state) {
					case FLY:
						if (flap)
							setSprite(Assets.playerFly[1]);
						else
							setSprite(Assets.playerFly[0]);
						break;
					case IDLE:
						setSprite(Assets.playerIdle[0]);
						break;
					case WALK:
						setSprite(Assets.playerIdle[0]);
						break;
					default:
						break;
				}
			break;
			
			case RIGHT:
				switch (state) {
					case FLY:
						if (flap)
							setSprite(Assets.playerFly[3]);
						else
							setSprite(Assets.playerFly[2]);
						break;
					case IDLE:
						setSprite(Assets.playerIdle[1]);
						break;
					case WALK:
						setSprite(Assets.playerIdle[1]);
						break;
					default:
						break;
				}
		}
	}
	
}
