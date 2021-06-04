package tictactoe_ai.ai;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tictactoe_ai.game.CellState;
import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IReferee;
import tictactoe_ai.game.PlayerSymbol;

public abstract class AI implements IAI {
	protected String name = "UnnamedAI";
	
	protected IReferee referee;
	
	public AI(IReferee referee) {
		this.referee = referee;
	}
	
	public String getName() {
		return name;
	}
	
	protected int getFinalScoreForState(IGameState state, PlayerSymbol symbol) {
		throw new NotImplementedException();
	}
	
	@Override
	public Iterable<IGameState> getAllPossibleStates(IGameState state, PlayerSymbol symbol) {
		List<IGameState> states = new ArrayList<IGameState>();
		for(int x = 0; x < state.getBoardSize(); x++) {
			for(int y = 0; y < state.getBoardSize(); y++) {
				if (state.getValueOfCell(x, y) == CellState.NOTHING) {
					IGameState newState = state.getCopy();
					newState.setValueOfCell(symbol, x, y);
					states.add(newState);
				}
			}
		}
		
		return states;
	}
}
