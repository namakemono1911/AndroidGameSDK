package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;

public class Component implements ComponentInterface {
    public GameObject object;

    public Component(GameObject object) {
        this.object = object;
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
