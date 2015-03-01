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

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JComboBox;


public class GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Vector<Vector> rowData;
	
	public JPanel getContentPane(){
		return contentPane;
	}
	
	public JTable getTable(){
		return table;
	}
		
	public GUI(Game game) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/kingpin logo.png")));
		setTitle("Kingpin - Bowling Score Keeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1981, 1083);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setBounds(21, 21, 141, 35);
		btnAddPlayer.addActionListener(game);
		contentPane.add(btnAddPlayer);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(221, 21, 141, 35);
		btnStartGame.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  btnAddPlayer.setEnabled(false);
			  }
		});
		btnStartGame.addActionListener(game);
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
		scrollPane.setBounds(70, 104, 1828, 480);
	    contentPane.add(scrollPane);
	    
	    
	    
	}
}
