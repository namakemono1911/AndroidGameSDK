package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore;


import android.content.Context;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.Camera;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.factory.ObjectFactory;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObjectManager;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.manager.TextureManager;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.opengl.GLRenderer;
import com.example.kaihatsuyoukanrisha.ueno_testapp.scene.SceneInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class GameHandler implements Runnable {
    protected GLRenderer renderer = null;
    protected TextureManager textureManager;
    protected GameObjectManager objectManager;
    protected ObjectFactory objectFactory = null;
    protected List<Camera> cameraList = new ArrayList<>();
    protected Vector<SceneInterface> sceneVector = new Vector<>();
    protected Vector<SceneInterface> nextSceneVector = new Vector<>();
    protected Context context;

    protected GL10 gl;
    private boolean whetherUpdate = true;

    protected GameHandler() {}

    public boolean startup(Context context) {
        renderer = new GLRenderer(context, this);
        textureManager = new TextureManager(context);
        objectManager = new GameObjectManager();
        objectFactory = new ObjectFactory(objectManager);
        this.context = context;

        return true;
    }

    public boolean init() {
        changeScene();

        new Thread(this).start();

        return true;
    }

    public boolean uninit() {
        renderer.uninit();
        renderer = null;
        textureManager = null;
        objectManager = null;

        return true;
    }

    public void update() {
        if (!checkWhetherUpdate()) {
            return;
        }

        //更新処理
        objectManager.updateGameObjects();

        objectManager.updateTransform();

        changeWhetherUpdate(false);
    }

    public void draw(GL10 gl) {
        for (int i = 0; i < 500; i++) {
            if (checkWhetherUpdate()) {
                continue;
            }

            //描画処理
            for (Camera c : cameraList) {
                renderer.beginRendering(gl, c);
                objectManager.drawGameObjects(gl);
            }
            break;
        }

        changeWhetherUpdate(true);
    }

    public void setGL10(GL10 gl) { this.gl = gl;}

    public void setCamera(Camera camera) { cameraList.add(camera); }

    public void removeCamera(Camera camera) { cameraList.remove(camera); }

    protected int getTexture(int id) { return textureManager.getTextureID(id); }

    public GLRenderer getRenderer() { return renderer; }

    @Override
    public void run() {
        while (true) {
            update();
        }
    }

    private boolean checkWhetherUpdate() {
        synchronized (this) {
            return whetherUpdate;
        }
    }

    private void changeWhetherUpdate(boolean flag) {
        synchronized (this) {
            whetherUpdate = flag;
        }
    }

    private void changeScene() {
        for (SceneInterface scene : sceneVector) {
            scene.release();
            objectManager.allDaleteGameObjects();
        }
        sceneVector.clear();
        sceneVector.addAll(nextSceneVector);

        for (SceneInterface scene : sceneVector) {
            scene.setup();
            objectManager.initGameObjects();
        }
        nextSceneVector.clear();
    }
}
