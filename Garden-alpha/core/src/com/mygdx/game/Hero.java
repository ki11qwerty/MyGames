package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Hero implements MyConstSettings {
    Texture img;
    int speed;
    int Xposition;
    int Yposition;

    public Hero(int speed) {
        this.img = new Texture("Hero.png");
        this.speed = speed;
        this.Xposition = (760);
        this.Yposition = (350); //было sizeY or Y * 2 /50
    }

    public void update() {
        if (Xposition < 0){
            Xposition = 0;}
        if (Xposition > WIDTH_WINDOW - img.getWidth())
            Xposition = WIDTH_WINDOW - img.getWidth();
        if (Yposition < 0)
            Yposition = 0;
        if (Yposition > HEIGHT_WINDOW - img.getHeight())
            Yposition = HEIGHT_WINDOW - img.getHeight();

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
    @Override
    public void run(){

    }
}