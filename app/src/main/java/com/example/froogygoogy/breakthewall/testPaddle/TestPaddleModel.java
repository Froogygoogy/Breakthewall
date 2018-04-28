package com.example.froogygoogy.breakthewall.testPaddle;

import com.example.froogygoogy.breakthewall.Assets;
import com.example.froogygoogy.breakthewall.framework.BallCollider;
import com.example.froogygoogy.breakthewall.model.Ball;
import com.example.froogygoogy.breakthewall.model.Board;
import com.example.froogygoogy.breakthewall.model.Paddle;

import java.util.LinkedList;


public class TestPaddleModel {

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    private Ball ball;
    private Board board;
    private Paddle paddle;
    LinkedList<BallCollider> ballColliders ;

    public TestPaddleModel(float boardWidth, float boardHeight) {
        board = new Board(boardWidth,boardHeight);
        ball = new Ball(boardWidth/2, boardHeight/4, Assets.ball.getWidth()/2);
        ball.setSpeedX((float) (boardWidth * (Math.sqrt(2)/2)));
        ball.setSpeedY(-ball.getSpeedX());
        paddle = new Paddle((boardWidth/2)-(Assets.paddle.getWidth()/2),(boardHeight/2)-(Assets.paddle.getHeight()/2),Assets.paddle.getWidth(),Assets.paddle.getHeight(),boardWidth);
        ballColliders = new LinkedList<BallCollider>();
        ballColliders.add(paddle);
        ballColliders.add(board);
    }

    public void onTouchUp() {
        paddle.setMoving(false);
    }

    public void onTouch(float x, float y) {
        paddle.setTarget(x);
        paddle.setMoving(true);
    }

    public void onUpdate(float deltaTime) {
        float remainingTime = deltaTime;
        float minCTime  = deltaTime;

        while (remainingTime>0)
        {
            int i = 0;
            int pos = 0;
            boolean collided = false;

            for (BallCollider collider : ballColliders) {
                float cTime =  collider.collisionTime(ball);
                if(cTime >= 0 && cTime < remainingTime)
                {
                    minCTime = cTime;
                    collided = true;
                    pos = i;
                }
                i++;
            }
            if(collided)
            {
                ball.move(minCTime);
                paddle.move(minCTime);
                ballColliders.get(pos).bounce(ball);
                remainingTime -= minCTime;
            }
            else
            {
                ball.move(remainingTime);
                paddle.move(remainingTime);
                break;
            }
        }
    }
}
