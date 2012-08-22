package com.instantviking.grid;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.instantviking.tile.Tile;
import com.instantviking.tile.TileType;

public class LineTest {

	Line line;

	@Before
	public void setUp() {
		line =
				new Line(new Tile[] {
						new Tile(TileType.BLUE, 0, 0),
						new Tile(TileType.BLUE, 1, 0),
						new Tile(TileType.GREEN, 2, 0),
						new Tile(TileType.ORANGE, 3, 0),
						new Tile(TileType.ORANGE, 4, 0),
						new Tile(TileType.ORANGE, 5, 0),
						new Tile(TileType.ORANGE, 6, 0),
						new Tile(TileType.ORANGE, 7, 0) });
	}

	@Test
	public void matches_shouldFindMatchesFromLeftMostExtreme() {
		List<Tile> matches = line.matches(0);
		Assert.assertEquals(2, matches.size());
		for (Tile t : matches) {
			Assert.assertEquals(TileType.BLUE, t.type);
		}
	}

	@Test
	public void matches_shouldFindFirstMatchesFromRightOfTheSet() {
		List<Tile> matches = line.matches(1);
		Assert.assertEquals(2, matches.size());
		for (Tile t : matches) {
			Assert.assertEquals(TileType.BLUE, t.type);
		}
	}

	@Test
	public void matches_shouldFindMatchesFromInside() {
		List<Tile> matches = line.matches(2);
		Assert.assertEquals(1, matches.size());
		for (Tile t : matches) {
			Assert.assertEquals(TileType.GREEN, t.type);
		}
	}

	@Test
	public void matches_shouldFindMatchesFromRightMostExtremesLeftExtreme() {
		List<Tile> matches = line.matches(3);
		Assert.assertEquals(5, matches.size());
		for (Tile t : matches) {
			Assert.assertEquals(TileType.ORANGE, t.type);
		}
	}

	@Test
	public void matches_shouldFindMatchesFromRightMostExtremesMiddle() {
		List<Tile> matches = line.matches(5);
		Assert.assertEquals(5, matches.size());
		for (Tile t : matches) {
			Assert.assertEquals(TileType.ORANGE, t.type);
		}
	}

	@Test
	public void matches_shouldFindMatchesFromRightMostExtreme() {
		List<Tile> matches = line.matches(7);
		Assert.assertEquals(5, matches.size());
		for (Tile t : matches) {
			Assert.assertEquals(TileType.ORANGE, t.type);
		}
	}

	@Test
	public void matches_avoidIndexZeroBug() {
		line =
				new Line(new Tile[] {
						new Tile(TileType.GREEN, 0, 0),
						new Tile(TileType.ORANGE, 1, 0),
						new Tile(TileType.ORANGE, 2, 0),
						new Tile(TileType.ORANGE, 3, 0),
						new Tile(TileType.ORANGE, 4, 0),
						new Tile(TileType.GREEN, 5, 0),
						new Tile(TileType.GREEN, 6, 0),
						new Tile(TileType.RED, 7, 0) });

		List<Tile> matches = line.matches(2);

		Assert.assertEquals(4, matches.size());
		for (Tile t : matches) {
			Assert.assertEquals(TileType.ORANGE, t.type);
		}
	}

	@Test
	public void matches_avoidIndexNMinusOneBug() {
		line =
				new Line(new Tile[] {
						new Tile(TileType.GREEN, 0, 0),
						new Tile(TileType.YELLOW, 1, 0),
						new Tile(TileType.ORANGE, 2, 0),
						new Tile(TileType.RED, 3, 0),
						new Tile(TileType.RED, 4, 0),
						new Tile(TileType.ORANGE, 5, 0),
						new Tile(TileType.ORANGE, 6, 0),
						new Tile(TileType.BLUE, 7, 0) });

		List<Tile> matches = line.matches(5);

		Assert.assertEquals(2, matches.size());
		for (Tile t : matches) {
			Assert.assertEquals(TileType.ORANGE, t.type);
		}
	}

}
