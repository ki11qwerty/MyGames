package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Forest extends Resource {
    public Forest(Texture img, int width, int height, int countOfResource, Vector2 position) {
        this.img = img;
        this.countOfResource = countOfResource;
        this.position = new Vector2((position.x - (width / 2)),(position.y - (height / 2)));
        this.rectangle = new Rectangle(this.position.x,this.position.y,this.img.getWidth(),this.img.getHeight());

    }

}
