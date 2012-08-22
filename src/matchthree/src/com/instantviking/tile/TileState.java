package com.instantviking.tile;

public enum TileState {
	RESTING, MOVING, BEING_DESTROYED, DESTROYED;

	public boolean useDefault() {
		return this == RESTING || this == MOVING;
	}
}