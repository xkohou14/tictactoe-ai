package tictactoe_ai.gui;

import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IPlayer;

public interface IBoard {
	public void setState(IGameState state);
	
	public void setFirstPlayer(IPlayer player);
	public void setSecondPlayer(IPlayer player);
	
	public void run();
}
