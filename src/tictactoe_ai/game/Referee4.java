package tictactoe_ai.game;

import java.util.List;

public class Referee4 extends Referee {
	/**
	 * Counts how many fields can end the game. These fields has power of winCondition 
	 * (length of the winning line). If there are 2 fields to finish line -> winCondition - 2..
	 * 
	 * If win => max, if lose => min
	 */
	@Override
	public int scoreState(IGameState state, PlayerSymbol symbol) {
		if (isWonByPlayer(state, symbol)) {
			return Integer.MAX_VALUE;
		} else {
			if (isWonByPlayer(state, symbol.oposite())) {
				return Integer.MIN_VALUE;
			} else {
				int score = 0;
				int winC = state.getWinCondition();
				for (int[] pos : getPlayerPlaces(state, symbol)) {
					score += scorePosition(state, pos, symbol);
				}
				for (int[] pos : getPlayerPlaces(state, symbol.oposite())) {
					score -= scorePosition(state, pos, symbol.oposite());
				}
				//System.out.println("Score " + score + "  " + symbol + "\n" + state);
				return score;
			}
				
		}
	}
	
	private int scorePosition(IGameState state, int[] pos, PlayerSymbol symbol) {
		int score = emptyFieldsInNeigborhood(state, pos[0], pos[1]);
		
		for (direction d : directions) {
			int[] newPos = newPos(pos[0], pos[1], d);
			if (isEmpty(newPos[0], newPos[1], state)) {
				score += 1;
			} else {
				try {
					if (state.getValueOfCell(newPos[0], newPos[1]) == symbol.get()) {
						score += 2 * scorePosition(state, newPos, symbol);
					}	
				} catch (Exception e) {
					//System.out.println(e.getMessage());
				}
			}
		}
		
		return score;
	}
}
