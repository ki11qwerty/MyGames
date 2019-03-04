package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EnemyTarget {
    Texture texture;
    Float speed;
    Vector2 position;

    public  EnemyTarget(){
        texture = new Texture("enemy1.png");
        speed = 3f;
        position = new Vector2(200, 200);
    }
    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y);
    }


    public void update(){
        position.y +=speed;
    }
}
