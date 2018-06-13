package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class wtf extends ApplicationAdapter {
    public static  void main(String[] args){
        long time = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - time);
    }
}
