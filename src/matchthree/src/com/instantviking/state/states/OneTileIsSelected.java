package com.instantviking.state.states;

import com.instantviking.GameBoard;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;

public class OneTileIsSelected extends State {

	private Tile selectedTile;

	public OneTileIsSelected(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	@Override
	public State selectTile(Tile newSelectedTile) {
		if (neighbours(selectedTile, newSelectedTile)) {
			newSelectedTile.select();
			machine.twoTilesAreSelected.setTiles(selectedTile, newSelectedTile);
			return machine.twoTilesAreSelected;
		} else if (selectedTile == newSelectedTile) {
			selectedTile.unselect();
			this.selectedTile = null;
			return machine.neutral;
		}

		return this;
	}

	private boolean neighbours(Tile a, Tile b) {
		return distance(a, b) == 1;
	}

	private int distance(Tile a, Tile b) {
		return Math.abs(a.row - b.row) + Math.abs(a.column - b.column);
	}

	public void setSelectedTile(Tile selectedTile) {
		this.selectedTile = selectedTile;
	}
}
