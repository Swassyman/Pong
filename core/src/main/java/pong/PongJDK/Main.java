package pong.PongJDK;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

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
        //loading textures and objects
        player1Pong = new Texture("pong assets/arts/Player.png");
        player2Pong = new Texture("pong assets/arts/Computer.png");
        ball = new Texture("pong assets/arts/Ball.png");
        board = new Texture("pong assets/arts/Board.png");
        viewport = new FitViewport(8,5);
        spriteBatch = new SpriteBatch();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        pong1Sprite = new Sprite(player1Pong);
        pong1Sprite.setSize(.22f,1.25f); //setting the right size of the pongs
       // pong1Sprite.setX(0); // setting position of pong to left side

        pong2Sprite = new Sprite(player2Pong);
        pong2Sprite.setSize(.22f,1.25f);
        float posPong2 = worldWidth - .23f; //position of pong 2
        //pong2Sprite.setX(worldWidth-.23f); //position of pong to right side
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); //updating viewport incase of resize of window
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined); //update camera projection matrix every resize
        // i saw it on the docs
    }

    @Override
    public void render() {
        // only redraw when something changes
        logicOfWaddles(); //to move the waddles
        draw(); //forms the textures needed every frame
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK); //clears screen every frame
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined); //camera shiz
        spriteBatch.begin();

        float worldHeight = viewport.getWorldHeight();
        float worldWidth = viewport.getWorldWidth();
        spriteBatch.draw(board, 0, 0, worldWidth, worldHeight); //draw background board
        pong1Sprite.draw(spriteBatch); //draw left pong
        pong2Sprite.setPosition(worldWidth - pong2Sprite.getWidth(), pong2Sprite.getY());
        pong2Sprite.draw(spriteBatch); //draw right pong

        spriteBatch.end();
    }
    private void logicOfWaddles(){
        float delta = Gdx.graphics.getDeltaTime();
        float speed = 3f;
        float worldHeight = viewport.getWorldHeight();
        float worldWidth = viewport.getWorldWidth();

        // clamp the paddles to the screen so they don't go off the bottom or top
        pong1Sprite.setY(MathUtils.clamp(pong1Sprite.getY(), 0, worldHeight-pong1Sprite.getHeight()));
        pong2Sprite.setY(MathUtils.clamp(pong2Sprite.getY(), 0, worldHeight-pong2Sprite.getHeight()));

        // move the paddles based on user input
        //Left Paddle
        float dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) dy = speed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) dy = -speed;
        pong1Sprite.translateY(dy * delta);

        dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) dy = speed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) dy = -speed;
        pong2Sprite.translateY(dy * delta);
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
