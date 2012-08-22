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

public class UndoSwap extends AnimatingState {

	private Tile a;
	private Tile b;

	public UndoSwap(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	public void swap(Tile a, Tile b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void enter() {
		super.enter();
		adopt(a, b);
		a.beginMove(b.row, b.column);
		b.beginMove(a.row, a.column);
	}

	@Override
	public void leave() {
		super.leave();
		abandon(a, b);
	}

	@Override
	protected long getAnimationTime() {
		return AnimationTimes.TIME_FOR_UNSWAP;
	}

	@Override
	protected List<Tile> getAnimatedTiles() {
		return Arrays.asList(a, b);
	}

	@Override
	protected void renderTile(SpriteBatch batch, Tile tile, float progress) {
		TileRenderer.renderTileInMotion(batch, tile, progress);
	}

	@Override
	protected State finishAnimation() {
		a.finishMove();
		b.finishMove();
		return machine.neutral;
	}

}
