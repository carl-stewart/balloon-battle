package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;

import dev.marianoalipi.balloonbattle.Animation;
import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;

public class Balloon extends Entity {

	private Entity owner;
	private int balloonsAmount;
	
	public Balloon () {
		super();
	}
	
	public Balloon (int x, int y, int width, int height, Game game, Entity owner) {
		super(x, y, width, height, game);
		this.owner = owner;
		this.sprite = Assets.balloonsTwo[0];
		this.animation = new Animation(Assets.balloonsTwo, 400);
		this.direction = Direction.LEFT;
		this.balloonsAmount = 2;
	}
	
	@Override
	public void tick() {
		
		if (owner instanceof Player) {
			// Adjust a little horizontal offset for when the player is facing right
			if (owner.getDirection() == Direction.RIGHT)
				setX(owner.getX() + 2);
			else
				setX(owner.getX());
			// Adjust a little vertical offset for when the player is walking
			if (owner.getAnimation() == Player.walkLeftAnim || owner.getAnimation() == Player.walkRightAnim)
				setY(owner.getY() - getHeight() + 3);
			else
				setY(owner.getY() - getHeight());
		} else if (owner instanceof Enemy) {
			// Adjust a little horizontal offset for when the enemy is facing right
			if (owner.getDirection() == Direction.RIGHT)
				setX(owner.getX() + 2);
			else
				setX(owner.getX());
			
			setY(owner.getY() - getHeight() + 3);
		}
		
		getHitbox().setLocation(getX(), getY());
		
		getAnimation().tick();
		setSprite(getAnimation().getCurrentFrame());
		
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

}
