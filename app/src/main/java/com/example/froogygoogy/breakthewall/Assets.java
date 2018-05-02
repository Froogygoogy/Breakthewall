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

        if(paddle != null)
        {
            paddle.recycle();
        }
        paddle = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.paddle),
                brickWidth * 8 / 5, brickHeight, true

        );


        if(bricks != null)
        {
            for (Bitmap brick:bricks
                    ) {
                brick.recycle();
            }
        }
        Bitmap brick1 = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brick),
                brickWidth, brickHeight, true

        );
        Bitmap brick2 = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brickgreen),
                brickWidth, brickHeight, true

        );
        Bitmap brick3 = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brickpurple),
                brickWidth, brickHeight, true

        );
        Bitmap brick4 = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brickred),
                brickWidth, brickHeight, true

        );
        Bitmap brick5 = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.brickyellow),
                brickWidth, brickHeight, true
        );

        bricks = new Bitmap[]{brick1,brick2,brick3,brick4,brick5};


    }
}
