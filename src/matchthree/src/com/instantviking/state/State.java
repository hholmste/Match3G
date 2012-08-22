package com.instantviking.state;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.instantviking.GameBoard;
import com.instantviking.tile.Tile;

public abstract class State {

	protected final GameBoard gameBoard;
	protected final Machine machine;
	private final List<Tile> adoptedTiles;

	public State(GameBoard gameBoard, Machine machine) {
		this.adoptedTiles = new ArrayList<Tile>();
		this.gameBoard = gameBoard;
		this.machine = machine;
	}

	public State tick(long delta) {
		return this;
	}

	public State selectTile(Tile selectedTile) {
		return this;
	}

	public void leave() {
	}

	public void enter() {
	}

	/**
	 * each state implementation is responsible for rendering whatever tiles it
	 * has adopted, as nobody else will.
	 * 
	 * @param batch
	 */
	public void render(SpriteBatch batch) {
	}

	/**
	 * when a state wants to control the animation of a tile, it must first
	 * 'adopt' it.
	 * 
	 * @param tile
	 */
	protected void adopt(Tile... tiles) {
		for (Tile tile : tiles) {
			this.adoptedTiles.add(tile);
			this.gameBoard.remove(tile);
		}
	}

	/**
	 * when a state is done with a tile, it must abandon it, which lets the
	 * gameBoard take back control
	 * 
	 * @param tile
	 */
	protected void abandon(Tile... tiles) {
		for (Tile tile : tiles) {
			this.adoptedTiles.remove(tile);
			this.gameBoard.add(tile);
		}
	}

}
