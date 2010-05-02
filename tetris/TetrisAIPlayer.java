package tetris;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * A class to play tetris online using
 * the Java Robot class.
 *
 * @author mellort
 */
public class TetrisAIPlayer {
	/**
	 * Piece_X and piece_Y tell us where to
	 * grab the screen pixel for the current
	 * faling piece.
	 */
	public int PIECE_X;
	public int PIECE_Y;
	/**
	 * Pieces tells us the colors of the pieces
	 * of the game, and piecestrings tells us
	 * the corresponding string colors.
	 */
	public Color[] pieces;
	public String[] pieceStrings;
	
	/**
	 * The wait times for the AI player.
	 *
	 */
	public int WAIT_TIME;
	public int KEY_TIME;
	//the tetris game
	public Tetris tetris;
	
	/**
	 * A constructor for the AI Player.
	 * takes in the colors for the pieces
	 * along with the strings of the pieces
	 * names, and constants for the AI.
	 *
	 */
	public TetrisAIPlayer(Color[] pieces, String[] pieceStrings, double[] aiConstants) {
		tetris = new Tetris(aiConstants);
		this.pieces = pieces;
		this.pieceStrings = pieceStrings;
	}
	
	public TetrisAIPlayer(Color[] pieces, String[] pieceStrings) {
		double[] aiConstants = {2.0, 5.0, 7.0, 10.0};
		tetris = new Tetris(aiConstants);
		this.pieces = pieces;
		this.pieceStrings = pieceStrings;
	}
	
	public TetrisAIPlayer() {
	}

	
	/**
	* setters for wait time and key time
	*/
	public void setWaitTime(int time) { this.WAIT_TIME = time; }
	public void setKeyTime(int time) { this.KEY_TIME = time; }

	/**
	* setters for the pixels to scrape
	*/
	
	public void setPieceX(int x) { this.PIECE_X = x;}
	public void setPieceY(int y) { this.PIECE_Y = y;}

	/**
	 * A method to play the game.
	 *
	 */
	public int playGame() throws Exception{
		//make a new robot
		Robot robot =  new Robot();
		//keep track of cleared pieces
		int clearedPieces=0;
		
		//while the game isn't over, play it
		while(!tetris.over) {
			//get piece from color
			int next=-1;
			
			/*
			 * Get the next piece.
			 */
			while( next==-1) {
				//get the color of the pixel
				Color pieceColor = robot.getPixelColor(PIECE_X, PIECE_Y);
				//match the color with known colors
				for(int i=0; i<pieces.length; i++) {
					if( pieceColor != null && pieces[i].equals(pieceColor) ) {
						next =i;
					}
				}
			
				if (next == -1) {
					//System.out.println("waiting");
				}
				//sleep
				Thread.currentThread().sleep(KEY_TIME);
			}
			
			//print out the kind of piece we got
			System.out.println("got a " + pieceStrings[next]);
			
			//add the piece to the tetris game
			tetris.addPiece(next);
			//make the move, and determine where the piece needs to move in the online game
			int[] moves = tetris.makeMove();
			//remove tetrises
			tetris.tetrisify();
			//print the board
			tetris.printBoard();
			//increment cleared pieces
			clearedPieces++;
			
			//move piece
			int displacement = moves[0];
			int rotation = moves[1];
			
			System.out.println("need to move: " + displacement + " rotate: " + rotation);

			//rotate the piece
			while( rotation > 0 ) {
				System.out.println("\trotate");
				robot.keyPress(KeyEvent.VK_UP);
				Thread.currentThread().sleep(KEY_TIME);
				robot.keyRelease(KeyEvent.VK_UP);
				
				Thread.currentThread().sleep(WAIT_TIME);
				rotation--;
			}

			//now move the piece
			if( displacement < 0 ) {
				//move right
				while( displacement < 0) {
					System.out.println("\tright");
					robot.keyPress(KeyEvent.VK_RIGHT);
					Thread.currentThread().sleep(KEY_TIME);
					robot.keyRelease(KeyEvent.VK_RIGHT);
					
					Thread.currentThread().sleep(WAIT_TIME);
					displacement++;
				}
			} else if( displacement > 0) {
				//move left
				while( displacement > 0) {
					System.out.println("\tleft");
					robot.keyPress(KeyEvent.VK_LEFT);
					Thread.currentThread().sleep(KEY_TIME);
					robot.keyRelease(KeyEvent.VK_LEFT);
					
					Thread.currentThread().sleep(WAIT_TIME);
					displacement--;
				}
			}
			

			//now, hard drop the piece
			System.out.println("\tspace");
			
			robot.keyPress(KeyEvent.VK_SPACE);
			Thread.currentThread().sleep(KEY_TIME);
			robot.keyRelease(KeyEvent.VK_SPACE);
			
			//sleep
			Thread.currentThread().sleep(WAIT_TIME);
		}
		
		
		return 0;
	}
	
	

}
