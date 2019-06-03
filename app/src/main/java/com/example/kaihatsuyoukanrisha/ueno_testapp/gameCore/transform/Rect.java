package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform;

public class Rect {
    public int top, right, bottom, left;

    public Rect(int top, int right, int bottom, int left) {
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
    }

    public int getWidth() {
        return right - left;
    }

    public int getHeight() {
        return bottom - top;
    }
}

