package sample;

import javafx.scene.media.AudioClip;
import sample.interfaces.checker;
import sample.interfaces.canShoot;

public class Ship extends MovingGameObject implements checker, canShoot {
	private MovingGameObject bullet;
	private GameObject destroyedImage;
	private AudioClip shootSound;
	private AudioClip explosionSound;

	public Ship(String filename) {
		super(filename, 50, 50);
		init();
	}

	public MovingGameObject getBullet() {
		return bullet;
	}

	public GameObject getDestroyedImage() {
		return destroyedImage;
	}

	private void initPlayerShoot() {
		bullet = new MovingGameObject("myRes/images/ship_shot.png");
		bullet.setSpeed(10);
	}

	private void initShootSound() {
		shootSound = new AudioClip(getClass().getClassLoader().getResource("sounds/shoot.mp3").toString());
		shootSound.setVolume(0.15);
	}

	private void initExplosionSound() {
		explosionSound = new AudioClip(getClass().getClassLoader().getResource("sounds/explosion.mp3").toString());
		explosionSound.setVolume(0.15);
	}

	private void init() {
		initPlayerShoot();
		initShootSound();
		initExplosionSound();

		destroyedImage = new GameObject("myRes/images/playerDestroyedImage.png", 50, 50);

		setPosition((Game.WIDTH - getWidth())/2, Game.HEIGHT-getHeight() + 5);
		setSpeed(200);
		setAlive(true);
	}

	private void checkPlayerShoot() {
		if (bullet.getPositionY() < 0) {
			bullet.die();
		}
	}

	@Override
	public void die() {
		super.die();
		destroyedImage.setPosition(getPositionX(), getPositionY());
		destroyedImage.setAlive(true);
		explosionSound.play();
	}

	@Override
	public void check() {
		if ((getPositionX() > Game.WIDTH - getWidth() + 7) &&
				isMovingRight()) {
			setMovingRight(false);
		} else {
			if ((getPositionX() < 0 - 7) &&
					isMovingLeft()) {
				setMovingLeft(false);
			}
		}

		checkPlayerShoot();
	}

	@Override
	public void shoot() {
		if (!bullet.isAlive()) {
			shootSound.play();

			bullet.setAlive(true);
			bullet.setMovingUp(true);
			bullet.setPosition(this.getPositionX() + this.getWidth() / 2 - 3,
					this.getPositionY() - 5);
		}
	}
}
