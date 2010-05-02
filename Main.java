import java.util.Random;
import tetris.*;

public class Main {
	public static void main(String[] args) throws Exception {
		double[] constants ={0.7079009304384309, 3.8753536098633123, 7.015729027236182, 5.720294020792873};
		TetrisAI ai = new TetrisAI(constants);
		System.out.println(ai.playGame());
	}

}
