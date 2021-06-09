package tictactoe_ai.ai;

import java.util.List;
import java.util.Random;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import tictactoe_ai.game.CellState;
import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IReferee;
import tictactoe_ai.game.PlayerSymbol;
import tictactoe_ai.game.Referee;

public class MinMaxAI extends AI {		
	
	public MinMaxAI(IReferee referee) {
		super(referee);
		this.name = "MinMaxAI - minimax";
	}

	public IGameState getNextState(IGameState state, PlayerSymbol symbol) {
//		List<IGameState> sts = getAllPossibleStates(state, symbol);
//		IGameState maxState = sts.get(0);
//		int maxValue = this.referee.scoreState(maxState, symbol);
//		for(IGameState s : sts) {
//			int stateScore = miniMax(s, 5, false, symbol.oposite());			
//			if (stateScore > maxValue) {
//				System.out.print(" " + stateScore);
//				maxState = s;
//				maxValue = stateScore;
//			}
//		}
		int[] action = miniMax(state.getCopy(), state.getWinCondition(), true, symbol);
		
		System.out.println("\nSelected " + action[2] + "  " + action[0] + "  " + action[1]);
		state.setValueOfCell(symbol, action[0], action[1]);
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
		//System.out.println("Running minimax depth " + depth);
		IReferee ref = this.referee;
		int[] bestScore = new int[]{-1,-1,0};
		int[] actualScore;
		if (maxPlayer) {
			bestScore[2] = Integer.MIN_VALUE;
		} else {
			bestScore[2] = Integer.MAX_VALUE;
		}
		
		if (depth == 0 || ref.gameIsOver(state)) {
			bestScore[2] = ref.scoreState(state, symbol);
			if (depth == 0)
				System.out.println("Depth");
			else 
				System.out.println("Win " + (ref.isWonByPlayer(state, symbol) ? symbol : symbol.oposite()));
			return bestScore;
		}
		
		for (int[] move : getAllPossibleMoves(state, maxPlayer ? symbol : symbol.oposite())) {
			System.out.println("Move " + (maxPlayer ? symbol : symbol.oposite()));
			state.setValueOfCell(symbol, move[0], move[1]);
			actualScore = miniMax(state, depth - 1, !maxPlayer, symbol);
			actualScore[0] = move[0];
			actualScore[1] = move[1];
			state.setValueOfCell(CellState.NOTHING, move[0], move[1]);
			System.out.println("After Move " + (maxPlayer ? symbol : symbol.oposite()));
			
			if (maxPlayer) {
				if (actualScore[2] > bestScore[2]) {
					System.out.println("Set MAX " + actualScore[0] + " " + actualScore[1]);
					bestScore = actualScore;
				}
			} else {
				if (actualScore[2] < bestScore[2]) {
					System.out.println("Set MIN " + actualScore[0] + " " + actualScore[1]);
					bestScore = actualScore;
				}
			}
		}
		
		System.out.println("Returning " + bestScore[0] + " " + bestScore[1]);
		return bestScore;
	}
}
