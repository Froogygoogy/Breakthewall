package com.example.froogygoogy.breakthewall.testPaddle;

import android.util.DisplayMetrics;

import com.example.froogygoogy.breakthewall.framework.GameActivity;
import com.example.froogygoogy.breakthewall.framework.IGameController;

public class TestPaddle extends GameActivity {
    private TestPaddleController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new TestPaddleController(displayMetrics.widthPixels,displayMetrics.heightPixels, this);
        return controller;
    }
}
