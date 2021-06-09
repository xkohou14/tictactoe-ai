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
	
	public direction opositeDirection(direction d) {
		switch (d) {
		case UP:
			return direction.DOWN;
		case DOWN:
			return direction.UP;
		case RIGHT:
			return direction.LEFT;
		case LEFT:
			return direction.RIGHT;
		case D_LEFT:
			return direction.U_RIGHT;
		case D_RIGHT:
			return direction.U_LEFT;
		case U_RIGHT:
			return direction.D_LEFT;
		case U_LEFT:
			return direction.D_RIGHT;

		default:
			return d;
		}
	}
	
	public int[] newPos (int x, int y, direction d) {
		switch (d) {
		case UP:
			x -= 1;
			break;
		case DOWN:
			x += 1;
			break;
		case RIGHT:
			y += 1;
			break;
		case LEFT:
			y -= 1;
			break;
		case D_LEFT:
			y -= 1;
			x += 1;
			break;
		case D_RIGHT:
			x += 1;
			y += 1;
			break;
		case U_RIGHT:
			y += 1;
			x -= 1;
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
	public boolean isEmpty(int x, int y, IGameState state) {
		try {
			return state.getValueOfCell(x, y) == CellState.NOTHING;
		} catch (Exception e) {
			return false;
		}
	}
	
	protected List<direction> getDirection(List<int[]> line) {
		List<direction> directions = new ArrayList<Referee.direction>();
		
		if (line.size() > 1) {
			int[] first = line.get(0);
			int[] second = line.get(1);
			int xDelta = second[0] - first[0];
			int yDelta = second[1] - first[1];
			int scalar = xDelta * yDelta;
			if (scalar == 0) { //horizontal or vertical
				if (xDelta != 0) {
					if (xDelta > 0) {
						directions.add(direction.DOWN);
					} else {
						directions.add(direction.UP);
					}
				}
				if (yDelta != 0) {
					if (yDelta > 0) {
						directions.add(direction.RIGHT);
					} else {
						directions.add(direction.LEFT);
					}
				}
			} else {
				if (scalar > 0) {
					if (xDelta > 0) {
						directions.add(direction.D_RIGHT);
					} else {
						directions.add(direction.U_LEFT);
					}
				} else {
					if (xDelta > 0) {
						directions.add(direction.D_LEFT);
					} else {
						directions.add(direction.U_RIGHT);
					}
				}
			}
		} else {
//			directions.add(direction.UP);
//			directions.add(direction.DOWN);
//			directions.add(direction.LEFT);
//			directions.add(direction.RIGHT);
//			directions.add(direction.U_LEFT);
//			directions.add(direction.U_RIGHT);
//			directions.add(direction.D_LEFT);
//			directions.add(direction.D_RIGHT);
		}
		
		return directions;
	}

	@Override
	public boolean gameIsOver(IGameState state) {
		return isWonByPlayer(state, PlayerSymbol.CIRCLE) || isWonByPlayer(state, PlayerSymbol.CROSS) 
				|| emptyFields(state) == 0;
	}

}
