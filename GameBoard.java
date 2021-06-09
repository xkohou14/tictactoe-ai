package tictactoe_ai.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import tictactoe_ai.game.GameState;
import tictactoe_ai.game.IGameState;
import tictactoe_ai.game.PlayerSymbol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class GameBoard extends JFrame

{
	
	JButton play, reset, add;
    JComboBox slctplayer, metd;
	JLabel selectplayer, method, sizelabel, winlengthlabel;
	JTextField size , winlength;
	public JFrame frame;
	GameBoard()
	{
		String s1 [] = {"Human player", "Bot"};
		String s2 [] = {"Minimax", "Alpha-beta pruning"};
		
		slctplayer= new JComboBox (s1);
		metd= new JComboBox (s2);
		
		selectplayer = new JLabel("Select your player");  
		selectplayer.setForeground(Color.blue);  
	       
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

        selectplayer.setBounds(60 , 70, 150, 30);  
       
        method.setBounds(760 , 70, 150, 30);  
        sizelabel.setBounds(60 , 200, 500, 30);
        winlengthlabel.setBounds(700 , 200, 500, 30);
        play.setBounds(270 , 400, 150, 30);
        reset.setBounds(500 , 400, 150, 30);
        add.setBounds(900 , 400, 150, 30);
        size.setBounds(250, 200, 150, 30);
        winlength.setBounds(900 , 200, 150, 30);
        slctplayer.setBounds(250 , 70, 150, 30);
        metd.setBounds(900 , 70, 150, 30);
        
        add(selectplayer);
        add(method);
        add(slctplayer);
        add(metd);
        add(sizelabel);
        
        add(size);
        add(winlength);
        add(winlengthlabel);
        add(play);
        add(reset);
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
    			JPanel panel = new JPanel();
    	        JFrame f = new JFrame("Game");
    	        List<JButton> btns = new ArrayList<JButton>();
    	        
    			try {
    				gridSize = Integer.parseInt(size.getText().toString());
    			} catch (Exception e) {
					e.printStackTrace(); 
				}
    			panel.setLayout(new GridLayout(gridSize, 
    					gridSize, 
    					hgap, vgap)); // = new JPanel();
    			GameState state = new GameState(gridSize, gridSize);
    			for(int i=0;i<gridSize;i++)
    	        {
    	            for(int j=0;j<gridSize;j++)
    	            {
    	                JButton btn=new JButton();
    	                //btn.setSize(150,150);
    	                btns.add(btn);
    	                final int x = i;
    	                final int y = j;
    	                btn.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								clickBtn(x, y, PlayerSymbol.CIRCLE, state, btns);
								btn.setEnabled(false);
							}
						});
    	                panel.add(btn);
    	                
    	                System.out.println("Btn");
    	            }
    	        }
    			f.add(panel);
    			f.pack();
    			f.setVisible(true);
    		}}
        );	
        
        
        reset.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent ae) {
    			size.setText("10");
    			winlength.setText("10");
    		}
    	});	
	    	
	    	

	}
	public void setstate (IGameState st, List<JButton> btns)
	{
		for(int x = 0; x < st.getBoardSize(); x++) {
			for(int y = 0; y < st.getBoardSize(); y++) {
				String s = "";
				switch (st.getValueOfCell(x, y)) {
				case CIRCLE:
					s = "O";
					break;
				case CROSS:
					s = "X";
					break;
				case NOTHING:
					s = " ";
					break;

				default:
					break;
				}
				btns.get(x*st.getBoardSize() + y).setText(s);
			}
		}
	}
	public void clickBtn(int x,int y,PlayerSymbol s, IGameState st, List<JButton> btns) {
		st.setValueOfCell(s, x, y);
		setstate(st, btns);
	}
   
 
public static void main(String[] args) {
		
		
		
		GameBoard d= new GameBoard(); 
		
		
}
}