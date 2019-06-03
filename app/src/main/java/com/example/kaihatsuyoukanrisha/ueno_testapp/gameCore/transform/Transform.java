package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.transform;

abstract public class Transform {
    public Vec3 pos;
    public Vec3 rot;
    public Vec3 size;

    public Transform() {
        pos = new Vec3();
        rot = new Vec3();
        size = new Vec3(1,1,1);
    }

    public Transform(Vec3 pos, Vec3 rot, Vec3 scale) {
        this.pos = pos;
        this.rot = rot;
        this.size = scale;
    }

    abstract public Vec3 getAxisX();

    abstract public Vec3 getAxisY();

    abstract public Vec3 getAxisZ();

    abstract public void calcMatrix();

    abstract public float[] getMatrix();

    abstract public void multiplyMatrix(float[] matrix);

    abstract public Vec3 getWorldPos();
}