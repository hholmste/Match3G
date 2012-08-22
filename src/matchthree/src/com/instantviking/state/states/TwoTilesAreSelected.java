package com.instantviking.state.states;

import com.instantviking.GameBoard;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;

// is this really necessary?
public class TwoTilesAreSelected extends State {

	private Tile a;
	private Tile b;

	public TwoTilesAreSelected(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	public void setTiles(Tile a, Tile b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void enter() {
		a.beginMove(b.row, b.column);
		b.beginMove(a.row, a.column);
	}

	@Override
	public State tick(long delta) {
		machine.swappingTiles.a = a;
		machine.swappingTiles.b = b;
		return machine.swappingTiles;
	}

	@Override
	public void leave() {
		this.a = null;
		this.b = null;
	}
}
