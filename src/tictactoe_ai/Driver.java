package tictactoe_ai;

import tictactoe_ai.ai.AlphaBeta;
import tictactoe_ai.ai.FirstAI;
import tictactoe_ai.ai.MaxAI;
import tictactoe_ai.ai.MinMaxAI;
import tictactoe_ai.game.Player;
import tictactoe_ai.game.Referee1;
import tictactoe_ai.game.Referee2;
import tictactoe_ai.game.Referee3;
import tictactoe_ai.game.Referee4;
import tictactoe_ai.gui.ConsoleViewer;

public class Driver {

	public static void main(String[] args) {
		Referee3 ref = new Referee3();
//		FirstAI ai1 = new FirstAI(ref);
//		FirstAI ai2 = new FirstAI(ref);
		
//		MaxAI ai1 = new MaxAI(ref);
//		MaxAI ai2 = new MaxAI(ref);
		
		MinMaxAI ai1 = new MinMaxAI(ref);
		MinMaxAI ai2 = new MinMaxAI(ref);
		
//		AlphaBeta ai1 = new AlphaBeta(ref);
//		AlphaBeta ai2 = new AlphaBeta(ref);
		
		Player p1 = new Player(ai1);
		Player p2 = new Player(ai2);
		
		new ConsoleViewer(3, 3, p1, p2, ref);
		
	}

}
