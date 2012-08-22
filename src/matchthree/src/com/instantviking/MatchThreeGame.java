package com.instantviking;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.instantviking.assets.Sprites;
import com.instantviking.assets.Textures;
import com.instantviking.io.InputHandler;

public class MatchThreeGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private InputProcessor inputProcessor;

	GameBoard gameBoard;

	private long lastFrame;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();

		Textures.init();
		Sprites.init();

		gameBoard = new GameBoard(batch);
		inputProcessor = new InputHandler(gameBoard);

		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void dispose() {
		batch.dispose();
		Textures.dispose();
	}

	@Override
	public void render() {
		long delta = System.currentTimeMillis() - lastFrame;

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		gameBoard.tick(batch, delta);

		batch.end();

		lastFrame = System.currentTimeMillis();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
