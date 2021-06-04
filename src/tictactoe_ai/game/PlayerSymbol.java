package tictactoe_ai.game;

public enum PlayerSymbol {
    CIRCLE(CellState.CIRCLE),
    CROSS(CellState.CROSS);

    private CellState aRef;
    private PlayerSymbol(CellState aRef) {
        this.aRef = aRef;
    }
    public CellState get() {
    	return this.aRef;
    }
    public PlayerSymbol oposite() {
    	if (this.aRef == CellState.CIRCLE)
    		return CROSS;
    	return CIRCLE;
    }
}
