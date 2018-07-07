// проект временно заморожен, всвязи с проблемами которые пока не удается решить, при разработке всплывающего окна меню
//построек, оказалось что придется менять всю архетектуру приложения, и лучше остановить это дело сейчас, чем
//чем потом переделывать все по новой когда будет большой обьем...

/*****************************************************
 *---------------------------------------------------*
 *               version - 0.036    @Stable          *
 *        created      by   Ki11qwerty               *
 *        disigner     -    Killqwerty               *
 *        codeWriter   -    Killqwerty               *
 *        brainFucker  -    Killqwerty               *
 *---------------------------------------------------*
 *               latest upDate -                     *
 *                                                   *
 * 0.036 fix forest position & created random pos    *
 * 0.035 start creating resourses... test forest     *
 * textures                                          *
 * 0.034 fix bugs with ghostConstruct &              *
 * rectangle position on map (its fcn stable!)       *
 *                                                   *
 * 0.033 add ghostConstruct (non stable)             *
 *                                                   *
 * 0.032 fix bug with rectangles                     *
*****************************************************/
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Garden extends ApplicationAdapter implements MyConstSettings {
    public static int screenXPosition;
    public static int screenYPosition;
    SpriteBatch batch;
    MyMap map;
    Hero hero;
    Building building;
//    Random rand = new Random();
//    int nextBlockSwaping = 0;
//    int constructNum = 0;
    int constructType = 0;         //надо выпилить со следующий обновы

    @Override
    public void create() {
        screenXPosition = 0;
        screenYPosition = 0;
        batch = new SpriteBatch();
        map = new MyMap(SIZE_X, SIZE_Y);
        hero = new Hero(10);
        building = new Building(SIZE_X, SIZE_Y);
        new Thread(this).start();
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        map.render(batch);           //отрисовка массив-карты
        building.render(batch); // отрисовка строений
        batch.draw(hero.img, hero.Xposition, hero.Yposition);  //отрисовка героя на карте
        batch.end();
    }

    public void update() {
        hero.update();
        building.update();
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_1)) {
//            nextBlockSwaping = 1;
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_2)) {
//            nextBlockSwaping = 2;
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_3)) {
//            System.out.println("3");
//            nextBlockSwaping = 3;
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)) {
            System.out.println("1 - powerStation");
            building.setBuildType(1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
            System.out.println("2 - LumberJack");
            building.setBuildType(2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)) {
            System.out.println("3 - обнуление setBuildType = 0");
            building.setBuildType(0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)) {
            System.out.println(Hero.HeroMoney);
        }
        /*-------------------------------------------------------------------------------------------------------------
        *далее следует адовая дичь. из за разных точек отчета Y у Gdx.input.getY(), считывает не с левого нижнего угла
        * а с левого верхнего, поэтому из высоты экрана отнимаем getY(). далее статическая переменная
        * Garden.Possition высчитывает положения экрана, а далее делим на 50 (ширина одной ячейки отображаемого массива
         * там точно без ста грамм не обойтись короче...
        * ------------------------------------------------------------------------------------------------------------
         */
//        if (Gdx.input.justTouched()) {          //свап-блок для мышки
//            if (constructNum == 0) {
//                System.out.println(" " + (Gdx.input.getX() - Garden.screenXPosition) / 50 + ", " +
//                        (Gdx.graphics.getHeight() - Gdx.input.getY() - Garden.screenYPosition) / 50);
//                map.swapTexture((Gdx.input.getX() - Garden.screenXPosition) / 50, (Gdx.graphics.getHeight() -
//                                Gdx.input.getY() - Garden.screenYPosition) / 50,
//                        nextBlockSwaping);
//            }
//        }
//        if (Gdx.input.justTouched()) {         //стройка для мышки
//            if (constructType != 0) {
//                System.out.println(" " + (Gdx.input.getX() - Garden.screenXPosition) / 50 + ", " +
//                        (Gdx.graphics.getHeight() - Gdx.input.getY() - Garden.screenYPosition) / 50);
//                building.build((Gdx.input.getX() - Garden.screenXPosition) / 50, (Gdx.graphics.getHeight() -
//                                Gdx.input.getY() - Garden.screenYPosition) / 50,
//                        constructType);
//            }
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {     //свап-блок для позиции героя
//            if (constructNum == 0) {
//                map.swapTexture((hero.Xposition -Garden.screenXPosition) / 50, (hero.Yposition -
//                        Garden.screenYPosition) / 50, nextBlockSwaping);
//            }
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {     //стройка для позиции героя
//            if (constructType != 0) {
//                building.build((hero.Xposition -Garden.screenXPosition) / 50, (hero.Yposition -
//                                Garden.screenYPosition)/ 50,
//                        constructType);
//            }
//        }
        if (Gdx.input.justTouched()) {
           // if (constructType != 0) {
//                System.out.println(" " + (Gdx.input.getX() - Garden.screenXPosition) / 50 + ", " +
//                        (Gdx.graphics.getHeight() - Gdx.input.getY() - Garden.screenYPosition) / 50);
                building.createBuildingThread(getVector2Click(Gdx.input.getX() - Garden.screenXPosition,
                        Gdx.graphics.getHeight() - Gdx.input.getY() - Garden.screenYPosition));
            }
        }
   // }

    public Vector2 getVector2Click(int x, int y){
        return new Vector2(x,y);
    }
    @Override
    public void run() {            //отслеживание мышки и игрока, двигаем экран
        while (true) {
            if (Gdx.input.getX() > WIDTH_WINDOW - 100 || hero.Xposition >= WIDTH_WINDOW - 15)
                screenXPosition -= SCROLL_SPEED;
            if (Gdx.input.getX() < 100 || hero.Xposition <= 15)
                screenXPosition += SCROLL_SPEED;
            if (Gdx.graphics.getHeight() - Gdx.input.getY() > HEIGHT_WINDOW - 100 || hero.Yposition >= HEIGHT_WINDOW -15)
                screenYPosition -= SCROLL_SPEED;
            if (Gdx.graphics.getHeight() - Gdx.input.getY() < 100 || hero.Yposition <= 15)
                screenYPosition += SCROLL_SPEED;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Hero.HeroMoney += 1;

        }
    }
}
