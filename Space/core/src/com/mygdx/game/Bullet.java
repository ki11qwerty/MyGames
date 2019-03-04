package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
    Vector2 position;
    float speed;
    boolean active;
    int trajectory;

    public Bullet(){
        position = new Vector2(-1,-1);
        speed = 20f;
        active = false;
    }
    public void disable(){
        active = false;
    }
    public void enable(float x, float y, int t){
        switch (t) {
            case 0:
                position.set(x + 41, y + 94);
                break;
            case 1:
                position.set(x + 94, y + 41 );
                break;
            case 2:
                position.set(x + 47, y);
                break;
            case 3:
                position.set(x, y + 41);
                break;
            case 4:
                position.set(x + 77, y + 77);
                break;
            case 5:
                position.set(x + 77, y);
                break;
            case 6:
                position.set(x, y);
                break;
            case 7:
                position.set(x, y + 77);
                break;
        }
        trajectory = t;
        active = true;
    }
    public void update(){
        switch (trajectory) {
            case 0: {
                position.y += speed;
                break;
            }
            case 1: {
                position.x += speed;
                break;
            }
            case 2: {
                position.y -= speed;
                break;
            }
            case 3: {
                position.x -= speed;
                break;
            }
            case 4: {
                position.y += speed;
                position.x += speed;
                break;
            }
            case 5: {
                position.x += speed;
                position.y -= speed;
                break;
            }
            case 6:
                position.y -= speed;
                position.x -= speed;
                break;
            case 7: {
                position.x -= speed;
                position.y += speed;
                break;
            }
        }
        if (position.x > 5464 || position.x < -5 || position.y >3072 || position.y < -10)
            disable();
    }
}
