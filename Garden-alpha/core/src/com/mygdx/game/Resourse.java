package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Resourse {
    Texture img;
    int countOfResourse;
    Vector2 position;
    Rectangle rectangle;
    public Resourse(){

    }

    public Texture getImg() {
        return img;
    }

    public Vector2 getPosition() {
        return position;
    }
}
