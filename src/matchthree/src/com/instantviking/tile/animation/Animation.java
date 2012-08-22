package com.instantviking.tile.animation;

public interface Animation {

	void tick(long delta);

	float getCompletionFactor();

}
