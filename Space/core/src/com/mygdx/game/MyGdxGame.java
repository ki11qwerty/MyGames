package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Player player;
	static Bullet[] bullets;
	Texture textureBullet;
	Asteroid textureAsteroid;
	//EnemyTarget enemyTarget;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		player = new Player();
		textureBullet = new Texture("bullet.png");
		bullets = new Bullet[100];
		for (int i =0; i<bullets.length; i++){
			bullets[i]= new Bullet();
		}
		textureAsteroid = new Asteroid(100);
	//	enemyTarget = new EnemyTarget();
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 0, 0 , 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
	background.render(batch);
	//	enemyTarget.render(batch);
		textureAsteroid.render(batch);
	player.render(batch);
	for (int i =0; i<bullets.length; i++){
		if (bullets[i].active){
			batch.draw(textureBullet, bullets[i].position.x -10, bullets[i].position.y - 10);
		}
	}
		batch.end();
	}


	public  void update(){
		background.update();
		player.update();
		textureAsteroid.update();
		for (int i = 0; i<bullets.length; i++){
			if (bullets[i].active){
				bullets[i].update();
			}
		}
	//	enemyTarget.update();
	//	if (enemyTarget.position.y > player.position.y + 100){
	//		enemyTarget.speed = 0f;
	//	}
	//	else if (enemyTarget.position.y < player.position.y){
	//		enemyTarget.speed = 5f;
		//}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
