package com.instantviking.grid;

import org.junit.Assert;
import org.junit.Test;

import com.instantviking.tile.Tile;
import com.instantviking.tile.TileType;

public class GridTest {

	@Test
	public void shouldGetRow() {
		Grid g = TestGrids.rowsOfSimilarColor();
		System.out.println("rows of similar color: \n" + g.toString());
		assertOneColorLine(g.getRow(0), TileType.BLUE);
	}

	@Test
	public void shouldGetColumn() {
		Grid g = TestGrids.columnsOfSimilarColor();

		System.out.println("columns of similar color: \n" + g.toString());
		assertOneColorLine(g.getColumn(3), TileType.ORANGE);
	}

	private void assertOneColorLine(Line line, TileType type) {
		for (Tile t : line.asArray()) {
			Assert.assertEquals(String.format("\nline failed: %s\n", line.toString()), type, t.type);
		}
	}

}
