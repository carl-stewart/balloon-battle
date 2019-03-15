package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;

import dev.marianoalipi.balloonbattle.Animation;
import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;

public class Balloon extends Entity {

	private Player player;
	private int balloonsAmount;
	
	public Balloon () {
		super();
	}
	
	public Balloon (int x, int y, int width, int height, Game game, Player player) {
		super(x, y, width, height, game);
		this.player = player;
		this.sprite = Assets.balloonsTwo[0];
		this.animation = new Animation(Assets.balloonsTwo, 400);
		this.direction = Direction.LEFT;
		this.balloonsAmount = 2;
	}
	
	@Override
	public void tick() {
		
		// Adjust a little horizontal offset for when the player is facing right
		if (player.getDirection() == Direction.RIGHT)
			setX(player.getX() + 2);
		else
			setX(player.getX());
		// Adjust a little vertical offset for when the player is walking
		if (player.getAnimation() == player.walkLeftAnim || player.getAnimation() == player.walkRightAnim)
			setY(player.getY() - getHeight() + 3);
		else
			setY(player.getY() - getHeight());
		
		
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
