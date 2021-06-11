package tictactoe_ai.ai;

import tictactoe_ai.game.CellState;
import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IReferee;
import tictactoe_ai.game.PlayerSymbol;

public class MinMaxAI extends AI {		
	
	public MinMaxAI(IReferee referee) {
		super(referee);
		this.name = "MinMaxAI - minimax";
	}

	public IGameState getNextState(IGameState state, PlayerSymbol symbol) {
		int[] action = miniMax(state.getCopy(), Math.min(state.getWinCondition(), 4), true, symbol);
		
		log("\nSelected " + action[2] + "  " + action[0] + "  " + action[1]);
		if (action[0] >= 0 && action[1] >= 0)
			state.setValueOfCell(symbol, action[0], action[1]);
		else {
			System.out.println("-----Cannot set " + action[0] + " " + action[1]);
		}
//		int depth = 1;//Math.min(state.getWinCondition(), 5) * 2;
//		int maxScore = maxValue(state.getCopy(), depth, symbol);
//		System.out.println("Best score: " + maxScore);
//		for (IGameState st : this.getAllPossibleStates(state, symbol)) {
//			//log(st.toString(), true);
//			if (maxScore == minValue(state, depth-1, symbol)) {
//				return st;
//			}
//		}
//		System.exit(0);
		return state;
	}
	
	/**
	 * Returns triple [x, y, score]
	 * @param state
	 * @param depth
	 * @param maxPlayer
	 * @param symbol
	 * @return
	 */
	private int[] miniMax(IGameState state, int depth, boolean maxPlayer, PlayerSymbol symbol) {
		IReferee ref = this.referee;
		int[] bestScore = new int[]{-1,-1,0};
		int[] actualScore = new int[]{-1,-1,0};
		if (maxPlayer) {
			bestScore[2] = Integer.MIN_VALUE;
		} else {
			bestScore[2] = Integer.MAX_VALUE;
		}
		
		if (depth == 0 || ref.gameIsOver(state)) {
			bestScore[2] = ref.scoreState(state, symbol);
			if (depth == 0)
				log("Depth");
			else 
				log("Win " + (ref.isWonByPlayer(state, symbol) ? symbol : symbol.oposite()));
			return bestScore;
		}
		
		PlayerSymbol discoverSymbol = maxPlayer ? symbol : symbol.oposite();
		for (int[] move : getAllPossibleMoves(state, discoverSymbol)) {
			log("Move " + (discoverSymbol));
			state.setValueOfCell(discoverSymbol, move[0], move[1]);
			actualScore = miniMax(state, depth - 1, !maxPlayer, symbol);
			actualScore[0] = move[0];
			actualScore[1] = move[1];
//			if (ref.isWonByPlayer(state, discoverSymbol)) { // there is no need to discover something else
//				state.setValueOfCell(CellState.NOTHING, move[0], move[1]);
//				actualScore[2] = maxPlayer ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//				return actualScore;
//			}
//			if (ref.isWonByPlayer(state, discoverSymbol.oposite())) { // we have to save what we can
//				state.setValueOfCell(CellState.NOTHING, move[0], move[1]);
//				actualScore[2] = maxPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
//				return actualScore;
//			}
			state.setValueOfCell(CellState.NOTHING, move[0], move[1]);
			log("After Move " + (symbol));
			
			if (maxPlayer) {
				if (actualScore[2] >= bestScore[2]) {
					log("Set MAX " + actualScore[0] + " " + actualScore[1] + " " + actualScore[2]);
					bestScore = actualScore;
				}
			} else {
				if (actualScore[2] <= bestScore[2]) {
					log("Set MIN " + actualScore[0] + " " + actualScore[1] + " " + actualScore[2]);
					bestScore = actualScore;
				}
			}
		}
		if (bestScore[0] < 0 || bestScore[1] < 0) {
			bestScore[0] = actualScore[0];
			bestScore[1] = actualScore[1];
		}
		
		log("Returning " + bestScore[0] + " " + bestScore[1] + " for " + symbol);
		return bestScore;
	}
	
	private int maxValue(IGameState state, int depth, PlayerSymbol symbol) {
		IReferee ref = this.referee;
		int bestScore = Integer.MIN_VALUE;
		if (depth == 0 || ref.gameIsOver(state)) {
			return ref.scoreState(state, symbol);
		}
		PlayerSymbol discoverSymbol = symbol.oposite();
		for (int[] move : getAllPossibleMoves(state, discoverSymbol)) {
			log("Move " + (discoverSymbol));
			state.setValueOfCell(discoverSymbol, move[0], move[1]);
			bestScore = Math.max(bestScore, minValue(state, depth-1, symbol));
			state.setValueOfCell(CellState.NOTHING, move[0], move[1]);
			log("After Move " + (discoverSymbol));
		}
		return bestScore;
	}
	
	private int minValue(IGameState state, int depth, PlayerSymbol symbol) {
		IReferee ref = this.referee;
		int bestScore = Integer.MAX_VALUE;
		if (depth == 0 || ref.gameIsOver(state)) {
			return ref.scoreState(state, symbol);
		}
		PlayerSymbol discoverSymbol = symbol;
		for (int[] move : getAllPossibleMoves(state, discoverSymbol)) {
			log("Move " + (discoverSymbol));
			state.setValueOfCell(discoverSymbol, move[0], move[1]);
			bestScore = Math.min(bestScore, maxValue(state, depth-1, symbol));
			state.setValueOfCell(CellState.NOTHING, move[0], move[1]);
			log("After Move " + (discoverSymbol));
		}
		return bestScore;
	}
	
	private void log(String s) {
		if (false) {
			System.out.println(s);
		}
	}
	private void log(String s, boolean l) {
		if (l) {
			System.out.println(s);
		}
	}
}
