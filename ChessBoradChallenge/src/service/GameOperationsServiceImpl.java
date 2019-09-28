package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;

import constants.Constants;
import domain.ChessBoard;
import domain.Player;
import exceptionHandler.InvalidMoveException;
import exceptionHandler.InvalidPawnException;

public class GameOperationsServiceImpl implements GameOperationsService{

	private static ChessBoard chessBoard=new ChessBoard();
	private static List<Player> players=new ArrayList<Player>();
	private PawnMoveService pawnMoveService=new PawnMoveServiceImpl(chessBoard, players);
	private String s;
	boolean isWFirstMove=true, isBFirstMove=true;

	/*This method draws the chess board on to the console*/
	@Override
	public ChessBoard drawChessBoard(String src, String dest) {
		String key="";

		for(Integer i=0; i<9; i++) {
			for(Integer j=0; j<9; j++) {
				key=i.toString()+j.toString();
				if(i==0&&j==0) {
					s="   \t\t";
					System.out.print(s);
				}else if(i==0&&j<9&&j!=0) {
					s="  "+j+"\t  ";
					System.out.print(s);
				}else if(i>0&&i<9&&j==0) {
					s="  "+i+"\t\t";	
					System.out.print(s);
				}else {
					if(i%2!=0) {
						if(j%2!=0) {
							s="  O\t  ";
						}
						else {
							s="  *\t  ";	
						}
					}else if(i%2==0) {
						if(j%2==0) {
							s="  O\t  ";
						}
						else {
							s="  *\t  ";	
						}
					}
					if(GameOperationsServiceImpl.chessBoard.getBlackChessMen().containsKey(key)) {
						s="  "+GameOperationsServiceImpl.chessBoard.getBlackChessMen().get(key)+"\t  ";
					}else if(GameOperationsServiceImpl.chessBoard.getWhiteChessMen().containsKey(key)) {
						s="  "+GameOperationsServiceImpl.chessBoard.getWhiteChessMen().get(key)+"\t  ";
					}
					System.out.print(s);
				}

			}
			if(i==0)
				System.out.println("\n\n\t\t  Pawns Won "+GameOperationsServiceImpl.players.get(0).getPawnsWon()+"\n\t\t  "+GameOperationsServiceImpl.players.get(0).getName()+"\t\t\t\t\t\t["+(!GameOperationsServiceImpl.players.get(0).getPieceChosen().equals(Constants.WHITE)?Constants.BLACK:Constants.WHITE)+"]\n\t\t  -------------------------------------------------------------\n");
			if(i==8)
				System.out.println("\n\n\t\t  -------------------------------------------------------------\n\t\t  "+GameOperationsServiceImpl.players.get(1).getName()+"\t\t\t\t\t\t["+(!GameOperationsServiceImpl.players.get(1).getPieceChosen().equals(Constants.WHITE)?Constants.BLACK:Constants.WHITE)+"]\n\t\t  Pawns Won "+GameOperationsServiceImpl.players.get(1).getPawnsWon()+"\n");
			else if(i!=0)
				System.out.println("\n\n");
		}
		if(!src.isEmpty())
			System.out.println("[Last Move: "+src+" -> "+dest+"]");
		if((GameOperationsServiceImpl.players.get(0).getPawnsWon().contains("k")||GameOperationsServiceImpl.players.get(0).getPawnsWon().contains("K"))||(GameOperationsServiceImpl.players.get(1).getPawnsWon().contains("k")||GameOperationsServiceImpl.players.get(1).getPawnsWon().contains("K"))) {
			System.exit(0);
		}
		return GameOperationsServiceImpl.chessBoard;

	}

