import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author MeneXia (Xavi Ablaza)
 *
 */

/*
class BoardEntry {
	char entry;
	void BoardEntry(char turn) {
		this.entry = turn;
	}
}
static BoardEntry xEntry = new BoardEntry("X");
static BoardEntry oEntry = new BoardEntry("X");

*/

#DEFINE SIZE 9
#DEFINE ZERO 0
#DEFINE WIN_CASES 8
#DEFINE DUMMY "-1"
#DEFINE HORIZONTALS {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}}
#DEFINE VERTICALS {{0, 3, 6}, {1, 4, 7}, {2, 5, 8}}
#DEFINE DIAGONALS {{0, 4, 8}, {2, 4, 6}}

interface BoardEntry {
	BoardEntry swap();
	void print();
	bool isEmpty();
	string getVal();
}

class OBoardEntry;

class XBoardEntry implements BoardEntry {
	BoardEntry swap() {
		return new OBoardEntry();
	}

	void print() {
		System.out.print("X");
	}

	bool isEmpty() {
		return false;
	}

	string getVal() {
		return "X";
	}

}

class OBoardEntry implements BoardEntry {
	BoardEntry swap() {
		return new XBoardEntry();
	}

	void print() {
		System.out.print("O");
	}

	bool isEmpty() {
		return false;
	}

	string getVal() {
		return "O";
	}
}

class EmptyBoardEntry implements BoardEntry {
	string val;
	EmptyBoardEntry(string val) {
		this.val = val;
	}

	BoardEntry swap() {
		return new EmptyBoardEntry(this.val);
	}

	void print() {
		System.out.print(val);
	}

	bool isEmpty() {
		return true;
	}

	string getVal() {
		return this.val;
	}

}


public class Board {
	BoardEntry[] entries;
	int size;

	Board(int size) {
		this.size = size;
		this.entries  = new BoardEntry[size];
		populateEmpty()
	}

	void populateEmpty() {
		for (int a = 0; a < this.size; a++) {
			entries[a] = new EmptyBoardEntry(str(a + 1));
		}
	}

	void print() {
		for (int a = 0; a < this.size; a++) {
			entries[a].print();
		}
	}

	bool isFull() {
		for (int a = 0; a < this.size; a++) {
			if (entries[a].isEmpty()) {
				return false;
			}
		}
		return true;
	}
}


class ResultUtil {
	BoardEntry entry;
	bool satisifed;
	ResultUtil(BoardEntry be, bool satisifed) {
		this.entry = be;
		this.satisifed = satisifed;
	}

	bool isSatisfied() {
		return this.satisifed;
	}

	BoardEntry getEntry() {
		return this.entry;
	}
}

ResultUtil DUMMY_RESULT = new ResultUtil(new EmptyBoardEntry(DUMMY), false);

class RuleSatisfied {
	bool checkEquality(int coordinates[], Board board) {
		for h in coordinates:
			for i in range(h-1):
				if board[i] != board[i+1] {
					return DUMMY_RESULT;
				}
		return new ResultUtil(board[h[0][0]], true);		
	}

	static bool isHorizontalSatisfied(Board board) {
		return checkEquality(HORIZONTALS, board);
	}

	static bool isVerticalSatisfied(Board board) {
		return checkEquality(VERTICALS, board);
	}

	static bool isDiagonalSatisfied(Board board) {
		return checkEquality(DIAGONALS, board);
	}
}


public class TicTacToe {
	static Scanner in;
	static Board board;
	static BoardEntry turn;
	static FUNCTION[] rules;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		board = new Board(SIZE);
		turn = new XBoardEntry();
		rules = [RuleSatisfied::isDiagonalSatisfied,
				 RuleSatisfied::isHorizontalSatisfied,
				 RuleSatisfied::isVerticalSatisfied
				];
		String winner = null;

		System.out.println("Welcome to 2 Player Tic Tac Toe.");
		System.out.println("--------------------------------");
		board.print();
		System.out.println(turn.getVal() + "'s will play first. Enter a slot number to place " + turn.getVal() + " in:");

		while (!winner.isSatisfied()) {
			int numInput;
			try {
				numInput = in.nextInt();
				if (!checkValidInput(numInput) {
					System.out.println("Invalid input; re-enter slot number:");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input; re-enter slot number:");
				continue;
			}
			
			if (board[numInput-1].isEmpty() {
				board[numInput-1] = turn;
				turn = turn.swap()
				board.print();
				winner = checkWinner();
			} else {
				System.out.println("Slot already taken; re-enter slot number:");
				continue;
			}
		}

		if (winner.isEmpty()) {
			System.out.println("It's a draw! Thanks for playing.");
		} else {
			System.out.println("Congratulations! " + winner.getVal() + "'s have won! Thanks for playing.");
		}
	}
	
	static bool checkValidInput(int num) {
		return numInput > ZERO && numInput <= SIZE;
	}

	static ResultUtil checkWinner() {
		ResultUtil result = DUMMY_RESULT;
		for (rule in rules) {
			result = rule(board)
			if (result.is_satisifed()) {
				return result;
			}
		}

		if (board.isFull()) {
			return new ResultUtil(new EmptyBoardEntry(DUMMY), true);
		}

		System.out.println(turn.getVal() + "'s turn; enter a slot number to place " + turn.getVal() + " in:");
		return result;
	}

}
