package com.example.froogygoogy.breakthewall.framework;

import com.example.froogygoogy.breakthewall.model.Ball;

/**
 * Created by jvilar on 4/04/18.
 */

public interface BallCollider {
    float collisionTime(Ball ball);
    void bounce(Ball ball);
}
