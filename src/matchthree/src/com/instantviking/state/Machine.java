package com.instantviking.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.instantviking.GameBoard;
import com.instantviking.state.states.DestroyMatches;
import com.instantviking.state.states.DiscoverMoreMatches;
import com.instantviking.state.states.DiscoverUserMatches;
import com.instantviking.state.states.Neutral;
import com.instantviking.state.states.OneTileIsSelected;
import com.instantviking.state.states.SwappingTiles;
import com.instantviking.state.states.TilesAreBeingDestroyed;
import com.instantviking.state.states.TilesAreFalling;
import com.instantviking.state.states.TilesHaveBeenDestroyed;
import com.instantviking.state.states.TwoTilesAreSelected;
import com.instantviking.state.states.UndoSwap;
import com.instantviking.tile.Tile;

public class Machine {

	public static final Logger LOG = new Logger("Machine");

	private State currentState;

	public Neutral neutral;
	public OneTileIsSelected oneTileIsSelected;
	public TwoTilesAreSelected twoTilesAreSelected;
	public SwappingTiles swappingTiles;
	public DiscoverUserMatches discoverUserMatches;
	public DestroyMatches destroyMatches;
	public UndoSwap undoSwap;
	public TilesAreBeingDestroyed tilesAreBeingDestroyed;
	public TilesHaveBeenDestroyed tilesHaveBeenDestroyed;
	public TilesAreFalling tilesAreFalling;
	public DiscoverMoreMatches discoverMoreMatches;

	public Machine(GameBoard gameBoard) {
		this.neutral = new Neutral(gameBoard, this);
		this.oneTileIsSelected = new OneTileIsSelected(gameBoard, this);
		this.twoTilesAreSelected = new TwoTilesAreSelected(gameBoard, this);
		this.swappingTiles = new SwappingTiles(gameBoard, this);
		this.discoverUserMatches = new DiscoverUserMatches(gameBoard, this);
		this.undoSwap = new UndoSwap(gameBoard, this);
		this.destroyMatches = new DestroyMatches(gameBoard, this);
		this.tilesAreBeingDestroyed = new TilesAreBeingDestroyed(gameBoard, this);
		this.tilesHaveBeenDestroyed = new TilesHaveBeenDestroyed(gameBoard, this);
		this.tilesAreFalling = new TilesAreFalling(gameBoard, this);
		this.discoverMoreMatches = new DiscoverMoreMatches(gameBoard, this);

		this.currentState = neutral;
	}

	public void tick(long delta) {
		transition(currentState.tick(delta));
	}

	public void selectTile(Tile tileSelected) {
		transition(currentState.selectTile(tileSelected));
	}

	private void transition(State newState) {
		if (newState != currentState) {
			LOG.error(currentState.getClass().getSimpleName() + " -> " + newState.getClass().getSimpleName());
			currentState.leave();
			currentState = newState;
			currentState.enter();
		}
	}

	public void renderAdditionalTiles(SpriteBatch batch) {
		currentState.render(batch);
	}

}
