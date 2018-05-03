package com.example.froogygoogy.breakthewall.testLevels;

import android.util.DisplayMetrics;
import android.util.Log;

import com.example.froogygoogy.breakthewall.Assets;
import com.example.froogygoogy.breakthewall.framework.GameActivity;
import com.example.froogygoogy.breakthewall.framework.IGameController;
import com.example.froogygoogy.breakthewall.testBricks.TestBrickController;

public class TestLevels extends GameActivity {
    private TestLevelsController controller;

    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new TestLevelsController(displayMetrics.widthPixels,displayMetrics.heightPixels, this);
        return controller;
    }


}
