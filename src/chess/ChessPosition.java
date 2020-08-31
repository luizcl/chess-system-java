package chess;

import boardgame.Board;
import boardgame.Position;
import chess.exceptions.ChessException;

public class ChessPosition {
	
	private char column;
	private int row;
	private int firstColumn;
	private int firstRow;
	private int lastColumn;
	private int lastRow;
	
	
	public ChessPosition(char column, int row, Board board) {
		if(board == null) {
			throw new ChessException("The board should already exist before the Chess piece");
		}
		this.firstRow = firstRow();
		this.firstColumn = firstColumn();
		this.lastRow = lastRow(board);
		this.lastColumn = lastColumn(board);
		if(column < this.firstColumn || column > this.lastColumn || row < this.firstRow || row > this.lastColumn)
			throw new ChessException("Error instantianting ChessPosition. Valid values are from A1 to " + String.valueOf(this.lastColumn) + this.lastRow);
		this.column = column;
		this.row = row;
	}
	
	private char firstColumn() {
		return 'A';
	}
	
	public static char lastColumn(Board board) {
		
		int first = (char) 'A';
		int last = first + board.getColumns() - 1;	
		return ( (char) last);
	}
	
	private int firstRow() {
		return 1;
	}
	
	public static int lastRow(Board board) {
		return board.getRows();
	}

	public char getColumn() {
		return this.column;
	}

	public int getRow() {
		return this.row;
	}
	
	public int getFirstColumn() {
		return firstColumn;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getLastColumn() {
		return lastColumn;
	}

	public int getLastRow() {
		return lastRow;
	}

	protected Position toPosition() {
		return new Position( this.lastRow - this.row , this.column-'A');
	}
	
	public static ChessPosition fromPosition(Position position, Board board) {
		 return new ChessPosition((char)('A' + position.getColumn()), 8 - position.getRow(), board);
	}
	
	@Override
	public String toString() {
		return "" + this.column + this.row;
	}
}
