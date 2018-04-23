package com.example.froogygoogy.breakthewall.testBall;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.froogygoogy.breakthewall.Assets;
import com.example.froogygoogy.breakthewall.framework.Graphics;
import com.example.froogygoogy.breakthewall.framework.IGameController;
import com.example.froogygoogy.breakthewall.framework.TouchHandler;
import com.example.froogygoogy.breakthewall.model.Ball;
import com.example.froogygoogy.breakthewall.model.Board;

import java.util.List;

import static com.example.froogygoogy.breakthewall.framework.TouchHandler.TouchType.TOUCH_UP;

public class TestBallController implements IGameController{
    private static final float ASPECT_RATIO = 2.0f/3;
    private static final float WIDTH_FRACTION = 0.94f;
    private static final int BRICKS_IN_ROW = 10;
    private Graphics graphics;
    private Ball ball;
    private Board board;
    private float xboard,yboard;


    public TestBallController(int width, int height, Context context) {
        int brickWidth = (int) (width * WIDTH_FRACTION / BRICKS_IN_ROW);
        int brickHeight = brickWidth * 2 / 5;
        float boardWidth = brickWidth * BRICKS_IN_ROW;
        float boardHeight = boardWidth / ASPECT_RATIO;
        Assets.createAssets(context,brickWidth,brickHeight);
        xboard = (width - boardWidth) / 2;
        yboard = (height - boardHeight) / 2;
        this.ball = new Ball(boardWidth/2,boardHeight/2, Assets.ball.getWidth()/2);
        board = new Board(boardWidth,boardHeight);
        this.graphics = new Graphics(width,height);

    }

    @Override
    public void onUpdate(float deltaTime, List<TouchHandler.TouchEvent> touchEvents) {
        for (TouchHandler.TouchEvent event : touchEvents) {
            if(event.type != TOUCH_UP)
            {
                ball.setSpeedX(event.x - xboard - ball.getX());
                ball.setSpeedY(event.y - xboard - ball.getY());
            }
        }
        float remainingTime = deltaTime;
        while (remainingTime > 0)
        {
            float cTime =  board.collisionTime(ball);
            if(cTime > remainingTime)
            {
                ball.move(remainingTime);
                break;
            }
            else
            {
                ball.move(cTime);
                board.bounce(ball);
                remainingTime -= cTime;
            }
        }
    }

    @Override
    public Bitmap onDrawingRequested() {
        graphics.clear(0xFF000000);
        graphics.drawRect(xboard,yboard,board.getWidth(),board.getHeight(),0xFFFF0000);
        float x = ball.getX() + xboard,
                y = ball.getY() + yboard;
        graphics.drawBitmap(Assets.ball, x, y);
        return graphics.getFrameBuffer();
    }
}
