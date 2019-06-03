package com.example.kaihatsuyoukanrisha.ueno_testapp;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDK;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDKInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.input.AndroidInput;
import com.example.kaihatsuyoukanrisha.ueno_testapp.scene.TestScene;

public class MainActivity extends AppCompatActivity {
    private ImageView earth;
    private GLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameSDKInterface sdk = GameSDK.getSDK();
        sdk.startup(this);
        sdk.setScene(new TestScene());
        glView = sdk.getRenderer();
        setContentView(glView);
    }

    @Override
    public void onResume() {
        super.onResume();
        glView.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        glView.onPause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AndroidInput.setTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void changeActivity(View view) {
    }
}
