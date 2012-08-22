package com.instantviking.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.instantviking.tile.TileState;
import com.instantviking.tile.TileType;

public class Sprites {
	public static Sprite background;

	public static Sprite leftWall;
	public static Sprite rightWall;
	public static Sprite topWall;
	public static Sprite bottomWall;
	public static Sprite red;
	public static Sprite green;
	public static Sprite blue;
	public static Sprite orange;
	public static Sprite yellow;
	public static Sprite glow;

	public static Sprite redFading;
	public static Sprite greenFading;
	public static Sprite blueFading;
	public static Sprite orangeFading;
	public static Sprite yellowFading;

	public static Sprite debuggery;

	public static void init() {
		background = makeSprite(Textures.background);

		leftWall = makeSprite(Textures.leftWallTexture);
		rightWall = makeSprite(Textures.rightWallTexture);
		topWall = makeSprite(Textures.topWallTexture);
		bottomWall = makeSprite(Textures.bottomWallTexture);
		red = makeSprite(Textures.redTexture);
		green = makeSprite(Textures.greenTexture);
		blue = makeSprite(Textures.blueTexture);
		orange = makeSprite(Textures.orangeTexture);
		yellow = makeSprite(Textures.yellowTexture);
		glow = makeSprite(Textures.glowTexture);

		redFading = makeSprite(Textures.redTexture);
		greenFading = makeSprite(Textures.greenTexture);
		blueFading = makeSprite(Textures.blueTexture);
		orangeFading = makeSprite(Textures.orangeTexture);
		yellowFading = makeSprite(Textures.yellowTexture);

		debuggery = makeSprite(Textures.debuggery);
	}

	private static Sprite makeSprite(Texture texture) {
		Sprite sprite = new Sprite(new TextureRegion(texture, 0, 0, 64, 64));
		sprite.setSize(0.1f, 0.1f);
		sprite.setOrigin(32, 32);
		return sprite;
	}

	public static Sprite forTileType(TileType tileType, TileState tileState) {
		switch (tileType) {
		case RED: {
			return tileState.useDefault() ? red : redFading;
		}
		case BLUE: {
			return tileState.useDefault() ? blue : blueFading;
		}
		case GREEN: {
			return tileState.useDefault() ? green : greenFading;
		}
		case ORANGE: {
			return tileState.useDefault() ? orange : orangeFading;
		}
		case YELLOW: {
			return tileState.useDefault() ? yellow : yellowFading;
		}
		default: {
			throw new RuntimeException("No such tile: " + tileType);
		}
		}
	}
}
