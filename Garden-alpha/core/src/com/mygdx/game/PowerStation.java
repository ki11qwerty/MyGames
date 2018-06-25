package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PowerStation extends Construction {
    public PowerStation(Texture[] imgArray, Vector2 position) {
        this.cost = 100;
        this.imgArray = imgArray;
        this.position = position;


    }


}
