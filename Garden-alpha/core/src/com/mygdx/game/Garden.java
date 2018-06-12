package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Garden extends ApplicationAdapter {
    SpriteBatch batch;
    private final int MAP_SIZE_X = 1350 / 50;
    private final int MAP_SIZE_Y = 750 / 50;
    MyMap map;
    Hero hero;
    Constructions constructions;
    Random rand = new Random();
    int nextBlockSwaping = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        map = new MyMap(MAP_SIZE_X, MAP_SIZE_Y);
        hero = new Hero(10);
        constructions = new Constructions(MAP_SIZE_X,MAP_SIZE_Y);
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for (int i = 0; i < map.myMapArr.length; i++) {
            for (int j = 0; j < map.myMapArr[0].length; j++) {
                batch.draw(map.myMapArr[i][j], i * map.myMapArr[i][j].getWidth()
                        , j * map.myMapArr[i][j].getHeight());
            }
        }
        constructions.render(batch);
        batch.draw(hero.img, hero.Xposition, hero.Yposition);

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
            ;
            nextBlockSwaping = 3;
        }
        if (Gdx.input.isTouched()) {
            System.out.println(" " + Gdx.input.getX() / 50 + ", " + (Gdx.graphics.getHeight() - Gdx.input.getY()) / 50);
            map.swapTexture(Gdx.input.getX() / 50, (Gdx.graphics.getHeight() - Gdx.input.getY()) / 50,
                    nextBlockSwaping);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            map.swapTexture(hero.Xposition / 50, hero.Yposition / 50, nextBlockSwaping);
            System.out.println(" " + hero.Xposition / 50 + ", " + hero.Yposition / 50);
        }
    }
}
