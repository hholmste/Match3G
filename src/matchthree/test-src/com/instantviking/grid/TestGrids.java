package com.instantviking.grid;

import static com.instantviking.tile.TileType.BLUE;
import static com.instantviking.tile.TileType.GREEN;
import static com.instantviking.tile.TileType.ORANGE;
import static com.instantviking.tile.TileType.PURPLE;
import static com.instantviking.tile.TileType.RED;
import static com.instantviking.tile.TileType.YELLOW;

import com.instantviking.tile.Tile;
import com.instantviking.tile.TileType;

public class TestGrids {

	public static Grid build(TileType[][] types) {
		Grid g = new Grid(8);

		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				g.putAt(new Tile(types[row][column], row, column), row, column);
			}
		}

		return g;
	}

	public static Grid build(Line[] rows) {
		Grid g = new Grid(8);

		for (int row = 0; row < 8; row++) {
			Tile[] rowTiles = rows[row].asArray();
			for (int column = 0; column < 8; column++) {
				g.putAt(rowTiles[column], row, column);
			}
		}

		return g;
	}

	public static Line buildLine(TileType[] types, int rowNum) {
		Tile[] tiles = new Tile[8];
		for (int col = 0; col < 8; col++) {
			tiles[col] = new Tile(types[col], rowNum, col);
		}
		return new Line(tiles);
	}

	public static Grid rowsOfSimilarColor() {
		return build(new Line[] {
				buildLine(new TileType[] { BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE }, 0),
				buildLine(new TileType[] { RED, RED, RED, RED, RED, RED, RED, RED }, 1),
				buildLine(new TileType[] { GREEN, GREEN, GREEN, GREEN, GREEN, GREEN, GREEN, GREEN }, 2),
				buildLine(new TileType[] { ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE }, 3),
				buildLine(new TileType[] { PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE }, 4),
				buildLine(new TileType[] { YELLOW, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW, YELLOW }, 5),
				buildLine(new TileType[] { PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE, PURPLE }, 6),
				buildLine(new TileType[] { ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, PURPLE }, 7), });
	}

	public static Grid columnsOfSimilarColor() {
		return build(new Line[] {
				buildLine(new TileType[] { BLUE, RED, GREEN, ORANGE, PURPLE, YELLOW, PURPLE, ORANGE }, 0),
				buildLine(new TileType[] { BLUE, RED, GREEN, ORANGE, PURPLE, YELLOW, PURPLE, ORANGE }, 1),
				buildLine(new TileType[] { BLUE, RED, GREEN, ORANGE, PURPLE, YELLOW, PURPLE, ORANGE }, 2),
				buildLine(new TileType[] { BLUE, RED, GREEN, ORANGE, PURPLE, YELLOW, PURPLE, ORANGE }, 3),
				buildLine(new TileType[] { BLUE, RED, GREEN, ORANGE, PURPLE, YELLOW, PURPLE, ORANGE }, 4),
				buildLine(new TileType[] { BLUE, RED, GREEN, ORANGE, PURPLE, YELLOW, PURPLE, ORANGE }, 5),
				buildLine(new TileType[] { BLUE, RED, GREEN, ORANGE, PURPLE, YELLOW, PURPLE, ORANGE }, 6),
				buildLine(new TileType[] { BLUE, RED, GREEN, ORANGE, PURPLE, YELLOW, PURPLE, ORANGE }, 7), });
	}
}
