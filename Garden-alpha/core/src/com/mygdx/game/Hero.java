package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class Hero implements MyConstSettings {
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
        if (Xposition > WIDTH_WINDOW - 25)
            Xposition = WIDTH_WINDOW - 25;
        if (Yposition < 0)
            Yposition = 0;
        if (Yposition > HEIGHT_WINDOW - 25)
            Yposition = HEIGHT_WINDOW - 25;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            Yposition += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            Yposition -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            Xposition += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            Xposition -= speed;
        }
    }
}