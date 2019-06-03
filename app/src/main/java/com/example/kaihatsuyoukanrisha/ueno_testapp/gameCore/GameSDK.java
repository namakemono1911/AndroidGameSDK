package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore;

import android.content.Context;
import android.graphics.Point;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.ComponentInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Transform;
import com.example.kaihatsuyoukanrisha.ueno_testapp.scene.SceneInterface;

import javax.microedition.khronos.opengles.GL10;

public class GameSDK extends GameHandler implements GameSDKInterface {
    private static GameSDK sdk = null;

    private GameSDK() {
    }

    public static GameSDKInterface getSDK() {
        if (sdk == null) {
            sdk = new GameSDK();
        }

        return sdk;
    }

    @Override
    public boolean startup(Context context) {
        return super.startup(context);
    }

    public void setScene(SceneInterface ... scenes) {
        for (SceneInterface scene : scenes) {
            nextSceneVector.add(scene);
        }
    }

    public final Point getDisplaySize() {
        return renderer.getDisplaySize();
    }

    public GameObject createEmptyGameObject() {
        return objectFactory.createEmptyObject();
    }

    public GameObject createCamera() {
        return objectFactory.createCamera();
    }

    public void deleteObject(GameObject object) { objectManager.addGarbageObject(object); }

    @Override
    public void deleteComponent(ComponentInterface component) {
        objectManager.addGarbageComponent(component);
    }

    public GameObject createCamera(Transform transform) {
        return objectFactory.createCamera(transform);
    }

    public void setParent(GameObject parent, GameObject child) {
        objectManager.setParent(parent, child);
    }

    @Override
    public int getTextureID(int id) {
        return textureManager.getTextureID(id);
    }

    public boolean loadTexture(int imageID) {
        return textureManager.setTexture(imageID);
    }

    public void deleteAllTexture() {
        textureManager.release();
    }

    @Override
    public GL10 getGL() {
        return gl;
    }

    @Override
    public Context getContext() { return context; }
}
