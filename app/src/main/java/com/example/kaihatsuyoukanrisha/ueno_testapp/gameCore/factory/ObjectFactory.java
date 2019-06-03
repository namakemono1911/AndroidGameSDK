package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.factory;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.Camera;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObjectManager;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Transform;

public class ObjectFactory {
    private GameObjectManager manager;

    public ObjectFactory(GameObjectManager manager) {
        this.manager = manager;
    }

    public GameObject createEmptyObject() {
        GameObject object = new GameObject();
        object.name = "empty";
        manager.addObject(object);

        return object;
    }

    public GameObject createCamera() {
        GameObject object = new GameObject();
        object.name = "camera";
        object.addComponent(new Camera(object));
        manager.addObject(object);

        return object;
    }

    public GameObject createCamera(Transform transform) {
        GameObject object = createCamera();
        object.transform = transform;
        manager.addObject(object);

        return object;
    }
}
