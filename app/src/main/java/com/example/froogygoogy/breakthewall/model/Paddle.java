package com.example.froogygoogy.breakthewall.model;

import com.example.froogygoogy.breakthewall.framework.BallCollider;
import com.example.froogygoogy.breakthewall.framework.CollisionTime;
import com.example.froogygoogy.breakthewall.framework.Rectangular;

public class Paddle implements BallCollider,Rectangular {
    private float x,y;
    private float width,height;
    private float speed;
    private float target;
    private boolean moving;
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public float getSpeedX() {
        if(!moving)
        {
            return 0;
        }
        else {
            return speed;
        }
    }

    @Override
    public float getSeepdY() {
        return 0;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Paddle(float x,float y, float width, float height, float speed)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.target = x;
        this.moving = false;
    }
    public void move(float deltaTime)
    {
        if(moving = true)
        {
            if( x < target)
            {
                x += deltaTime*speed;
                if(x > target)
                {
                    x = target;
                }
            }
            else
            {
                x -= deltaTime*speed;
                if(x < target)
                {
                    x = target;
                }
            }
        }
    }

    @Override
    public float collisionTime(Ball ball) {

        return CollisionTime.ballRectangular(ball,this);
    }

    @Override
    public void bounce(Ball ball) {
        float dx = ball.getX() - (x + width / 2);
        float dy = ball.getY() - (y + height / 2);
        dx = (float) (dx / Math.sqrt((Math.pow(dx,2)+Math.pow(dy,2))));
        dy  = (float) (dy / Math.sqrt((Math.pow(dx,2)+Math.pow(dy,2))));

        float ballSpeed = (float) Math.sqrt((Math.pow(ball.getSpeedX(),2)+Math.pow( ball.getSpeedY(),2)));

        ball.setSpeedX(ballSpeed*dx);
        ball.setSpeedY(ballSpeed*dy);
    }
}
