package com.instantviking.state.states;

import java.util.HashSet;
import java.util.Set;

import com.instantviking.GameBoard;
import com.instantviking.Match;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;

public class DestroyMatches extends State {

	private Set<Tile> destroyables;

	public DestroyMatches(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	public void setDestroyables(Set<Match> forDestruction) {
		destroyables = new HashSet<Tile>();
		for (Match m : forDestruction) {
			for (Tile t : m.getMatches()) {
				destroyables.add(t);
			}
		}
	}

	@Override
	public State tick(long delta) {
		machine.tilesAreBeingDestroyed.setDestroyables(destroyables);

		for (Tile t : destroyables) {
			t.beginDestroy();
		}

		return machine.tilesAreBeingDestroyed;
	}

}
