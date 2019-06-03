package com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.component.mesh;

import com.example.kaihatsuyoukanrisha.ueno_testapp.gameCore.gameobject.GameObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Board extends MeshInterface {
    private int textuerID;
    private FloatBuffer buffer;         // 頂点バッファ
    private FloatBuffer normalBuffer;   // 法線用バッファ
    private FloatBuffer textureBuffer;  // テクスチャバッファ
    private float vertex[] = {
            0.5f, 0.5f, 0.0f,
            -0.5f, 0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f
    };
    private float normal[] = {
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f
    };
    private float uv[] = {
            1.0f, 0.0f,
            0.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f
    };

    public Board(GameObject object) {
        super(object);
        this.textuerID = -1;
    }

    public Board(GameObject object, int imageID) {
        super(object);
        this.textuerID = imageID;
    }

    @Override
    public void init() {
        //頂点登録
        ByteBuffer vb = ByteBuffer.allocateDirect(vertex.length * 4);   //4はfloatの大きさ
        vb.order(ByteOrder.nativeOrder());
        buffer = vb.asFloatBuffer();
        buffer.put(vertex);
        buffer.position(0);

        //法線登録
        ByteBuffer nb = ByteBuffer.allocateDirect(normal.length * 4);
        nb.order(ByteOrder.nativeOrder());
        normalBuffer = nb.asFloatBuffer();
        normalBuffer.put(normal);
        normalBuffer.position(0);

        //テクスチャ座標登録
        ByteBuffer tb = ByteBuffer.allocateDirect(uv.length * 4);
        tb.order(ByteOrder.nativeOrder());
        textureBuffer = tb.asFloatBuffer();
        textureBuffer.put(uv);
        textureBuffer.position(0);
    }

    @Override
    public void draw(GL10 gl) {
        //初期行列保存
        gl.glPushMatrix();

        gl.glMultMatrixf(object.transform.getMatrix(), 0);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, buffer);

        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);


        if (textuerID != -1) {
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textuerID);
            gl.glEnable(GL10.GL_TEXTURE_2D);
        }
        else {
            gl.glDisable(GL10.GL_TEXTURE_2D);
        }

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        //初期行列吐き出し
        gl.glPopMatrix();

        /*if (textuerID == -1) {
            gl.glEnable(GL10.GL_TEXTURE_2D);
        }*/
    }
}
