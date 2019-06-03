package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore;

import android.content.Context;
import android.graphics.Point;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.Camera;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.ComponentInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.opengl.GLRenderer;
import com.example.kaihatsuyoukanrisha.ueno_testapp.scene.SceneInterface;

import javax.microedition.khronos.opengles.GL10;

public interface GameSDKInterface {
    public boolean startup(Context context);
    public GameObject createEmptyGameObject();
    public GameObject createCamera();
    public void deleteObject(GameObject object);
    public void deleteComponent(ComponentInterface component);
    public void setParent(GameObject parent, GameObject child);
    public void setScene(SceneInterface... scenes);
    public Point getDisplaySize();
    public GLRenderer getRenderer();
    public void setCamera(Camera camera);
    public void removeCamera(Camera camera);
    public int getTextureID(int id);
    public boolean loadTexture(int imageID);
    public void deleteAllTexture();
    public GL10 getGL();
    public Context getContext();
}
