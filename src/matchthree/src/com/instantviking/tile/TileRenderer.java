package com.instantviking.tile;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.instantviking.assets.Sprites;

public class TileRenderer {
	private static final float HEIGHT_OFFSET = 0.3f;
	private static final float WIDTH_OFFSET = 0.4f;

	public static void renderTileInMotion(SpriteBatch batch, Tile tile, float progress) {
		if (tile != null) {
			Sprite sprite = Sprites.forTileType(tile.type, tile.state);

			float xPos = (tile.column / 10.0f) - WIDTH_OFFSET;
			float yPos = HEIGHT_OFFSET - (tile.row / 10.0f);

			float xMoveTiles = tile.column - tile.desiredColumn;
			float yMoveTiles = tile.row - tile.desiredRow;

			float xPartTiles = 0.1f * xMoveTiles * progress;
			float yPartTiles = 0.1f * yMoveTiles * progress;

			xPos -= xPartTiles;
			yPos += yPartTiles;

			sprite.setPosition(xPos, yPos);

			sprite.draw(batch);
		}
	}

	public static void renderTileBeingDestroyed(SpriteBatch batch, Tile tile, float progress) {
		if (tile != null) {
			Sprite sprite = Sprites.forTileType(tile.type, tile.state);

			float xPos = (tile.column / 10.0f) - WIDTH_OFFSET;
			float yPos = HEIGHT_OFFSET - (tile.row / 10.0f);

			sprite.setPosition(xPos, yPos);
			sprite.setColor(1.0f, 1.0f, 1.0f, 1.0f - progress);

			sprite.draw(batch);
		}
	}

	public static void renderTile(SpriteBatch batch, Tile tile) {
		if (tile != null) {
			Sprite sprite = Sprites.forTileType(tile.type, tile.state);
			setSpritePosition(sprite, tile);
			setSpriteColor(sprite, tile);
			sprite.draw(batch);

			if (tile.isSelected()) {
				Sprites.glow.draw(batch);
			}
		}
	}

	private static void setSpriteColor(Sprite sprite, Tile tile) {
		if (tile.isBeingDestroyed()) {
			float fadeFactor = 1.0f;
			sprite.setColor(1.0f, 1.0f, 1.0f, 1.0f - fadeFactor);
		}
	}

	private static synchronized void setSpritePosition(Sprite sprite, Tile tile) {
		float xPos = (tile.column / 10.0f) - WIDTH_OFFSET;
		float yPos = HEIGHT_OFFSET - (tile.row / 10.0f);

		if (tile.isMoving()) {
			float part = 1.0f;

			float xMoveTiles = tile.column - tile.desiredColumn;
			float yMoveTiles = tile.row - tile.desiredRow;

			float xPartTiles = 0.1f * xMoveTiles * part;
			float yPartTiles = 0.1f * yMoveTiles * part;

			xPos -= xPartTiles;
			yPos += yPartTiles;
		}

		sprite.setPosition(xPos, yPos);

		if (tile.isSelected()) {
			Sprites.glow.setPosition(xPos, yPos);
		}

	}

}
