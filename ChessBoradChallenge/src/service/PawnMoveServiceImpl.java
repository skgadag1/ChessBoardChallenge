package service;

import java.util.ArrayList;
import java.util.List;
import constants.Constants;
import domain.ChessBoard;
import domain.Player;

public class PawnMoveServiceImpl implements PawnMoveService{

	private ChessBoard chessBoard;
	private List<Player> players;
	public PawnMoveServiceImpl(ChessBoard chessBoard, List<Player> players) {
		this.chessBoard=chessBoard;
		this.players=players;
	}

	/*For each method description please refer to 'PawnMoveService.java' file*/
	
	@Override
	public boolean moveDiagonally(String pawn, String src, String dest, int player, boolean isKing) {
		Integer row1=0, col1=0, row2=0, col2=0;
		Character vp=src.charAt(0), vm=dest.charAt(0), hp=src.charAt(1), hm=dest.charAt(1);
		col1=Integer.parseInt(hp.toString());
		col2=Integer.parseInt(hm.toString());	
		row1=Integer.parseInt(vp.toString());
		row2=Integer.parseInt(vm.toString());
		List<String> path=new ArrayList<String>();
		if(isKing) {
			if(row1==row2+1&&col1==col2+1) {

			}else if(row1==row2+1&&col1==col2-1) {
				
			}else if(row1==row2-1&&col1==col2+1) {
				
			}else if(row1==row2-1&&col1==col2-1) {
				
			}else {
				return false;
			}
		}
		if(row1<row2&&col1<col2) {	/*condition to check Bishop/Queen is diagonally moving to forward-right*/ 
			for(int i=row1+1; i<=row2; i++) {
				col1+=1;
				path.add(""+i+col1);
			}
		}else if(row1<row2&&col1>col2) {  /*condition to check Bishop/Queen is diagonally moving to forward-left*/
			for(int i=row1+1; i<=row2; i++) {
				col1-=1;
				path.add(""+i+col1);
			}
		}else if(row1>row2&&col1>col2) {  /*condition to check Bishop/Queen is diagonally moving to backward-left*/
			for(int i=row1-1; i>=row2; i--) {
				col1-=1;
				path.add(""+i+col1);
			}
		}else if(row1>row2&&col1<col2) {  /*condition to check Bishop/Queen is diagonally moving to backward-right*/
			for(int i=row1-1; i>=row2; i--) {
				col1+=1;
				path.add(""+i+col1);
			}
		}

		if(path.stream().anyMatch(x->x.equals(dest))){
			return this.isObstaclePresent(path, player, dest);	
		}else {
			return false;
		}
		
	}

