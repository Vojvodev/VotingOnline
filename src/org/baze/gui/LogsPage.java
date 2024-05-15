package org.baze.gui;

import java.awt.Color;
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

import org.baze.model.Log;

public class LogsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	

	/**
	 * Create the frame.
	 */
	public LogsPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.RED));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblApplicationLogs = new JLabel("Application Logs");
		lblApplicationLogs.setHorizontalAlignment(SwingConstants.CENTER);
		lblApplicationLogs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblApplicationLogs.setBounds(248, 10, 488, 64);
		contentPane.add(lblApplicationLogs);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 10, 113, 49);
		contentPane.add(btnBack);
		
		
	// -------------- POPULATING THE TABLE --------------------
		
		String[] columnNames = {"ID", "User", "Action", "Time"};
		
		DefaultTableModel model = new DefaultTableModel();
		
		
		// ADD HEADER
		for (String columnName : columnNames) {
            model.addColumn(columnName);
        }
		
		// ADD DATA
		for (Log log : FrontPageAdmin.logs) {
            model.addRow(new Object[]{log.getId(), log.getUsers_id(), log.getAction(), log.getTime()});
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
