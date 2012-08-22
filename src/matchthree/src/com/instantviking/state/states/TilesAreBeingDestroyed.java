package com.instantviking.state.states;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.instantviking.AnimationTimes;
import com.instantviking.GameBoard;
import com.instantviking.state.AnimatingState;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;
import com.instantviking.tile.TileRenderer;

public class TilesAreBeingDestroyed extends AnimatingState {

	List<Tile> destroyables;

	public TilesAreBeingDestroyed(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	@Override
	public State finishAnimation() {
		for (Tile tile : destroyables) {
			tile.finishDestroy();
		}

		machine.tilesHaveBeenDestroyed.setDestroyedTiles(destroyables);
		return machine.tilesHaveBeenDestroyed;
	}

	@Override
	public void renderTile(SpriteBatch batch, Tile tile, float progress) {
		TileRenderer.renderTileBeingDestroyed(batch, tile, progress);
	}

	@Override
	public void enter() {
		super.enter();
		for (Tile tile : destroyables) {
			adopt(tile);
		}
	}

	@Override
	public void leave() {
		super.leave();
		for (Tile tile : destroyables) {
			abandon(tile);
		}
	}

	public void setDestroyables(Set<Tile> destroyables) {
		this.destroyables = new ArrayList<Tile>(destroyables);
	}

	@Override
	protected long getAnimationTime() {
		return AnimationTimes.TIME_FOR_DESTRUCTION;
	}

	@Override
	protected List<Tile> getAnimatedTiles() {
		return destroyables;
	}

}
