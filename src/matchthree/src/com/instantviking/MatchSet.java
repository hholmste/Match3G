package com.instantviking;

import java.util.HashSet;
import java.util.Set;

import com.instantviking.tile.Tile;

public class MatchSet {

	public enum Direction {
		HORIZONTAL, VERTICAL
	};

	private final Set<Tile> tiles = new HashSet<Tile>();

	public void add(Tile tile) {
		tiles.add(tile);
	}

	public boolean isDestroyable() {
		return tiles.size() >= 3;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Matches[");
		for (Tile tile : tiles) {
			builder.append(tile.toString()).append(", ");
		}
		return builder.append("]").toString();
	}

}
