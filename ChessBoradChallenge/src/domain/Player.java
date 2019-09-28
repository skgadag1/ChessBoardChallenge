package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {

	private String name;
	private String pieceChosen;
	private HashMap<String, String> history=new HashMap<String, String>();
	private List<String> pawnsWon=new ArrayList<String>();
	
	public Player() {
		this.name=null;
		this.pieceChosen=null;
		this.history=null;
		this.pawnsWon=null;
	}

	public Player(String name, String pieceChosen, HashMap<String, String> history,List<String> pawnsWon) {
		super();
		this.name = name;
		this.pieceChosen = pieceChosen;
		this.history = history;
		this.pawnsWon=pawnsWon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPieceChosen() {
		return pieceChosen;
	}

	public void setPieceChosen(String pieceChosen) {
		this.pieceChosen = pieceChosen;
	}

	public HashMap<String, String> getHistory() {
		return history;
	}

	public void setHistory(HashMap<String, String> history) {
		this.history = history;
	}

	public List<String> getPawnsWon() {
		return pawnsWon;
	}

	public void setPawnsWon(List<String> pawnsWon) {
		this.pawnsWon = pawnsWon;
	}
	
	
	
	
}
