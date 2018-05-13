import javafx.embed.swing.JFXPanel;
import javafx.geometry.Rectangle2D;
import org.junit.Test;
import sample.GameObject;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class GameObjectTest {
	@Test
	public void testPosition() {
		GameObject gameObject = new GameObject();

		gameObject.setPosition(1, 1);

		double expX = 1;
		double expY = 1;

		assertThat(gameObject.getPositionX(), equalTo(expX));
		assertThat(gameObject.getPositionY(), equalTo(expY));
	}

	@Test
	public void testSize() {
		JFXPanel jfxPanel = new JFXPanel();
		GameObject gameObject = new GameObject("myRes/images/playerDestroyedImage.png");
		gameObject.setWidth(50);
		gameObject.setHeight(60);

		double expWidth = 50;
		double expHeight = 60;

		assertThat(gameObject.getWidth(), equalTo(expWidth));
		assertThat(gameObject.getHeight(), equalTo(expHeight));
	}

	@Test
	public void testLife() {
		GameObject gameObject = new GameObject();
		gameObject.setAlive(true);

		assertThat(gameObject.isAlive(), equalTo(true));

		gameObject.die();

		assertThat(gameObject.isAlive(), equalTo(false));
	}

	@Test
	public void testBoundary() {
		JFXPanel jfxPanel = new JFXPanel();
		GameObject gameObject = new GameObject("myRes/images/playerDestroyedImage.png", 50, 60);
		gameObject.setPosition(10, 25);

		Rectangle2D expResult = new Rectangle2D(10, 25, 50, 60);

		assertThat(gameObject.getBoundary(), equalTo(expResult));
	}

	@Test
	public void testIntersect() {
		JFXPanel jfxPanel = new JFXPanel();
		GameObject gameObject1 = new GameObject("myRes/images/playerDestroyedImage.png", 50, 60);
		GameObject gameObject2 = new GameObject("myRes/images/playerDestroyedImage.png", 50, 60);
		GameObject gameObject3 = new GameObject("myRes/images/playerDestroyedImage.png", 50, 60);

		gameObject2.setPosition(10, 10);
		gameObject3.setPosition(100, 100);

		assertThat(gameObject1.intersects(gameObject2), equalTo(true));
		assertThat(gameObject1.intersects(gameObject3), equalTo(false));

	}
}