package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Constructions extends Thread {
    Texture nullPng;
    Texture[] powerStation;
    Texture LomberJack;
    Texture[][] constMap;
    volatile int buildX = 0;
    volatile int buildY = 0;
    volatile int buildType = 0;
    boolean buildInProgress = false;

    public Constructions(int sizeX, int sizeY) {
        nullPng = new Texture("null.png");
        constMap = new Texture[sizeX][sizeY];
        powerStation = new Texture[3];
        for (int i = 0; i < sizeX; i++) {               //заполнить массив прозрачными Png
            for (int j = 0; j < sizeY; j++) {
                constMap[i][j] = nullPng;
            }
        }
        for (int i = 0; i < 3; i++) {                   //заполнить массив 3 фазами отрисовки powerStation
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
                for (int i = 0; i < 2; i++) {
                    constMap[buildX][buildY] = powerStation[i]; //две отрисовки с ожиданием
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                constMap[buildX][buildY] = powerStation[2];   // третья после ожидания, дальнего ожидания не требуется
            }
            buildInProgress =false;
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
            buildInProgress = true;

        }
        else System.out.println("sorry, but building in progress");
    }

    public boolean checkMap(int x, int y) {
        if (x > constMap.length - 1 || y > constMap[0].length - 1 || x < 0 || y < 0)
            return false;
        else
            return true;
    }
}
