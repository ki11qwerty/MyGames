package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Garden extends ApplicationAdapter implements MyConstSettings {
    public static int screenXPosition =0;
    public static int screenYPosition =0;
    SpriteBatch batch;
    MyMap map;
    Hero hero;
    Constructions constructions;
    Random rand = new Random();
    int nextBlockSwaping = 0;
    int constructNum = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        map = new MyMap(SIZE_X, SIZE_Y);
        hero = new Hero(10);
        constructions = new Constructions(SIZE_X, SIZE_Y);
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        map.render(batch);           //отрисовка массив-карты
        constructions.render(batch); // отрисовка строений
        batch.draw(hero.img, hero.Xposition, hero.Yposition);  //отрисовка героя на карте

        batch.end();
    }

    public void update() {
        hero.update();
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_1)) {
            nextBlockSwaping = 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_2)) {
            nextBlockSwaping = 2;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_3)) {
            System.out.println("3");
            nextBlockSwaping = 3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {
            System.out.println("4");
            constructNum = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_5)) {
            System.out.println("5");
            constructNum = 0;
        }
        if (Gdx.input.justTouched()) {          //свап-блок для мышки
            if (constructNum == 0) {
                System.out.println(" " + Gdx.input.getX() / 50 + ", " + (Gdx.graphics.getHeight() - Gdx.input.getY()) / 50);
                map.swapTexture(Gdx.input.getX() / 50, (Gdx.graphics.getHeight() - Gdx.input.getY()) / 50,
                        nextBlockSwaping);
            }
        }
        if (Gdx.input.justTouched()) {         //стройка для мышки
            if (constructNum != 0) {
                System.out.println(" " + Gdx.input.getX() / 50 + ", " + (Gdx.graphics.getHeight() - Gdx.input.getY()) / 50);
                constructions.build(Gdx.input.getX() / 50, (Gdx.graphics.getHeight() - Gdx.input.getY()) / 50,
                        constructNum);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {     //свап-блок для позиции героя
            if (constructNum == 0) {
                map.swapTexture(hero.Xposition / 50, hero.Yposition / 50, nextBlockSwaping);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {     //стройка для позиции героя
            if (constructNum != 0) {
                constructions.build(hero.Xposition / 50, hero.Yposition / 50,
                        constructNum);
            }
        }
    }
}
