package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Constructions extends Thread {
    Texture nullPng;
    Texture[] powerStation;
    Texture LomberJack;
    Texture[][] constMap;
    int buildX = 0;
    int buildY = 0;
    int buildType = 0;
    boolean buildInProgress = false;

    public Constructions(int sizeX, int sizeY) {
        nullPng = new Texture("null.png");
        constMap = new Texture[sizeX][sizeY];
        powerStation = new Texture[4];
        for (int i = 0; i < sizeX; i++) {               //заполнить массив прозрачными Png
            for (int j = 0; j < sizeY; j++) {
                constMap[i][j] = nullPng;
            }
        }
        for (int i = 0; i < 4; i++) {                   //заполнить массив 4 фазами отрисовки powerStation
            powerStation[i] = new Texture("powerStation" + i + ".png");
        }
    }

    void render(SpriteBatch batch) {
        for (int i = 0; i < constMap.length; i++) {
            for (int j = 0; j < constMap[0].length; j++) {
                batch.draw(constMap[i][j], i * constMap[i][j].getWidth(), j *
                        constMap[i][j].getHeight());
            }
        }
    }

    void update() {

    }

    @Override
    public void run() {                                       //костыль продашн сука
        System.out.println("вошли в ран");
        switch (buildType) {
            case (1): { //powerStation
                for (int i = 0, x = buildX, y = buildY; i < 4; i++) {
                    if (i == 0)
                        buildInProgress = false;      //далее может принимать еще кординаты новых построек
                    constMap[x][y] = powerStation[i]; //отрисовки постройки с ожиданием

                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    void build(int x, int y, int type) {
        System.out.println("начало метода build");

        if (checkMap(x, y) == true && type != 0 && buildInProgress == false) {
            buildX = x;
            buildY = y;
            buildType = type;
            new Thread(Constructions.this).start();
            buildInProgress = true;                         //сделано чтобы не перебивались кординаты старых с новыми

        } else System.out.println("sorry, but cordinates uncorrect");
    }

    public boolean checkMap(int x, int y) {
        if (x > constMap.length - 1 || y > constMap[0].length - 1 || x < 0 || y < 0)   //кординаты не существуют
            return false;

        else if (constMap[x][y].equals(nullPng))                       // нет ли постороек
            return true;
        else
            return false;
    }
}
