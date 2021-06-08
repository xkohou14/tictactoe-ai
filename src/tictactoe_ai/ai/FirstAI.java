package tictactoe_ai.ai;

import java.util.List;
import java.util.Random;

import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IReferee;
import tictactoe_ai.game.PlayerSymbol;

public class FirstAI extends AI {		
	
	public FirstAI(IReferee referee) {
		super(referee);
		this.name = "Firts AI (Random)";
	}

	public IGameState getNextState(IGameState state, PlayerSymbol symbol) {
		List<IGameState> sts = getAllPossibleStates(state, symbol);
		Random rand = new Random();
		return sts.get(rand.nextInt(sts.size()));
	}
}
