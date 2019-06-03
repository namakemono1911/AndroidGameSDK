package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.manager;

import com.example.kaihatsuyoukanrisha.ueno_testapp.scene.SceneInterface;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Map<String, SceneInterface> sceneMap = new HashMap<>();

    public boolean setScene(String name, SceneInterface scene) {
        if (sceneMap.containsKey(name)) {
            return false;
        }

        sceneMap.put(name, scene);
        return true;
    }

    public SceneInterface getScene(String name) {
        return sceneMap.get(name);
    }
}
