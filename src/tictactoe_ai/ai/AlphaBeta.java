package tictactoe_ai.ai;

import tictactoe_ai.game.CellState;
import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IReferee;
import tictactoe_ai.game.PlayerSymbol;

public class AlphaBeta extends AI {		
	
	public AlphaBeta(IReferee referee) {
		super(referee);
		this.name = "AlphaBeta";
	}

	public IGameState getNextState(IGameState state, PlayerSymbol symbol) {
		int[] action = aplhaBeta(state.getCopy(), Math.min(state.getWinCondition(), 5), true, symbol, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		log("\nSelected " + action[2] + "  " + action[0] + "  " + action[1]);
		if (action[0] >= 0 && action[1] >= 0)
			state.setValueOfCell(symbol, action[0], action[1]);
		else {
			System.out.println("-----Cannot set " + action[0] + " " + action[1]);
		}
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
	private int[] aplhaBeta(IGameState state, int depth, boolean maxPlayer, PlayerSymbol symbol, int alpha, int beta) {
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
			actualScore = aplhaBeta(state, depth - 1, !maxPlayer, symbol, alpha, beta);
			actualScore[0] = move[0];
			actualScore[1] = move[1];
			
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
			state.setValueOfCell(CellState.NOTHING, move[0], move[1]);
			log("After Move " + (symbol));
			
			if (maxPlayer) {
				if (bestScore[2] >= beta) {
					return bestScore;
				}
				alpha = Math.max(alpha, bestScore[2]);
			} else {
				if (bestScore[2] <= alpha) {
					return bestScore;
				}
				beta = Math.min(beta, bestScore[2]);
			}
		}
		if (bestScore[0] < 0 || bestScore[1] < 0) {
			bestScore[0] = actualScore[0];
			bestScore[1] = actualScore[1];
		}
		
		log("Returning " + bestScore[0] + " " + bestScore[1] + " for " + symbol);
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
