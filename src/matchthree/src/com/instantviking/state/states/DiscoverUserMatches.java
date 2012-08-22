package com.instantviking.state.states;

import java.util.HashSet;
import java.util.Set;

import com.instantviking.GameBoard;
import com.instantviking.Match;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;

public class DiscoverUserMatches extends State {

	Tile a;
	Tile b;

	Set<Match> validMatches;

	public DiscoverUserMatches(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	public void setUserTiles(Tile a, Tile b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public State tick(long delta) {
		collectMatches();
		if (thereAreMatches()) {
			machine.destroyMatches.setDestroyables(validMatches);
			return machine.destroyMatches;
		} else {
			machine.undoSwap.swap(a, b);
			return machine.undoSwap;
		}
	}

	private boolean thereAreMatches() {
		return !validMatches.isEmpty();
	}

	private void collectMatches() {
		validMatches.addAll(filter(gameBoard.collectMatches(a)));
		validMatches.addAll(filter(gameBoard.collectMatches(b)));
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

	@Override
	public void enter() {
		validMatches = new HashSet<Match>();
	}

}
