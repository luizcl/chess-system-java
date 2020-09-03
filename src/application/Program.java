package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exceptions.ChessException;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<ChessPiece>();
		
		while(true) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc, chessMatch.getBoard());
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc, chessMatch.getBoard());
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
			}
			catch (ChessException e){
				System.out.println(UI.ANSI_RED);
				System.out.println(e.getMessage());
				System.out.println(UI.ANSI_RESET);
				sc.hasNextLine();
				clearBuffer(sc);
			}
			catch (InputMismatchException e){
				System.out.println(UI.ANSI_RED);
				System.out.println(e.getMessage());
				System.out.println(UI.ANSI_RESET);
				sc.hasNextLine();
				clearBuffer(sc);
			}
		}
		
	}
	
	public static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
	}

}
