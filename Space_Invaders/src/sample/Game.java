package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Optional;

public class Game extends Canvas {
	private GraphicsContext gc;

	private Ship player;
	private Fleet fleet;

	private GameObject background;

	private myTimer timer = new myTimer();
	private long lastNanoTime;
	private boolean inGame;

	private int score = 0;

	public static double WIDTH, HEIGHT;

	private Pane root;

	public Game(double width, double height, Pane root) {
		super(width, height);
		WIDTH = width;
		HEIGHT = height;
		this.root = root;
		initElements();
	}

	public myTimer getTimer() {
		return timer;
	}

	public Ship getPlayer() {
		return player;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	private void initBackground() {
		background = new GameObject("images/gameWallpaper.jpg");
		background.setPosition(0, (getHeight() - background.getHeight()));
		background.setAlive(true);
	}

	private void initElements() {
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		Font.loadFont(getClass().getClassLoader().getResource("arcadeclassic.TTF").toExternalForm(), 10);
		gc.setFont(new Font("ArcadeClassic", 17));
		inGame = true;

		player = new Ship("images/shipSkin.png");

		fleet = new Fleet("images/invader.png");

		initBackground();

		lastNanoTime = System.nanoTime();

		timer.start();
	}

	public void checkElements() {
		player.check();
		fleet.check();
	}

	private void updateElements(double elapsedTime) {
		if (inGame) {
			player.update(elapsedTime);
			player.getBullet().update();
			fleet.update(elapsedTime);
		}
	}

	private void renderScore() {
		if (player.isAlive()) {
			gc.fillText("Your score: " + Integer.toString(score), 3, 15);
		}
	}

	private void renderScore(double x, double y) {
		gc.fillText("Your score: " + Integer.toString(score), x, y);
	}

	private void renderElements() {
		background.render(gc);

		if (fleet.intersect(player.getBullet(), gc)) {
			score += 100;
		}
		fleet.intersect(player, gc);

		fleet.render(gc);

		player.render(gc);
		player.getBullet().render(gc);

		renderScore();
	}

	private void gameOver() {
		ArrayList<Score> scores = XMLWorker.read(getClass().getClassLoader().getResource("scores.xml").toString());
		Text gameOverText = initGameOverText();
		Text newHighScoreText = initHighScoreText();
		Button saveBtn = initSaveBtn(scores);
		Button cancelBtn = initCancelBtn();
		if (scores.isEmpty()) {
			root.getChildren().add(gameOverText);
			transition(gameOverText);
		} else {
			if (score > scores.get(0).getValue()) {
				root.getChildren().add(newHighScoreText);
				transition(newHighScoreText);
			} else {
				root.getChildren().add(gameOverText);
				transition(gameOverText);
			}
		}

		root.getChildren().addAll(saveBtn, cancelBtn);
		root.getStylesheets().add("sample/styles/GameOverStyle.css");

		renderScore(WIDTH/2 - 65, HEIGHT/2 + 20);
	}

	private Text initGameOverText() {
		Text text = new Text(WIDTH/2 - 100, HEIGHT/2, "Game Over!");
		text.setFont(Font.font("ArcadeClassic", 40));

		return text;
	}

	private Text initHighScoreText() {
		Text text = new Text(WIDTH/2 - 135, HEIGHT/2, "New High Score!");
		text.setFont(Font.font("ArcadeClassic", 40));

		return text;
	}

	private Button initCancelBtn() {
		Button cancelBtn = new Button("Cancel");
		cancelBtn.setLayoutX(WIDTH/2 - 15);
		cancelBtn.setLayoutY(HEIGHT/2 + 30);
		cancelBtn.setOnAction(event -> System.exit(0));

		return cancelBtn;
	}

	private Button initSaveBtn(ArrayList<Score> scores) {
		Button saveBtn = new Button("Save");
		saveBtn.setLayoutX(WIDTH/2 - 115);
		saveBtn.setLayoutY(HEIGHT/2 + 30);
		saveBtn.setOnAction(event -> {
			TextInputDialog dialog = initDialog();

			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				if (!result.get().equals("")) {
					Score newScore = new Score(result.get(), score);
					scores.add(newScore);
					scores.sort((Score s1, Score s2) -> s2.getValue() - s1.getValue());
					XMLWorker.write("./src/myRes/scores.xml", scores);
					System.exit(0);
				}
			}
		});

		return saveBtn;
	}

	private TextInputDialog initDialog() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.initStyle(StageStyle.UTILITY);
		dialog.setGraphic(null);
		dialog.setTitle("Game Over!");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter your name: ");

		return dialog;
	}

	private void transition(Text text) {
		FillTransition fillTransition = new FillTransition(Duration.millis(200), text, Color.RED, Color.YELLOW);
		fillTransition.setCycleCount(Animation.INDEFINITE);
		fillTransition.setAutoReverse(true);
		fillTransition.play();
	}

	public class myTimer extends AnimationTimer {
		@Override
		public void handle(long currentNanoTime) {
			double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
			lastNanoTime = currentNanoTime;

			gc.clearRect(0, 0, getWidth(), getHeight());

			checkElements();

			updateElements(elapsedTime);

			renderElements();

			fleet.shoot();

			if (!player.isAlive()) {
				inGame = false;
				gameOver();
				this.stop();
			}

			if (fleet.isDestroyed()) {
				System.out.println("You win");
				inGame = false;
				this.stop();
			}
		}
	}
}