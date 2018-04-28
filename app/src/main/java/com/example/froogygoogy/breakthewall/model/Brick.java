package com.jvilaruji.breakthewall.model;

import static com.jvilaruji.breakthewall.model.CollisionTime.EPSILON;

/**
 * Created by jvilar on 6/04/18.
 */

public class Brick implements BallCollider, Rectangular {
    private float x, y;
    private float width, height;
    private int color;
    private int score;

    public Brick(float x, float y, float width, float height, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getSpeedX() {
        return 0;
    }

    @Override
    public float getSeepdY() {
        return 0;
    }

    public int getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public float collisionTime(Ball ball) {
        return CollisionTime.ballRectangular(ball, this);
    }

    @Override
    public void bounce(Ball ball) {
        float xb = ball.getX(), yb = ball.getY(), r = ball.getRadius();

        // Rebound against a border:

        if ( x <= xb && xb <= x + width) {
            if (yb + r >= y && yb < y) {
                ball.setSpeedY(-Math.abs(ball.getSpeedY()));
                ball.setY(2 * yb + r - y);
                return;
            }

            if (yb - r <= y + height && yb > y + height) {
                ball.setSpeedY(Math.abs(ball.getSpeedY()));
                ball.setY(2 * yb - r - y - height);
                return;
            }
        }

        if ( y <= yb && yb <= y + height) {
            if (xb + r >= x && xb < x) {
                ball.setSpeedX(-Math.abs(ball.getSpeedX()));
                ball.setX(2 * xb + r - x);
                return;
            }

            if (xb - r <= x + width && xb > x + width) {
                ball.setSpeedX(Math.abs(ball.getSpeedX()));
                ball.setX(2 * xb - r - x - width);
                return;
            }
        }


        float dx = xb - x, dy = yb - y;
        float r2 = r * r + EPSILON;

        if (dx < 0 && dy < 0 && dx * dx + dy * dy <= r2 ) {
            ball.setSpeedX(-Math.abs(ball.getSpeedX()));
            ball.setSpeedY(-Math.abs(ball.getSpeedY()));
            adjust(ball, dx, dy, r);
            return;
        }

        dx = xb - x - width;
        if (dx > 0 && dy < 0 && dx * dx + dy * dy <= r2 ) {
            ball.setSpeedX(Math.abs(ball.getSpeedX()));
            ball.setSpeedY(-Math.abs(ball.getSpeedY()));
            adjust(ball, dx, dy, r);
            return;
        }

        dy = yb - y - height;
        if (dx > 0 && dy > 0 && dx * dx + dy * dy <= r2 ) {
            ball.setSpeedX(Math.abs(ball.getSpeedX()));
            ball.setSpeedY(Math.abs(ball.getSpeedY()));
            adjust(ball, dx, dy, r);
            return;
        }

        dx = xb - x;
        if (dx < 0 && dy > 0 && dx * dx + dy * dy <= r2 ) {
            ball.setSpeedX(-Math.abs(ball.getSpeedX()));
            ball.setSpeedY(Math.abs(ball.getSpeedY()));
            adjust(ball, dx, dy, r);
            return;
        }
    }

    private void adjust(Ball ball, float dx, float dy, float r) {
        float norm = (float)Math.sqrt(dx*dx+dy*dy);
        float coef = (r - norm) / norm;
        ball.setX(ball.getX() + dx * coef);
        ball.setY(ball.getY() + dy * coef);
    }

}
