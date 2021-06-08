package tictactoe_ai.gui;

import tictactoe_ai.game.GameState;
import tictactoe_ai.game.Player;
import tictactoe_ai.game.PlayerSymbol;
import tictactoe_ai.game.Referee;

public class ConsoleViewer {
	
	public ConsoleViewer(int size, int winSize, Player p1, Player p2, Referee ref) {
		GameState state = new GameState(size, winSize);
		
		boolean p1Turn = true;
		while(!ref.gameIsOver(state)) {
			System.out.println("\n" + p1.getName() + "("+ref.scoreState(state, PlayerSymbol.CIRCLE)+") vs " + p2.getName() + "("+ref.scoreState(state, PlayerSymbol.CROSS)+")\n" + state);
			if (p1Turn) {
				state = (GameState) p1.play(state, PlayerSymbol.CIRCLE);
			} else {
				state = (GameState) p1.play(state, PlayerSymbol.CROSS);
			}
			p1Turn = !p1Turn;
		}
		
		System.out.println("\n Result of the game " + p1.getName() + "("+ref.scoreState(state, PlayerSymbol.CIRCLE)+") vs " + p2.getName() + "("+ref.scoreState(state, PlayerSymbol.CROSS)+")\n" + state);
		System.out.println();
	}
}
