import java.util.Scanner;

import constants.Constants;
import exceptionHandler.InvalidMoveException;
import exceptionHandler.InvalidPawnException;
import service.GameOperationsService;
import service.GameOperationsServiceImpl;

public class StartTheGame {

	public static void main(String[] args) {
		Scanner playerIp=new Scanner(System.in);
		GameOperationsService gameOperationsService=new GameOperationsServiceImpl();
		Boolean checkmate=false;
		/*Instructions to the players*/
		System.out.println("Hey Players!\n");
		System.out.println("Following are the instructions and guide lines to play chess using command line:");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("1. This is a standard 8x8 Chess Board representation with following notations used:");
		System.out.println("\t-> '*' -> a black square/cell");
		System.out.println("\t-> 'O' -> a white square/cell");
		System.out.println("\t-> 'R' -> white Rook    &  'r' -> black Rook");
		System.out.println("\t-> 'N' -> white Knight  &  'n' -> black Knight");
		System.out.println("\t-> 'B' -> white Bishop  &  'b' -> black Bishop");
		System.out.println("\t-> 'Q' -> white Queen   &  'q' -> black Queen");
		System.out.println("\t-> 'K' -> white King    &  'k' -> black King");
		System.out.println("\t-> 'x' -> white Pawn    &  'y' -> black Pawn");
		System.out.println("\n2. To move the pawn enter the 'pawn name' as shown in the representation and \n   enter the square no. where you wnat the pawn to be moved");
		System.out.println("\n\tIn the below example given, 'move' is combination of 'Row no.'(3) and 'Col no.'(6)");
		System.out.println("\tex: pawn: N2\n\t    move:36\n");
		System.out.println("\n3. Apart from the above notations, rest of the notations used are self-explanatory");
		System.out.println("\n**Note: This is game is designed according to rules and standards of real world Chess, so always try to make valid moves,\n\tyou will end up getting exceptions otherwise!");
		System.out.println("\n\t\t***HAPPY GAMING***\n\nplease press 'Enter' key to statrt...");
		playerIp.nextLine();
		System.out.println("\nAll set to go..");
		System.out.println("No. of Players: 2\n");
		String colorChoice="";
		
		/*Player-1 pawn color selection. Depending on which color of pawn Player-1 chooses, Player-2's pawn color will be decided*/
		do {
			System.out.println("Player 1, please choose the pawn color:");
			System.out.println("1. White\n2. Black");
			colorChoice=playerIp.next();
			if(colorChoice.equals("1")||colorChoice.equals("2"))
				break;
			else {
				System.out.println("\nPlease choose either 1 or 2");
				continue;
			}
		}while(true);
		
		char toggle=colorChoice.equals("1")?'1':'2';
		String color1=(colorChoice.equals("1")?Constants.WHITE:Constants.BLACK);
		gameOperationsService.arrangPieces(color1); /*This method draws the chess board on to the console*/
		
		/*Rest of the play is covered in this snippet*/
		while(checkmate==false) {
			if(toggle=='1') {
				toggle='2';
				System.out.println("Turn: Player-1");
			}else if(toggle=='2'){
				toggle='1';
				System.out.println("Turn: Player-2");
			}
			System.out.print("Pawn:");
			String pawn=playerIp.next();
			System.out.print("Move:");
			String move=playerIp.next();
			/*This try block checks for valid pawn*/
			try {
				System.out.println("\n");
				gameOperationsService.validatePawn(pawn,move,toggle=='1'?'2':'1');
				/*This try block checks for valid move*/
				try {
					gameOperationsService.validateMove(pawn, move,toggle=='1'?'2':'1');
				}catch (InvalidMoveException m) {
					if(toggle=='1')
						toggle='2';
					else if(toggle=='2')
						toggle='1';
					System.out.println("\n"+m+"\n");
				}
			} catch (InvalidPawnException p) {
				if(toggle=='1')
					toggle='2';
				else if(toggle=='2')
					toggle='1';
				System.out.println("\n"+p+"\n");
			}
		}
		
		playerIp.close();

	}

}
