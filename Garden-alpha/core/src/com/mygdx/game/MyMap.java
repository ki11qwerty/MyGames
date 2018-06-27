package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class MyMap  {
    private Texture myTexture1;
    private Texture myTexture2;
    private Texture myTexture3;
    Texture[][] myMapArr;

    public MyMap(int sizeX, int sizeY) {
        this.myTexture1 = new Texture("green1.png");
        this.myTexture2 = new Texture("green2.png");
        this.myTexture3 = new Texture("green3.png");
        myMapArr = new Texture[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (i == 0 || i == sizeX - 1 || j == 0 || j == sizeY - 1) {
                    myMapArr[i][j] = myTexture3;
                    continue;
                }
                if (Math.random() * 1 > 0.5)              //рандомим одну из двуж текстур
                    myMapArr[i][j] = myTexture1;
                else
                    myMapArr[i][j] = myTexture2;
            }
        }
    }

    public Texture[][] getMyMapArr() {
        return myMapArr;
    }

    public void render(SpriteBatch batch) {                     //отрисовываем массив-карту
        for (int i = 0; i < myMapArr.length; i++) {
            for (int j = 0; j < myMapArr[0].length; j++) {
                batch.draw(myMapArr[i][j], i * myMapArr[i][j].getWidth() + Garden.screenXPosition
                        , j * myMapArr[i][j].getHeight() + Garden.screenYPosition);
            }
        }
    }

//    public void swapTexture(int i, int j, int nextBlockSwaping) {  //свапаем епт
//        System.out.println("в свапе " +i+","+j+",свапинг - "+nextBlockSwaping);
//        if (checkArray(i, j) == true) {
//            switch (nextBlockSwaping) {
//                case (1): {
//                    myMapArr[i][j] = myTexture1;
//                    break;
//                }
//                case (2): {
//                    myMapArr[i][j] = myTexture2;
//                    break;
//                }
//                case (3): {
//                    myMapArr[i][j] = myTexture3;
//                    break;
//                }
//            }
//        } else return;
//    }

    public boolean checkArray(int i, int j) {         //проверка краев массива, чтоб не тыкнуть за края
        if (i > myMapArr.length - 1 || j > myMapArr[0].length - 1 || i < 0 || j < 0)
            return false;
        else
            return true;
    }

    public void update() {

    }
}
