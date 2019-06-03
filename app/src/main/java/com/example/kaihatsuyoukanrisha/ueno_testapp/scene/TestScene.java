package com.example.kaihatsuyoukanrisha.ueno_testapp.scene;

import com.example.kaihatsuyoukanrisha.ueno_testapp.R;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.mesh.Board;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.script.CameraController;
import com.example.kaihatsuyoukanrisha.ueno_testapp.script.CharactorController;

public class TestScene extends SceneInterface {

    public TestScene() {
        super("TestScene");
    }

    @Override
    public void setup() {
        sdk.loadTexture(R.drawable.alien_ufo);
        sdk.loadTexture(R.drawable.space_kaseijin);

        GameObject camera = sdk.createCamera();
        camera.addComponent(new CameraController(camera));
        camera.transform.pos.z = 3.0f;
        camera.transform.rot.y = 180.f;
        camera.transform.rot.x = 10.f;
        camera.transform.rot.z = 10.f;
        //camera.transform.pos.x = 10.0f;

        GameObject object = sdk.createEmptyGameObject();
        //object.transform.pos.z -= 3.0f;
        //object.transform.pos.x -= 0.3f;
        object.addComponent(new Board(object, sdk.getTextureID(R.drawable.fantasy_flatwoods_monster)));
        object.addComponent(new CharactorController(object));
    }

    @Override
    public void release() {
        sdk.deleteAllTexture();
    }
}
