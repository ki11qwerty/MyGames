package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;                          //задание на ближайшие рабочие дни сделать
import com.badlogic.gdx.graphics.g2d.SpriteBatch;                  //строения обьектами

public class Building extends Thread {
    Texture nullPng;
    Texture[] powerStation;
    Texture[][][] lumberjack;
    Texture[][] constMap;
   Construction[] constract; //---------------
    int constructingTime = 2500;
    int buildX = 0;
    int buildY = 0;
    int buildType = 0;
    boolean buildInProgress = false;

    public Building(int sizeX, int sizeY) {
        constract = new Construction[100];    //--------------------
        constract[0] = new PowerStation(powerStation,0,0);
        nullPng = new Texture("null.png");
        constMap = new Texture[sizeX][sizeY];
        lumberjack = new Texture[4][4][2];              // [количествоСтадий][x][y]
        powerStation = new Texture[4];
        for (int i = 0; i < sizeX; i++) {               //заполнить массив прозрачными Png
            for (int j = 0; j < sizeY; j++) {
                constMap[i][j] = nullPng;
            }
        }
        for (int i = 0; i < 4; i++) {                   //заполнить массив 4 фазами отрисовки powerStation
            powerStation[i] = new Texture("powerStation" + i + ".png");
        }
        for(int z = 0; z < lumberjack.length; z++){      //заполнить массив 4 фазами отрисовки lumberjack
            for (int x = 0; x < lumberjack[0].length; x++){
                for (int y = 0; y < lumberjack[0][0].length; y++){
                    lumberjack[z][x][y] = new Texture("lumberjack/lumberjack"+z+""+x+""+y+".png");
                }
            }
        }
    }

    void render(SpriteBatch batch) {
        for (int i = 0; i < constMap.length; i++) {
            for (int j = 0; j < constMap[0].length; j++) {
                batch.draw(constMap[i][j], i * constMap[i][j].getWidth() + Garden.screenXPosition, j *
                        constMap[i][j].getHeight() + Garden.screenYPosition);
            }
        }
        for (int i = 0; i < constract.length; i++){       //новый массив построек версия 2.0             //------------
            if(constract[i] != null)
            batch.draw(constract[i].getImg(), constract[i].getXposition(),constract[i].getYposition());
        }
    }

    void update() {

    }

    @Override
    public void run() {                                       //костыль продашн сука
        System.out.println("вошли в ран");
        switch (buildType) {
            case (1): {                                               //powerStation
                for (int i = 0, x = buildX, y = buildY; i < 4; i++) {
                    if (i == 0)
                        buildInProgress = false;      //далее может принимать еще кординаты новых построек
                    constMap[x][y] = powerStation[i]; //отрисовки постройки с ожиданием

                    try {
                        Thread.sleep(constructingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
            case (2): {                                                 //lumberJack
                for (int z = 0, x = buildX, y = buildY; z < 4; z++) {
                    if (z == 0)
                        buildInProgress = false;
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 4; j++) {
                            constMap[x + j][y + i] = lumberjack[z][j][i];
                        }
                    }
                    try {
                        Thread.sleep((int)(constructingTime * 1.5f));
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

        if (checkMap(x, y, type) == true && type != 0 && buildInProgress == false) {
            buildX = x;
            buildY = y;
            buildType = type;
            new Thread(Building.this).start();
            buildInProgress = true;                         //сделано чтобы не перебивались кординаты старых с новыми

        } else System.out.println("sorry, but cordinates uncorrect \n" +
                "x-" + x + ",y-" + y + ",type" + type);
    }

    public boolean checkMap(int x, int y, int type) {
        if (type == 1) {                         // надо переделать все под switch при следующей оптимизации кода =) !!!
            if (x > constMap.length - 1 || y > constMap[0].length - 1 || x < 0 || y < 0)   //кординаты не существуют
                return false;

            else if (constMap[x][y].equals(nullPng))                       // нет ли построек
                return true;
            else
                return false;
        }
        if (type == 2) {
            if (x > constMap.length - 1 || y > constMap[0].length - 1 || x < 0 || y < 0)   //кординаты не существуют
                return false;
            else if (constMap[x][y].equals(nullPng)) {       //можно сделать как то по красивее но пока пох
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (!constMap[x + j][y + i].equals(nullPng))
                            return false;
                    }
                }
                    return true;
                }
            } else
                return false;
        return false;     }

   }
