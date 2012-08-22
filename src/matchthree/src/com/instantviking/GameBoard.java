package com.instantviking;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.instantviking.assets.Sprites;
import com.instantviking.grid.Grid;
import com.instantviking.grid.Line;
import com.instantviking.state.Machine;
import com.instantviking.tile.Tile;
import com.instantviking.tile.TileSource;

/**
 * Holds state of gameboard
 */
public class GameBoard {

	private static final Logger LOGGER = new Logger("board");
	private Grid grid = new Grid(8);
	private TileSource tileSource = new TileSource();

	public Tile firstSelectedTile;
	public Tile secondSelectedTile;

	public Set<MatchSet> matchSets;
	public Set<Tile> movingTiles;

	private final Machine machine;

	public GameBoard(SpriteBatch batch) {
		grid.generateNewBoard(tileSource);
		LOGGER.error("initial board:");
		dumpGrid();
		machine = new Machine(this);
	}

	public void tick(SpriteBatch batch, long delta) {
		if (delta > 0) {
			update(delta);
			renderBorder(batch);
			grid.render(batch);
			machine.renderAdditionalTiles(batch);
		}
	}

	private void update(long delta) {
		machine.tick(delta);
	}

	private void renderBorder(SpriteBatch batch) {

		for (int i = 0; i < 8; i++) {
			float offset = i / 10.0f;
			Sprites.leftWall.setPosition(-0.5f, 0.3f - offset);
			Sprites.leftWall.draw(batch);

			Sprites.rightWall.setPosition(0.4f, 0.3f - offset);
			Sprites.rightWall.draw(batch);

			Sprites.topWall.setPosition(0.3f - offset, 0.4f);
			Sprites.topWall.draw(batch);

			Sprites.bottomWall.setPosition(0.3f - offset, -0.5f);
			Sprites.bottomWall.draw(batch);
		}

		// wtf?
		/*
		 * Sprites.background.setScale(1024.0f / 480.0f, 1024.0f / 800.0f);
		 * Sprites.background.setPosition(-0.0f, 0.0f);
		 * Sprites.background.draw(batch);
		 */
	}

	public void dumpGrid() {
		grid.dump();
	}

	public void clickSquare(int row, int column) {
		if (grid.containsCoordinate(row, column)) {
			machine.selectTile(grid.tileAt(row, column));
		}
	}

	public void swap(Tile a, Tile b) {
		grid.swap(a, b);
	}

	public Set<Match> collectMatches(Tile tile) {
		Set<Match> matches = new HashSet<Match>();

		matches.add(new Match(grid.getRow(tile.row).matches(tile.column)));
		matches.add(new Match(grid.getColumn(tile.column).matches(tile.row)));

		return matches;
	}

	public Line getColumn(Integer column) {
		return grid.getColumn(column);
	}

	public void moveTile(Tile tile) {
		grid.putAt(tile, tile.desiredRow, tile.desiredColumn);
	}

	public Tile createNewTile(int row, int column) {
		return tileSource.createTile(grid, row, column);
	}

	public void remove(Tile tile) {
		grid.removeFrom(tile.row, tile.column);
	}

	public void add(Tile tile) {
		if (!tile.isDestroyed()) {
			grid.putAt(tile, tile.row, tile.column);
		}
	}

}
