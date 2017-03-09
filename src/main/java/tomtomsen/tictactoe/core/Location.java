package tomtomsen.tictactoe.core;

public class Location {
	private int column;
	private int row;

	public Location(final int column, final int row) {
		this.row = row;
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
}
