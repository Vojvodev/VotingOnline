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

import org.baze.model.User;

public class AllUsersPage extends JFrame {

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
					AllUsersPage frame = new AllUsersPage();
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
	public AllUsersPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.RED));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListOfAll = new JLabel("List of All Users");
		lblListOfAll.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfAll.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblListOfAll.setBounds(259, 11, 488, 64);
		contentPane.add(lblListOfAll);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 11, 113, 49);
		contentPane.add(btnBack);
		
	
		
		
	// -------------- POPULATING THE TABLE --------------------
		
		String[] columnNames = {"ID", "First Name", "Last Name", "Username", "Email", "Voted", "Address", "Created At"};
		
		DefaultTableModel model = new DefaultTableModel();
		
		
		// ADD HEADER
		for (String columnName : columnNames) {
            model.addColumn(columnName);
        }
		
		// ADD DATA
		for (User user : FrontPageAdmin.allUsers) {
            model.addRow(new Object[]{
            							user.getId(), user.getFname(), user.getLname(), 
            							user.getUsername(), user.getEmail(), user.isVoted(), 
            							user.getAddress(), user.getCreatedAt()}
            			);
        }
		
		
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(96, 161, 804, 283);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);

	
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrontPageAdmin().setVisible(true);
				dispose();
			}
		});
		
		
	}
}
