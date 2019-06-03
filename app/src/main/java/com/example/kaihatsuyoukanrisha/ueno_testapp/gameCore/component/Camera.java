package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component;

import android.graphics.Point;
import android.opengl.Matrix;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.GameSDK;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Rect;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Rectf;
import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform.Vec3;

import static java.lang.StrictMath.abs;

public class Camera extends Component {
    private float[] projectionMatrix = new float[16];
    private float[] viewMatrix = new float[16];
    private Rect viewport;
    private Rectf frustumRect = new Rectf(0,0,0,0);
    public float fovy;
    private float aspect;
    private float near;
    private float far;

    public Camera(GameObject object) {
        super(object);
        Matrix.setIdentityM(viewMatrix, 0);
        Matrix.setIdentityM(projectionMatrix, 0);
        fovy = 90.f;
        near = 0.01f;
        far = 1000.0f;

        Point displaySize = GameSDK.getSDK().getDisplaySize();
        setViewport(new Rect(0, displaySize.x, displaySize.y, 0));
        GameSDK.getSDK().setCamera(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        Vec3 pos = object.transform.getWorldPos();
        Vec3 at = object.transform.getAxisZ().addition(pos);
        Vec3 up = object.transform.getAxisY();
        Matrix.setLookAtM(viewMatrix, 0,
                pos.x, pos.y, pos.z,
                at.x, at.y, at.z,
                up.x, up.y, up.z);
    }

    @Override
    public void delete() {
        projectionMatrix = null;
        viewMatrix = null;
        viewport = null;
        GameSDK.getSDK().removeCamera(this);
    }

    public float[] getViewMatrix() { return viewMatrix; }

    public float[] getProjectionMatrix() { return projectionMatrix; }

    public final Rect getViewport() {
        return viewport;
    }

    public void setViewport(Rect rect) {
        viewport = null;
        viewport = rect;
        Point size = getViewPortSize();
        aspect = (float)size.x / size.y;

        Matrix.perspectiveM(projectionMatrix, 0, fovy, aspect, near, far);
    }

    public Point getViewPortSize() {
        return new Point(abs(viewport.right - viewport.left), abs(viewport.top - viewport.bottom));
    }

    public void setFovy(float fovy) {
        this.fovy = fovy;
        Matrix.perspectiveM(projectionMatrix, 0, fovy, aspect, near, far);
    }
}
