import java.awt.EventQueue;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Game implements ActionListener{
	private Player[] players;
	private int frameNumber = 0;
	private int playerNumber = 0;
	private int playerCount = 0;
	private GUI gui; //The user interface (view)
	private final int maxPlayers = 6; //The maximum number of players allowed in the game
	
	
	Game(){
		players = new Player[maxPlayers];
		gui = new GUI(this);
		gui.setVisible(true);
		
	}
	
	
	private void addPlayer(String n){
		players[playerCount]=new Player(n);
		playerCount++;
	}
	
	//Refreshes the view to show the latest scores in the table
	private void refreshTable(){
		//cycle through each frame for each player
		for(int i = 0;i<playerNumber+1;i++){
			Player player = players[i];
			for(int j = 0;j<frameNumber+1;j++){
				JTable table = gui.getTable();
				Frame frame = player.getFrame(j);
				String scores = new String(frame.getTotal() + " (" + frame.getScore1()+","+frame.getScore2()+"+" + frame.getBonus()+ ")");
				table.setValueAt(scores, i, j+1); 
			}
		}
		
	}
	
	
	private void playFrame(){
		int num_input;
		
		//for each player in the game
		while(playerNumber<playerCount){
						
			num_input =  Integer.parseInt(JOptionPane.showInputDialog(players[playerNumber].getName()+ ":\n Frame: " + (frameNumber+1) + "\nEnter number of pins hit (first throw):"));	

			players[playerNumber].addBonusPoints(num_input);
			players[playerNumber].setFrameScore(frameNumber,1,num_input);
			
			if(num_input==10){
				if(frameNumber==9){
					
					num_input= Integer.parseInt(JOptionPane.showInputDialog(players[playerNumber].getName()+ ":\nBonus Ball 1 - Enter number of pins hit:"));
					players[playerNumber].addBonusPoints(num_input);
					num_input= Integer.parseInt(JOptionPane.showInputDialog(players[playerNumber].getName()+ ":\nBonus Ball 2 - Enter number of pins hit:"));
					players[playerNumber].addBonusPoints(num_input);
				}
			}
			else{
				num_input= Integer.parseInt(JOptionPane.showInputDialog(players[playerNumber].getName()+ ":\n Frame: " + (frameNumber+1) + "\nEnter number of pins hit (second throw):"));
				players[playerNumber].addBonusPoints(num_input);
				players[playerNumber].setFrameScore(frameNumber,2,num_input);
				if(frameNumber==9 && players[playerNumber].getFrame(frameNumber).getTotal()==10){
					num_input= Integer.parseInt(JOptionPane.showInputDialog(players[playerNumber].getName()+ ":\nBonus Ball - Enter number of pins hit:"));
					players[playerNumber].addBonusPoints(num_input);
				}
			}
			
			refreshTable();
			
			playerNumber++;
		}
		playerNumber=0;
		frameNumber++;
	}
	
	//Find the highest scoring player(s) and create a string for announcing
	private String getWinnerString(){
		int maxScore=0;
		
		String winner = "";
		//find highest scoring player
		for(int i=0;i<playerCount;i++){
			if(players[i].getTotalScore()>maxScore){
				maxScore=players[i].getTotalScore();
				winner = players[i].getName();
				
			}
		}
		//find any equally high scoring players
		for(int i=0;i<playerCount;i++){
			if(players[i].getTotalScore()==maxScore&&!(players[i].getName().equals(winner))){
				winner += " and ";
				winner += players[i].getName();
				
			}
				
		}
		String winnerString = new String("Congratulations " +winner+", you are the winner(s) with a score of "+maxScore);
		return winnerString;
	}
	
	
	private void startGame(){
		while(frameNumber<10){
			playFrame();
		}
		
		JOptionPane.showMessageDialog(gui,
				getWinnerString(),
			    "Game Over",
			    JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public static void main(String[] args) {
		new Game();		
	}

	//handle button presses on interface
	public void actionPerformed(ActionEvent e) {
		
		JButton src = (JButton)e.getSource();
		
		if(src.getText() == "Add Player"){
			String s;
	    	s = JOptionPane.showInputDialog("Enter Player Name");
	    	String blank = new String("");
			if ((s != null) && (s.length() > 0)) { //if input was put in
				addPlayer(s);
				Vector<String> row = new Vector<String>();
		    	row.addElement(s); //add name to name cell
		    	for(int i = 0;i<11;i++)
		    		row.addElement(blank); //add the empty frame score cells

		    	DefaultTableModel tableModel = (DefaultTableModel) gui.getTable().getModel();
		    	tableModel.addRow(row);
		    	
		    	//don't allow more than the the maximum number of players
		    	if(playerCount==maxPlayers){
		    		src.setEnabled(false); //disable add player button
		    		startGame();
		    	}
			}
		}
		
		if(src.getText() == "Start Game"){
			startGame();
		}
		
		
		
	}

}
