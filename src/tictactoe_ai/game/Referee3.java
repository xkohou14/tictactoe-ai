package tictactoe_ai.game;

import java.util.List;

public class Referee3 extends Referee {
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
				int score = scoreForPlayer(state, symbol) - scoreForPlayer(state, symbol.oposite());
//				int l = getAllLines(state, symbol).size();
//				int lo = getAllLines(state, symbol.oposite()).size();
//				System.out.println("Score " + score + "  " + symbol + " lines " + l +" lines oposite " + lo +"\n" + state);
				return score;
			}
				
		}
	}
	
	private int scoreForPlayer(IGameState state, PlayerSymbol symbol) {
		int score = 0;
		int winC = state.getWinCondition();
		for(List<int[]> line : getAllLines(state, symbol)) {
			if (line.size() == 0) {
				continue;
			}
			int[] first = line.get(0);
			int[] last = line.get(line.size()-1);
			if (line.size() == 1) {
				score += emptyFieldsInNeigborhood(state, first[0], first[1]) / 2;
				continue;
			}
			int lastIndex = state.getBoardSize() - 1;					
			for(direction d : getDirection(line)) {						
				int[] newPos = newPos(first[0], first[1], opositeDirection(d));
				if (isEmpty(newPos[0], newPos[1], state)) {
					score += winC * (line.size());
				}
				newPos = newPos(last[0], last[1], d);
				if (isEmpty(newPos[0], newPos[1], state)) {
					score += winC * (line.size());
				}
			}
		}
		return score;
	}
}