	/*This method helps 'drawChessBoard()' method to place pawns in appropriate position*/
	@Override
	public ChessBoard arrangPieces(String initColor) {
		GameOperationsServiceImpl.players.add(new Player("Player-1", initColor, null,new ArrayList<String>()));
		GameOperationsServiceImpl.players.add(new Player("Player-2", initColor.equals(Constants.WHITE)?Constants.BLACK:Constants.WHITE, null,new ArrayList<String>()));
		boolean isWhite=(initColor.equals(Constants.WHITE)?true:false);
		String src="";
		HashMap<String, String> whiteChessMen=new HashMap<String, String>();
		HashMap<String, String> blackChessMen=new HashMap<String, String>();
		for(Integer i=1; i<=8; i++) {
			for(Integer j=1; j<=8; j++) {
				src=((i==1||i==8)?i.toString()+j.toString():i.toString());
				switch(src) {
				case "11":	if(isWhite)
					whiteChessMen.put(src,"R1");
				else 
					blackChessMen.put(src,"r1");
				continue;
				case "12":	if(isWhite)
					whiteChessMen.put(src,"N1");
				else 
					blackChessMen.put(src,"n1");
				continue;
				case "13":	if(isWhite)
					whiteChessMen.put(src,"B1");
				else 
					blackChessMen.put(src,"b1");
				continue;
				case "14":	if(isWhite)
					whiteChessMen.put(src,"Q");
				else 
					blackChessMen.put(src,"q");
				continue;
				case "15":	if(isWhite)
					whiteChessMen.put(src,"K");
				else 
					blackChessMen.put(src,"k");
				continue;
				case "16":	if(isWhite)
					whiteChessMen.put(src,"B2");
				else 
					blackChessMen.put(src,"b2");
				continue;
				case "17":	if(isWhite)
					whiteChessMen.put(src,"N2");
				else 
					blackChessMen.put(src,"n2");
				continue;
				case "18":	if(isWhite)
					whiteChessMen.put(src,"R2");
				else 
					blackChessMen.put(src,"r2");
				continue;
				case "2":	if(isWhite)
					whiteChessMen.put(src+j,"x"+j);
				else 
					blackChessMen.put(src+j,"y"+j);
				continue;
				case "7":	if(isWhite)
					blackChessMen.put(src+j,"y"+j);
				else 
					whiteChessMen.put(src+j,"x"+j);
				continue;
				case "81":	if(isWhite)
					blackChessMen.put(src,"r1");
				else 
					whiteChessMen.put(src,"R1");
				continue;
				case "82":	if(isWhite)
					blackChessMen.put(src,"n1");
				else 
					whiteChessMen.put(src,"N1");
				continue;
				case "83":	if(isWhite)
					blackChessMen.put(src,"b1");
				else 
					whiteChessMen.put(src,"B1");
				continue;
				case "84":	if(isWhite)
					blackChessMen.put(src,"q");
				else 
					whiteChessMen.put(src,"Q");
				continue;
				case "85":	if(isWhite)
					blackChessMen.put(src,"k");
				else 
					whiteChessMen.put(src,"K");
				continue;
				case "86":	if(isWhite)
					blackChessMen.put(src,"b2");
				else 
					whiteChessMen.put(src,"B2");
				continue;
				case "87":	if(isWhite)
					blackChessMen.put(src,"n2");
				else 
					whiteChessMen.put(src,"N2");
				continue;
				case "88":	if(isWhite)
					blackChessMen.put(src,"r2");
				else 
					whiteChessMen.put(src,"R2");
				continue;
				default: 	continue;
				}
			}
			if(i==2) {
				//				color1=color2;
				i=6;
			}
		}
		GameOperationsServiceImpl.chessBoard.setBlackChessMen(blackChessMen);
		GameOperationsServiceImpl.chessBoard.setWhiteChessMen(whiteChessMen);
		return this.drawChessBoard("", "");
		

	}

	/*This method refresh the chess board representation each time the move is made*/
	@Override
	public ChessBoard refreshChessBoard(String src, String dest) {
		return this.drawChessBoard(src, dest);

	}

