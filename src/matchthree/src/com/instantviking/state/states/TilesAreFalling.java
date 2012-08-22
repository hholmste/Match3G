package com.instantviking.state.states;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.instantviking.AnimationTimes;
import com.instantviking.GameBoard;
import com.instantviking.state.AnimatingState;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;
import com.instantviking.tile.TileRenderer;

public class TilesAreFalling extends AnimatingState {

	private List<Tile> fallingTiles;

	public TilesAreFalling(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	@Override
	protected void renderTile(SpriteBatch batch, Tile tile, float progress) {
		TileRenderer.renderTileInMotion(batch, tile, getProgress());
	}

	@Override
	protected State finishAnimation() {
		for (Tile tile : fallingTiles) {
			tile.finishMove();
			machine.discoverMoreMatches.addTile(tile);
		}
		return machine.discoverMoreMatches;
	}

	@Override
	protected long getAnimationTime() {
		return AnimationTimes.TIME_FOR_FALLING;
	}

	public void addFallingTile(Tile tile) {
		if (fallingTiles == null) {
			fallingTiles = new ArrayList<Tile>();
		}

		if (tile != null) {
			fallingTiles.add(tile);
		}

	}

	@Override
	public void enter() {
		super.enter();
		for (Tile tile : fallingTiles) {
			adopt(tile);
		}
	}

	@Override
	public void leave() {
		super.leave();
		for (Tile tile : fallingTiles) {
			abandon(tile);
		}

		fallingTiles = null;
	}

	@Override
	protected List<Tile> getAnimatedTiles() {
		return fallingTiles;
	}
}
