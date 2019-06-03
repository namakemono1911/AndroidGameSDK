package com.example.kaihatsuyoukanrisha.ueno_testapp.script;

import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.input.AndroidInput;

public class CameraController extends ScriptInterface {
    public CameraController(GameObject object) {
        super(object);
    }

    public void init() {
        int i = 0;
    }

    public void update() {
        if (AndroidInput.hasMove()) {
            PointF move = AndroidInput.getAmountMovement(AndroidInput.TOUCH.ONE);
            object.transform.pos.x += move.x * 0.01f;
            object.transform.pos.y -= move.y * 0.01f;
        }
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        object.transform.pos.x += distanceX * 0.01f;
        object.transform.pos.y += distanceY * 0.01f;

        return true;
    }
}
