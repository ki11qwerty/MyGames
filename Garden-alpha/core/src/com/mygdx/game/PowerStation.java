package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PowerStation extends Construction {
    public PowerStation(Texture[] imgArray, Vector2 position) {
        this.cost = 100;
        this.imgArray = new Texture[4];
        this.imgArray = imgArray;
        this.img = imgArray[0];
        this.position = new Vector2(position.x - (this.img.getWidth() / 2),
                position.y - (this.img.getHeight()) / 2);
        this.rectangle = new Rectangle(this.position.x,
                this.position.y, this.img.getWidth() +100, this.img.getHeight()+100);
    }


}