	@Override
	public boolean moveOneSquareForward(String pawn, String src, String dest, int player, boolean isFirstMove) {
		Integer row1=0, col1=0, row2=0, col2=0;
		Character vp=src.charAt(0), vm=dest.charAt(0), hp=src.charAt(1), hm=dest.charAt(1);
		col1=Integer.parseInt(hp.toString());
		col2=Integer.parseInt(hm.toString());	
		row1=Integer.parseInt(vp.toString());
		row2=Integer.parseInt(vm.toString());
		if(player==0) {
			if((row1!=8&&(row1+1)==row2)||(isFirstMove&&(row1+2)==row2)) {
				return this.verifyColumns(col1, col2, dest, player);
			}else {
				return false;
			}
		}else if(player==1) {
			if((row1!=1&&(row1-1)==row2)||(isFirstMove&&(row1-2)==row2)) {
				return this.verifyColumns(col1, col2, dest, player);
			}else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean moveInLShape(String pawn, String src, String dest, int player) {
		Integer row1=0, col1=0, row2=0, col2=0;
		Character vp=src.charAt(0), vm=dest.charAt(0), hp=src.charAt(1), hm=dest.charAt(1);
		row1=Integer.parseInt(vp.toString());
		row2=Integer.parseInt(vm.toString());
		col1=Integer.parseInt(hp.toString());
		col2=Integer.parseInt(hm.toString());
		if((row2==(row1+1)&&(col2==(col1+2)||col2==(col1-2))) || 
				(row2==(row1-1)&&(col2==(col1+2)||col2==(col1-2)))) {
			return this.verifyLShapeMove(player, dest);
		}else if((row2==(row1+2)&&(col2==(col1+1)||col2==(col1-1))) || 
				(row2==(row1-2)&&(col2==(col1+1)||col2==(col1-1)))) {
			return this.verifyLShapeMove(player, dest);
		}else {
			return false;
		}
	}

	@Override
	public boolean moveInHVDirection(String pawn, String src, String dest, int player, boolean isKing) {
		List<String> path=new ArrayList<String>();
		String cell="";
		Integer row1=0, col1=0, row2=0, col2=0;

		Character vp=src.charAt(0), vm=dest.charAt(0), hp=src.charAt(1), hm=dest.charAt(1);
		if(vp==vm) {
			col1=Integer.parseInt(hp.toString());
			col2=Integer.parseInt(hm.toString());	
			row1=row2=Integer.parseInt(vp.toString());
		}else if(hp==hm) {
			row1=Integer.parseInt(vp.toString());
			row2=Integer.parseInt(vm.toString());	
			col1=col2=Integer.parseInt(hp.toString());
		}
		if(isKing) {
			if(row1==row2&&col1==col2+1) {

			}else if(row1==row2&&col1==col2-1) {
				
			}else if(row1==row2+1&&col1==col2) {
				
			}else if(row1==row2-1&&col1==col2) {
				
			}else {
				return false;
			}
		}
		if(Integer.parseInt(dest)>Integer.parseInt(src)) {
			for(Integer i=row1; i<=row2; i++) {
				for(Integer j=col1; j<=col2; j++) {
					if(vp==vm) {
						cell=vp+j.toString();
						if(!cell.equals(src))
							path.add(cell);
					}else if(hp==hm) {
						cell=i.toString()+hp;
						if(!cell.equals(src))
							path.add(cell);
					}else {
						return false;
					}
				}

			}
		}else if(Integer.parseInt(dest)<Integer.parseInt(src)) {
			for(Integer i=row1; i>=row2; i--) {
				for(Integer j=col1; j>=col2; j--) {
					if(vp==vm) {
						cell=vp+j.toString();
						if(!cell.equals(src))
							path.add(cell);
					}else if(hp==hm) {
						cell=i.toString()+hp;
						if(!cell.equals(src))
							path.add(cell);
					}else {
						return false;
					}
				}

			}
		}
		
		return this.isObstaclePresent(path, player, dest);

	}
	
	/*----------------------------Private Methods-----------------------------------*/
	private boolean isObstaclePresent(List<String> path, int player, String dest) {
		for(int i=0; i<path.size(); i++) {
			if(this.players.get(player).getPieceChosen().equals(Constants.BLACK)) {
				if(this.chessBoard.getBlackChessMen().containsKey(path.get(i))) {
					return false;
				}else if(this.chessBoard.getWhiteChessMen().containsKey(path.get(i))) {
					if(path.get(i).equals(dest)) {
						return true;
					}else {
						return false;
					}
				}else {
					if(i==path.size()-1) {
						return true;							
					}else {
						continue;
					}
				}
			}else if(this.players.get(player).getPieceChosen().equals(Constants.WHITE)) {
				if(this.chessBoard.getWhiteChessMen().containsKey(path.get(i))) {
					return false;
				}else if(this.chessBoard.getBlackChessMen().containsKey(path.get(i))){
					if(path.get(i).equals(dest)) {
						return true;
					}else {
						return false;
					}
				}else {
					if(i==path.size()-1) {
						return true;							
					}else {
						continue;
					}
				}
			}
		}
		return false;
	}
	
	private boolean verifyColumns(Integer col1, Integer col2, String dest, int player) {
		if(col1!=8&&(col1+1)==col2) {
			if(this.players.get(player).getPieceChosen().equals(Constants.BLACK)) {
				if(this.chessBoard.getWhiteChessMen().containsKey(dest)) {
					return true;
				}else {
					return false;
				}
			}else if(this.players.get(player).getPieceChosen().equals(Constants.WHITE)) {
				if(this.chessBoard.getBlackChessMen().containsKey(dest)) {
					return true;
				}else{
					return false;
				}
			}
		}else if(col1!=1&&(col1-1)==col2) {
			if(this.players.get(player).getPieceChosen().equals(Constants.BLACK)) {
				if(this.chessBoard.getWhiteChessMen().containsKey(dest)) {
					return true;
				}else {
					return false;
				}
			}else if(this.players.get(player).getPieceChosen().equals(Constants.WHITE)) {
				if(this.chessBoard.getBlackChessMen().containsKey(dest)) {
					return true;
				}else{
					return false;
				}
			}
		}else if(col1==col2) {
			if(this.chessBoard.getWhiteChessMen().containsKey(dest)) {
				return false;
			}else if(this.chessBoard.getBlackChessMen().containsKey(dest)) {
				return false;
			}else {
				return true;
			}
		}else {
			return false;
		}
		return false;
	}
	
	private boolean verifyLShapeMove(int player, String dest) {
		if(this.players.get(player).getPieceChosen().equals(Constants.BLACK)) {
			if(this.chessBoard.getWhiteChessMen().containsKey(dest)) {
				return true;
			}else if(this.chessBoard.getBlackChessMen().containsKey(dest)) {
				return false;
			}else
				return true;
		}else if(this.players.get(player).getPieceChosen().equals(Constants.WHITE)) {
			if(this.chessBoard.getBlackChessMen().containsKey(dest)) {
				return true;
			}else if(this.chessBoard.getWhiteChessMen().containsKey(dest)) {
				return false;
			}else
				return true;
		}else {
			return false;	
		}
		
	}




}
