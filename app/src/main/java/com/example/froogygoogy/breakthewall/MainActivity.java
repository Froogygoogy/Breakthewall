package com.example.froogygoogy.breakthewall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.froogygoogy.breakthewall.testBall.TestBall;
import com.example.froogygoogy.breakthewall.testBricks.TestBricks;
import com.example.froogygoogy.breakthewall.testPaddle.TestPaddle;
import com.example.froogygoogy.breakthewall.testframework.TestFramework;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void testFramework(View view) {
        Intent intent = new Intent(this, TestFramework.class);
        startActivity(intent);
    }
    public void testBall(View view) {
        Intent intent = new Intent(this, TestBall.class);
        startActivity(intent);
    }

    public void testPaddle(View view){
        Intent intent = new Intent(this, TestPaddle.class);
        startActivity(intent);
    }
    public void testBricks(View view){
        Intent intent = new Intent(this, TestBricks.class);
        startActivity(intent);
    }
}
