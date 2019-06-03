package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.mesh;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.Component;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;

import javax.microedition.khronos.opengles.GL10;

abstract public class MeshInterface extends Component {
    public MeshInterface(GameObject object) {
        super(object);
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

    abstract public void draw(GL10 gl);
}
