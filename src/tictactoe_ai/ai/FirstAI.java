package tictactoe_ai.ai;

import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IReferee;
import tictactoe_ai.game.PlayerSymbol;

public class FirstAI extends AI {
	public FirstAI(IReferee referee) {
		super(referee);
	}

	public IGameState getNextState(IGameState state, PlayerSymbol symbol) {
		Iterable<IGameState> sts = getAllPossibleStates(state, symbol);
		IGameState f = null;
		for (IGameState st : sts) {
			if (f == null) {
				f = st;
			}
			return st;
		}
		return f;
	}
}
