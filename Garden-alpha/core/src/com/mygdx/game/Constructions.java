package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Constructions {
    Texture powerStation;
    Texture LomberJack;
    Texture[][] constructionMap;
    public Constructions(int sizeX, int sizeY){
        constructionMap = new Texture[sizeX][sizeY];
        powerStation = new Texture("powerStation.png");
    }

    void render(SpriteBatch batch){                           // ну короче пока тут стоп...
    }
    void update(){

    }
}
