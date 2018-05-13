package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.interfaces.renderable;

import java.io.FileNotFoundException;

public class GameObject implements renderable {
	protected Image image;
	protected Point2D position;
	protected double width;
	protected double height;
	protected boolean alive;

	public GameObject() {
		position = new Point2D(0, 0);
	}

	public GameObject(String filename) {
		this();
		Image i = new Image(getClass().getClassLoader().getResource(filename).toString());
		setImage(i);
	}

	public GameObject(String filename, double requestedWidth, double requestedHeight) {
		this();
		Image i = new Image(getClass().getClassLoader().getResource(filename).toString(),
				requestedWidth, requestedHeight, false, false);
		setImage(i);
	}

	protected void setImage(Image image) {
		this.image = image;
		width = image.getWidth();
		height = image.getHeight();

	}

	public void setPosition(double x, double y) {
		position = new Point2D(x, y);
	}

	public Point2D getPosition() {
		return position;
	}

	public double getPositionX() {
		return position.getX();
	}

	public double getPositionY() {
		return position.getY();
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void die() {
		alive = false;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (alive) {
			gc.drawImage(image, getPositionX(), getPositionY());
		}
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(getPositionX(), getPositionY(), width, height);
	}

	public boolean intersects(GameObject other) {
		return other.getBoundary().intersects(this.getBoundary());
	}
}
