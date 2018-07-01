package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;                          //задание на ближайшие рабочие дни сделать
import com.badlogic.gdx.graphics.g2d.SpriteBatch;                  //строения обьектами - выполнено епт
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Building extends Thread {
    Texture nullPng;
    Texture[] powerStation;
    Texture[] lumberjack;
    Texture[][] constMap;
    Construction[] constract; //---------------                       потом перефигачить это все дело в список (!3)
    int constructingTime = 2500;
    Vector2 buildPosition;
    int buildType = 0;
    boolean buildInProgress = false;
    int buildIndexInArray;

    public Building(int sizeX, int sizeY) {
        constract = new Construction[100];    //--------------------  потом перефигачить это все дело в список (!3)
        nullPng = new Texture("null.png");
        constMap = new Texture[sizeX][sizeY];
        lumberjack = new Texture[4];
        powerStation = new Texture[4];
        for (int i = 0; i < sizeX; i++) {               //заполнить массив прозрачными Png
            for (int j = 0; j < sizeY; j++) {
                constMap[i][j] = nullPng;
            }
        }
        for (int i = 0; i < 4; i++) {                   //заполнить массив 4 фазами отрисовки powerStation
            powerStation[i] = new Texture("construction/powerStation" + i + ".png");
        }
        for (int i = 0; i < 4; i++) {                   //заполнить массив 4 фазами отрисовки lumberjack
            lumberjack[i] = new Texture("construction/lumberjack" + i + ".png");
        }
    }

    void render(SpriteBatch batch) {
        for (int i = 0; i < constMap.length; i++) {
            for (int j = 0; j < constMap[0].length; j++) {
                batch.draw(constMap[i][j], i * constMap[i][j].getWidth() + Garden.screenXPosition, j *
                        constMap[i][j].getHeight() + Garden.screenYPosition);
            }
        }
        for (int i = 0; i < constract.length; i++) {       //новый массив построек версия 2.0             //------------
            if (constract[i] != null)
                batch.draw(constract[i].getImg(), constract[i].getPosition().x + Garden.screenXPosition,
                        constract[i].getPosition().y + Garden.screenYPosition);
        }
    }

    void update() {

    }

    //    @Override
//    public void run() {                                       //костыль продашн сука
//        System.out.println("вошли в ран");
//        switch (buildType) {
//            case (1): {                                               //powerStation
//                for (int i = 0, x = buildX, y = buildY; i < 4; i++) {
//                    if (i == 0)
//                        buildInProgress = false;      //далее может принимать еще кординаты новых построек
//                    constMap[x][y] = powerStation[i]; //отрисовки постройки с ожиданием
//
//                    try {
//                        Thread.sleep(constructingTime);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            break;
//            case (2): {                                                 //lumberJack
//                for (int z = 0, x = buildX, y = buildY; z < 4; z++) {
//                    if (z == 0)
//                        buildInProgress = false;
//                    for (int i = 0; i < 2; i++) {
//                        for (int j = 0; j < 4; j++) {
//                            constMap[x + j][y + i] = lumberjack[z][j][i];
//                        }
//                    }
//                    try {
//                        Thread.sleep((int)(constructingTime * 1.5f));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            break;
//        }
//    }
//
//    void build(int x, int y, int type) {
//        System.out.println("начало метода build");
//
//        if (checkMap(x, y, type) == true && type != 0 && buildInProgress == false) {
//            buildX = x;
//            buildY = y;
//            buildType = type;
//            new Thread(Building.this).start();
//            buildInProgress = true;                         //сделано чтобы не перебивались кординаты старых с новыми
//
//        } else System.out.println("sorry, but cordinates uncorrect \n" +
//                "x-" + x + ",y-" + y + ",type" + type);
//    }
//
//    public boolean checkMap(int x, int y, int type) {
//        if (type == 1) {                         // надо переделать все под switch при следующей оптимизации кода =) !!!
//            if (x > constMap.length - 1 || y > constMap[0].length - 1 || x < 0 || y < 0)   //кординаты не существуют
//                return false;
//
//            else if (constMap[x][y].equals(nullPng))                       // нет ли построек
//                return true;
//            else
//                return false;
//        }
//        if (type == 2) {
//            if (x > constMap.length - 1 || y > constMap[0].length - 1 || x < 0 || y < 0)   //кординаты не существуют
//                return false;
//            else if (constMap[x][y].equals(nullPng)) {       //можно сделать как то по красивее но пока пох
//                for (int i = 0; i < 2; i++) {
//                    for (int j = 0; j < 4; j++) {
//                        if (!constMap[x + j][y + i].equals(nullPng))
//                            return false;
//                    }
//                }
//                    return true;
//                }
//            } else
//                return false;
//        return false;     }
//
//   }
    public void createBuildingThread(Vector2 position, int type) {//поиск места в массиве,
        if (buildInProgress == false) {                           //инициализация переменных аргументами
            System.out.println("проход в if - progress true");    //с запуском в разных потоках
            buildInProgress = true;
            for (int i = 0; i < constract.length; i++) {
                if (constract[i] == null) {
                    System.out.println("итерация массива -" + i);
                    buildPosition = position;
                    buildType = type;
                    buildIndexInArray = i;
                    new Thread(Building.this).start();
                    return;
                }
            }
        } else {
            System.out.println("buildInProgres " + buildInProgress + ",return -> false");
            buildInProgress = false;
            return;
        }

    }                 //нужны хитбоксы

    @Override
    public void run() {
        building(buildPosition, buildType, buildIndexInArray);
    }

    public void building(Vector2 position, int type, int indexOfArray) {         //надо вхуярить проверку места и денег
        switch (type) {                                                          //отдельным методом
            case (1): {
                System.out.println(Hero.HeroMoney);
                constract[indexOfArray] = new PowerStation(powerStation, position);
                if (constract[indexOfArray].getCost() <= Hero.HeroMoney &&
                        checkAreaForBuilding(constract[indexOfArray].getPosition(),indexOfArray) == true) {
                    Hero.HeroMoney -= constract[indexOfArray].getCost();
                    animationBuildingProgress(buildIndexInArray);
                } else {
                    System.out.println("Sorry you cant buy this need -" + constract[indexOfArray].getCost() +
                            "\n But you have only - " + Hero.HeroMoney);
                    constract[indexOfArray] = null;
                }
            }
            break;
            case (2): {
                System.out.println(Hero.HeroMoney);
                constract[indexOfArray] = new LumberJack(lumberjack, position);
                if (constract[indexOfArray].getCost() <= Hero.HeroMoney &&
                        checkAreaForBuilding(constract[indexOfArray].getPosition(), buildIndexInArray)) {
                    Hero.HeroMoney -= constract[indexOfArray].getCost();
                    animationBuildingProgress(buildIndexInArray);
                } else {
                    System.out.println("Sorry you cant buy this need -" + constract[indexOfArray].getCost() +
                            "\n But you have only - " + Hero.HeroMoney);
                    constract[indexOfArray] = null;
                }
            }
            break;
        }
        buildInProgress = false;
    }

    public void animationBuildingProgress(int buildIndexInArray) {
        buildInProgress = false;
        for (int i = 0; i < 4; i++) {
            try {
                constract[buildIndexInArray].setImg(i);
                Thread.sleep(constructingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkAreaForBuilding(Vector2 position, int buildIndexInArray) {
        Rectangle rect = constract[buildIndexInArray].getRectangle();
        for (int i = 0; i < constract.length; i++) {
            if (i == buildIndexInArray)
                continue;
            if (constract[i] == null) {
                System.out.println("" + i + " - итерация внутри проверочного метода");
                continue;
            }
            System.out.println("for внутри проверочного метода");
            if (constract[i].getRectangle().overlaps(rect))
                return false;
        }
        return true;
    }
}
