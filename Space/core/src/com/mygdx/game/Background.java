package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    Texture texture;
    Texture textureStar;
    Star[]  stars;
    Texture texturePlanet;
    static Vector2 positionBack;


    public class Planet{
    Vector2 position;
    public Planet(){
        position = new Vector2();
    }
}

public class Star{
    Vector2 position;
    float speed;

    public Star(){
        position = new Vector2((float)Math.random() * 5464,(float)Math.random() * 3072);
        speed = 1.0f +(float)Math.random() * 2;
    }


    public void update() {
        position.x -=speed;
        if (position.x < -20){
            position.x = 5500;
            position.y = (float)Math.random() * 3072;
        }
    }

}
    public Background(){
        texture = new Texture("background.png");
        positionBack = new Vector2(0,0);
        textureStar = new Texture("star.png");
        texturePlanet = new Texture("planetEarth.png");
        stars = new Star[2500];
        for (int i = 0; i<stars.length; i++){
            stars[i] = new Star();
        }

    }
    public void render(SpriteBatch batch){
        batch.draw(texture, positionBack.x , positionBack.y);
        for (int i =0; i<stars.length; i++){
            batch.draw(textureStar, stars[i].position.x, stars[i].position.y);
        }
        batch.draw(texturePlanet, positionBack.x + 750, positionBack.y + 750);
    }
    public void update() {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update();
           if (Background.positionBack.x < -4098)   //
               Background.positionBack.x = -4098;  //
           if (Background.positionBack.x > 0)       //
               Background.positionBack.x = 0;      //
            if (Background.positionBack.y < -2306)   //     положение заднего фона относительно игкрока
                Background.positionBack.y = -2306;  //                           и окна приложения
            if (Background.positionBack.y > 0)       //
                Background.positionBack.y = 0;      //

        }
    }
}
