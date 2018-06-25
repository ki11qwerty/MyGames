package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Construction {
    int cost;
    Texture[] imgArray;
    Texture img;
    int Xposition;
    int Yposition;

    public Construction() {
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(int i) {
        if (i <= imgArray.length)
            img = imgArray[i];
        else
            return;
    }

    public int getCost() {
        return cost;
    }
    public int getXposition(){
        return Xposition;
    }
    public int getYposition(){
        return Yposition;
    }
    public void setPositions(int x ,int y){
        Xposition = x;
        Yposition = y;
    }
}
