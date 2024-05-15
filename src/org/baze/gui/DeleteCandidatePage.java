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

import org.baze.dataaccess.LogsDataAccess;
import org.baze.model.Log;
import org.baze.service.CandidateService;

public class DeleteCandidatePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CandidateService candidateService = new CandidateService();
	
	private String notification = "";
	private JTextField textFieldId;

	/**
	 * Create the frame.
	 */
	public DeleteCandidatePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.RED));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteACandidate = new JLabel("Delete A Candidate");
		lblDeleteACandidate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteACandidate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblDeleteACandidate.setBounds(196, 11, 588, 64);
		contentPane.add(lblDeleteACandidate);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 11, 113, 49);
		contentPane.add(btnBack);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(290, 367, 442, 34);
		contentPane.add(lblNotification);
		
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(414, 149, 190, 49);
		contentPane.add(textFieldId);
		
		JLabel lblCandidateId = new JLabel("ID");
		lblCandidateId.setHorizontalAlignment(SwingConstants.CENTER);
		lblCandidateId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCandidateId.setBounds(350, 147, 69, 51);
		contentPane.add(lblCandidateId);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(432, 289, 156, 42);
		contentPane.add(btnDelete);
		
		
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new FrontPageAdmin().setVisible(true);
				dispose();
			}
		});
		
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int candidateId = -1; 
				if(textFieldId.getText() != null && !"".equals(textFieldId.getText())) {
					candidateId = Integer.parseInt(textFieldId.getText());
				}
				
				if(candidateId < 1) {
					notification = "Could Not Delete Candidate!";
				}
				else if(candidateService.selectById(candidateId) == null) {
					notification = "Candidate Does Not Exist!";
				}
				else {
					if(candidateService.deleteCandidate(candidateId)) {
						notification = "Candidate Successfully Deleted!";
					}
					else {
						notification = "Could Not Delete Candidate!";
					}
				}
				
				
				lblNotification.setText(notification);
				
				if(notification != null && !"".equals(notification)) {
					Log log = new Log(WelcomePage.currentUser.getId(), notification);
					LogsDataAccess.createLog(log);	
				}
				
			}
		});
		
		
	}
}
