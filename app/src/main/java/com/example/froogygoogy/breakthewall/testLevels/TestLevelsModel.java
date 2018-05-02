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

public class TestLevelsModel {
    private static final float ASPECT_RATIO = 2.0f/3;
    private static final float WIDTH_FRACTION = 0.94f;
    private static final int BRICKS_IN_ROW = 10;

    private Ball ball;
    private Board board;
    private Paddle paddle;
    LinkedList<BallCollider> ballColliders;

    public List<Brick> getBricks() {
        return bricks;
    }

    List<Brick> bricks;
    float boardWidth;
    float boardHeight;
    public Ball getBall(){
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public TestLevelsModel(float width, float height){
        int brickWidth = (int) (width * WIDTH_FRACTION / BRICKS_IN_ROW);
        int brickHeight = brickWidth * 2 / 5;

        this.boardWidth = brickWidth * BRICKS_IN_ROW;
        this.boardHeight = boardWidth / ASPECT_RATIO;

        board = new Board(boardWidth,boardHeight);


        paddle = new Paddle((boardWidth/2)-(Assets.paddle.getWidth()/2), (float) (( 0.9 * boardHeight)-(Assets.paddle.getHeight()/2)),Assets.paddle.getWidth(),Assets.paddle.getHeight(),boardWidth);

        ball = new Ball(boardWidth/2, paddle.getY() - Assets.ball.getWidth()/2 , Assets.ball.getWidth()/2);
        ball.setSpeedX((float) (boardWidth * (Math.sqrt(2)/2)));
        ball.setSpeedY(-ball.getSpeedX());

        float yBrick = 0.2f * boardHeight;
        bricks = new LinkedList<Brick>();
        for (int i = 0; i <= BRICKS_IN_ROW -1; i++)
        {
            Brick brick = new Brick(i * brickWidth, yBrick,
                    brickWidth, brickHeight, i % Assets.bricks.length);
            bricks.add(brick);
         }
        Log.d("YBRICK",""+yBrick);


        ballColliders = new LinkedList<BallCollider>();
        ballColliders.add(paddle);
        ballColliders.add(board);
        ballColliders.addAll(bricks);
    }

    public void onTouchUp() {
        paddle.setMoving(false);
    }

    public void onTouch(float x, float y){
        paddle.setTarget(x);
        paddle.setMoving(true);
    }

    public void onUpdate(float deltaTime){

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
