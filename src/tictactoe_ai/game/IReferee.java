package tictactoe_ai.game;

public interface IReferee {
	public int scoreState(IGameState state, PlayerSymbol symbol);
	
	public boolean isWonByPlayer(IGameState state, PlayerSymbol symbol);
	public boolean gameIsOver(IGameState state);
}
