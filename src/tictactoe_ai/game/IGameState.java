package tictactoe_ai.game;

public interface IGameState {
	
	/**
	 * returns deep copy of the game state
	 * @return
	 */
	public IGameState getCopy();
	
	public int getBoardSize();
	public void setBoardSize(int boardSize);
	
	/**
	 * How many symbols has to be in the line
	 * @return
	 */
	public int getWinCondition();	
	public void setWinCondition(int winCondition);
	
	public CellState getValueOfCell(int x, int y);
	public CellState[][] getCellsValues();
	public void setValueOfCell(CellState state, int x, int y);
	public void setValueOfCell(PlayerSymbol symbol, int x, int y);
}
