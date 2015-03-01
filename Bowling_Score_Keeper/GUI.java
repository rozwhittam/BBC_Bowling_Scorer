import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JComboBox;


public class GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Vector<Vector> rowData;
	private JButton btnAddPlayer;
	private JButton btnStartGame;
	
	public JPanel getContentPane(){
		return contentPane;
	}
	
	public JTable getTable(){
		return table;
	}
	
	public void disableAddPlayer(){
		btnAddPlayer.setEnabled(false);
	}
	
	public void enableStartGame(){
		btnStartGame.setEnabled(true);
	}

	//Ask for input with Dialog Box and validate it
	public int getUserInput(String message, int total){
		int input=-1;
		Boolean valid;
		
		do{			
			valid=true;
			try{
				input =  Integer.parseInt(JOptionPane.showInputDialog(message));	
				//Check input is positive
				if(input<0){
					JOptionPane.showMessageDialog(null,
							"You entered an incorrect number!\nEnter a Number between 0 and 10 for the score!",
						    "That's not a score!",
						    JOptionPane.PLAIN_MESSAGE);
					valid=false;
				}
				//Check total doesn't go over ten
				if((input+total)>10){
					JOptionPane.showMessageDialog(null,
							"You entered an incorrect number!\nYou can't knock down more than ten pins!",
						    "That's not a score!",
						    JOptionPane.PLAIN_MESSAGE);
					valid=false;
				}
			
			//Check input is a number
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null,
						"You did not enter a number!\nEnter a Number between 0 and 10 for the score!",
					    "That's not a score!",
					    JOptionPane.PLAIN_MESSAGE);
				valid=false;
			}
			
			
		}while(valid==false);
		
		return input;
	}
	
	public GUI(Game game) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/kingpin logo.png")));
		setTitle("Kingpin - Bowling Score Keeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1975, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setBounds(21, 21, 141, 35);
		btnAddPlayer.addActionListener(game);
		contentPane.add(btnAddPlayer);
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(221, 21, 141, 35);
		btnStartGame.addActionListener(game);
		btnStartGame.setEnabled(false);
		contentPane.add(btnStartGame);
	    
	    rowData = new Vector<Vector>();
	    
	    Vector<String> columnNames = new Vector<String>();
	    columnNames.addElement("Name");
	    columnNames.addElement("Frame 1");
	    columnNames.addElement("Frame 2");
	    columnNames.addElement("Frame 3");
	    columnNames.addElement("Frame 4");
	    columnNames.addElement("Frame 5");
	    columnNames.addElement("Frame 6");
	    columnNames.addElement("Frame 7");
	    columnNames.addElement("Frame 8");
	    columnNames.addElement("Frame 9");
	    columnNames.addElement("Frame 10");
	    table = new JTable(rowData, columnNames);
  
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(68, 113, 1826, 226);
	    contentPane.add(scrollPane);
	    
	    
	    
	}
}
