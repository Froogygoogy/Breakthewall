package com.example.froogygoogy.breakthewall.framework;

import com.example.froogygoogy.breakthewall.model.Ball;

/**
 * Created by jvilar on 4/04/18.
 */

public class CollisionTime {
    public static final float EPSILON = 0.001f;

    public static float ballRectangular(Ball ball, Rectangular rectangular) {
        float x = ball.getX(), y = ball.getY(),
            sx = ball.getSpeedX() - rectangular.getSpeedX(),
            sy = ball.getSpeedY() - rectangular.getSeepdY(),
            r = ball.getRadius();
        float xr = rectangular.getX(), yr = rectangular.getY(),
                w = rectangular.getWidth(), h = rectangular.getHeight();

        float time, t2;

        if (sx > 0)
            time = CollisionTime.pointVSegment(x, y, sx, sy, xr - r, yr, yr + h);
        else
            time = CollisionTime.pointVSegment(x, y, sx, sy, xr + w + r, yr, yr + h);

        if (sy > 0)
            t2 = CollisionTime.pointHSegment(x, y, sx, sy, xr, xr + w, yr - r);
        else
            t2 = CollisionTime.pointHSegment(x, y, sx, sy, xr, xr + w, yr + h + r);

        if (t2 >= 0 && (time < 0 || t2 < time))
            time = t2;

        // Check corners
        t2 = CollisionTime.pointCircle(x, y, sx, sy, xr, yr, r);
        if (t2 >= 0 && (time < 0 || t2 < time))
            time = t2;
        t2 = CollisionTime.pointCircle(x, y, sx, sy, xr + w, yr, r);
        if (t2 >= 0 && (time < 0 || t2 < time))
            time = t2;
        t2 = CollisionTime.pointCircle(x, y, sx, sy, xr + w, yr + h, r);
        if (t2 >= 0 && (time < 0 || t2 < time))
            time = t2;
        t2 = CollisionTime.pointCircle(x, y, sx, sy, xr, yr + h, r);
        if (t2 >= 0 && (time < 0 || t2 < time))
            time = t2;
        return time;
    }

    public static float pointHLine(float x, float y, float sx, float sy, float yl) {
        if (sy == 0)
            return y == yl ? 0 : -1;
        if (Math.abs(yl - y) < EPSILON)
            return -1;
        return (yl - y) / sy;
    }


    public static float pointVLine(float x, float y, float sx, float sy, float xl) {
        if (sx == 0)
            return x == xl ? 0 : -1;
        if (Math.abs(xl - x) < EPSILON)
            return -1;
        return (xl - x) / sx;
    }

    public static float pointHSegment (float x, float y, float sx, float sy, float x1, float x2, float yh) {
        if (sy == 0)
            return y == yh ? 0 : -1;
        if (Math.abs(yh - y) < EPSILON)
            return -1;
        float t = (yh - y) / sy;
        float xp = x + t * sx;
        return (x1 <= xp && xp <= x2) ? t : -1;
    }

    public static float pointVSegment (float x, float y, float sx, float sy, float xv, float y1, float y2) {
        if (sx == 0)
            return x == xv ? 0 : -1;
        if (Math.abs(xv - x) < EPSILON)
            return -1;
        float t = (xv - x) / sx;
        float yp = y + t * sy;
        return (y1 <= yp && yp <= y2) ? t : -1;
    }

    public static float pointCircle (float x, float y, float sx, float sy, float xc, float yc, float r) {
        float dx = x - xc, dy = y - yc;
        float a = sx*sx + sy * sy, b = 2 * (sx * dx + sy * dy), c = dx * dx + dy * dy - r * r;
        float det = b*b - 4*a*c;

        if (det < 0)
            return -1;
        float rd = (float)Math.sqrt(det);
        float t1 = (-b - rd) / (2*a), t2 = (-b + rd) / (2*a);
        if (Math.min(Math.abs(t1), Math.abs(t2))< EPSILON)
            return -1;
        if (t1 < 0)
            return t2;
        if (t2 < 0)
            return t1;
        return Math.min(t1, t2);
    }
}
