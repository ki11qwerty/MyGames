package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    Texture texture;
    Texture[] positionTexture = new Texture[8];
    Vector2 position;
    float speed;
    public int numberTexture;
    int fireConter;
    int fireRate;


    public Player() {
        texture = new Texture("silver/player1.png");
        numberTexture = 0;
        for(int i =0; i < positionTexture.length; i++){
            positionTexture[i]= new Texture("silver/player" + (i + 1) +".png");
        }
        speed = 10;
        position = new Vector2(100, 100);
        fireRate = 2;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }


    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            setTexture(0);
            position.y += speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {            //отслеживаем нажатие кнопок, движение
            setTexture(3);                                     //замена картинки игрока
            position.x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            setTexture(2);
            position.y -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            setTexture(1);
            position.x += speed;
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {  //для проверки, удалить!!!!
            if(speed <30)
            speed += 10;
            else
                speed = 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            fireConter++;
            if (fireConter >= fireRate){
                fireConter =0;
                fire();
            }
        }


                 //обработка движения по диагонали с заменой картинки

        if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D))
            setTexture(4);
        if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D))
            setTexture(5);
        if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.S))
            setTexture(6);
        if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.W))
            setTexture(7);



        if (position.x > 1178) {
            position.x = 1178;

           Asteroid.position.x -=speed;
            Background.positionBack.x -= speed / 2;      // если игрок доходит до конца экрана
        }                                             // остонавливаем движение, и двигаем задний фон
        if (position.x < 100){
            position.x = 100;

            Asteroid.position.x += speed;
            Background.positionBack.x += speed / 2;
        }
        if (position.y > 570) {
            position.y = 570;

            Asteroid.position.y -=speed;
            Background.positionBack.y -= speed / 2;
        }
        if (position.y < 100) {
            position.y = 100;

            Asteroid.position.y +=speed;
            Background.positionBack.y += speed / 2;
        }
    }

    public void setTexture(int a) {                 // задаем рисунок игрока согласно движению
        switch (a) {                                //
            case 0:
                this.texture = positionTexture[0];
                numberTexture =0;
                break;
            case 1:
                this.texture = positionTexture[1];
                numberTexture =1;
                break;
            case 2:
                this.texture = positionTexture[2];
                numberTexture =2;
                break;
            case 3:
                this.texture = positionTexture[3];
                numberTexture =3;
                break;
            case 4:
                this.texture = positionTexture[4];
                numberTexture =4;
                break;
            case 5:
                this.texture = positionTexture[5];
                numberTexture =5;
                break;
            case 6:
                this.texture = positionTexture[6];
                numberTexture =6;
                break;
            case 7:
                this.texture = positionTexture[7];
                numberTexture =7;
                break;
        }
    }
    public void fire(){
        for(int i=0; i<MyGdxGame.bullets.length; i ++){
            if(!MyGdxGame.bullets[i].active){
                MyGdxGame.bullets[i].enable(position.x, position.y, getNumberTexture());
                break;
            }
        }
    }
    public  int getNumberTexture(){
        return numberTexture;
    }
    public  float getYposition(){
        return position.y;
    }
}
