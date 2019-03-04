package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    Texture texture;
   static Vector2 position;
    int countMaterials;
    float speed;
    float angle;
     public Asteroid(int i){
         texture = new Texture("asteroid1.png");
         speed = 0.5f;
         position = new Vector2(500,500);
         countMaterials = i;
         angle = (float)Math.random() * 360;

     }

     public int mine(int drillPower){
         if (countMaterials < drillPower){
             recreate();
             return countMaterials;
         }
         else
         countMaterials -= drillPower;
         return drillPower;
     }


     public void render(SpriteBatch batch){
         batch.draw(texture, position.x - 50, position.y - 41, 50, 41, 100, 83, 1,1,angle,0,0, 100,83,false,false);
     }
     public void recreate(){
         position.set((float)Math.random() * 5400, (float)Math.random() * 2900);
     }
     public void update(){
angle += speed;
      position.set(position.x,position.y);
     }

}
