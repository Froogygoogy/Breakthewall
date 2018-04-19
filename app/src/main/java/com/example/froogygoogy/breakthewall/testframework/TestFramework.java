package com.example.froogygoogy.breakthewall.testframework;

import android.util.DisplayMetrics;

import com.example.froogygoogy.breakthewall.framework.GameActivity;
import com.example.froogygoogy.breakthewall.framework.IGameController;

public class TestFramework extends GameActivity {
    private   TestFrameworkController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        controller = new TestFrameworkController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/10);
        return controller;
    }
}
