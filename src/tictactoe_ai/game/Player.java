package tictactoe_ai.game;

import tictactoe_ai.ai.AI;

public class Player implements IPlayer{
	
	private AI ai;
	public Player() {
		this.ai = null;
	}
	public Player(AI ai) {
		this.ai = ai;
	}

	@Override
	public boolean isHumanPLayer() {
		return this.ai == null;
	}

	@Override
	public String getName() {
		return this.isHumanPLayer() ? "Human Player": this.ai.getName();
	}

	@Override
	public IGameState play(IGameState state, PlayerSymbol symbol) {
		return this.isHumanPLayer() ? state : this.ai.getNextState(state, symbol);
	}

}
