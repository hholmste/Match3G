package com.instantviking.grid;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.instantviking.tile.Tile;
import com.instantviking.tile.TileRenderer;
import com.instantviking.tile.TileSource;

public class Grid {

	private static final Logger LOGGER = new Logger("grid");
	private final Line[] rows;
	private final int rowLength;
	private final int columnLength;

	public Grid(int size) {
		rows = new Line[size];

		for (int i = 0; i < size; i++)
			rows[i] = new Line(size);

		rowLength = size;
		columnLength = size;
	}

	public static Grid square(int size) {
		return new Grid(size);
	}

	public Line getRow(int row) {
		return rows[row];
	}

	public Line getColumn(int column) {
		Tile[] columnSource = new Tile[columnLength];

		for (int row = 0; row < columnLength; row++) {
			columnSource[row] = rows[row].get(column);
		}

		return new Line(columnSource);
	}

	public void putAt(TileSource generator, int row, int column) {
		rows[row].put(generator.createTile(this, row, column), column);
	}

	public void putAt(Tile tile, int row, int column) {
		rows[row].put(tile, column);
	}

	public void generateNewBoard(TileSource generator) {
		for (int row = 0; row < rowLength; row++) {
			for (int column = 0; column < columnLength; column++) {
				putAt(generator, row, column);
			}
		}
	}

	public boolean containsCoordinate(int row, int column) {
		return row >= 0 && row < rowLength && column >= 0 && column < columnLength;
	}

	public Tile tileAt(int row, int column) {
		return rows[row].get(column);
	}

	public void render(SpriteBatch batch) {
		for (int row = 0; row < rowLength; row++) {
			for (int column = 0; column < columnLength; column++) {
				TileRenderer.renderTile(batch, rows[row].get(column));
			}
		}
	}

	public void dump() {
		LOGGER.error(this.toString());
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder("\n");

		for (int row = 0; row < rowLength; row++) {
			b.append(rows[row].toString()).append("\n");
		}

		return b.toString();
	}

	public void swap(Tile a, Tile b) {
		putAt(a, a.row, a.column);
		putAt(b, b.row, b.column);
	}

	public void removeFrom(int row, int column) {
		if (row >= 0)
			rows[row].remove(column);
	}

}
