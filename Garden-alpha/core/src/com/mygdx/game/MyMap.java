package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class MyMap {
    private Texture myTexture1;
    private Texture myTexture2;
    private Texture myTexture3;
    private Vector2 position;
    Texture[][] myMapArr;
    public MyMap(int sizeX , int sizeY){
        this.myTexture1 = new Texture("green1.png");
        this.myTexture2 = new Texture("green2.png");
        this.myTexture3 = new Texture("green3.png");
        myMapArr = new Texture[sizeX][sizeY];
        for(int i=0; i<sizeX; i++){
            for(int j=0; j<sizeY;j++){
                if(i ==0 || i == sizeX-1 || j == 0 || j == sizeY -1){
                    myMapArr[i][j] = myTexture3;
                continue;}
                if (Math.random()*1 > 0.5)
                    myMapArr[i][j]= myTexture1;
                else
                    myMapArr[i][j]= myTexture2;
            }
        }
    }
    public Texture[][] getMyMapArr(){
        return myMapArr;
    }

    public void render(SpriteBatch batch){

    }
    public void swapTexture(int i , int j, int nextBlockSwaping){
        switch (nextBlockSwaping){
            case(1):{
                myMapArr[i][j] = myTexture1;
                break;
            }
            case(2):{
                myMapArr[i][j] = myTexture2;
                break;
            }
            case(3):{
                myMapArr[i][j] = myTexture3;
                break;
            }
        }
    }
    public void update(){

    }
}
