package pong.PongJDK;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    Texture player1Pong; //for left pong
    Texture player2Pong; //for right pong
    Texture ball; //for ball duh
    Texture board; //for board duh
    FitViewport viewport; //for the window
    SpriteBatch spriteBatch;
    Sprite pong1Sprite; //for left pong
    Sprite pong2Sprite; //for right pong

    @Override
    public void create() {
        player1Pong = new Texture("pong assets/arts/Player.png");
        player2Pong = new Texture("pong assets/arts/Computer.png");
        ball = new Texture("pong assets/arts/Ball.png");
        board = new Texture("pong assets/arts/Board.png");
        viewport = new FitViewport(8,5);
        spriteBatch = new SpriteBatch();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        pong1Sprite = new Sprite(player1Pong);
        float pong1SpriteWidth = pong1Sprite.getWidth();
        float pong1SpriteHeight = pong1Sprite.getHeight();
        pong1Sprite.setSize(.22f,1.25f); //setting the right size of the pongs
        pong1Sprite.setX(0); // setting position of pong to left side

        pong2Sprite = new Sprite(player2Pong);
        float pong2SpriteWidth = pong2Sprite.getWidth();
        float pong2SpriteHeight = pong2Sprite.getHeight();
        pong2Sprite.setSize(.22f,1.25f);
        pong2Sprite.setX(worldWidth - pong2SpriteWidth); //not working as intended
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); //updating viewport incase of resize of window
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined); //update camera projection matrix every resize
                                                                        // i saw it on the docs
    }

    @Override
    public void render() {
       draw(); //forms the textures needed every frame // possibly really bad practice
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK); //clears screen every frame
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined); //camera shiz
        spriteBatch.begin();

        float worldHeight = viewport.getWorldHeight();
        float worldWidth = viewport.getWorldWidth();
        spriteBatch.draw(board,0,0, worldWidth, worldHeight); //draw background board
        pong1Sprite.draw(spriteBatch); //draw left pong
        pong2Sprite.draw(spriteBatch); //draw right pong

        spriteBatch.end();
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
    }
}
