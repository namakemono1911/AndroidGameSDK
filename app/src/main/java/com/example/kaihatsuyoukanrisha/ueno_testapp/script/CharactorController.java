package com.example.kaihatsuyoukanrisha.ueno_testapp.script;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDK;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.input.AndroidInput;

public class CharactorController extends ScriptInterface {
    public CharactorController(GameObject object) {
        super(object);
    }

    public void update() {
        if (AndroidInput.multiDown()) {
            GameSDK.getSDK().deleteObject(object);
        }
    }
}
