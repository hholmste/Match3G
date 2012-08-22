package com.instantviking.state.states;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.instantviking.GameBoard;
import com.instantviking.grid.Line;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;

public class TilesHaveBeenDestroyed extends State {

	private List<Tile> destroyedTiles;

	public TilesHaveBeenDestroyed(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	public void setDestroyedTiles(List<Tile> destroyedTiles) {
		this.destroyedTiles = destroyedTiles;
	}

	@Override
	public State tick(long delta) {
		return machine.tilesAreFalling;
	}

	@Override
	public void enter() {
		applyDestructionToColumns(affectedColumns());
	}

	@Override
	public void leave() {
		this.destroyedTiles = null;
	}

	private void applyDestructionToColumns(Set<Integer> affectedColumns) {
		for (Integer column : affectedColumns) {
			applyDestructionToColumn(gameBoard.getColumn(column), column);
		}
	}

	private void applyDestructionToColumn(Line column, int columnIndex) {
		int spacesToFall = 0;

		for (int i = column.size() - 1; i >= 0; i--) {
			Tile tile = column.get(i);
			if (tile == null) {
				spacesToFall++;
				Tile newTile = gameBoard.createNewTile(-1 * spacesToFall, columnIndex);
				newTile.beginMove(spacesToFall - 1, columnIndex);
				machine.tilesAreFalling.addFallingTile(newTile);
			} else if (spacesToFall > 0) {
				tile.beginMove(tile.row + spacesToFall, tile.column);
				machine.tilesAreFalling.addFallingTile(tile);
			}
		}
	}

	private Set<Integer> affectedColumns() {
		Set<Integer> result = new HashSet<Integer>();

		for (Tile t : destroyedTiles) {
			result.add(t.column);
		}

		return result;
	}

}
