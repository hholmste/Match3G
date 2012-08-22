package com.instantviking.state;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.instantviking.GameBoard;
import com.instantviking.tile.Tile;

public abstract class AnimatingState extends State {

	private float current = 0.0f;
	private float progress = 0.0f;

	public AnimatingState(GameBoard gameBoard, Machine machine) {
		super(gameBoard, machine);
	}

	@Override
	public void enter() {
		super.enter();
	}

	@Override
	public void leave() {
		super.leave();
		current = 0.0f;
		progress = 0.0f;
	}

	@Override
	public State tick(long delta) {
		this.current += delta;
		this.progress = current / getAnimationTime();

		if (isDone()) {
			return finishAnimation();
		}

		return super.tick(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		for (Tile tile : getAnimatedTiles()) {
			renderTile(batch, tile, getProgress());
		}
	}

	protected boolean isDone() {
		return this.current >= this.getAnimationTime();
	}

	protected float getProgress() {
		return progress;
	}

	/**
	 * Retrieves the number of milliseconds this state's animation should run
	 * for
	 */
	protected abstract long getAnimationTime();

	/** Retrieves the list of tiles that this state is responsible for animating */
	protected abstract List<Tile> getAnimatedTiles();

	/** Renders a single tile */
	protected abstract void renderTile(SpriteBatch batch, Tile tile, float progress);

	/** Called when the animation has reached its end */
	protected abstract State finishAnimation();

}
