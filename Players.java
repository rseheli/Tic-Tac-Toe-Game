import java.util.Scanner;
public class Players {
	private char moveChar;
	private Scanner keyboard;
	boolean isComputer;
	public Players(Scanner input, char ch) {
		keyboard= input;
		moveChar= ch;
		isComputer= false;
	}
	public Players(char ch) {
		keyboard=null;
		moveChar=ch;
		isComputer=true;
	}
	public char getMark () { return moveChar; }
	
	public int selectMove(Board board) {
		int position;
		if(isComputer==false) {
		do {
			System.out.println("Enter a value for your move from 0-8: ");
			position= keyboard.nextInt();
			if (board.IsPositionValid(position)== true) {
				System.out.println("Enter a valid move");
			}
			System.out.println();
			
		}while (board.IsPositionValid(position)== false);
		}
		else {
			position=board.findBestMove(moveChar);		
		}
		return position;
	}
}
