package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import constants.Constants;
import domain.ChessBoard;
import exceptionHandler.InvalidMoveException;
import exceptionHandler.InvalidPawnException;
import service.GameOperationsService;
import service.GameOperationsServiceImpl;

public class TestCaseChessBoardRepresentation{

	static ChessBoard chessBoard=new ChessBoard();
	static GameOperationsService gameOperationsService=new GameOperationsServiceImpl();
	static HashMap<String,String>whiteMen=new HashMap<String,String>();
	static HashMap<String,String>blackMen=new HashMap<String,String>();
	static HashMap<String,String>chessMen=new HashMap<String,String>();
	static HashMap<String,String>testMen=new HashMap<String,String>();
	
	@BeforeClass
	//TestCasetotestatthebeginningallpiecesarearrangedproperlyontheboard
	public static void createChessBoard(){
		chessBoard=gameOperationsService.arrangPieces(Constants.WHITE);
		whiteMen.put("11","R1");
		whiteMen.put("12","N1");
		whiteMen.put("13","B1");
		whiteMen.put("14","Q");
		whiteMen.put("15","K");
		whiteMen.put("16","B2");
		whiteMen.put("17","N2");
		whiteMen.put("18","R2");
		whiteMen.put("21","x1");
		whiteMen.put("22","x2");
		whiteMen.put("23","x3");
		whiteMen.put("24","x4");
		whiteMen.put("25","x5");
		whiteMen.put("26","x6");
		whiteMen.put("27","x7");
		whiteMen.put("28","x8");

		blackMen.put("81","r1");
		blackMen.put("82","n1");
		blackMen.put("83","b1");
		blackMen.put("84","q");
		blackMen.put("85","k");
		blackMen.put("86","b2");
		blackMen.put("87","n2");
		blackMen.put("88","r2");
		blackMen.put("71","y1");
		blackMen.put("72","y2");
		blackMen.put("73","y3");
		blackMen.put("74","y4");
		blackMen.put("75","y5");
		blackMen.put("76","y6");
		blackMen.put("77","y7");
		blackMen.put("78","y8");
		
		chessMen.putAll(chessBoard.getWhiteChessMen());
		chessMen.putAll(chessBoard.getBlackChessMen());
		
		testMen.putAll(whiteMen);
		testMen.putAll(blackMen);
	}

	@AfterClass
	public static void engGame() {
		System.out.println("All 16 test cases have run!");
		System.exit(0);
	}
	
	private void updateChessMen() {
		chessMen.clear();
		chessMen.putAll(chessBoard.getBlackChessMen());
		chessMen.putAll(chessBoard.getWhiteChessMen());
	}
	
	private void updateTestMen(String oldKey, String newKey, String newValue) {
		testMen.remove(oldKey);
		testMen.put(newKey, newValue);
	}
	
	@Test
	public void case01() throws InterruptedException {
		if(testMen.equals(chessMen))
			System.out.println("-----------------------------------------\n\tCase 1 : Board Representation successful!\n-----------------------------------------");
		else
			System.out.println("-----------------------------------------\n\tCase 1 : Board Representation failed!\n-----------------------------------------");
  	  Thread.sleep(1500);
		assertEquals(testMen,chessMen);
	}
	
