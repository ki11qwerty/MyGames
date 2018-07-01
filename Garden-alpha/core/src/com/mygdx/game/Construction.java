package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Construction {
    int cost;
    Texture[] imgArray;
    Texture img;
    Vector2 position;
    Rectangle rectangle;
    public Construction() {
    }

    public Texture getImg() {
        return this.img;
    }

    public void setImg(int i) {         // для отдельного потока постройки, который будет дергать метод в итерации
        if (i <= this.imgArray.length)
            this.img = this.imgArray[i];
        else
            return;
    }

    public int getCost() {
        return this.cost;
    }
    public Vector2 getPosition(){
        return this.position;
    }

    public void setPositions(Vector2 position){
        this.position = position;
    }
    public Rectangle getRectangle(){
        return this.rectangle;
    }
}
