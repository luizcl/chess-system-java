package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import boardgame.Board;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}  
	
	public static ChessPosition readChessPosition(Scanner sc, Board board) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			column = Character.toUpperCase(column);
			int row = (int) (s.charAt(1) - '0');
			return new ChessPosition(column, row, board);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Error reading Chess Position. Valid values are from A1 to " + ChessPosition.lastColumn(board) + ChessPosition.lastRow(board));
		}
	}
	
	public static void printBoard(ChessPiece[][] pieces) {

		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_BLUE + (pieces.length - i) + " " + ANSI_RESET);
			for (int j = 0; j < pieces[i].length; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		
		//Print Columns name
		System.out.print("   ");
		for (int i = 0; i < pieces[0].length; i++) {
			int ascii = 'A';
			ascii = ascii + i;
			char letter = (char) ascii;
			System.out.print(ANSI_BLUE + letter + " " + ANSI_RESET);
		}
		System.out.println();

	}

	private static void printPiece(ChessPiece piece) {

		System.out.print(" ");
		if (piece == null) {
			System.out.print("-");
		} 
		else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} 
			else {
				System.out.print(ANSI_PURPLE + piece + ANSI_RESET);
			}
		}
	}
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		if(!chessMatch.getCheckMate()) {
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
			if(chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
		}
		else {
			System.out.println("CHECKMATE!");
			System.out.println("Winner: " + chessMatch.getCurrentPlayer());
		}
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {

		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_BLUE + (pieces.length - i) + " " + ANSI_RESET);
			for (int j = 0; j < pieces[i].length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		
		//Print Columns name
		System.out.print("   ");
		for (int i = 0; i < pieces[0].length; i++) {
			int ascii = 'A';
			ascii = ascii + i;
			char letter = (char) ascii;
			System.out.print(ANSI_BLUE + letter + " " + ANSI_RESET);
		}
		System.out.println();

	}
	
	private static void printPiece(ChessPiece piece, boolean background) {
		
		System.out.print(" ");
		
		if(background) {
			System.out.print(ANSI_YELLOW_BACKGROUND);
		}
		
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} 
		else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} 
			else {
				System.out.print(ANSI_PURPLE + piece + ANSI_RESET);
			}
		}
	}
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Captured pieces:");
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.println("Captured pieces:");
		System.out.print("Black: ");
		System.out.print(ANSI_PURPLE);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
		
	}
	
	public static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
	}
	
}
