public class Board {
	private char [][] board = { {' ',' ',' '},{' ',' ',' '},{' ',' ',' '} };
	private int[][] scoreboard= {{0,0,0}, {0,0,0}, {0,0,0}};
	private static final int Xs=10;
	private static final int Os =1;
	
	private int countrowXO (int row) {
		int count=0;
		for(int i =0; i<3; i++)
			if(board[row][i]=='X') count=count+Xs;
			else if (board[row][i]=='O') count=count+Os;
		return count;
	}
	private int countcolXO (int col) {
		int count=0;
		for(int i =0; i<3; i++)
			if(board[i][col]=='X') count=count+Xs;
			else if (board[i][col]=='O') count=count+Os;
		return count;
	}
	private int countLRDiagonalXO () {
		int count=0;
		for(int i =0; i<3; i++)
			if(board[i][i]=='X') count=count+Xs;
			else if (board[i][i]=='O') count=count+Os;
		return count;
	}
	private int countRLDiagonalXO () {
		int count=0;
		for(int i =0; i<3; i++)
			if(board[i][2-i]=='X') count=count+Xs;
			else if (board[i][2-i]=='O') count=count+Os;
		return count;
	}
	private int calScore(char movech, int xos) {
		int xs;
		int os;
		int score = 0;
		xs=(int)(xos/10);
		os=xos%10;
		if(xs==2) {
			if(movech=='O')return 10;
			else return 100;
		}
		if(os==2) {
			if(movech=='O')return 100;
			else return 10;
		}
		if(xs==1) {
			if(movech=='O')score=score+1;
			else score=score-1;
		}
		if(os==1) {
			if(movech=='O')score=score-1;
			else score=score+1;
		}
		return score;
	}
	private int scoreRow(char movech, int row) {
		int xos=0;
		int score = 0;
		xos=countrowXO(row);
		score=calScore(movech,xos);
		return score;
	}
	private int scoreCol(char movech, int col) {
		int xos=0;
		int score = 0;
		xos=countcolXO(col);
		score=calScore(movech,xos);
		return score;
	}
	private int scorelrDiag(char movech) {
		int xos=0;
		int score = 0;
		xos=countLRDiagonalXO();
		score=calScore(movech,xos);
		return score;
	}
	private int scorerlDiag(char movech) {
		int xos=0;
		int score = 0;
		xos=countRLDiagonalXO();
		score=calScore(movech,xos);
		return score;
	}
	private boolean isSpaceOccupied(int i, int j) {
		if(board[i][j] != ' ') return true;
		return false;
	}
	private void calcScoreBoard(char movech) {
		int i;
		int j;
		int score = 0;
		for(i=0; i<3; i++) {
			for(j=0; j<3; j++) {
				if (isSpaceOccupied(i,j)==true) {
					scoreboard[i][j]=-100;
					continue;
				}
				score=0;
				score=score+scoreRow(movech,i);
				score=score+scoreCol(movech,j);
				if(i==j)
					score=score+scorelrDiag(movech);
				if((i+j)==2)
					score=score+scorerlDiag(movech);	
				scoreboard[i][j]=score;
			}
		}
	}
	private boolean didWin(char playerMark, int score) {
		int count;
		if(playerMark=='X')
			count=(int)(score/10);
		else
			count=score%10;
		if(count==3)return true;
		return false;
	}
	
	public boolean didPlayerWin(Players player) {
		int i;
		int score;
		char ch;
		ch=player.getMark();
		for(i=0; i<3; i++) {
			score=countrowXO(i);
			if(didWin(ch,score)==true)return true;
			score=countcolXO(i);
			if(didWin(ch,score)==true)return true;
		}
		score=countLRDiagonalXO();
		if(didWin(ch,score)==true)return true;
		score=countRLDiagonalXO();
		if(didWin(ch,score)==true)return true;
		return false;
	}
	public int findBestMove(char moveChar) {
		int i,j;
		int besti;
		int bestj;
		int highestScore;
		calcScoreBoard(moveChar);
		besti=1;
		bestj=1;
		highestScore=scoreboard[1][1];
		for(i=0; i<3; i++) {
			for(j=0; j<3; j++) {
				if(scoreboard[i][i] > highestScore) {
					highestScore=scoreboard[i][j];
					besti=i;
					bestj=j;
				}
			}
		}
	return (PositionConvert(besti,bestj));
	}
	
	
	
	public int PositionConvert(int x, int y) {
		int position = 0;
		position = (3*x)+y;
		return position;
		}
	public int ConvertRow(int pos) {
		int row = 0;
		row = (int)(pos/3);
				return row;
	}
	public int ConvertCol(int pos) {
		int col= 0;
		col= pos%3;
		return col;
	}
	
	public boolean isGameOver() {
		int i,j;
		for(i=0; i<3; i=i+1)
			for(j=0; j<3; j=j+1)
				if (board[i][j] == ' ')
					return false;
						return true;
			}
	public boolean IsPositionValid (int position) {
		int x,y;
		if ((position<0) || (position>8))
			
			return false;
		x= ConvertRow(position);
		y=ConvertCol(position);
		if (board[x][y]==' ') return true;
		return false;
	}
	public void markMove(int position, char mark) {
		int x,y;
		x= ConvertRow(position);
		y=ConvertCol(position);
		board [x][y]= mark;
	}
	public void printBoard() {
		int i,j;
		for(i=0; i<3; i=i+1) {
			for(j=0; j<3; j=j+1) {
				if (board[i][j]==' ') {
					System.out.print(PositionConvert(i,j) +" ");
				}
				else {
					System.out.print(board[i][j] +" ");
				}
				switch(i) {
				case 0: System.out.print("|");
				break;
				case 1: System.out.print("|");
				break;
				case 2:System.out.print("|");
				break;
				}
			}
			if((i != 2)) {
				System.out.println("\n--+--+--");
			}
			System.out.println();
		}
	}

}
