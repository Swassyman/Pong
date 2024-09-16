package pong.PongJDK;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ScoreBoard {
    Stage stage;
    Label player1Score;
    Label player2Score;
    int player1ScoreValue = 0;
    int player2ScoreValue = 0;
    Label.LabelStyle style;

    public void createLabels(float worldWidth, float worldHeight) {
        stage = new Stage(new ScreenViewport());
        style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        player1Score = new Label(String.valueOf(player1ScoreValue), style);
        player2Score = new Label(String.valueOf(player2ScoreValue), style);
        stage.addActor(player1Score);
        stage.addActor(player2Score);
        player1Score.setPosition(0, worldHeight - player1Score.getHeight());
        player2Score.setPosition(worldWidth - player2Score.getWidth(), worldHeight - player2Score.getHeight());
    }

    public void drawLabels() {
        stage.act();
        stage.draw();
    }

    public void updateLabels() {
        player1Score.setText(String.valueOf(player1ScoreValue));
        player2Score.setText(String.valueOf(player2ScoreValue));
    }


}
