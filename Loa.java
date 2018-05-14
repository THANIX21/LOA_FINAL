import java.util.Scanner;

public class Loa {

	private static int size = 0;
	private static int mode = 0;

	public static void main(String[] args) {
		if (args.length < 2) {
			reportErrorAndTerminate("too few arguments");
		}
		size = Integer.parseInt(args[0]);
		if ((size < 4) || (size > 16)) {
			reportErrorAndTerminate("illegal size");
		}
		mode = Integer.parseInt(args[1]);
		if (mode == 0) {
			testMode();
		} else if (mode == 1) {
			singleMode();
		} else if (mode == 2) {
			multiMode(args[2]);
		} else if (mode == 3) {
			guiMode();
		} else {
			reportErrorAndTerminate("illegal mode");
		}
	}

	private static void reportAndTerminate(String message) {
		System.out.println(message);
		System.exit(-1);
	}

	private static void reportErrorAndTerminate(String message) {
		reportAndTerminate("ERROR: " + message);
	}

	private static void testMode() {
		Scanner s = new Scanner(System.in);
		int nextPlayer = Board.BLACK;
		boolean done = false;
		Board.newBoard(size);
		showBoard();
		while (!done) {
			String move = s.next();
			if (move.length() != 4) {
				reportErrorAndTerminate("illegal move");
			}
			if (move.equals("QUIT")) {
				reportAndTerminate("player quit");
			}
			int srcRow = move.charAt(0) - 'A';
			int srcCol = move.charAt(1) - 'A';
			int dstRow = move.charAt(2) - 'A';
			int dstCol = move.charAt(3) - 'A';
			if (!Board.isValidMove(nextPlayer, srcRow, srcCol, dstRow, dstCol)) {
				reportErrorAndTerminate("invalid move");
			}
			Board.makeMove(nextPlayer, srcRow, srcCol, dstRow, dstCol);
			showBoard();
			if (Board.hasWon(nextPlayer)) {
				done = true;
			} else if (nextPlayer == Board.BLACK) {
				nextPlayer = Board.WHITE;
			} else {
				nextPlayer = Board.BLACK;
			}
		}
		if (nextPlayer == Board.BLACK) {
			reportAndTerminate("WINNER: black");
		} else {
			reportAndTerminate("WINNER: white");
		}
		s.close();
	}

	private static void singleMode() {
		Scanner s = new Scanner(System.in);
		int nextPlayer = Board.BLACK;
		boolean done = false;
		Board.newBoard(size);
		showBoard();
		while (!done) {
			if (nextPlayer == Board.BLACK) {
				String move = s.next();
				if (move.length() != 4) {
					reportErrorAndTerminate("illegal move");
				}
				if (move.equals("QUIT")) {
					reportAndTerminate("player quit");
				}
				int srcRow = move.charAt(0) - 'A';
				int srcCol = move.charAt(1) - 'A';
				int dstRow = move.charAt(2) - 'A';
				int dstCol = move.charAt(3) - 'A';
				if (!Board.isValidMove(nextPlayer, srcRow, srcCol, dstRow, dstCol)) {
					reportErrorAndTerminate("invalid move");
				}
				Board.makeMove(nextPlayer, srcRow, srcCol, dstRow, dstCol);
			} else {
				Player.makeMove(nextPlayer);
			}
			showBoard();
			if (Board.hasWon(nextPlayer)) {
				done = true;
			} else if (nextPlayer == Board.BLACK) {
				nextPlayer = Board.WHITE;
			} else {
				nextPlayer = Board.BLACK;
			}
		}
		if (nextPlayer == Board.BLACK) {
			reportAndTerminate("WINNER: black");
		} else {
			reportAndTerminate("WINNER: white");
		}
		s.close();
	}

	private static void showBoard() {
		System.out.print("  ");
		for (int c = 0; c < size; c++) {
			System.out.print((char) ('A' + c));
			System.out.print(' ');
		}
		System.out.println();
		for (int r = size - 1; r >= 0; r--) {
			System.out.print((char) ('A' + r) + " ");
			for (int c = 0; c < size; c++) {
				int piece = Board.getPiece(r, c);
				if (piece == Board.BLACK) {
					System.out.print("B");
				} else if (piece == Board.WHITE) {
					System.out.print("W");
				} else {
					System.out.print(".");
				}
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	private static void multiMode(String host) {
		/*--------------------------------------------
		 * FILL IN THE CODE FOR MULTIPLAYER MODE
		 *-------------------------------------------*/
	}

	private static void guiMode() {
		showGuiBoard(1);
	}

	private static void showGuiBoard(int nextPlayer) {
		double halfsize = (1/((size*1.0)*2));
		StdDraw.setCanvasSize();
		for(double i = halfsize; i <= 1- halfsize; i = i + (halfsize*2)){
			StdDraw.filledSquare(i,i,halfsize);
		}
		StdDraw.setPenColor(StdDraw.RED);
		/*for(double i=0.1;i<=0.9;i=i+0.2){
			StdDraw.filledCircle(i,i,0.05);
		}*/



	}

}
