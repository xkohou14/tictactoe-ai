package tictactoe_ai.ai;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IReferee;
import tictactoe_ai.game.PlayerSymbol;

public abstract class AI implements IAI {
	protected String name = "UnnamedAI";
	
	protected IReferee referee;
	public AI(IReferee referee) {
		
	}
	
	public String getName() {
		return name;
	}
	
	protected int getFinalScoreForState(IGameState state, PlayerSymbol symbol) {
		throw new NotImplementedException();
	}
}
