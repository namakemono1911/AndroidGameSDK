package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform;

public class Rectf {
    public float top, right, bottom, left;

    public Rectf(float top, float right, float bottom, float left) {
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
    }

    public float getWidth() {
        return right - left;
    }

    public float getHeight() {
        return bottom - top;
    }
}
