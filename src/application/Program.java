package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPosition;
import chess.exceptions.ChessException;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		while(true) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc, chessMatch.getBoard());
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc, chessMatch.getBoard());
				
				//ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				chessMatch.performChessMove(source, target);
			}
			catch (ChessException e){
				System.out.println(e.getMessage());
				sc.hasNextLine();
				clearBuffer(sc);
			}
			catch (InputMismatchException e){
				System.out.println(e.getMessage());
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
