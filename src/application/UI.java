package application;

import chess.ChessPiece;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) {
		
		for(int i=0 ; i<pieces.length ; i++) {
			System.out.print( (pieces.length - i) + " ");
			for(int j=0; j<pieces[i].length ; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.print("  ");
		for(int i=0 ; i<pieces[0].length ; i++) {
			int ascii = (int) 'A';
			ascii = ascii + i;
			char letter = (char) ascii;
			System.out.print(letter + " ");
		}
		System.out.println();
		
	}
	
	private static void printPiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print("-");
		}
		else {
			System.out.print("piece ");
		}
		System.out.print(" ");
	}
	
}
