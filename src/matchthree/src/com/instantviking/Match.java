package com.instantviking;

import java.util.List;

import com.instantviking.tile.Tile;

public class Match {
	private final List<Tile> matches;

	public Match(List<Tile> matches) {
		this.matches = matches;
	}

	public boolean isValid() {
		return matches.size() >= 3;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();

		b.append("matches[");
		for (Tile t : matches)
			b.append(t.type).append(" at row ").append(t.row).append(" col ").append(t.column).append(", ");

		return b.append("]").toString();
	}

	/**
	 * silly debuggery
	 */
	public void markForDebuggery() {
		for (Tile t : matches)
			t.markForDebuggery();
	}

	public List<Tile> getMatches() {
		return matches;
	}

}
