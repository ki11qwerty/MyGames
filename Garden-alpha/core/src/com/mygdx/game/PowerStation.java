package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class PowerStation extends Construction {
    Texture[] imgArray;
    Texture img;
    public PowerStation(Texture[] imgArray, int x, int y) {
        this.cost = 100;
        this.imgArray = imgArray;
        Xposition = x;
        Yposition = y;

    }


}
