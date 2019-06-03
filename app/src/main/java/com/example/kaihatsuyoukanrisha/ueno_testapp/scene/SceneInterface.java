package com.example.kaihatsuyoukanrisha.ueno_testapp.scene;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDK;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDKInterface;

abstract public class SceneInterface {
    String sceneName;
    GameSDKInterface sdk;

    public SceneInterface(String name) {
        this.sceneName = name;
        sdk = GameSDK.getSDK();
    }

    abstract public void setup();
    abstract public void release();
}
