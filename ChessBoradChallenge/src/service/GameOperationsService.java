package service;

import domain.ChessBoard;
import exceptionHandler.InvalidMoveException;
import exceptionHandler.InvalidPawnException;

public interface GameOperationsService {

	/*This method draws the chess board on to the console*/
	public ChessBoard drawChessBoard(String src, String dest);
	
	/*This method helps 'drawChessBoard()' method to place pawns in appropriate position*/
	public ChessBoard arrangPieces(String initColor);
	
	/*This method refresh the chess board representation each time the move is made*/
	public ChessBoard refreshChessBoard(String src, String dest) ;
	
	/*This method helps 'refreshChessBoard()' method to refresh the board depending on the players move*/
	public ChessBoard makeMove(String src, String dest, int playerNo) throws InvalidMoveException;
	
	/*This method validates the pawn name entered by the player*/
	public void validatePawn(String src, String dest, Character player) throws InvalidPawnException;
	
	/*This method validates the move made by the player*/
	public ChessBoard validateMove(String pawn, String dest, Character player) throws InvalidMoveException;
	
}
