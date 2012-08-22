package com.instantviking.io;

import com.badlogic.gdx.InputProcessor;
import com.instantviking.GameBoard;

public class InputHandler implements InputProcessor {

	private final GameBoard board;

	int touchDownSquareRow;
	int touchDownSquareColumn;

	public InputHandler(GameBoard board) {
		this.board = board;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		this.touchDownSquareColumn = xToColumnSquare(x);
		this.touchDownSquareRow = yToRowSquare(y);

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int upSquareColumn = xToColumnSquare(x);
		int upSquareRow = yToRowSquare(y);

		if (upSquareColumn >= 0 && upSquareRow >= 0 && upSquareColumn == touchDownSquareColumn && upSquareRow == touchDownSquareRow) {
			board.clickSquare(touchDownSquareRow, touchDownSquareColumn);
		}

		this.touchDownSquareColumn = -1;
		this.touchDownSquareRow = -1;

		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int yToRowSquare(int pixelY) {
		if (pixelY > 592 || pixelY < 208) {
			return -1;
		}

		return (pixelY - 208) / 48;
	}

	private int xToColumnSquare(int pixelX) {
		if (pixelX > 432 || pixelX < 48) {
			return -1;
		}

		return (pixelX - 48) / 48;
	}

}
