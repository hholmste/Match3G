package com.instantviking.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Textures {

	public static Texture background;
	public static Texture leftWallTexture;
	public static Texture rightWallTexture;
	public static Texture topWallTexture;
	public static Texture bottomWallTexture;
	public static Texture redTexture;
	public static Texture greenTexture;
	public static Texture blueTexture;
	public static Texture orangeTexture;
	public static Texture yellowTexture;
	public static Texture glowTexture;

	public static Texture debuggery;

	public static void init() {
		background = loadTexture("data/border.png");
		leftWallTexture = loadTexture("data/wallLeft.png");
		rightWallTexture = loadTexture("data/wallRight.png");
		topWallTexture = loadTexture("data/wallTop.png");
		bottomWallTexture = loadTexture("data/wallBottom.png");
		redTexture = loadTexture("data/tileRed.png");
		greenTexture = loadTexture("data/tileGreen.png");
		blueTexture = loadTexture("data/tileBlue.png");
		orangeTexture = loadTexture("data/tileOrange.png");
		yellowTexture = loadTexture("data/tileYellow.png");
		glowTexture = loadTexture("data/glow.png");

		debuggery = loadTexture("data/debuggery.png");
	}

	private static Texture loadTexture(String filename) {
		Texture texture = new Texture(Gdx.files.internal(filename));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return texture;
	}

	public static void dispose() {
		background.dispose();

		leftWallTexture.dispose();
		rightWallTexture.dispose();
		topWallTexture.dispose();
		bottomWallTexture.dispose();

		redTexture.dispose();
		greenTexture.dispose();
		blueTexture.dispose();
		orangeTexture.dispose();
		yellowTexture.dispose();

		glowTexture.dispose();

		debuggery.dispose();
	}

}