	/*This method helps 'refreshChessBoard()' method to refresh the board depending on the players move*/
	@Override
	public ChessBoard makeMove(String pawn, String dest, int playerNo) throws InvalidMoveException {
		String src="";
		Set<Entry<String, String>> entrySet=null;
		if(GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.BLACK))
			entrySet=GameOperationsServiceImpl.chessBoard.getBlackChessMen().entrySet();
		else
			entrySet=GameOperationsServiceImpl.chessBoard.getWhiteChessMen().entrySet();
		String aquiredPawn=this.acquirePawn(playerNo, dest);
		if(!aquiredPawn.isEmpty())
			System.out.println("Bingo! you just aquired a pawn ["+aquiredPawn+"]\n");
		for(Entry<String, String> entry : entrySet) {
			if(entry.getValue().equals(pawn)) {
				pawn=entry.getValue();
				src=entry.getKey();
				if(src.equals(dest)) {
					throw new InvalidMoveException("Can't dest the piece to the same house!\nPlease dest the pawn to appropriate cell to Win the match!\n");
				}
				if(GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.BLACK)) {
					GameOperationsServiceImpl.chessBoard.getBlackChessMen().remove(src);
					GameOperationsServiceImpl.chessBoard.getBlackChessMen().put(dest, pawn);					
				}
				else if(GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.WHITE)) {
					GameOperationsServiceImpl.chessBoard.getWhiteChessMen().remove(src);
					GameOperationsServiceImpl.chessBoard.getWhiteChessMen().put(dest, pawn);		
				}
				break;	
			}else {
				continue;
			}
		}
		return this.refreshChessBoard(pawn, dest);

	}

	/*This method validates the pawn name entered by the player*/
	@Override
	public void validatePawn(String src, String dest, Character player) throws InvalidPawnException{
		int playerNo=Integer.parseInt(player.toString())-1;
		boolean isValidPawn=false;
		Set<Entry<String, String>> entrySet=null;
		if(GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.BLACK))
			entrySet=GameOperationsServiceImpl.chessBoard.getBlackChessMen().entrySet();
		else
			entrySet=GameOperationsServiceImpl.chessBoard.getWhiteChessMen().entrySet();

		for(Entry<String, String> entry : entrySet) {
			if(entry.getValue().equals(src)) {
				isValidPawn=true;
				break;	
			}else {
				continue;
			}
		}
		if(isValidPawn)
			return;
		else
			throw new InvalidPawnException("The Pawn-"+src+" either doesn't belong to you or doesn't exist!\nPlease pick the valid Pawn!");
	}

	/*This method simulates acquiring the pawn*/
	private String acquirePawn(int playerNo, String dest) {
		String pawn="";
		if(GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.BLACK)&&GameOperationsServiceImpl.chessBoard.getWhiteChessMen().containsKey(dest)) {
			GameOperationsServiceImpl.players.get(playerNo).getPawnsWon().add(GameOperationsServiceImpl.chessBoard.getWhiteChessMen().get(dest));
			pawn=GameOperationsServiceImpl.chessBoard.getWhiteChessMen().get(dest);
			if(pawn.equals("K")) {
				System.out.println("Congratulations "+GameOperationsServiceImpl.players.get(playerNo).getName()+"! You Win!");
//				System.exit(0);
			}
			GameOperationsServiceImpl.chessBoard.getWhiteChessMen().remove(dest);
		}else if(GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.WHITE)&&GameOperationsServiceImpl.chessBoard.getBlackChessMen().containsKey(dest)) {
			GameOperationsServiceImpl.players.get(playerNo).getPawnsWon().add(GameOperationsServiceImpl.chessBoard.getBlackChessMen().get(dest));
			pawn=GameOperationsServiceImpl.chessBoard.getBlackChessMen().get(dest);
			if(pawn.equals("k")) {
				System.out.println("Congratulations "+GameOperationsServiceImpl.players.get(playerNo).getName()+"! You Win!");
//				System.exit(0);
			}
			GameOperationsServiceImpl.chessBoard.getBlackChessMen().remove(dest);
		}
		return pawn;
	}

	/*This method validates the move made by the player*/
	@Override
	public ChessBoard validateMove(String pawn, String dest, Character player) throws InvalidMoveException {
		if(!dest.matches("([1-8][1-8])")) {
			throw new InvalidMoveException("Can't interpret the dest!");
		}
		String src="";
		boolean isFirstMove=false;
		int playerNo=Integer.parseInt(player.toString())-1;
		Set<Entry<String, String>> entrySet=null;

		if(GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.BLACK))
			entrySet=GameOperationsServiceImpl.chessBoard.getBlackChessMen().entrySet();
		else
			entrySet=GameOperationsServiceImpl.chessBoard.getWhiteChessMen().entrySet();
		for(Entry<String, String> entry : entrySet) {
			if(entry.getValue().equals(pawn)) {
				pawn=entry.getValue();
				src=entry.getKey();
				break;	
			}else {
				continue;
			}
		}

		/*Condition to check whether the pawn is a 'Rook'*/
		if(pawn.matches("([rR][1-2])")) {
			if(this.pawnMoveService.moveInHVDirection(pawn,src, dest,playerNo,false)) {
				return this.makeMove(pawn, dest,playerNo);	
			}else {
				throw new InvalidMoveException("Invalid Move to 'cell-"+dest+"'! Move violates restrictions imposed on 'Rook'!\n");
			}
			
		/*Condition to check whether the pawn is a 'Knight'*/
		}else if(pawn.matches("([nN][1-2])")) {
			if(this.pawnMoveService.moveInLShape(pawn,src, dest,playerNo)) {
				return this.makeMove(pawn, dest,playerNo);	
			}else {
				throw new InvalidMoveException("Invalid Move to 'cell-"+dest+"'! Move violates restrictions imposed on 'Knight'!\n");
			}

		/*Condition to check whether the pawn is a 'Queen' or 'King'*/
		}else if(pawn.matches("([qQkK])")) {
			boolean isKing=pawn.matches("([kK])")?true:false;

			Character vp=src.charAt(0), vm=dest.charAt(0), hp=src.charAt(1), hm=dest.charAt(1);
			int row1=Integer.parseInt(vp.toString());
			int row2=Integer.parseInt(vm.toString());
			int col1=Integer.parseInt(hp.toString());
			int col2=Integer.parseInt(hm.toString());
			if(row1==row2||col1==col2) {
				if(this.pawnMoveService.moveInHVDirection(pawn,src, dest,playerNo,isKing)) {
					return this.makeMove(pawn, dest,playerNo);	
				}else {
					throw new InvalidMoveException("Invalid Move to 'cell-"+dest+"'! Move violates restrictions imposed on 'Queen'!\n");
				}	
			}else {
				if(this.pawnMoveService.moveDiagonally(pawn,src, dest, playerNo,isKing)) {
					return this.makeMove(pawn, dest,playerNo);
				}else {
					throw new InvalidMoveException("Invalid Move to 'cell-"+dest+"'! Move violates restrictions imposed on 'Queen'!\n");
				}
			}
		/*Condition to check whether the pawn is a 'Bishop'*/
		}else if(pawn.matches("([bB][1-2])")) {
			if(this.pawnMoveService.moveDiagonally(pawn,src, dest, playerNo,false)) {
				return this.makeMove(pawn, dest,playerNo);	
			}else {
				throw new InvalidMoveException("Invalid Move to 'cell-"+dest+"'! Move violates restrictions imposed on 'Bishop'!\n");
			}
		/*Condition to check whether the pawn is a 'Pawn'*/
		}else if(pawn.matches("([xXyY][1-8])")) {
			if(GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.BLACK)&&this.isBFirstMove) {
				entrySet=GameOperationsServiceImpl.chessBoard.getBlackChessMen().entrySet().stream().filter(x->x.getValue().startsWith("y")).collect(Collectors.toSet());
				Character c1,c2;
				for(Entry<String, String> entry : entrySet) {
					c1=entry.getValue().charAt(1);
					c2=entry.getKey().charAt(1);
					if(c1==c2) {
						continue;
					}else {
						this.isBFirstMove=false;
						break;
					}

				}
			}else if(GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.WHITE)&&this.isWFirstMove) {
				entrySet=GameOperationsServiceImpl.chessBoard.getWhiteChessMen().entrySet().stream().filter(x->x.getValue().startsWith("x")).collect(Collectors.toSet());
				Character c1,c2;
				for(Entry<String, String> entry : entrySet) {
					c1=entry.getValue().charAt(1);
					c2=entry.getKey().charAt(1);
					if(c1==c2) {
						continue;
					}else {
						this.isWFirstMove=false;
						break;
					}
				}
			}
			isFirstMove=(this.isWFirstMove?this.isWFirstMove:(this.isBFirstMove?this.isBFirstMove:false));

			if(this.pawnMoveService.moveOneSquareForward(pawn,src, dest,playerNo,isFirstMove)) {
				this.isBFirstMove=GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.BLACK)?false:this.isBFirstMove;
				this.isWFirstMove=GameOperationsServiceImpl.players.get(playerNo).getPieceChosen().equals(Constants.WHITE)?false:this.isWFirstMove;	
				return this.makeMove(pawn, dest,playerNo);	
			}else {
				throw new InvalidMoveException("Invalid Move to 'cell-"+dest+"'! Move violates restrictions imposed on 'Pawn'!\n");
			}

		}else {
			throw new InvalidMoveException("Can't interpret the dest!");
		}

	}

}
