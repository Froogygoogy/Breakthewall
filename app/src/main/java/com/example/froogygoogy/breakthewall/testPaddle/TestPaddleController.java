package com.example.froogygoogy.breakthewall.testPaddle;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.froogygoogy.breakthewall.Assets;
import com.example.froogygoogy.breakthewall.framework.Graphics;
import com.example.froogygoogy.breakthewall.framework.IGameController;
import com.example.froogygoogy.breakthewall.framework.TouchHandler;


import java.util.List;

import static com.example.froogygoogy.breakthewall.framework.TouchHandler.TouchType.TOUCH_UP;

public class TestPaddleController implements IGameController {
    private static final float ASPECT_RATIO = 2.0f/3;
    private static final float WIDTH_FRACTION = 0.94f;
    private static final int BRICKS_IN_ROW = 10;

    private Graphics graphics;
    private TestPaddleModel model;
    private float xboard,yboard;

    float boardWidth;
    float boardHeight;


    public TestPaddleController(int width, int height, Context context) {
        int brickWidth = (int) (width * WIDTH_FRACTION / BRICKS_IN_ROW);
        int brickHeight = brickWidth * 2 / 5;
        this.boardWidth = brickWidth * BRICKS_IN_ROW;
        this.boardHeight = boardWidth / ASPECT_RATIO;

        Assets.createAssets(context,brickWidth,brickHeight);
        this.xboard = (width - boardWidth) / 2;
        this.yboard = (height - boardHeight) / 2;
        model = new TestPaddleModel(boardWidth,boardHeight);
        this.graphics = new Graphics(width,height);
    }

    @Override
    public void onUpdate(float deltaTime, List<TouchHandler.TouchEvent> touchEvents) {
        for (TouchHandler.TouchEvent event : touchEvents) {
            switch (event.type){
                case TOUCH_UP:
                    model.onTouchUp();
                    break;
                case TOUCH_DRAGGED: case TOUCH_DOWN:
                    model.onTouch(event.x - xboard, event.y - yboard);
                    break;
            }
        }
        model.onUpdate(deltaTime);
    }


    @Override
    public Bitmap onDrawingRequested() {
        graphics.clear(0xFF000000);
        graphics.drawRect(xboard,yboard,boardWidth,boardHeight,0xFFFF0000);
        float x = model.getBall().getX() + xboard, y = model.getBall().getY() + yboard;
        graphics.drawBitmap(Assets.paddle, model.getPaddle().getX() + xboard,
                model.getPaddle().getY() + yboard, xboard, xboard + boardWidth - 1);
        graphics.drawBitmap(Assets.ball, x, y);

        return graphics.getFrameBuffer();

    }
}
