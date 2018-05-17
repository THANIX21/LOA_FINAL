import java.util.Scanner;
import javax.swing.JOptionPane;

public class Loa {

	private static int size = 0;
	private static int mode = 0;
	private static boolean done = false;

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
		Board.newBoard(size);
		double halfsize = (1/((size*1.0)*2));
		showGuiBoard(2);

		int clicked = 0;
		double fromX = 0.0;
		double fromY = 0.0;
		double toX = 0.0;
		double toY = 0.0;
		int fromCol = 0;
		int fromRow = 0;
		int toCol = 0;
		int toRow = 0;
		int mistake = 0;

		while(done==false){

			clicked = 0;
			fromX = 0.0;
			fromY = 0.0;
			toX = 0.0;
			toY = 0.0;
			fromCol = 0;
			fromRow = 0;
			toCol = 0;
			toRow = 0;
			StdDraw.changeClicked();

			while(Board.makeMove(2,fromRow,fromCol,toRow,toCol)==false){

				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledCircle(1.075,1-0.075,0.05);

				if(Board.makeMove(2,fromRow,fromCol,toRow,toCol)==true){

					Board.makeMove(2,fromRow,fromCol,toRow,toCol);

				}else{

					clicked = 0;
					fromX = 0.0;
					fromY = 0.0;
					toX = 0.0;
					toY = 0.0;
					fromCol = 0;
					fromRow = 0;
					toCol = 0;
					toRow = 0;

					StdDraw.changeClicked();

					while(clicked!=2){

						if(StdDraw.mousePressed()==true){

							if((((StdDraw.mouseX())>=1-halfsize*4)&&((StdDraw.mouseX())<=1))&&(StdDraw.mouseY()<0)){
								System.exit(1);
							}
							clicked = clicked + 1;

							if(clicked==1){
								fromX = StdDraw.mouseX();
								fromY = StdDraw.mouseY();
								StdDraw.changeClicked();
							}

							if(clicked==2){
								toX = StdDraw.mouseX();
								toY = StdDraw.mouseY();
							}

						}
					}

					fromCol=(int) (Math.floor(fromX/(1.0/size)));
		  		fromRow=(int) (Math.floor((1.0-fromY)/(1.0/size)));
	  			toCol=(int) (Math.floor(toX/(1.0/size)));
	 				toRow=(int) (Math.floor((1.0-toY)/(1.0/size)));

				}
			}

			showGuiBoard(2);
			winDisplay();
			StdDraw.setPenColor(StdDraw.RED);
			Player.makeMove(1);
			StdDraw.filledCircle(1.075,1-0.075,0.05);
			try{
				Thread.sleep(1000);
			}catch (Exception e){

			}
			showGuiBoard(2);
			winDisplay();
		}
	}

	private static void winDisplay(){

		double halfsize = (1/((size*1.0)*2));//determine half the width of a block dynamically according to the size that is given
		if(Board.hasWon(1)==true){
			done=true;
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(halfsize,-0.075,"W");
			StdDraw.text(halfsize+(halfsize/4),-0.075,"H");
			StdDraw.text(halfsize+(halfsize/4)*2,-0.075,"I");
			StdDraw.text(halfsize+(halfsize/4)*3,-0.075,"T");
			StdDraw.text(halfsize+(halfsize/4)*4,-0.075,"E");
			StdDraw.text(halfsize+(halfsize/4)*5,-0.075," ");
			StdDraw.text(halfsize+(halfsize/4)*6,-0.075," ");
			StdDraw.text(halfsize+(halfsize/4)*7,-0.075,"W");
			StdDraw.text(halfsize+(halfsize/4)*8,-0.075,"I");
			StdDraw.text(halfsize+(halfsize/4)*9,-0.075,"N");
			StdDraw.text(halfsize+(halfsize/4)*10,-0.075,"S");
			try{
				Thread.sleep(2500);
			}catch (Exception e){
			}
			System.exit(1);
		}else if(Board.hasWon(2)==true){
			done=true;
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(halfsize,-0.075,"B");
			StdDraw.text(halfsize+(halfsize/4),-0.075,"L");
			StdDraw.text(halfsize+(halfsize/4)*2,-0.075,"A");
			StdDraw.text(halfsize+(halfsize/4)*3,-0.075,"C");
			StdDraw.text(halfsize+(halfsize/4)*4,-0.075,"K");
			StdDraw.text(halfsize+(halfsize/4)*5,-0.075," ");
			StdDraw.text(halfsize+(halfsize/4)*6,-0.075," ");
			StdDraw.text(halfsize+(halfsize/4)*7,-0.075,"W");
			StdDraw.text(halfsize+(halfsize/4)*8,-0.075,"I");
			StdDraw.text(halfsize+(halfsize/4)*9,-0.075,"N");
			StdDraw.text(halfsize+(halfsize/4)*10,-0.075,"S");
			try{
				Thread.sleep(2500);
			}catch (Exception e){
			}
			System.exit(1);
		}
	}

	private static void showGuiBoard(int nextPlayer) { //method to be called whenever the board is spawned or a move is made to show the change

		double halfsize = (1/((size*1.0)*2));//determine half the width of a block dynamically according to the size that is given
		StdDraw.setCanvasSize();//initialize the canvas on which is to be drawn
		StdDraw.setXscale(0,1.15);//set border size x
		StdDraw.setYscale(-0.15,1.0);//set border size y
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(1-halfsize*2,-0.075,halfsize*2,0.075);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(1-halfsize*3,-0.075,"Q");
		StdDraw.text(1-halfsize*3+(halfsize/2),-0.075,"U");
		StdDraw.text(1-halfsize*3+(halfsize/2)*2,-0.075,"I");
		StdDraw.text(1-halfsize*3+(halfsize/2)*3,-0.075,"T");

		//start with first grid of dark grey blocks using the halfsize variable to determine their points of origin
		for(double i = halfsize; i <= 1.0; i = i + (halfsize*2)){
			for(double k = i; k <= 1.0; k = k + (halfsize*4)){

				StdDraw.setPenColor(StdDraw.DARK_GRAY);
				StdDraw.filledSquare(i,i,halfsize);
				StdDraw.filledSquare(i,k,halfsize);
				StdDraw.filledSquare(k,i,halfsize);

			}
		} //end first grid

		//second grid of light grey blocks using the halfsize variable to determine their points of origin
		for(double i = halfsize; i <= 1- halfsize; i = i + (halfsize*4)){
			for(double k = halfsize;k <= 1- halfsize; k = k + (halfsize*4)){

				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.filledSquare(k+halfsize*2,i,halfsize);
				StdDraw.filledSquare(i,k+halfsize*2,halfsize);

			}
		}// end second grid

		// read where the pieces on the board are and display them using circles
		for (int r = size - 1; r >= 0; r--) {
			for (int c = 0; c < size; c++) {

				int piece = Board.getPiece(r, c);
				if (piece == Board.BLACK) {

					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledCircle(halfsize+(c*(halfsize*2)),halfsize+((size-r-1)*(halfsize*2)),halfsize/2);

				} else if (piece == Board.WHITE) {

					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(halfsize+(c*(halfsize*2)),halfsize+((size-r-1)*(halfsize*2)),halfsize/2);

				}
			}
		}// end circle for loop

	}

}
