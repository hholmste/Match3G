package com.instantviking.tile;

/**
 * Holds a single tile
 */
public class Tile {

	public TileState state;
	public TileType type;
	public int row;
	public int column;
	public int desiredRow;
	public int desiredColumn;
	public int oldRow;
	public int oldColumn;

	private boolean marked = false;
	private boolean selected;

	public Tile(TileType type, int row, int column) {
		this.type = type;
		this.state = TileState.RESTING;
		this.row = row;
		this.column = column;
	}

	public void notifyAnimationFinished() {
		if (this.state == TileState.MOVING) {
			this.state = TileState.RESTING;
			this.oldRow = this.row;
			this.oldColumn = this.column;
			this.row = this.desiredRow;
			this.column = this.desiredColumn;
		} else if (this.state == TileState.BEING_DESTROYED) {
			this.state = TileState.DESTROYED;
		}
	}

	public void select() {
		this.selected = true;
	}

	public void unselect() {
		this.selected = false;
	}

	public boolean isSelected() {
		return this.selected;
	}

	@Override
	public String toString() {
		return String.format("Tile[%s, %d/%d]", type.getShortName(), row, column);
	}

	public void markForDebuggery() {
		this.marked = true;
	}

	public void unmarkForDebuggery() {
		this.marked = false;
	}

	public boolean isMarked() {
		return marked;
	}

	public void beginDestroy() {
		this.state = TileState.BEING_DESTROYED;
	}

	public void finishDestroy() {
		state = TileState.DESTROYED;
	}

	public boolean isMoving() {
		return this.state == TileState.MOVING;
	}

	public boolean isBeingDestroyed() {
		return state == TileState.BEING_DESTROYED;
	}

	public boolean isDestroyed() {
		return state == TileState.DESTROYED;
	}

	public void beginMove(int desiredRow, int desiredColumn) {
		this.desiredRow = desiredRow;
		this.desiredColumn = desiredColumn;
		this.state = TileState.MOVING;
	}

	public void finishMove() {
		oldRow = row;
		row = desiredRow;
		desiredRow = -1;

		oldColumn = column;
		column = desiredColumn;
		desiredColumn = -1;

		state = TileState.RESTING;
	}

}
