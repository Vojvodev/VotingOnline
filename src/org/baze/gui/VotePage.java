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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.baze.dataaccess.CandidateDataAccess;
import org.baze.dataaccess.ElectionDataAccess;
import org.baze.dataaccess.UserDataAccess;
import org.baze.dataaccess.VoteDataAccess;
import org.baze.model.Candidate;
import org.baze.model.Election;

public class VotePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCandidateId;
	private JTextField textFieldElectionsId;

	private String notification = "";

	/**
	 * Create the frame.
	 */
	public VotePage() {
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
		
		JLabel lblNewLabel = new JLabel("Place Your Vote");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 46));
		lblNewLabel.setBounds(270, 10, 450, 56);
		contentPane.add(lblNewLabel);
		
		textFieldCandidateId = new JTextField();
		textFieldCandidateId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textFieldCandidateId.setBounds(340, 238, 145, 49);
		contentPane.add(textFieldCandidateId);
		textFieldCandidateId.setColumns(10);
		
		textFieldElectionsId = new JTextField();
		textFieldElectionsId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textFieldElectionsId.setBounds(512, 238, 145, 49);
		contentPane.add(textFieldElectionsId);
		textFieldElectionsId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Candidate ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(340, 206, 139, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Elections ID");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(512, 206, 139, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnVote = new JButton("Vote");
		btnVote.setFont(new Font("Tahoma", Font.PLAIN, 36));
		btnVote.setBounds(370, 360, 270, 66);
		contentPane.add(btnVote);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(296, 456, 442, 34);
		contentPane.add(lblNotification);
		
		
		
		
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrontPageUser().setVisible(true);
				dispose();
			}
		});
				
		
		// TODO: PREBACITI U SERVIS METODU KOJA VRACA STRING notification
		btnVote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userId		= WelcomePage.currentUser.getId();
				int candidateId	= -1;		Candidate candidate = new Candidate();
				int electionId	= -1;		Election election = new Election();
				
				if(textFieldCandidateId.getText() != null && !"".equals(textFieldCandidateId.getText())) {
					candidateId = Integer.parseInt(textFieldCandidateId.getText());
					candidate = CandidateDataAccess.getById(candidateId);
				}
				
				if(textFieldElectionsId.getText() != null && !"".equals(textFieldElectionsId.getText())) {
					electionId = Integer.parseInt(textFieldElectionsId.getText());
					election = ElectionDataAccess.getById(electionId);
				}
				
				
				if(userId < 1 || candidateId < 1 || electionId < 1) {
					notification = "Could Not Save Vote!";
				}
				else if(candidate == null) {
					notification = "Could Not Find Candidate!";
				}
				else if(election == null) {
					notification = "Could Not Find Election!";
				}
				else if(VoteDataAccess.selectByUserAndElection(userId, electionId) != null) {
					notification = "You have Already Placed your Vote!";
				}
				else if(!election.isActive()) {
					notification = "Elections Are Not Active!";
				}
				else {
					if(VoteDataAccess.createVote(userId, candidateId, electionId)) {
						notification = "Vote Successfully Created!";
						UserDataAccess.setVoted(userId, true);
					}
					else {
						notification = "Could Not Save Vote!";
					}
				}
				
				
				lblNotification.setText(notification);
				
				
			}
		});
		
		
		
		
	}
}
