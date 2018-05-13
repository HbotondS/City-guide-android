package sample;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import sample.interfaces.moveable;

public class MovingGameObject extends GameObject implements moveable {
	private Point2D velocity;
	private boolean movingRight;
	private boolean movingLeft;
	private boolean movingUp;
	private boolean movingDown;
	private int speed;

	public MovingGameObject() {
		super();
		velocity = new Point2D(0, 0);
	}

	public MovingGameObject(String filename) {
		this();
		Image i = new Image(filename);
		setImage(i);
	}

	public MovingGameObject(String filename, double requestedWidth, double requestedHeight) {
		this();
		Image i = new Image(filename, requestedWidth, requestedHeight, false, false);
		setImage(i);
	}

	public void setVelocity(double x, double y) {
		velocity = new Point2D(x, y);
	}

	public double getVelocityX() {
		return velocity.getX();
	}

	public double getVelocityY() {
		return velocity.getY();
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public int getSpeed() {
		return speed;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
		movingLeft =false;
		setVelocity(movingRight, speed, 0);
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
		movingRight = false;
		setVelocity(movingLeft, -speed, 0);
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
		movingDown = false;
		setVelocity(movingUp, 0, -speed);
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
		movingUp = false;
		setVelocity(movingDown, 0, speed);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	private void setVelocity(boolean direction, double x, double y) {
		if (direction) {
			setVelocity(x, y);
		} else {
			setVelocity(0, 0);
		}
	}

	@Override
	public void update(double time) {
		if (alive) {
			position = position.add(getVelocityX() * time, getVelocityY() * time);
		}
	}

	@Override
	public void update() {
		if (alive) {
			position = position.add(velocity);
		}
	}
}
