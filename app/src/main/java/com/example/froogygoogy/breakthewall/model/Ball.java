package com.example.froogygoogy.breakthewall.model;

public class Ball {
    private float radius;
    private float x,y,speedX,speedY;

    public Ball(float xPosition,float yPosition,float Radius)
    {
        this.radius = Radius;
        this.x = xPosition;
        this.y = yPosition;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public  void move(float deltaTime)
    {
        x += speedX * deltaTime;
        y += speedY * deltaTime;
    }

    public float getRadius() {
        return radius;
    }
}
