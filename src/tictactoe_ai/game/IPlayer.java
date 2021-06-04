package tictactoe_ai.game;

public interface IPlayer {
	
	public boolean isHumanPLayer();
	public String getName();
	
	public IGameState play(IGameState state, PlayerSymbol symbol);
}
