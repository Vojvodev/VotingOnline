package org.baze.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.baze.dataaccess.ElectionDataAccess;
import org.baze.model.Election;

public class AllElectionsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllElectionsPage frame = new AllElectionsPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AllElectionsPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.RED));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 11, 113, 49);
		contentPane.add(btnBack);
		
		JLabel lblListOfAll = new JLabel("List of All Elections");
		lblListOfAll.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfAll.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblListOfAll.setBounds(258, 11, 488, 64);
		contentPane.add(lblListOfAll);
		

	
	
	// -------------- POPULATING THE TABLE --------------------
		
		String[] columnNames = {"ID", "Name", "Year", "Start", "End", "Active"};
		
		DefaultTableModel model = new DefaultTableModel();
		
		
		// ADD HEADER
		for (String columnName : columnNames) {
            model.addColumn(columnName);
        }
		
		// ADD DATA
		for (Election election : FrontPageAdmin.allElections) {
			model.addRow(new Object[]{election.getId(), election.getName(), election.getYear(), election.getStartDate(), election.getEndDate(), election.isActive()});
        }
		
		
		
		
		
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(96, 161, 804, 283);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		JLabel lblBottomText = new JLabel("Showing only active elections ...");
		lblBottomText.setHorizontalAlignment(SwingConstants.CENTER);
		lblBottomText.setBounds(605, 481, 204, 14);
		contentPane.add(lblBottomText);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShowAll.setBounds(811, 477, 89, 23);
		contentPane.add(btnShowAll);

				
				
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
				
				
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrontPageAdmin().setVisible(true);
				dispose();
			}
		});
		
		
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrontPageAdmin.allElections = ElectionDataAccess.selectAllElections();
				
				new AllElectionsPage().setVisible(true);
				dispose();
			}
		});
	
	
	
	
	
	
	}
	
}
