package com.example.froogygoogy.breakthewall.testLevels;

import android.util.Log;

import com.example.froogygoogy.breakthewall.Assets;
import com.example.froogygoogy.breakthewall.framework.BallCollider;
import com.example.froogygoogy.breakthewall.model.Ball;
import com.example.froogygoogy.breakthewall.model.Board;
import com.example.froogygoogy.breakthewall.model.Brick;
import com.example.froogygoogy.breakthewall.model.Paddle;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

public class TestLevelsModel {
    private static final float ASPECT_RATIO = 2.0f/3;
    private static final float WIDTH_FRACTION = 0.94f;
    private static final int BRICKS_IN_ROW = 10;


    private Ball ball;
    private Board board;
    private Paddle paddle;
    private Level level;


    LinkedList<BallCollider> ballColliders;
    public State state;

    public enum State {
        WAITING_UP, WAITING_DOWN, PLAYING
    }
    List<Brick> bricks;
    float boardWidth;
    float boardHeight;
    public List<Brick> getBricks() {
        return bricks;
    }
    public Ball getBall(){
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Level getLevel(){return level;}

    public TestLevelsModel(){


        //tiene que ser esto la ultima linea
        startLevel(0);
    }


    public void onTouchUp() {
        if(state == state.WAITING_UP){
            state = State.WAITING_DOWN;
        }

    }

    public void onTouch(float x, float y){
        if (state == State.WAITING_DOWN){
            state = State.PLAYING;

        }
    }


    public void startLevel(int n, com.example.froogygoogy.breakthewall.model.Level level){
       this.level = level;

        paddle = new Paddle((boardWidth/2)-(Assets.paddle.getWidth()/2), (float) (( 0.9 * boardHeight)-(Assets.paddle.getHeight()/2)),Assets.paddle.getWidth(),Assets.paddle.getHeight(),boardWidth);
        ball = new Ball(boardWidth/2, paddle.getY() - Assets.ball.getWidth()/2 , Assets.ball.getWidth()/2);
        ball.setSpeedX((float) (boardWidth * (Math.sqrt(2)/2)));
        ball.setSpeedY(-ball.getSpeedX());

        bricks.clear();

        //falta por acabar esto

        ballColliders.clear();
        state = State.WAITING_UP;

    }

    public void onUpdate(float deltaTime){

        if (state != State.PLAYING){return;}

        float remainingTime = deltaTime;
        float minCTime  = deltaTime;

        while (remainingTime>0)
        {
            int i = 0;
            int pos = 0;
            boolean colliding = false;

            for (BallCollider collider : ballColliders) {
                float cTime =  collider.collisionTime(ball);
                if(cTime >= 0 && cTime < remainingTime)
                {
                    minCTime = cTime;
                    colliding = true;
                    pos = i;
                }
                i++;
            }

            if(colliding) {
                ball.move(minCTime);
                paddle.move(minCTime);
                ballColliders.get(pos).bounce(ball);
                remainingTime -= minCTime;
                if(ballColliders.get(pos).getClass() == Brick.class)
                {
                    bricks.remove(ballColliders.get(pos));
                    ballColliders.remove(ballColliders.get(pos));
                    if(bricks.isEmpty()){
                        // call setLevel with the next level and do a return from the function

                    }
                }
            }

            else {
                ball.move(remainingTime);
                paddle.move(remainingTime);
                break;
            }
        }
    }



}
