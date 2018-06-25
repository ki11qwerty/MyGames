package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Construction {
    int cost;
    Texture[] imgArray;
    Texture img = new Texture("powerStation3.png");
    Vector2 position;

    public Construction() {
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(int i) {         // для отдельного потока постройки, который будет дергать метод в итерации
        if (i <= imgArray.length)
            img = imgArray[i];
        else
            return;
    }

    public int getCost() {
        return cost;
    }
    public Vector2 getPosition(){
        return position;
    }

    public void setPositions(Vector2 position){
        this.position = position;
    }
}
