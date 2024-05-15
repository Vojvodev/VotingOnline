package org.baze.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.baze.model.Candidate;

public class VotingListPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public VotingListPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLUE));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 10, 113, 49);
		contentPane.add(btnBack);
		
		JLabel lblTitle = new JLabel("List of All Candidates");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitle.setBounds(279, 10, 488, 64);
		contentPane.add(lblTitle);
		
		
		
	// -------------- POPULATING THE TABLE --------------------
	
		String[] columnNames = {"First Name", "Last Name", "Party", "Created At"};
		
		DefaultTableModel model = new DefaultTableModel();
		
		
		// ADD HEADER
		for (String columnName : columnNames) {
            model.addColumn(columnName);
        }
		
		// ADD DATA
		for (Candidate candidate : FrontPageUser.votingListCandidates) {
            model.addRow(new Object[]{candidate.getFname(), candidate.getLname(), candidate.getPartyName(), candidate.getCreatedAt()});
        }
		
		
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(96, 161, 804, 283);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
	
	
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrontPageUser().setVisible(true);
				dispose();
			}
		});
		
		
		
		
		
	}
}
