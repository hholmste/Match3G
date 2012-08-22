package com.instantviking.state.states;

import com.instantviking.GameBoard;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;

/**
 * Nothing has been selected, no animations of any consequence are running
 * 
 */
public class Neutral extends State {

	public Neutral(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	@Override
	public State selectTile(Tile selectedTile) {
		selectedTile.select();
		machine.oneTileIsSelected.setSelectedTile(selectedTile);
		return machine.oneTileIsSelected;
	}

}
