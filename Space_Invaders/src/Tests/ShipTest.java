import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import org.junit.Test;
import sample.Game;
import sample.Ship;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ShipTest {
	@Test
	public void testInit() {
		JFXPanel jfxPanel = new JFXPanel();
		Ship ship = new Ship("myRes/images/shipSkin.png");

		Point2D expPos = new Point2D((Game.WIDTH - ship.getWidth())/2,
				Game.HEIGHT - ship.getHeight() + 5);

		assertThat(ship.getPosition(), equalTo(expPos));
		assertThat(ship.getSpeed(), equalTo(200));
		assertThat(ship.isAlive(), equalTo(true));
	}

	@Test
	public void testShoot() {
		JFXPanel jfxPanel = new JFXPanel();
		Ship ship = new Ship("myRes/images/shipSkin.png");
		ship.shoot();

		assertThat(ship.getBullet().isAlive(), equalTo(true));
		assertThat(ship.getBullet().isMovingUp(), equalTo(true));
	}

	@Test
	public void testCheckPlayerShoot() {
		JFXPanel jfxPanel = new JFXPanel();
		Ship ship = new Ship("myRes/images/shipSkin.png");
		ship.shoot();
		ship.getBullet().setPosition(10, -10);
		ship.check();

		assertThat(ship.getBullet().isAlive(), equalTo(false));
	}

	@Test
	public void testCheckPlayerDirections() {
		JFXPanel jfxPanel = new JFXPanel();
		Ship ship = new Ship("myRes/images/shipSkin.png");
		ship.setMovingRight(true);
		ship.setPosition(Game.WIDTH+200, 0);
		ship.check();

		assertThat(ship.isMovingRight(), equalTo(false));

		ship.setMovingLeft(true);
		ship.setPosition(-200, 0);
		ship.check();

		assertThat(ship.isMovingLeft(), equalTo(false));
	}
}