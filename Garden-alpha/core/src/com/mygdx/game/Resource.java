package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Resource {
    Texture img;
    int countOfResource;
    Vector2 position;
    Rectangle rectangle;
    public Resource(){

    }

    public Texture getImg() {
        return img;
    }

    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 pos){
        this.position = new Vector2(pos);
        this.update();
    }
    public Rectangle getRectangle(){
        return this.rectangle;
    }

    public void update(){
        this.rectangle.setPosition(this.position.x + Garden.screenXPosition,
                this.position.y + Garden.screenYPosition);
    }
}
