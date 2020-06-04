import java.util.Scanner;
public class Game {
	static Scanner keyboard = new Scanner (System.in);
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board myboard = new Board ();
		Players player1 = new Players (keyboard, 'X');
		Players player2 = new Players ( keyboard,'0');
		Players CurrentPlayer;
		int position;
		CurrentPlayer = player1;
		do {
			myboard.printBoard();
			position= CurrentPlayer.selectMove(myboard);
			myboard.markMove(position, CurrentPlayer.getMark());
			CurrentPlayer = (CurrentPlayer == player1) ? player2 : player1;		
		}while(myboard.isGameOver()== false);
		myboard.printBoard();
		
		if(myboard.didPlayerWin (player1)==true) {
			myboard.printBoard();
			System.out.println("Player 1 Won");
		}
		else {
			if(myboard.didPlayerWin (player2)==true) {
				myboard.printBoard();
				System.out.println("Player 2 Won");
			}
			else {
				System.out.println("Game was a Draw");
			}
			
		}
		System.out.println("Game is over");
		

	}

}