	@Test(expected =InvalidPawnException.class)
	public void case02() throws InvalidPawnException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 2 : Invalid Pawn Exception thrown successful!\n\tDescription: An attempt made to move opponents's pawn 'y1'\n-----------------------------------------------------------------");
		gameOperationsService.validatePawn("y1","31",'1');
		Thread.sleep(1500);
	}
	
	@Test//(expected =InvalidPawnException.class)
	public void case03() throws InvalidPawnException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 3 : Testing when 'validatePawn' doesn't throw exception!\n\tDescription: Did not throw exception because, it was a valid pawn\n-----------------------------------------------------------------");
		gameOperationsService.validatePawn("x1","31",'1');
		Thread.sleep(1500);
	}
	
	@Test(expected =InvalidMoveException.class)
	public void case04() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 4 : Invalid Move Exception thrown successful!\n\tDescription:  An attempt made to make wrond move for a pawn\n-----------------------------------------------------------------");
		gameOperationsService.validateMove("x1","51",'1');
		Thread.sleep(1500);
	}
	
	@Test//(expected =InvalidMoveException.class)
	public void case05() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 5 : Testing when 'validateMove' doesn't throw exception!\n\tPlayer 1 makes his 1st move\n\tDescription: Did not throw exception because, it was a valid move\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("x1","31",'1');
		this.updateChessMen();
		this.updateTestMen("21", "31", "x1");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
		
	}
	@Test//(expected =InvalidMoveException.class)
	public void case06() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 6 : Player 2 makes his 1st move!\n\tDescription: As this is his 1st move pawn can be moved 2 squares foward\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("y2","52",'2');
		this.updateChessMen();
		this.updateTestMen("72", "52", "y2");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case07() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 7 : Player 1 makes his 2nd move!\n\tDescription: As this is his 2nd move pawn can be moved 1 square foward\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("x1","41",'1');
		this.updateChessMen();
		this.updateTestMen("31", "41", "x1");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case08() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 8 : Player 2 makes his 2nd move!\n\tDescription: As an opponent's pawn available in next immediate diagonal square, it can acquire the pawn\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("y2","41",'2');
		this.updateChessMen();
		this.updateTestMen("52", "41", "y2");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case09() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 9 :  Player 1 makes his 3rd move!\n\tDescription: Player 1 moves the pawn 1 square forward\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("x2","32",'1');
		this.updateChessMen();
		this.updateTestMen("22", "32", "x2");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case10() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 10 :  Player 2 makes his next move!\n\tDescription: This case test the validity of the move for 'Knight'\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("n1","63",'2');
		this.updateChessMen();
		this.updateTestMen("82", "63", "n1");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case11() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 11 :  Player 1 makes his next move!\\n\\tDescription: This case test the validity of the move for 'Rook'\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("R1","41",'1');
		this.updateChessMen();
		this.updateTestMen("11", "41", "R1");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case12() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 12 :  Player 2 makes his next move!\\n\\tDescription: This case test the validity of the move for 'Pawn'\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("y5","65",'2');
		this.updateChessMen();
		this.updateTestMen("75", "65", "y5");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case13() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 13 :  Player 1 makes his next move!\\n\\tDescription: This case test the validity of the move for 'Pawn'\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("x5","35",'1');
		this.updateChessMen();
		this.updateTestMen("25", "35", "x5");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case14() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 14 :  Player 2 makes his next move!\\n\\tDescription: This case test the validity of the move for 'Bishop'\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("b2","42",'2');
		this.updateChessMen();
		this.updateTestMen("86", "42", "b2");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case15() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 15 :  Player 1 makes his next move!\\n\\tDescription: This case test the validity of the move for 'Queen (Q)'\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("Q","58",'1');
		this.updateChessMen();
		this.updateTestMen("14", "58", "Q");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case16() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 16 :  Player 2 makes his next move!\\n\\tDescription: This case test the validity of the move for 'Queen (q)'\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("q","66",'2');
		this.updateChessMen();
		this.updateTestMen("84", "66", "q");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case17() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 17 :  Player 1 makes his next move!\\n\\tDescription: This case test the validity of the move for 'Pawn'\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("x4","34",'1');
		this.updateChessMen();
		this.updateTestMen("24", "34", "x4");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
	@Test//(expected =InvalidMoveException.class)
	public void case18() throws InvalidMoveException, InterruptedException {
		System.out.println("-----------------------------------------------------------------\n\tCase 18 :  Player 2 makes his next move!\\n\\tDescription: This case test the 'CHECKMATE' state of the game by black 'Bishop (b2)' acquiring white 'King (K)'\n-----------------------------------------------------------------");
		chessBoard=gameOperationsService.validateMove("b2","15",'2');
		this.updateChessMen();
		this.updateTestMen("42", "15", "b2");
		Thread.sleep(1500);
		assertEquals(testMen, chessMen);
	}
}
