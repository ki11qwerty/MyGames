package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.security.Key;

public class Hero {
    Texture img;
    int speed;
    int Xposition;
    int Yposition;

    public Hero(int speed) {
        this.img = new Texture("Hero.png");
        this.speed = speed;
        this.Xposition = (1300 / 2);
        this.Yposition = (750 / 2);
    }

    public void update() {
        if (Xposition < 0)
            Xposition = 0;
        if (Yposition < 0)
            Yposition = 0;
    }
}