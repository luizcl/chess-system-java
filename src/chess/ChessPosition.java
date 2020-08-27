package chess;

import boardgame.Board;
import boardgame.Position;
import chess.exceptions.ChessException;

public class ChessPosition {
	
	private char column;
	private int row;
	private Board board;
	
	public ChessPosition(char column, int row, Board board) {
		if(board == null) {
			throw new ChessException("The board should already exist before the Chess piece");
		}
		this.board = board;
		if(column < firstColumn() || column > lastColumn() || row < firstRow() || row > lastColumn())
			throw new ChessException("Error instantianting ChessPosition. Valid values are from A1 to " + String.valueOf((lastColumn())) + lastRow());
		this.column = column;
		this.row = row;
	}
	
	private char firstColumn() {
		return 'A';
	}
	
	private char lastColumn() {
		
		int first = (char) 'A';
		int last = first + this.board.getColumns();	
		return ( (char) last);
	}
	
	private int firstRow() {
		return 1;
	}
	
	private int lastRow() {
		return 1+this.board.getRows();
	}

	public char getColumn() {
		return this.column;
	}

	public int getRow() {
		return this.row;
	}
	
	protected Position toPosition() {
		return new Position( 8-this.row , this.column-'A');
	}
	
	protected static ChessPosition fromPosition(Position position, Board board) {
		 return new ChessPosition((char)('A' - position.getColumn()), 8 - position.getRow(), board);
	}
	
	@Override
	public String toString() {
		return "" + this.column + this.row;
	}
}
