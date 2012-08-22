package com.instantviking.tile;

public enum TileType {
	RED("red"), ORANGE("ora"), YELLOW("yel"), GREEN("gre"), BLUE("blu"), PURPLE("pur");

	private final String shortName;

	private TileType(String shortName) {
		this.shortName = shortName;
	}

	public final String getShortName() {
		return shortName;
	}
}
