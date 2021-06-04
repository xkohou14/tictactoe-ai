package tictactoe_ai.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Referee implements IReferee {

	List<direction> directions = Arrays.asList(new direction[] {direction.DOWN, direction.D_RIGHT, direction.RIGHT, direction.U_RIGHT});
	
	@Override
	public boolean isWonByPlayer(IGameState state, PlayerSymbol symbol) {		
		for(int x = 0; x < state.getBoardSize(); x++) {
			for(int y = 0; y < state.getBoardSize(); y++) {
				for (direction d : directions) {
					List<int[]> positions = explore(state, x, y, d, symbol);
					if (positions != null) {
						if (positions.size() >= state.getWinCondition())
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public List<List<int[]>> getAllLines(IGameState state, PlayerSymbol symbol) {
		List<List<int[]>> lists = new ArrayList<List<int[]>>();
		for(int x = 0; x < state.getBoardSize(); x++) {
			for(int y = 0; y < state.getBoardSize(); y++) {
				for (direction d : directions) {
					List<int[]> positions = explore(state, x, y, d, symbol);
					if (positions != null) {
						lists.add(positions);
					}
				}
			}
		}
		return lists;
	}
	
	enum direction {
		DOWN,
		UP,
		RIGHT,
		LEFT,
		U_RIGHT,
		U_LEFT,
		D_RIGHT,
		D_LEFT
	}
	
	public int[] newPos (int x, int y, direction d) {
		switch (d) {
		case UP:
			y -= 1;
		case DOWN:
			y += 1;
			break;
		case RIGHT:
			x += 1;
			break;
		case LEFT:
			x -= 1;
			break;
		case D_LEFT:
			x -= 1;
			y += 1;
			break;
		case D_RIGHT:
			x += 1;
			y += 1;
			break;
		case U_RIGHT:
			x += 1;
			y -= 1;
			break;
		case U_LEFT:
			x -= 1;
			y -= 1;
			break;

		default:
			break;
		}
		return new int[] {x,y};
	}
	
	public List<int[]> explore(IGameState state, int x, int y, direction d, PlayerSymbol symbol) {
		int[] newP = newPos(x, y, d);
		if (x > state.getBoardSize()-1 || x < 0 || y < 0 || y > state.getBoardSize()-1) {
			return null;
		} else {
			CellState cellState = state.getValueOfCell(x, y);
			if (cellState == symbol.get()) {
				List<int[]> positions = new ArrayList<int[]>();
				positions.add(new int[]{x, y});
				List<int[]> otherPos = this.explore(state, newP[0], newP[1], d, symbol);
				if (otherPos != null) {
					return Stream.concat(positions.stream(), otherPos.stream())
	                        .collect(Collectors.toList());
				} else {
					return positions;
				}
			}			
		}				
		
		return null;		
	}
	
	public int emptyFields(IGameState state) {
		int empty = 0;
		for(int x = 0; x < state.getBoardSize(); x++) {
			for(int y = 0; y < state.getBoardSize(); y++) {
				if (state.getValueOfCell(x, y) == CellState.NOTHING) {
					empty++;
				}
			}
		}
		return empty;
	}

	@Override
	public boolean gameIsOver(IGameState state) {
		return isWonByPlayer(state, PlayerSymbol.CIRCLE) || isWonByPlayer(state, PlayerSymbol.CROSS) 
				|| emptyFields(state) == 0;
	}

}
