package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import sample.interfaces.canShoot;
import sample.interfaces.checker;
import sample.interfaces.moveable;
import sample.interfaces.renderable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Fleet implements checker, moveable, renderable, canShoot {
	private List<MovingGameObject> invaders;
	private List<MovingGameObject> bullets;
	private GameObject destroyedImage;
	private AudioClip invaderDieSound;
	private AudioClip bulletDestroyedSound;
	private boolean canAttack = false;
	private int numberOfBullet = 3;


	public Fleet(String filename) {
		init(filename);
	}

	public List<MovingGameObject> getInvaders() {
		return invaders;
	}

	public List<MovingGameObject> getBullets() {
		return bullets;
	}

	public void setCanAttack(boolean value) {
		canAttack = value;
	}

	public boolean canAttack() {
		return canAttack;
	}

	private void initInvaderBullets() {
		bullets = new ArrayList<>();
		for (int i = 0; i < 3; ++i) {
			MovingGameObject temp = new MovingGameObject("images/alien_shot.png");
			temp.setAlive(false);
			temp.setSpeed(7);

			bullets.add(temp);
		}
	}

	private void initBulletDestroyedSound() {
		bulletDestroyedSound = new AudioClip(getClass().getClassLoader().getResource("sounds/hitmarkerSound.mp3").toString());
		bulletDestroyedSound.setVolume(0.15);
	}

	private void initInvaderDestroyedSound() {
		invaderDieSound = new AudioClip(getClass().getClassLoader().getResource("sounds/invaderkilled.mp3").toString());
		invaderDieSound.setVolume(0.15);
	}

	private void initInvaders(String filename) {
		invaders = new ArrayList<>();
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 4; ++j) {
				MovingGameObject temp = new MovingGameObject(filename, 30, 30);
				temp.setPosition(2 + ((temp.getWidth() + 15) * i), 35 + ((temp.getHeight() + 15) * j));
				temp.setAlive(true);
				temp.setSpeed(150);
				temp.setMovingRight(true);

				invaders.add(temp);
			}
		}
	}

	private void init(String filename) {
		destroyedImage = new MovingGameObject("images/destroyed.png");

		initInvaderDestroyedSound();
		initBulletDestroyedSound();

		initInvaderBullets();

		initInvaders(filename);
	}

	public boolean intersectWithInvader(MovingGameObject bullet, GraphicsContext gc) {
		for (MovingGameObject invader : invaders) {
			if (invader.intersects(bullet) && invader.isAlive() && bullet.isAlive()) {
				invader.die();
				bullet.die();

				destroyedImage.setPosition(invader.getPositionX(), invader.getPositionY());
				destroyedImage.setAlive(true);
				destroyedImage.render(gc);

				invaderDieSound.play();

				return true;
			}
		}
		return false;
	}

	public void intersectWithBullet(MovingGameObject bullet, GraphicsContext gc) {
		for (MovingGameObject v : bullets) {
			if (v.intersects(bullet) && v.isAlive() && bullet.isAlive()) {
				v.die();
				bullet.die();
				bulletDestroyedSound.play();
				return;
			}
		}
	}

	public boolean intersect(MovingGameObject bullet, GraphicsContext gc) {
		boolean result = intersectWithInvader(bullet, gc);

		intersectWithBullet(bullet, gc);

		if (result) {
			canAttack = true;
			newFleet();
		}

		return result;
	}

	private void newFleet() {
		invaders = invaders.stream()
				.filter(MovingGameObject::isAlive)
				.collect(Collectors.toList());
		if (invaders.size() < numberOfBullet) {
			numberOfBullet = invaders.size();
		}
	}

	public void intersect(Ship player, GraphicsContext gc) {
		for (int i = 0; i < 3; i++) {
			if (player.intersects(bullets.get(i))) {
				bullets.get(i).die();
				player.die();
				player.getDestroyedImage().render(gc);
			}
		}
	}

	public boolean isDestroyed() {
		return invaders.isEmpty();
	}

	@Override
	public void render(GraphicsContext gc) {
		for (MovingGameObject v : invaders) {
			v.render(gc);
		}

		for (MovingGameObject v : bullets) {
			v.render(gc);
		}
	}

	@Override
	public void check() {
		for (MovingGameObject invader : invaders) {
			if (invader.getPositionX() > Game.WIDTH - invader.getWidth() &&
					invader.isMovingRight()) {
				for (MovingGameObject v : invaders) {
					v.setMovingLeft(true);
				}
				break;
			} else {
				if (invader.getPositionX() < 0 &&
						invader.isMovingLeft()) {
					for (MovingGameObject v : invaders) {
						v.setMovingRight(true);
					}
					break;
				}
			}
		}

		for (int i = 0; i < numberOfBullet; i++) {
			if (bullets.get(i).getPositionY() > Game.HEIGHT) {
				bullets.get(i).setMovingDown(false);
				bullets.get(i).die();
			}
		}
	}

	@Override
	public void update() {
		for (MovingGameObject v : invaders) {
			v.update();
		}

		for (MovingGameObject v : bullets) {
			v.update();
		}
	}

	@Override
	public void update(double time) {
		for (MovingGameObject v : invaders) {
			v.update(time);
		}

		for (MovingGameObject v : bullets) {
			v.update();
		}
	}

	@Override
	public void shoot() {
		if (canAttack) {
			Random rand = new Random();
			for (int i = 0; i < numberOfBullet; ++i) {
				if (!bullets.get(i).isAlive()) {
					int x = rand.nextInt(invaders.size());
					while (!invaders.get(x).isAlive()) {
						x = rand.nextInt(invaders.size());
					}
					bullets.get(i).setAlive(true);
					bullets.get(i).setPosition(invaders.get(x).getPositionX() + invaders.get(x).getWidth() / 2,
							invaders.get(x).getPositionY());
					bullets.get(i).setMovingDown(true);
				}
			}
		}
	}
}
