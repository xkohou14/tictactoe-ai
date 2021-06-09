package tictactoe_ai.game;

import java.util.List;

public class Referee2 extends Referee {
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
				for(List<int[]> line : getAllLines(state, symbol)) {
					if (line.size() == 0) {
						continue;
					}
					int[] first = line.get(0);
					int[] last = line.get(line.size()-1);
					int lastIndex = state.getBoardSize() - 1;					
					for(direction d : getDirection(line)) {						
						int[] newPos = newPos(first[0], first[1], opositeDirection(d));
						//System.out.println(symbol + " iA [" + first[0] + " " + first[1] + "] n =>["+ newPos[0] + " " + newPos[1] +  "] d " + opositeDirection(d));
						if (newPos[0] >= 0 && newPos[1] >= 0 && isEmpty(newPos[0], newPos[1], state)) {
							score += winC - (winC - line.size() - 1);
						}
						newPos = newPos(last[0], last[1], d);
						//System.out.println(symbol + " A [" + last[0] + " " + last[1] + "] n =>["+ newPos[0] + " " + newPos[1] +  "] d " + d);
						if (newPos[0] <= lastIndex && newPos[1] <= lastIndex && isEmpty(newPos[0], newPos[1], state)) {
							score += winC - (winC - line.size() - 1);
						}
					}
				}
				for(List<int[]> line : getAllLines(state, symbol.oposite())) {
					if (line.size() == 0) {
						continue;
					}
					int[] first = line.get(0);
					int[] last = line.get(line.size()-1);
					int lastIndex = state.getBoardSize() - 1;					
					for(direction d : getDirection(line)) {						
						int[] newPos = newPos(first[0], first[1], opositeDirection(d));
						//System.out.println(symbol + " iA [" + first[0] + " " + first[1] + "] n =>["+ newPos[0] + " " + newPos[1] +  "] d " + opositeDirection(d));
						if (newPos[0] >= 0 && newPos[1] >= 0 && isEmpty(newPos[0], newPos[1], state)) {
							score -= winC - (winC - line.size() - 1);
						}
						newPos = newPos(last[0], last[1], d);
						//System.out.println(symbol + " A [" + last[0] + " " + last[1] + "] n =>["+ newPos[0] + " " + newPos[1] +  "] d " + d);
						if (newPos[0] <= lastIndex && newPos[1] <= lastIndex && isEmpty(newPos[0], newPos[1], state)) {
							score -= winC - (winC - line.size() - 1);
						}
					}
				}
				//System.out.println("Score " + score);
				return score;
			}
				
		}
	}
}
