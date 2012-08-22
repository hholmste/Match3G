package com.instantviking.tile.animation;

import com.instantviking.tile.Tile;

public abstract class AbstractAnimation implements Animation {

	protected final Tile tile;
	protected final long totalDuration;
	protected long currentDuration;

	public AbstractAnimation(Tile tile, long totalDuration) {
		this.tile = tile;
		this.totalDuration = totalDuration;
		this.currentDuration = 0L;
	}

	@Override
	public void tick(long delta) {
		currentDuration += delta;
		if (currentDuration >= totalDuration) {
			tile.notifyAnimationFinished();
		}
	}

	@Override
	public float getCompletionFactor() {
		return (float) currentDuration / (float) totalDuration;
	}
}
