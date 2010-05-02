package tetris;

import java.awt.Color;

/**
 * A class to play the tetris games at TetrisFriends.com
 */
public class TetrisFriendsPlayer extends TetrisAIPlayer {

	public TetrisFriendsPlayer() {
		Color[] temp = { new Color(250,50,90), new Color(68,100,233), new Color(255,194,37), new Color(210,76,173), new Color(124,212,36), new Color(255,126,37), new Color(50,190,250)};
		this.pieces = temp;
		String[] tempStrings =  {"red","blue","yellow","purple","green","orange","lightblue"};
		this.pieceStrings = tempStrings;
		this.WAIT_TIME = 65;
		this.KEY_TIME = 65;
		this.tetris = new Tetris();
	}

	public TetrisFriendsPlayer(double aiConstants[]) {
		this();
		this.tetris = new Tetris(aiConstants);
	}
}
