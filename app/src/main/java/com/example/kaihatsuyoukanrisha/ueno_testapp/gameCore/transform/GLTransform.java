package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform;

import android.opengl.Matrix;

public class GLTransform extends Transform {
    private float[] worldMatrix = new float[16];
    private float[] transMtx = new float[16];
    private float[] rotateMtx = new float[16];
    private float[] scaleMtx = new float[16];

    public GLTransform() {
        super();
        Matrix.setIdentityM(worldMatrix, 0);

    }

    public GLTransform(Vec3 pos, Vec3 rot, Vec3 size) {
        super(pos, rot, size);
        Matrix.setIdentityM(worldMatrix, 0);
    }

    public Vec3 getAxisX() { return new Vec3(worldMatrix[0], worldMatrix[1], worldMatrix[2]); }

    public Vec3 getAxisY() { return new Vec3(worldMatrix[4], worldMatrix[5], worldMatrix[6]); }

    public Vec3 getAxisZ() { return new Vec3(worldMatrix[8], worldMatrix[9], worldMatrix[10]); }

    public void calcMatrix() {
        Matrix.setIdentityM(transMtx, 0);
        Matrix.setIdentityM(rotateMtx, 0);
        Matrix.setIdentityM(scaleMtx, 0);
        Matrix.setIdentityM(worldMatrix, 0);
        Matrix.translateM(transMtx, 0, pos.x, pos.y, pos.z);
        rotateMtx = rotateMatrix();
        Matrix.scaleM(scaleMtx, 0, size.x, size.y, size.z);
        Matrix.multiplyMM(worldMatrix, 0, transMtx, 0, rotateMtx, 0);
        Matrix.multiplyMM(worldMatrix, 0, worldMatrix, 0, scaleMtx, 0);
    }

    public float[] getMatrix() { return worldMatrix;}

    @Override
    public void multiplyMatrix(float[] matrix) {
        Matrix.multiplyMM(worldMatrix, 0, worldMatrix, 0, matrix, 0);
    }

    private float[] rotateMatrix() {
        float[] rotX = new float[16];
        float[] rotY = new float[16];
        float[] rotZ = new float[16];
        float[] result = new float[16];

        Matrix.setIdentityM(rotX, 0);
        Matrix.setIdentityM(rotY, 0);
        Matrix.setIdentityM(rotZ, 0);
        Matrix.rotateM(rotX,0,rot.x, 1.0f, 0.0f, 0.0f);
        Matrix.rotateM(rotY,0,rot.y, 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(rotZ,0,rot.z, 0.0f, 0.0f, 1.0f);
        Matrix.multiplyMM(result, 0, rotX, 0, rotY, 0);
        Matrix.multiplyMM(result, 0, result, 0, rotZ, 0);

        rotX = null;
        rotY = null;
        rotZ = null;

        return result;
    }

    public Vec3 getWorldPos() {
        return new Vec3(worldMatrix[12], worldMatrix[13], worldMatrix[14]);
    }
}
