package tictactoe_ai.gui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import tictactoe_ai.ai.AI;
import tictactoe_ai.ai.AlphaBeta;
import tictactoe_ai.ai.MinMaxAI;
import tictactoe_ai.game.GameState;
import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.IReferee;
import tictactoe_ai.game.Player;
import tictactoe_ai.game.PlayerSymbol;
import tictactoe_ai.game.Referee;
import tictactoe_ai.game.Referee2;
import tictactoe_ai.game.Referee3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class GameBoard extends JFrame
{
	
	JButton play, reset, add;
    JComboBox slctplayer;
    JRadioButton minimax, alphabeta;
   
   
	JLabel selectplayer, method, sizelabel, winlengthlabel, humanplayer;
	JTextField size , winlength;
	public JFrame frame;
	GameBoard()
	{
		
		String s2 [] = {"Minimax", "Alpha-beta pruning"};
		
	
	
		
		selectplayer = new JLabel("Player:");  
		selectplayer.setForeground(Color.blue);  
		
		humanplayer = new JLabel("HUMAN PLAYER");  
		humanplayer.setForeground(Color.blue);
		
	       
		sizelabel = new JLabel("Select your board size");
		winlengthlabel = new JLabel("Select your win condition");
	        
		method = new JLabel("Choose method");  
		method.setForeground(Color.blue);  
		sizelabel.setForeground(Color.red);
		winlengthlabel.setForeground(Color.red);
	    
        play=new JButton("PLAY");
        reset=new JButton("RESET");
        add=new JButton("add");    
        play.setBackground(Color.GREEN);
        reset.setBackground(Color.BLUE);
        
        size  = new JTextField();  
        winlength = new JTextField();
        //radiobuttons
        minimax= new JRadioButton("Minimax");
        alphabeta = new JRadioButton("Alpha-Beta");
        
        ButtonGroup group = new ButtonGroup();
        group.add(minimax);
        group.add(alphabeta);
        minimax.setSelected(true);

        selectplayer.setBounds(60 , 70, 150, 30);  
       
        method.setBounds(760 , 70, 150, 30);  
        sizelabel.setBounds(60 , 200, 500, 30);
        winlengthlabel.setBounds(700 , 200, 500, 30);
        play.setBounds(270 , 400, 150, 30);
        reset.setBounds(500 , 400, 150, 30);
        add.setBounds(900 , 400, 150, 30);
        size.setBounds(250, 200, 150, 30);
        size.setText("3");
        winlength.setBounds(900 , 200, 150, 30);
        winlength.setText("3");
        humanplayer.setBounds(250 , 70, 150, 30);
        minimax.setBounds(900 , 70, 180, 30); 
        alphabeta.setBounds(900 , 100, 180, 30); 
    
        
        add(selectplayer);
        add(method);
        add(humanplayer);

        add(sizelabel);
        
        add(size);
        add(winlength);
        add(winlengthlabel);
        add(play);
        add(reset);
        add(minimax);
        add(alphabeta);
        add(add);    
     
	        
    	this.setVisible(true);  
        this.setSize(1300, 1300);  
        this.setLayout(null);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setTitle("TicTacToe");  
        
        int hgap = 0 ; int vgap = 0; 
        pack();
        setVisible(true);
        
        JFrame instance = this;
        
        play.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae) {
    			int gridSize = 3;
    			int winC = 3;
    			JPanel panel = new JPanel();
    	        JFrame f = new JFrame("Game");
    	        List<JButton> btns = new ArrayList<JButton>();
    	        
    			try {
    				gridSize = Integer.parseInt(size.getText().toString());
    				winC = Integer.parseInt(winlength.getText().toString());
    			} catch (Exception e) {
					e.printStackTrace(); 
				}
    			panel.setLayout(new GridLayout(gridSize, 
    					gridSize, 
    					hgap, vgap)); // = new JPanel();
    			Referee3 ref = new Referee3();
    			AI ai;
    			if (minimax.isSelected()) {
    				ai = new MinMaxAI(ref);
    			} else {
    				ai = new AlphaBeta(ref);
    			}
    			final Player ai_player = new Player(ai);
    			GameState state = new GameState(gridSize, winC);
    			for(int i=0;i<gridSize;i++)
    	        {
    	            for(int j=0;j<gridSize;j++)
    	            {
    	                JButton btn=new JButton();
    	                btns.add(btn);
    	                final int x = i;
    	                final int y = j;
    	                btn.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								clickBtn(x, y, PlayerSymbol.CIRCLE, state, btns);
								btn.setEnabled(false);
								ai_player.play(state, PlayerSymbol.CROSS);
								setBoardState(state, btns);
								handleWin(state, btns, ref);
							}
						});
    	                panel.add(btn);
    	                
    	                System.out.println("Btn");
    	            }
    	        }    			
    			f.setSize(gridSize * 150, gridSize * 150);
    			f.add(panel);
    			f.pack();
    			f.setVisible(true);
    			setBoardState(state, btns);
    			//playAis(ai_player, new Player(ai), state, btns, ref);
    		}}
        );	
        
        
        reset.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae) {
    			size.setText("10");
    			winlength.setText("10");
    		}
    	});	
	    	
	    	

	}
	public void setBoardState (IGameState st, List<JButton> btns)
	{
		for(int x = 0; x < st.getBoardSize(); x++) {
			for(int y = 0; y < st.getBoardSize(); y++) {
				JButton btn = btns.get(x*st.getBoardSize() + y);
				String s = "";
				switch (st.getValueOfCell(x, y)) {
				case CIRCLE:
					s = "O";
					btn.setEnabled(false);
					break;
				case CROSS:
					s = "X";
					btn.setEnabled(false);
					break;
				case NOTHING:
					s = " ";
					break;

				default:
					break;
				}
				btn.setText(s);
			}
		}
	}
	public void clickBtn(int x,int y,PlayerSymbol s, IGameState st, List<JButton> btns) {
		st.setValueOfCell(s, x, y);
		setBoardState(st, btns);
	}
	
	public void playAis(Player p1, Player p2, IGameState state, List<JButton> btns, IReferee ref) {
		try {			
			boolean p1Turn = true;
			while(!ref.gameIsOver(state)) {
				if (p1Turn) {
					System.out.println("PLaying CIRCLE p1");
					state = (GameState) p1.play(state, PlayerSymbol.CIRCLE);
				} else {
					System.out.println("PLaying CROSS p2");
					state = (GameState) p1.play(state, PlayerSymbol.CROSS);
				}
				setBoardState(state, btns);
				TimeUnit.SECONDS.sleep(1);
				p1Turn = !p1Turn;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleWin(IGameState state, List<JButton> btns, Referee ref) {
		if (ref.isWonByPlayer(state, PlayerSymbol.CIRCLE)) {
			for (List<int[]> line : ref.getAllLines(state, PlayerSymbol.CIRCLE)) {
				if (line.size() == state.getWinCondition()) {
					for (int[] pos : line) {
						JButton btn = btns.get(pos[0]*state.getBoardSize() + pos[1]);
						btn.setBackground(Color.GREEN);
					}
				}
			}
		}
		if (ref.isWonByPlayer(state, PlayerSymbol.CROSS)) {
			for (List<int[]> line : ref.getAllLines(state, PlayerSymbol.CROSS)) {
				if (line.size() == state.getWinCondition()) {
					for (int[] pos : line) {
						JButton btn = btns.get(pos[0]*state.getBoardSize() + pos[1]);
						btn.setBackground(Color.RED);
					}
				}
			}
		}
	}
   
 
	public static void main(String[] args) {			
		new GameBoard(); 			
	}
}