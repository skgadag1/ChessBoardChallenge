package service;

public interface PawnMoveService {

	/*This method moves the pawn in diagonal direction*/
	public boolean moveDiagonally(String pawn, String src, String dest, int player, boolean isKing);
	
	/*This method moves the pawn in either Horizontal or Vertical direction*/
	public boolean moveInHVDirection(String pawn, String src, String dest, int player, boolean isKing);
	
	/*This method moves the pawn just one square in forward direction*/
	public boolean moveOneSquareForward(String pawn, String src, String dest, int player, boolean isFirstMove);
	
	/*This method moves the pawn in L-shape direction*/
	public boolean moveInLShape(String pawn, String src, String dest, int player);
}
