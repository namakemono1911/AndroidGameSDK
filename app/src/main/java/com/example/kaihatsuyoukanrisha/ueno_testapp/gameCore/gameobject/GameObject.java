package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDK;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.ComponentInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.mesh.MeshInterface;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.GLTransform;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Transform;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class GameObject {
    public Transform transform = new GLTransform();
    public String name;
    public String tag;
    public int layer;
    private GameObject parent;
    private List<GameObject> children = new ArrayList<>();
    private List<ComponentInterface> componentList = new ArrayList<>();
    private List<MeshInterface> meshList = new ArrayList<>();
    private GameObjectManager manager;

    //Love Friend Style
    static class FriendGameObject extends GameObject {
        //アクセスを許すクラス
        private final String[] permissionClass = {
            "com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObjectManager$"
        };

        protected FriendGameObject() {
            for (String name : permissionClass) {
                if (!this.getClass().getName().startsWith(name)) {
                    throw new RuntimeException();
                }
            }
        }

        protected void init(GameObject object) {
            object.init();
        }

        protected void delete(GameObject object) {
            object.delete();
        }

        protected void update(GameObject object) {
            object.update();
        }

        protected void updateTransform(GameObject object) { object.updateTransform(); }

        protected void draw(GameObject object, GL10 gl) {
            object.draw(gl);
        }
    }

    public GameObject() {
        tag = "default";
        layer = 0x1;
    }

    private void init() {
        for (ComponentInterface c : componentList) {
            c.init();
        }
    }

    private void delete() {
        for (ComponentInterface c : componentList) {
            c.delete();
        }

        if (!children.isEmpty()) {
            for (GameObject child : children) {
                child.delete();
            }
        }

        componentList.clear();
        componentList = null;
    }

    private void update() {
        for (ComponentInterface c : componentList) {
            c.update();
        }
    }

    private void updateTransform() {
        transform.calcMatrix();

        if (parent != null) {
            transform.multiplyMatrix(parent.transform.getMatrix());
        }
    }

    private void draw(GL10 gl) {
        for (MeshInterface m : meshList) {
            m.draw(gl);
        }
    }

    public <T extends ComponentInterface> T getComponent() {
        T object = null;
        for (ComponentInterface c : componentList) {
            if (c.getClass() == object.getClass()) {
                return (T) c;
            }
        }

        return null;
    }

    private void setManager(GameObjectManager manager) {
        this.manager = manager;
    }

    public void addComponent(ComponentInterface component) {
        componentList.add(component);
    }

    public void addComponent(MeshInterface mesh) {
        meshList.add(mesh);
        addComponent((ComponentInterface)mesh);
    }

    public void deleteComponent(ComponentInterface component) {
        if (component == null) return;

        GameSDK.getSDK().deleteComponent(component);
    }

    public final GameObject getParent() { return parent; }

    public final List<GameObject> getChildren() { return children; }

    public void setParent(GameObject parent) {
        if (parent == this) return;

        GameSDK.getSDK().setParent(parent, this);
    }

    public void setChild(GameObject child) {
        if (child == null || child == this) return;

        GameSDK.getSDK().setParent(this, child);
    }
}
