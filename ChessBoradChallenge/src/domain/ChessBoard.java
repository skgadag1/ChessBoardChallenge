package domain;

import java.util.HashMap;

public class ChessBoard {

	private HashMap<String, String> whiteChessMen=new HashMap<String, String>();
	private HashMap<String, String> blackChessMen=new HashMap<String, String>();
	
	public ChessBoard() {
		/*this.whiteChessMen=null;
		this.blackChessMen=null;*/
	}
	
	public HashMap<String, String> getWhiteChessMen() {
		return whiteChessMen;
	}

	public void setWhiteChessMen(HashMap<String, String> whiteChessMen) {
		this.whiteChessMen = whiteChessMen;
	}

	public HashMap<String, String> getBlackChessMen() {
		return blackChessMen;
	}


	public void setBlackChessMen(HashMap<String, String> blackChessMen) {
		this.blackChessMen = blackChessMen;
	}
	
}
