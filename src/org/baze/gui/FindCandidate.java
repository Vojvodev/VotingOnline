package org.baze.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.baze.dataaccess.CandidateDataAccess;
import org.baze.model.Candidate;

public class FindCandidate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSearch;

	private String notification = "";

	/**
	 * Create the frame.
	 */
	public FindCandidate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLUE));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Learn About Candidates");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(246, 21, 514, 60);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 11, 113, 49);
		contentPane.add(btnBack);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldSearch.setBounds(459, 106, 305, 34);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Search For (name)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(246, 106, 200, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblFname = new JLabel("");
		lblFname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFname.setBounds(38, 215, 163, 34);
		contentPane.add(lblFname);
		
		JLabel lblLname = new JLabel("");
		lblLname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLname.setBounds(180, 215, 231, 34);
		contentPane.add(lblLname);
		
		JLabel lblPartyName = new JLabel("");
		lblPartyName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPartyName.setBounds(38, 296, 373, 34);
		contentPane.add(lblPartyName);
		
		JLabel lblCreatedAt = new JLabel("");
		lblCreatedAt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCreatedAt.setBounds(659, 215, 231, 34);
		contentPane.add(lblCreatedAt);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearch.setBounds(774, 106, 86, 32);
		contentPane.add(btnSearch);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(309, 187, 442, 34);
		contentPane.add(lblNotification);
		
		JTextArea textAreaAbout = new JTextArea();
		textAreaAbout.setLineWrap(true);
		textAreaAbout.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textAreaAbout.setBounds(38, 338, 890, 148);
		contentPane.add(textAreaAbout);
		
		JLabel lblSlogan = new JLabel("");
		lblSlogan.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblSlogan.setBounds(459, 298, 467, 32);
		contentPane.add(lblSlogan);
		
		
		
		
		
		
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrontPageUser().setVisible(true);
				dispose();
			}
		});
		

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldSearch.getText();
				
				
				if(name != null && !"".equals(name)) {
					Candidate candidate = CandidateDataAccess.searchByName(name);
					
					if(candidate != null) {
						lblFname.setText(candidate.getFname());
						lblLname.setText(candidate.getLname());
						lblCreatedAt.setText("Joined On: " + candidate.getCreatedAt());
						lblSlogan.setText(CandidateDataAccess.getSlogan(candidate.getPartiesId()));
						
						
						String partyName = candidate.getPartyName();
						String partyAbout = candidate.getPartyAbout();
						
						lblPartyName.setText(partyName);
						textAreaAbout.setText(partyAbout);
						
						notification = "";
					}
					else {
						notification = "Could Not Find Candidate!";
					}
					
					lblNotification.setText(notification);
					
				}
				
			}
		});
		
		
		
	}
}
