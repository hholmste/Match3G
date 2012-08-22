package com.instantviking.tile;

import java.util.Random;

import com.instantviking.grid.Grid;

public class TileSource {

	private final Random rand = new Random();

	public Tile createTile(Grid source, int row, int column) {
		return new Tile(TileType.values()[rand.nextInt(5)], row, column);
	}

}
