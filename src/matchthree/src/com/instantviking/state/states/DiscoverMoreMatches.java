package com.instantviking.state.states;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.instantviking.GameBoard;
import com.instantviking.Match;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;

/**
 * knows what tiles have moved, and uses these to discover if there are more
 * matches to be made.
 * 
 */
public class DiscoverMoreMatches extends State {
	private List<Tile> tiles; // stuff that moved last state
	private Set<Match> validMatches;

	public DiscoverMoreMatches(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	@Override
	public void enter() {
		validMatches = new HashSet<Match>();
		collectMatches();
	}

	@Override
	public State tick(long delta) {
		if (validMatches.isEmpty()) {
			return machine.neutral;
		} else {
			machine.destroyMatches.setDestroyables(validMatches);
			return machine.destroyMatches;
		}
	}

	private void collectMatches() {
		for (Tile t : tiles)
			validMatches.addAll(filter(gameBoard.collectMatches(t)));
	}

	private Set<Match> filter(Set<Match> unfiltered) {
		Set<Match> filtered = new HashSet<Match>();

		for (Match m : unfiltered) {
			if (m.isValid()) {
				filtered.add(m);
			}
		}

		return filtered;
	}

	public void addTile(Tile tile) {
		getTiles().add(tile);
	}

	private List<Tile> getTiles() {
		if (tiles == null)
			tiles = new ArrayList<Tile>();
		return tiles;
	}

}
