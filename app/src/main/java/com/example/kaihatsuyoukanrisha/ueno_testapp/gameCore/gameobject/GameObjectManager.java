package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.ComponentInterface;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.microedition.khronos.opengles.GL10;

public class GameObjectManager {
    private List<GameObject> objectList = new ArrayList<>();
    private Queue<GameObject> workObject = new ArrayDeque<>();
    private Queue<GameObject> garbageObject = new ArrayDeque<>();
    private Queue<ComponentInterface> garbageComponent = new ArrayDeque<>();
    public Map<String, Integer> layer = new HashMap<String, Integer>() {
        {
            put("SYSTEM_LAYER",0x0000);
            put("DEFAULT_LAYER", 0x0001);
        }
    };
    private FriendGameObjectBridge objectBridge = new FriendGameObjectBridge();

    //いくよ友達の友達はラブ
    static class FriendGameObjectBridge extends GameObject.FriendGameObject {
        //ハートにくっつくたからもん
        protected void init(GameObject object) {
            super.init(object);
        }

        protected void delete(GameObject object) {
            super.delete(object);
        }

        protected void update(GameObject object) {
            super.update(object);
        }

        protected void updateTransform(GameObject object) { super.updateTransform(object); }

        protected void draw(GameObject object, GL10 gl) {
            super.draw(object, gl);
        }
    }

    public void initGameObjects() {
        for (GameObject object : objectList) {
            workObject.add(object);

            while (!workObject.isEmpty()) {
                GameObject work = workObject.poll();
                objectBridge.init(work);

                workObject.addAll(work.getChildren());
            }
        }
    }

    public void updateGameObjects() {
        for (GameObject object : objectList) {
            workObject.add(object);

            while (!workObject.isEmpty()) {
                GameObject work = workObject.poll();
                objectBridge.update(work);

                workObject.addAll(work.getChildren());
            }
        }

        cleanGarbageObject();
    }

    public void updateTransform() {
        for (GameObject object : objectList) {
            workObject.add(object);

            while (!workObject.isEmpty()) {
                GameObject work = workObject.poll();
                objectBridge.updateTransform(work);

                workObject.addAll(work.getChildren());
            }
        }

        cleanGarbageObject();
    }

    public void drawGameObjects(GL10 gl) {
        for (GameObject object : objectList) {
            workObject.add(object);

            while (!workObject.isEmpty()) {
                GameObject work = workObject.poll();
                objectBridge.draw(work, gl);

                workObject.addAll(work.getChildren());
            }
        }
    }

    public void allDaleteGameObjects() {
        for (GameObject object : objectList) {
            workObject.add(object);

            while (!workObject.isEmpty()) {
                GameObject work = workObject.poll();
                objectBridge.delete(work);

                workObject.addAll(work.getChildren());
            }
        }

        objectList.clear();
    }

    public void addGarbageObject(GameObject object) {
        garbageObject.add(object);
    }

    public void addGarbageComponent(ComponentInterface component) { garbageComponent.add(component); }

    private void cleanGarbageObject() {
        for (GameObject object : garbageObject) {
            if (object.getParent() == null) {
                objectList.remove(object);
            }
            objectBridge.delete(object);
        }
        garbageObject.clear();
    }

    private void cleanGarbageComponent() {
        for (ComponentInterface c : garbageComponent) {
            c.delete();
        }
        garbageComponent.clear();
    }

    public void setParent(GameObject parent, GameObject child) {
        if (child.getParent() != null) {
            child.getParent().getChildren().remove(child);
        }

        child.setParent(parent);
        if (parent == null) {
            this.objectList.add(child);
        }
        else {
            parent.setChild(child);
        }
    }

    public void addObject(GameObject object) {
        objectList.add(object);
    }
}
