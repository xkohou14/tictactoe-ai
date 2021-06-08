package tictactoe_ai;

import tictactoe_ai.ai.FirstAI;
import tictactoe_ai.game.Player;
import tictactoe_ai.game.Referee1;
import tictactoe_ai.gui.ConsoleViewer;

public class Driver {

	public static void main(String[] args) {
		Referee1 ref = new Referee1();
		FirstAI ai1 = new FirstAI(ref);
		FirstAI ai2 = new FirstAI(ref);
		
		Player p1 = new Player(ai1);
		Player p2 = new Player(ai2);
		
		new ConsoleViewer(10, 6, p1, p2, ref);
		
	}

}
