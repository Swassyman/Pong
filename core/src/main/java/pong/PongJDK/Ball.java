package pong.PongJDK;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class Ball {
    Texture ball;
    private final Vector2 position;
    private final Vector2 velocity;
    private Sprite ballSprite;
    private Circle ballCollision;
    private SpriteBatch batch;

    private float roundDelayTimer = 3; // Delay period in seconds (adjust as needed)
    private boolean isDelayInProgress = false; // Flag to indicate if the delay period is in progress
    private boolean isRoundInProgress = true;

    Ball(float x, float y, float vx, float vy){
        this.position = new Vector2(x,y);
        this.velocity = new Vector2(vx,vy);
    }
    public void createBall(){
        ball = new Texture("pong assets/arts/Ball.png");
        ballCollision = new Circle(position.x, position.y, 0.2f);
        ballSprite = new Sprite(ball);
        ballSprite.setSize(0.2f,0.2f);
        SpriteBatch ballSpriteBatch = new SpriteBatch();
        velocity.x = -.75f;
    }
    public void updateBall(Rectangle paddle1Collision, Rectangle paddle2Collision, float worldWidth, float worldHeight) {
        float delta = Gdx.graphics.getDeltaTime();
        if(isDelayInProgress){
            roundDelayTimer -= delta;
            if(roundDelayTimer <= 0) {
                isDelayInProgress = false;
            }
        }
        else{
            ballSprite.setX(MathUtils.clamp(position.x, 0, worldWidth));
            ballSprite.setY(MathUtils.clamp(position.y, 0, worldHeight - ballCollision.radius*2));
            if(position.x < 0) {
                System.out.println("Player 2 scored");
                velocity.x = -.75f;
                velocity.y = 0;
                position.x = worldWidth/2;
                position.y = worldHeight/2;
                endRound();

            } else if(position.x > worldWidth) {
                System.out.println("Player 1 scored");
                velocity.x = -.75f;
                velocity.y = 0;
                position.x = worldWidth/2;
                position.y = worldHeight/2;
                endRound();
            }

            if(position.y < 0 || position.y > worldHeight - ballCollision.radius*2) {
                velocity.y = -velocity.y;
            }
            else if(Intersector.overlaps(ballCollision, paddle1Collision)) {
                float angle = (float) Math.atan2(position.y - (paddle1Collision.y + paddle1Collision.height/2), position.x - (paddle1Collision.x + paddle1Collision.width/2));
                velocity.set((float)Math.cos(angle), (float)Math.sin(angle));
            }
            else if(Intersector.overlaps(ballCollision, paddle2Collision)) {
                float angle = (float) Math.atan2(position.y - (paddle2Collision.y + paddle2Collision.height/2), position.x - (paddle2Collision.x + paddle2Collision.width/2));
                velocity.set((float)Math.cos(angle), (float)Math.sin(angle));
            }
            updatePositionBall();
        }
    }
    public void updatePositionBall() {
        float delta = Gdx.graphics.getDeltaTime();
        float BALL_SPEED = 5f;
        position.x += velocity.x * delta * BALL_SPEED;
        position.y += velocity.y * delta * BALL_SPEED;
        ballCollision.set(position.x, position.y, 0.2f);
    }
    public void drawBall(SpriteBatch batch) {
            ballSprite.setPosition(position.x, position.y);
            ballSprite.draw(batch);
    }

    public void endRound() {
        isDelayInProgress = true;
        roundDelayTimer = 3;
    }

}

