import javafx.geometry.Point2D;
import org.junit.Test;
import sample.MovingGameObject;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MovingGameObjectTest {
	@Test
	public void testVelocity() {
		MovingGameObject mgo = new MovingGameObject();
		mgo.setVelocity(15, 20);

		double expVelX = 15;
		double expVelY = 20;

		assertThat(mgo.getVelocityX(), equalTo(expVelX));
		assertThat(mgo.getVelocityY(), equalTo(expVelY));
	}

	@Test
	public void testMovingRight() {
		MovingGameObject mgo = new MovingGameObject();
		mgo.setSpeed(10);
		mgo.setAlive(true);

		mgo.setMovingRight(true);
		mgo.update();

		Point2D expPosition = new Point2D(10, 0);

		assertThat(mgo.getPosition(), equalTo(expPosition));
		assertThat(mgo.isMovingRight(), equalTo(true));
	}

	@Test
	public void testMovingLeft() {
		MovingGameObject mgo = new MovingGameObject();
		mgo.setSpeed(10);
		mgo.setAlive(true);

		mgo.setMovingLeft(true);
		mgo.update();

		Point2D expPosition = new Point2D(-10, 0);

		assertThat(mgo.getPosition(), equalTo(expPosition));
		assertThat(mgo.isMovingLeft(), equalTo(true));
	}

	@Test
	public void testMovingUp() {
		MovingGameObject mgo = new MovingGameObject();
		mgo.setSpeed(10);
		mgo.setAlive(true);

		mgo.setMovingUp(true);
		mgo.update();

		Point2D expPosition = new Point2D(0, -10);

		assertThat(mgo.getPosition(), equalTo(expPosition));
		assertThat(mgo.isMovingUp(), equalTo(true));
	}

	@Test
	public void testMovingDown() {
		MovingGameObject mgo = new MovingGameObject();
		mgo.setSpeed(10);
		mgo.setAlive(true);

		mgo.setMovingDown(true);
		mgo.update();

		Point2D expPosition = new Point2D(0, 10);

		assertThat(mgo.getPosition(), equalTo(expPosition));
		assertThat(mgo.isMovingDown(), equalTo(true));
	}
}