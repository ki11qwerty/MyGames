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
        if (buildX == 0 && buildY == 0 && buildType == 0) {
            System.out.println("прошли иф");
            try {
                Thread.sleep(500);
                System.out.println("после сна");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (buildType) {
                case (1): { //powerStation
                    for (int i = 0; i < 3; i++) {
                        constMap[buildX][buildY] = powerStation[i];
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    buildX = 0;
                    buildY = 0;
                    buildType = 0;
                }
                break;
            }
        }
    }

    void build(int x, int y, int type) {
        System.out.println("начало метода build");

        if (checkMap(x, y) == true && type != 0) {
            new Thread(Constructions.this).start();
            try {
                Thread.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            buildX = x;
            buildY = y;
            buildType = type;
        }
    }

    public boolean checkMap(int x, int y) {
        if (x > constMap.length - 1 || y > constMap[0].length - 1 || x < 0 || y < 0)
            return false;
        else
            return true;
    }
}
