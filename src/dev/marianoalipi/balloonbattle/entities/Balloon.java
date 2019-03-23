package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;

import dev.marianoalipi.balloonbattle.Animation;
import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;

public class Balloon extends Entity {

	private Entity owner;
	private int balloonsAmount;
	public enum BalloonColor {RED, PINK, GREEN, YELLOW};
	private BalloonColor balloonColor;
	private Animation balloonsTwoAnim, balloonsOneAnim;
	
	public Balloon () {
		super();
	}
	
	public Balloon (int x, int y, int width, int height, int balloonsAmount, BalloonColor balloonColor, Game game, Entity owner) {
		super(x, y, width, height, game);
		this.owner = owner;
		this.sprite = Assets.balloon;
		this.setBalloonColor(balloonColor);
		this.balloonsTwoAnim = new Animation(Assets.balloonsTwo.get(getBalloonColor().toString()), 400);
		this.balloonsOneAnim = new Animation(Assets.balloonsOne.get(getBalloonColor().toString()), 400);
		this.animation = balloonsTwoAnim;
		this.direction = Direction.LEFT;
		this.balloonsAmount = balloonsAmount;
	}
	
	@Override
	public void tick() {
		
		if (owner instanceof Player) {
			
			// Adjust a little horizontal offset for when the player is facing right
			if (owner.getDirection() == Direction.RIGHT)
				if (owner.isGrounded())
					setX(owner.getX());
				else
					setX(owner.getX() + 4);
			else
				setX(owner.getX());
			// Adjust a little vertical offset for when the player is walking
			if (owner.getAnimation() == Player.walkLeftAnim || owner.getAnimation() == Player.walkRightAnim)
				setY(owner.getY() - getHeight() + 3);
			else
				setY(owner.getY() - getHeight());
			
		} else if (owner instanceof Enemy) {
			
			// Check for collision with player
			Player player = game.getPlayer(); 
			if (getHitbox().intersects(player.getHitbox())) {
				// Remove one balloon
				setBalloonsAmount(getBalloonsAmount() - 1);
				
				// Make the player bounce a little
				double hitboxCenterX = getHitbox().getX() + getHitbox().getWidth() / 2,
					   playerCenterX = player.getX() + player.getHitbox().getWidth() / 2;
				if (playerCenterX <= hitboxCenterX)
					player.setxSpeed(Math.abs(player.getxSpeed()) * -1);
				else
					player.setxSpeed(Math.abs(player.getxSpeed()));
				
				if (player.getY() + player.getHitbox().getHeight() >= getHitbox().getY())
					player.setySpeed(0.5 * Math.abs(player.getySpeed()));
				
				/* WRONG PLACE: this is when the player's balloon is popped.
				// Remove one balloon
				pBalloons.setBalloonsAmount(pBalloons.getBalloonsAmount() - 1);				
				*/
			}
			
			// Adjust a little horizontal offset for when the enemy is facing right
			if (owner.getDirection() == Direction.RIGHT)
				setX(owner.getX() + 2);
			else
				setX(owner.getX());
			
			setY(owner.getY() - getHeight() + 3);
		}
		
		// Relocate hitbox and adjust size
		if (getBalloonsAmount() == 2) {
			getHitbox().setLocation(getX(), getY());
			getHitbox().setSize((int)(Game.SCALE * 16), (int)(Game.SCALE * 12));
		} else if (getBalloonsAmount() == 1) {
			getHitbox().setLocation(getX() + (int)(Game.SCALE * 4), getY());
			getHitbox().setSize((int)(Game.SCALE * 8), (int)(Game.SCALE * 12));
		} else {
			getHitbox().setLocation(owner.getX() + (int)(owner.getWidth() / 2), owner.getY() + (int)(owner.getHeight() / 2));
			getHitbox().setSize(0, 0);
		}
		
		// Tick animation and update sprite
		if (owner.isGrounded()) {
			if (getAnimation() != null) {
				getAnimation().tick();
				setSprite(getAnimation().getCurrentFrame());
			} else {
				setSprite(null);
			}
		} else {
			if (getAnimation() != null)
				setSprite(getAnimation().getFrames()[0]);
			else
				setSprite(null);
		}
		
		setAnimation(getBalloonsAmount() == 2 ? balloonsTwoAnim : (getBalloonsAmount() == 1 ? balloonsOneAnim : null));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
		
		// Draw hitbox (for debugging)
		//g.setColor(Color.red);
		//g.drawRect((int)hitbox.getX(), (int)hitbox.getY(), (int)hitbox.getWidth(), (int)hitbox.getHeight());
	}

	/**
	 * @return the balloonsAmount
	 */
	public int getBalloonsAmount() {
		return balloonsAmount;
	}

	/**
	 * @param balloonsAmount the balloonsAmount to set
	 */
	public void setBalloonsAmount(int balloonsAmount) {
		this.balloonsAmount = balloonsAmount;
	}

	/**
	 * @return the balloonColor
	 */
	public BalloonColor getBalloonColor() {
		return balloonColor;
	}

	/**
	 * @param balloonColor the balloonColor to set
	 */
	public void setBalloonColor(BalloonColor balloonColor) {
		this.balloonColor = balloonColor;
	}

}
