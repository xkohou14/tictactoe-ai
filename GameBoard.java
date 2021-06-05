import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;
public class GameBoard extends JFrame

{
	
	JButton play, reset;
    JComboBox slctplayer, metd;
	JLabel selectplayer, method, sizelabel, winlengthlabel, add;
	JTextField size , winlength;
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
	    
	        play=new JButton("PLAY");
            reset=new JButton("RESET");
	        
	        
	        size  = new JTextField();  
	        winlength = new JPasswordField();
	     

		
	        
	        selectplayer.setBounds(60 , 70, 150, 30);  
	       
	        method.setBounds(200 , 70, 150, 30);  
	        sizelabel.setBounds(60 , 200, 150, 30);
	        winlengthlabel.setBounds(200 , 200, 150, 30);
	        play.setBounds(270 , 400, 150, 30);
	        reset.setBounds(390 , 400, 150, 30);
	        
	        add(selectplayer);
	        add(method);
	        add(slctplayer);
	        add(metd);
	        add (sizelabel);
	        
	        add(size);
	        add(winlength);
	        add(winlengthlabel);
	        add(play);
	        add(reset);
	        
	        
	        
	        
	 
	     
	        
	        
	    	this.setVisible(true);  
	        this.setSize(1300, 1300);  
	        this.setLayout(null);  
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        this.setTitle("TicTacToe");  
	        
	        play.addActionListener(new ActionListener(){
	    		public void actionPerformed(ActionEvent ae) {
	    			
	    		 
	    		}});	
	    	
			
	       

	}
	
 
public static void main(String[] args) {
		
		
		
		GameBoard d= new GameBoard(); 
		
		
}
}