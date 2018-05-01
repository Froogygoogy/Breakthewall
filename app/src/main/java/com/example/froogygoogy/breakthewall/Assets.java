package com.example.froogygoogy.breakthewall;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Assets {
    public static Bitmap ball;
    public static Bitmap[] bricks;//Esto hay que convertirlo a Array
    public static Bitmap paddle;

    public static void createAssets (Context context, int brickWidth, int brickHeight)
    {
        if (ball != null)
            ball.recycle();
        Resources resources = context.getResources();
        ball = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.ball),
                brickHeight, brickHeight, true
        );

/*
        if (bricks != null)
            bricks.recycle();
        bricks = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.bricks),
                brickHeight, brickHeight, true
        );

*/

        if(bricks[0] != null)
        {
            for (Bitmap brick:bricks
                 ) {
                brick.recycle();
            }
        }
        bricks[0] = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brick),
                brickWidth, brickHeight, true

        );
        bricks[1] = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brickgreen),
                brickWidth, brickHeight, true

        );
        bricks[2] = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brickpurple),
                brickWidth, brickHeight, true

        );
        bricks[3] = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brickred),
                brickWidth, brickHeight, true

        );
        bricks[4] = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brickyellow),
                brickWidth, brickHeight, true

        );

        if(paddle != null)
        {
            paddle.recycle();
        }
        paddle = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brick),
                brickWidth * 8 / 5, brickHeight, true

        );

    }
}
