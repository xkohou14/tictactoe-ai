package tictactoe_ai.game;

public class GameState implements IGameState {
	
	private int boardSize;
	private int winCondition;
	private CellState[][] cells;
	
	public GameState() {
		this.init(3, 3);
	}
	public GameState(int boardSize) {
		this.init(boardSize, 3);
	}
	public GameState(int boardSize, int winConditions) {
		this.init(boardSize, winConditions);
	}
	
	private void init(int board, int winC) {
		this.boardSize = board;
		this.winCondition = winC;
		
		this.cells = new CellState[board][board];
		for(int x = 0; x < this.getBoardSize(); x++) {
			for(int y = 0; y < this.getBoardSize(); y++) {
				this.cells[x][y] = CellState.NOTHING;
			}
		}
	}

	@Override
	public IGameState getCopy() {
		GameState newGameState = new GameState(this.boardSize, this.winCondition);
		for(int x = 0; x < newGameState.getBoardSize(); x++) {
			for(int y = 0; y < newGameState.getBoardSize(); y++) {
				newGameState.setValueOfCell(getValueOfCell(x, y), x, y);
			}
		}
		return newGameState;
	}

	@Override
	public int getBoardSize() {
		return this.boardSize;
	}

	@Override
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	@Override
	public int getWinCondition() {
		return this.winCondition;
	}

	@Override
	public void setWinCondition(int winCondition) {
		this.winCondition = winCondition;
	}

	@Override
	public CellState getValueOfCell(int x, int y) {
		return this.cells[x][y];
	}

	@Override
	public CellState[][] getCellsValues() {
		return this.cells;
	}

	@Override
	public void setValueOfCell(CellState state, int x, int y) {
		this.cells[x][y] = state;
	}

	@Override
	public void setValueOfCell(PlayerSymbol symbol, int x, int y) {
		this.cells[x][y] = symbol == PlayerSymbol.CIRCLE ? CellState.CIRCLE : CellState.CROSS;
	}
	
	@Override
	public String toString() {
		String str = "";
		for(int x = 0; x < this.getBoardSize(); x++) {
			for(int y = 0; y < this.getBoardSize(); y++) {
				switch (getValueOfCell(x, y)) {
				case NOTHING:
					str += "-";
					break;
				case CIRCLE:
					str += "0";
					break;
				case CROSS:
					str += "X";
					break;

				default:
					break;
				}
			}
			str += "\n";
		}
		return str;
	}

}
