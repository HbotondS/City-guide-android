import javafx.embed.swing.JFXPanel;
import javafx.scene.canvas.Canvas;
import org.junit.Test;
import sample.Fleet;
import sample.Game;
import sample.MovingGameObject;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class FleetTest {
	@Test
	public void testCheckInvadersMoving() {
		JFXPanel jfxPanel = new JFXPanel();
		Fleet fleet = new Fleet("myRes/images/invader.png");
		fleet.getInvaders().get(3).setPosition(Game.WIDTH+200, 0);
		fleet.check();

		assertThat(fleet.getInvaders().get(3).isMovingLeft(), equalTo(true));

		fleet.getInvaders().get(3).setPosition(-200, 0);
		fleet.check();

		assertThat(fleet.getInvaders().get(3).isMovingRight(), equalTo(true));
	}

	@Test
	public void testCheckBulletMoving() {
		JFXPanel jfxPanel = new JFXPanel();
		Fleet fleet = new Fleet("myRes/images/invader.png");
		fleet.setCanAttack(true);
		fleet.shoot();
		fleet.getBullets().get(1).setPosition(0, Game.HEIGHT+200);
		fleet.check();

		assertThat(fleet.getBullets().get(1).isAlive(), equalTo(false));
	}

	@Test
	public void testIntersect() {
		JFXPanel jfxPanel = new JFXPanel();
		Fleet fleet = new Fleet("myRes/images/invader.png");

		MovingGameObject mgo = fleet.getInvaders().get(3);

		fleet.intersect(mgo, (new Canvas()).getGraphicsContext2D());

		assertThat(mgo.isAlive(), equalTo(false));
		assertThat(fleet.canAttack(), equalTo(true));
	}
}