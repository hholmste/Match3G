package com.instantviking.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.instantviking.tile.Tile;
import com.instantviking.tile.TileType;

public class Line {
	private final Tile[] tiles;

	public Line(int size) {
		tiles = new Tile[8];
	}

	public Line(Tile[] source) {
		tiles = Arrays.copyOf(source, source.length);
	}

	public int size() {
		return tiles.length;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder("Line[");

		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] == null) {
				b.append(" ( ) ");
			} else {
				b.append(" ").append(tiles[i].type.getShortName()).append(" ");
			}

		}

		return b.append("]").toString();
	}

	void put(Tile tile, int index) {
		tiles[index] = tile;
	}

	public Tile get(int column) {
		return tiles[column];
	}

	public Tile[] asArray() {
		return Arrays.copyOf(tiles, tiles.length);
	}

	public List<Tile> matches(int fromIndex) {
		List<Tile> matches = new ArrayList<Tile>();

		TileType target = tiles[fromIndex].type;
		int left = walkLeft(fromIndex, target);
		int right = walkRight(fromIndex, target);

		for (int i = left; i <= right; i++) {
			matches.add(tiles[i]);
		}

		return matches;
	}

	private int walkLeft(int index, TileType target) {
		if (tiles[index].type != target) {
			return index + 1;
		}

		if (index == 0) {
			return index;
		}

		return walkLeft(index - 1, target);
	}

	private int walkRight(int index, TileType target) {
		if (tiles[index].type != target) {
			return index - 1;
		}

		if (index == tiles.length - 1) {
			return index;
		}
		return walkRight(index + 1, target);
	}

	public void remove(int index) {
		tiles[index] = null;
	}

}
