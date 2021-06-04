package tictactoe_ai.game;

import java.util.List;

public class Referee1 extends Referee {
	@Override
	public int scoreState(IGameState state, PlayerSymbol symbol) {
		if (isWonByPlayer(state, symbol)) {
			return Integer.MAX_VALUE;
		} else {
			if (isWonByPlayer(state, symbol.oposite())) {
				return Integer.MIN_VALUE;
			} else {
				int score = 0;
				for(List<int[]> list : getAllLines(state, symbol)) {
					if (list.size() == 0) {
						continue;
					}
					int[] first = list.get(0);
					int[] last = list.get(list.size()-1);
					int lastIndex = state.getBoardSize() - 1; 
					if (first[0] == 0 || first[1] == 0 || last[0] == lastIndex || last[1] == lastIndex) {
						score += list.size();
					} else {
						score += list.size() * 2;
					}
				}
				for(List<int[]> list : getAllLines(state, symbol.oposite())) {
					if (list.size() == 0) {
						continue;
					}
					int[] first = list.get(0);
					int[] last = list.get(list.size()-1);
					int lastIndex = state.getBoardSize() - 1; 
					if (first[0] == 0 || first[1] == 0 || last[0] == lastIndex || last[1] == lastIndex) {
						score -= list.size();
					} else {
						score -= list.size() * 2;
					}
				}
				return score;
			}
				
		}
	}
}
