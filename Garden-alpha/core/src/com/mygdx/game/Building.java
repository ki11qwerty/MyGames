package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;                          //задание на ближайшие рабочие дни сделать
import com.badlogic.gdx.graphics.g2d.SpriteBatch;                  //строения обьектами - выполнено епт
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Building extends Thread {
    Texture nullPng;
    Texture[] powerStation;
    Texture[] lumberjack;
    Texture[][] constMap;
    Construction[] construct; //---------------                       потом перефигачить это все дело в список (!3)
    Texture[][] ghostPreConstruction;
    Resource[] resource;
    Texture[] forest;
    int constructingTime = 2500;
    Vector2 buildPosition;
    int buildType = 0;
    boolean buildInProgress = false;
    int buildIndexInArray;
    Rectangle rect;
    Texture typeOfGhost;


    public Building(int sizeX, int sizeY) {
        construct = new Construction[100];    //--------------------  потом перефигачить это все дело в список (!3)
        nullPng = new Texture("null.png");
        constMap = new Texture[sizeX][sizeY];
        lumberjack = new Texture[4];
        powerStation = new Texture[4];
        ghostPreConstruction = new Texture[2][2];    // отрисовка призрака type1 type2
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
        ghostPreConstruction[0][0] = new Texture("construction/green1.png");
        ghostPreConstruction[0][1] = new Texture("construction/red1.png");
        ghostPreConstruction[1][0] = new Texture("construction/green2.png");
        ghostPreConstruction[1][1] = new Texture("construction/red2.png");
        rect = new Rectangle(-1000, -1000,0,0);
        typeOfGhost = nullPng;
       // resource = new Resource[10];
        forest = new Texture[2];
        forest[0] = new Texture("resourse/forest100x100.png");
        forest[1] = new Texture("resourse/forest150x150.png");
        createArrayOfResource(20);
//        resource[0] = new Forest(forest[1], forest[1].getWidth(),forest[1].getHeight(),       //3и проверочных, зафигачить потом метод по оозданию
//                1000, new Vector2(500,500));
//        resource[1] = new Forest(forest[1], forest[1].getWidth(),forest[1].getHeight(),
//                1000, new Vector2(630,500));
//        resource[2] = new Forest(forest[0], forest[0].getWidth(),forest[0].getHeight(),
//                1000, new Vector2(300,800));
//        resource[3] = new Forest(forest[1], forest[1].getWidth(),forest[1].getHeight(),
//                1000, new Vector2(760,500));
    }

    void render(SpriteBatch batch) {
        for (int i = 0; i < constMap.length; i++) {
            for (int j = 0; j < constMap[0].length; j++) {
                batch.draw(constMap[i][j], i * constMap[i][j].getWidth() + Garden.screenXPosition, j *
                        constMap[i][j].getHeight() + Garden.screenYPosition);
            }
        }
        for (int i = 0; i < construct.length; i++) {       //новый массив построек версия 2.0             //------------
            if (construct[i] != null) {
                batch.draw(construct[i].getImg(), construct[i].getPosition().x + Garden.screenXPosition,
                        construct[i].getPosition().y + Garden.screenYPosition);
//                construct[i].getRectangle().setPosition(construct[i].getPosition().x + Garden.screenXPosition,
//                        construct[i].getPosition().y + Garden.screenYPosition);  //------ version 0.034
            }
        }
        for (int i = 0; i<resource.length; i++){
            if (resource[i] != null){
                batch.draw(resource[i].getImg(), resource[i].getPosition().x + Garden.screenXPosition,
                        resource[i].getPosition().y + Garden.screenYPosition);
            }
        }
        if(buildType == 0){
            typeOfGhost = nullPng;
        }
        if(buildType == 1){                                //отрисовка макета для постройки
            batch.draw(typeOfGhost, Gdx.input.getX() - (ghostPreConstruction[0][0].getWidth() / 2 ),
                    Gdx.graphics.getHeight() - Gdx.input.getY() -(ghostPreConstruction[0][0].getHeight() / 2));
        }
        if(buildType == 2){
            batch.draw(typeOfGhost, Gdx.input.getX() - (ghostPreConstruction[1][0].getWidth() / 2 ),
                    Gdx.graphics.getHeight() - Gdx.input.getY() -(ghostPreConstruction[1][0].getHeight() / 2));
        }
    }

    void update() {
        for (int i = 0; i < construct.length; i++) {
            if (construct[i] != null)
                construct[i].update();
        }
        for (int i = 0; i < resource.length; i++) {
            if (resource[i] != null)
                resource[i].update();
        }
        if(buildType == 1) {
            rect.set(Gdx.input.getX() - 25, Gdx.graphics.getHeight() - Gdx.input.getY() - 25, 50, 50);
            if(checkAreaForBuilding(rect) == true){
                typeOfGhost = ghostPreConstruction[0][0];
            }
            else {
                typeOfGhost = ghostPreConstruction[0][1];
            }
        }
        if(buildType == 2) {
            rect.set(Gdx.input.getX() - 100, Gdx.graphics.getHeight() - Gdx.input.getY() - 50, 200, 100);
            if(checkAreaForBuilding(rect) == true){
                typeOfGhost = ghostPreConstruction[1][0];
            }
            else {
                typeOfGhost = ghostPreConstruction[1][1];
            }
        }
        if(buildType == 0){
            rect.set(-1000,-1000,0,0);
        }
    }
    public void setBuildType(int type){
        buildType = type;
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
    public synchronized void createBuildingThread(Vector2 position) {//поиск места в массиве,
        if (buildInProgress == false) {                           //инициализация переменных аргументами
            System.out.println("проход в if - progress true");    //с запуском в разных потоках
            buildInProgress = true;
            for (int i = 0; i < construct.length; i++) {
                if (construct[i] == null) {
                    System.out.println("итерация массива -" + i);
                    buildPosition = position;
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

    }

    @Override
    public void run() {
        building(buildPosition, buildType, buildIndexInArray);
    }

    public void building(Vector2 position, int type, int indexOfArray) {         //надо вхуярить проверку места и денег
        switch (type) {                                                          //отдельным методом
            case (1): {
                System.out.println(Hero.HeroMoney);
                if (Hero.HeroMoney >= 100 &&
                        typeOfGhost.equals(ghostPreConstruction[0][0])) {
                    Hero.HeroMoney -= 100;
                    construct[indexOfArray] = new PowerStation(powerStation, position);
                    animationBuildingProgress(buildIndexInArray);
                } else {
                    System.out.println("Sorry you cant buy this need - 100"+
                            "\n But you have only - " + Hero.HeroMoney);
                }
            }
            break;
            case (2): {
                System.out.println(Hero.HeroMoney);
                if (Hero.HeroMoney >= 1000 &&
                        typeOfGhost.equals(ghostPreConstruction[1][0])) {
                    Hero.HeroMoney -= 1000;
                    construct[indexOfArray] = new LumberJack(lumberjack, position);
                    animationBuildingProgress(buildIndexInArray);
                } else {
                    System.out.println("Sorry you cant buy this need - 1000" +
                            "\n But you have only - " + Hero.HeroMoney);
                }
            }
            break;
        }
        buildInProgress = false;
    }

    public void animationBuildingProgress(int buildIndexInArray) {
        buildInProgress = false;
        buildType = 0;
        for (int i = 0; i < 4; i++) {
            try {
                construct[buildIndexInArray].setImg(i);
                Thread.sleep(constructingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//
public boolean checkAreaForBuilding(Rectangle rect) {            //version 3.0
    for (int i = 0; i < construct.length; i++) {                 //проверка на наличие строений на местности
        if (construct[i] == null) {
            continue;
        }
        if (construct[i].getRectangle().overlaps(rect) || buildType == 0)
            return false;

    }
    for (int i = 0; i < resource.length; i++) {                 //проверка на наличие ресурсов на местности
        if (resource[i] == null) {
            continue;
        }
        if (resource[i].getRectangle().overlaps(rect) || buildType == 0)
            return false;

    }
    return true;
}

public void createArrayOfResource(int length) {
    Random rand = new Random();
    int count =0;
    resource = new Resource[length];
    for (int i = 0; i < length; i++) {
        resource[i] = new Forest(forest[i%forest.length], forest[i%forest.length].getWidth(),
                forest[i%forest.length].getHeight(),
                1000, new Vector2(100 + rand.nextInt(Garden.WIDTH_WINDOW * 2 - 300),
                100 + rand.nextInt(Garden.HEIGHT_WINDOW * 2 - 300)));
        for (int j = 0; j < length; j++) {
            count++;
            if (resource[j] == null || i == j)
                continue;
            if (resource[i].getRectangle().overlaps(resource[j].getRectangle())) {
                resource[i].setPosition(new Vector2(100 + rand.nextInt(Garden.WIDTH_WINDOW * 2 - 300),
                        100 + rand.nextInt(Garden.HEIGHT_WINDOW * 2 - 300)));
                j = -1;
                System.out.println("пизда рулю");
            }
        }

    }
    System.out.println(count+"---------------------------------------------------");
}
}
