package pong.PongJDK;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main implements ApplicationListener {
    // left pong texture
    Texture player1Pong;
    // right pong texture
    Texture player2Pong;
    // ball texture
    Texture ball;
    // board texture
    Texture board;
    // viewport for the window
    FitViewport viewport;
    // spritebatch for drawing textures
    SpriteBatch spriteBatch;
    // sprite for the left pong
    Sprite pong1Sprite;
    // sprite for the right pong
    Sprite pong2Sprite;
    // sprite for the ball
    Sprite ballSprite;
    //Collision Rectangles
    public Rectangle paddle1Collision;
    public Rectangle paddle2Collision;
    // class
    private Ball ball1;

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
        paddle1Collision = new Rectangle();

        pong2Sprite = new Sprite(player2Pong);
        pong2Sprite.setSize(.22f,1.25f);
        paddle2Collision = new Rectangle();

        ballSprite = new Sprite(ball);
        ball1 = new Ball(worldWidth/2,worldHeight/2, 0,0);
        ball1.createBall();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); //updating viewport in case of resize of window
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined); //update camera projection matrix every resize
        // I saw it on the docs
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
        //spriteBatch.draw(board, 0, 0, worldWidth, worldHeight); //draw background board
        pong1Sprite.draw(spriteBatch); //draw left pong
        pong2Sprite.setPosition(worldWidth - pong2Sprite.getWidth(), pong2Sprite.getY());
        pong2Sprite.draw(spriteBatch); //draw right pong
        ball1.drawBall(spriteBatch);
        spriteBatch.end();
    }
    private void logicOfWaddles(){
        float delta = Gdx.graphics.getDeltaTime();
        float speed = 5f;
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        viewport.getWorldWidth();

        // clamp the paddles to the screen so they don't go off the bottom or top
        pong1Sprite.setY(MathUtils.clamp(pong1Sprite.getY(), 0, worldHeight-pong1Sprite.getHeight()));
        paddle1Collision.set(pong1Sprite.getX(), pong1Sprite.getY(), .22f, 1.25f);

        pong2Sprite.setY(MathUtils.clamp(pong2Sprite.getY(), 0, worldHeight-pong2Sprite.getHeight()));
        paddle2Collision.set(pong2Sprite.getX(), pong2Sprite.getY(), .22f, 1.25f    );

        ball1.updateBall(paddle1Collision, paddle2Collision, worldWidth, worldHeight);
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
