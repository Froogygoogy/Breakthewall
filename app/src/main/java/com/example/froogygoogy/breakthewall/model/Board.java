package com.example.froogygoogy.breakthewall.model;

import com.example.froogygoogy.breakthewall.framework.BallCollider;
import com.example.froogygoogy.breakthewall.framework.CollisionTime;

public class Board implements BallCollider{
    private float width;
    private float height;

    public Board(float width,float height)
    {
        this.height = height;
        this.width = width;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public float collisionTime(Ball ball) {
        float x = ball.getX(), y = ball.getY(), sx = ball.getSpeedX(), sy = ball.getSpeedY(), r = ball.getRadius();
        float h = 0, v=0;
        if(x < r || x > (this.width - r) || y < r || y > (this.height - r) )
        {
            return  CollisionTime.EPSILON;
        }
        else
        {
           if(sx < 0)//left
           {
               v = CollisionTime.pointVLine(x, y, sx, sy, r);
           }
           else if( sx > 0)//right
           {
               v = CollisionTime.pointVLine(x, y, sx, sy, width - r);
           }

           if(sy < 0)//top
           {
               h = CollisionTime.pointHLine(x, y, sx, sy, r);
           }
           else if(sy > 0)//bottom
           {
               h = CollisionTime.pointHLine(x, y, sx, sy, height - r);
           }

           if(h < v && h > 0)
           {
               return h;
           }
           else if(v > 0)
           {
               return v;
           }
           else
           {
               return  CollisionTime.EPSILON;
           }
        }
    }

    @Override
    public void bounce(Ball ball) {
        float x = ball.getX(), y = ball.getY(), r = ball.getRadius();

        if ((x - r) <= 0) {//left
            ball.setX(2 * r - x);
            ball.setSpeedX(Math.abs(ball.getSpeedX()));
        }
        else if ((x + r) >= width) {//right
            ball.setX(2 * (width - r) - x);
            ball.setSpeedX(-Math.abs(ball.getSpeedX()));
        }
        else if ((y - r) <= 0) {//top
            ball.setY(2 * r - y);
            ball.setSpeedY(Math.abs(ball.getSpeedY()));
        }
        else if ((y + r) >= height) {//bottom
            ball.setY(2 * (height - r) - y);
            ball.setSpeedY(-Math.abs(ball.getSpeedY()));
        }
    }
}
