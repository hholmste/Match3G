package com.instantviking.state.states;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.instantviking.AnimationTimes;
import com.instantviking.GameBoard;
import com.instantviking.state.AnimatingState;
import com.instantviking.state.Machine;
import com.instantviking.state.State;
import com.instantviking.tile.Tile;
import com.instantviking.tile.TileRenderer;

public class SwappingTiles extends AnimatingState {

	Tile a;
	Tile b;

	public SwappingTiles(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	@Override
	protected State finishAnimation() {
		a.unselect();
		b.unselect();
		a.finishMove();
		b.finishMove();

		machine.discoverUserMatches.setUserTiles(a, b);

		return machine.discoverUserMatches;
	}

	@Override
	public void enter() {
		super.enter();
		adopt(a, b);
	}

	@Override
	public void leave() {
		super.leave();
		abandon(a, b);

		a = null;
		b = null;
	}

	@Override
	protected void renderTile(SpriteBatch batch, Tile tile, float progress) {
		TileRenderer.renderTileInMotion(batch, tile, progress);
	}

	@Override
	protected List<Tile> getAnimatedTiles() {
		return Arrays.asList(a, b);
	}

	@Override
	protected long getAnimationTime() {
		return AnimationTimes.TIME_FOR_SWAP;
	}

}
