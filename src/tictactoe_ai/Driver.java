package tictactoe_ai;

import tictactoe_ai.ai.FirstAI;
import tictactoe_ai.ai.MaxAI;
import tictactoe_ai.game.Player;
import tictactoe_ai.game.Referee1;
import tictactoe_ai.game.Referee2;
import tictactoe_ai.gui.ConsoleViewer;

public class Driver {

	public static void main(String[] args) {
		Referee2 ref = new Referee2();
//		FirstAI ai1 = new FirstAI(ref);
//		FirstAI ai2 = new FirstAI(ref);
		
		MaxAI ai1 = new MaxAI(ref);
		MaxAI ai2 = new MaxAI(ref);
		
		Player p1 = new Player(ai1);
		Player p2 = new Player(ai2);
		
		new ConsoleViewer(4, 3, p1, p2, ref);
		
	}

}
