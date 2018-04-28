package com.example.froogygoogy.breakthewall;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Assets {
    public static Bitmap ball;
<<<<<<< HEAD
    public static Bitmap bricks;//Esto hay que convertirlo a Array

=======
    public static Bitmap paddle;
>>>>>>> 2c3a7bb7152e1dc71124a515d88885b03494d1bb

    public static void createAssets (Context context, int brickWidth, int brickHeight)
    {
        if (ball != null)
            ball.recycle();
        Resources resources = context.getResources();
        ball = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.ball),
                brickHeight, brickHeight, true
        );
<<<<<<< HEAD

        if (bricks != null)
            bricks.recycle();
        bricks = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.bricks),
                brickHeight, brickHeight, true
        );


=======
        if(paddle != null)
        {
            paddle.recycle();
        }
        paddle = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.paddle),
                brickWidth * 8 / 5, brickHeight, true
>>>>>>> 2c3a7bb7152e1dc71124a515d88885b03494d1bb
        );

    }
}
