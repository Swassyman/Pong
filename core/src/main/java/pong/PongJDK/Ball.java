package pong.PongJDK;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    private Main main;
    private final float BALL_SPEED = 5f;
    private Vector2 position;
    private Vector2 velocity;
    private Sprite sprite;
    private Rectangle collision;

    Ball(float x, float y, float vx, float vy){
        this.position = new Vector2(x,y);
        this.velocity = new Vector2(vx,vy);
    }

    public void updateBall(){
        float delta = Gdx.graphics.getDeltaTime();
        position.x += velocity.x * delta * BALL_SPEED;
        position.y += velocity.y * delta * BALL_SPEED;
        collision.set(position.x, position.y, position.x, position.y);
    }

    public void drawBall(){
        sprite.setPosition(position.x, position.y);
        sprite.draw(main.spriteBatch);
    }
}
