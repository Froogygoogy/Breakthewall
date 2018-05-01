package com.example.froogygoogy.breakthewall.testBricks;

import android.util.DisplayMetrics;

import com.example.froogygoogy.breakthewall.framework.GameActivity;
import com.example.froogygoogy.breakthewall.framework.IGameController;

public class TestBricks extends GameActivity {
    private TestBrickController controller;

    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new TestBrickController(displayMetrics.widthPixels,displayMetrics.heightPixels, this);
        return controller;
    }
}
