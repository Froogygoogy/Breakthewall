package com.example.froogygoogy.breakthewall.testLevels;

import android.util.Log;

import com.example.froogygoogy.breakthewall.Assets;
import com.example.froogygoogy.breakthewall.framework.BallCollider;
import com.example.froogygoogy.breakthewall.model.Ball;
import com.example.froogygoogy.breakthewall.model.Board;
import com.example.froogygoogy.breakthewall.model.Brick;
import com.example.froogygoogy.breakthewall.model.Levels;
import com.example.froogygoogy.breakthewall.model.Paddle;

import java.util.LinkedList;
import java.util.List;

import static com.example.froogygoogy.breakthewall.model.Levels.NO_BRICK;
import static com.example.froogygoogy.breakthewall.testLevels.TestLevelsModel.State.PLAYING;
import static com.example.froogygoogy.breakthewall.testLevels.TestLevelsModel.State.WAITING_DOWN;
import static com.example.froogygoogy.breakthewall.testLevels.TestLevelsModel.State.WAITING_UP;

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
    int brickWidth;
    int brickHeight;
    int numLevel;
    State state;

    enum State{WAITING_UP,WAITING_DOWN,PLAYING}

    public Ball getBall(){
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public TestLevelsModel(float width, float height){
        brickWidth = (int) (width * WIDTH_FRACTION / BRICKS_IN_ROW);
        brickHeight = brickWidth * 2 / 5;

        this.boardWidth = brickWidth * BRICKS_IN_ROW;
        this.boardHeight = boardWidth / ASPECT_RATIO;
        board = new Board(boardWidth,boardHeight);
        bricks = new LinkedList<Brick>();
        ballColliders = new LinkedList<BallCollider>();

        Log.d("SPEED","LLEGO ANTES DE STARTLEVEL 0");
        startLevel(0);
    }

    public void startLevel(int level)
    {
        state = WAITING_UP;
        this.numLevel = level;
        paddle = new Paddle((boardWidth/2)-(Assets.paddle.getWidth()/2), (float) (( 0.9 * boardHeight)-(Assets.paddle.getHeight()/2)),Assets.paddle.getWidth(),Assets.paddle.getHeight(),boardWidth);
        ball = new Ball(boardWidth/2, paddle.getY() - Assets.ball.getWidth()/2 , Assets.ball.getWidth()/2);

        float x = (boardWidth/2)-(Assets.paddle.getWidth()/2);
        this.paddle.setX(x);
        this.paddle.setMoving(false);
        this.paddle.setTarget(x);
        Log.d("SPEED","LLEGO ANTES DE BALL");
        this.ball.setX(boardWidth/2);
        this.ball.setY(paddle.getY() - Assets.ball.getWidth()/2);
        this.ball.setSpeedX((float) (boardWidth * (Math.sqrt(2)/2)));
        this.ball.setSpeedY(-ball.getSpeedX());
        bricks.clear();
        Log.d("SPEED","LLEGO ANTES DE GETLEVEL");
        int[][] lvl = Levels.getLevel(level);
        Log.d("SPEED","LLEGO DESPUES DE GETLEVEL");

        float yBrick = 0.2f * boardHeight;

        for (int row = 0 ; row < lvl.length ; row++) {
            Log.d("SPEED","FOR FUERA"+row);

            for (int col = 0 ; col < lvl[row].length ; col++)
            {
                Log.d("SPEED","FOR DENTRO"+col);

                if (lvl[row][col] != NO_BRICK)
                {
                    Log.d("SPEED","IF DENTRO");

                    Brick brick = new Brick(col * brickWidth , 0.2f * board.getHeight()+row * brickHeight,
                            brickWidth, brickHeight, lvl[row][col]);
                    bricks.add(brick);
                }
            }
        }
        Log.d("SPEED","LLEGO ANTES DE ballColliders CLEAR");
        ballColliders.clear();
        ballColliders.add(paddle);
        ballColliders.add(board);
        ballColliders.addAll(bricks);
        Log.d("SPEED","FIN CREAR NIVEL");

    }

    public void onTouchUp() {
        switch (state)
        {
            case PLAYING:
                paddle.setMoving(false);
                break;
            case WAITING_UP:
                state = WAITING_DOWN;
                break;
            case WAITING_DOWN:
                break;
        }
    }

    public void onTouch(float x, float y){
        switch (state)
        {
            case PLAYING:
                paddle.setTarget(x);
                paddle.setMoving(true);
                break;
            case WAITING_UP:
                break;
            case WAITING_DOWN:
                state = PLAYING;
                break;
        }
    }

    public void onUpdate(float deltaTime){

        if(state != PLAYING)
        {
            return;
        }
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
                    if(bricks.isEmpty())
                    {
                        startLevel(this.numLevel +1);
                        Log.d("SPEED","DESPUES DE CAMBIAR DE NIVEL");
                        return;
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
