package tictactoe_ai.ai;

import java.util.List;
import java.util.Random;

import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IReferee;
import tictactoe_ai.game.PlayerSymbol;

public class MaxAI extends AI {		
	
	public MaxAI(IReferee referee) {
		super(referee);
		this.name = "Max AI - selects max";
	}

	public IGameState getNextState(IGameState state, PlayerSymbol symbol) {
		List<IGameState> sts = getAllPossibleStates(state, symbol);
		IGameState maxState = sts.get(0);		
		for(IGameState s : sts) {
			int oponentScore = this.referee.scoreState(s, symbol.oposite());			
			if (this.referee.scoreState(s, symbol) - oponentScore > this.referee.scoreState(maxState, symbol) - oponentScore) {
				System.out.println("Opponent: " + oponentScore);
				maxState = s;
			}
		}
		
		return maxState;
	}
}
