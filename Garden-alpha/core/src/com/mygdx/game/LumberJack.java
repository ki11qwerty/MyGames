package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class LumberJack extends Construction {
    public LumberJack(Texture[] imgArray, Vector2 position) {
        this.cost = 500;
        this.imgArray = new Texture[4];
        this.imgArray = imgArray;
        this.img = imgArray[0];
        this.position = new Vector2(position.x - (this.img.getWidth() / 2),
                position.y - (this.img.getHeight()) / 2);


    }


}
