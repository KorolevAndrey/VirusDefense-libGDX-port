package towerDefense.towers;

import towerDefense.Gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.Enemy;
import engine.graphics.OwnSprite;
import engine.projectiles.RocketFast;

public class RocketFastTower extends Tower {
	protected int delta;
	protected float shootingInterval;
	protected int bombRadius;

	public RocketFastTower(float x, float y, OwnSprite sprite, Gameplay game, float shootingInterval, float damage) {
		super(x, y, 100, 200, damage, game, shootingInterval);
		this.sprite = sprite;
		this.shootingInterval = shootingInterval;
		this.delta = (int) this.shootingInterval;
		this.name = "Echinozyt";
	}

	@Override
	public void draw(SpriteBatch batch) {
		this.sprite.draw(this.x * Gameplay.SIZE, this.y * Gameplay.SIZE, batch);

	}

	@Override
	public void shoot() {
		boolean done = false;
		for (Enemy enemy : this.game.getEnemies()) {
			if (enemy != null && !done) {
				float enemyX = enemy.getX();
				float enemyY = enemy.getY();
				float deltaX = enemyX - (this.getX() * Gameplay.DEFAULT_SIZE + Gameplay.DEFAULT_SIZE / 2);
				float deltaY = enemyY - (this.getY() * Gameplay.DEFAULT_SIZE + Gameplay.DEFAULT_SIZE / 2);

				float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				if (distance < this.radius + enemy.getRadius()) {
					RocketFast r = new RocketFast(this.x * Gameplay.DEFAULT_SIZE + Gameplay.DEFAULT_SIZE / 2, this.y
							* Gameplay.DEFAULT_SIZE + Gameplay.DEFAULT_SIZE / 2, this.bombRadius, this.damage, this.game, enemy);
					this.game.getProjectiles().add(r);

					done = true;
					this.game.getSoundHandler().play("shotT2");

				}
			}
		}

	}

	@Override
	public void update(int delta) {
		this.delta -= delta;
		if (this.delta <= 0) {
			this.delta = (int) this.shootingInterval;
			this.shoot();
		}
	}

	@Override
	public Tower clone() {
		return new RocketFastTower(this.x, this.y, this.sprite.clone(), this.game, this.shootingInterval, this.damage);
	}

}
