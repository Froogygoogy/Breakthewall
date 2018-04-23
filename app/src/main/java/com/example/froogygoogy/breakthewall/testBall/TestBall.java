package com.example.froogygoogy.breakthewall.testBall;

import android.content.Context;
import android.util.DisplayMetrics;

import com.example.froogygoogy.breakthewall.framework.GameActivity;
import com.example.froogygoogy.breakthewall.framework.IGameController;

public class TestBall extends GameActivity {
    private TestBallController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new TestBallController(displayMetrics.widthPixels,displayMetrics.heightPixels, this);
        return controller;
    }
}
