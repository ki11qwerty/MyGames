package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class wtf extends ApplicationAdapter {
    public static  void main(String[] args){
        WTF2 wtf2 = new WTF2();

    }
}
class WTF2{
    WTF2(){
        System.out.println("внутри конструктора");
        prt();

    }
    public void prt(){
        System.out.println("вызов метода прям блять из конструктора");
    }
}
