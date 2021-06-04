package tictactoe_ai.ai;

import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.PlayerSymbol;

public interface IAI {
	public IGameState getNextState(IGameState state, PlayerSymbol symbol);
	
	/**
	 * Returns all possible configurations
	 * @return
	 */
	public Iterable<IGameState> getAllPossibleStates(IGameState state, PlayerSymbol symbol);
}
