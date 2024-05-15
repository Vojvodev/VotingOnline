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

public class UpdateCandidatePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CandidateService candidateService = new CandidateService();
	
	private String notification = "";
	private JTextField textFieldID;
	private JTextField textFieldFname;
	private JTextField textFieldLname;
	private JTextField textFieldPartyId;

	/**
	 * Create the frame.
	 */
	public UpdateCandidatePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.RED));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Update A Candidate");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitle.setBounds(184, 11, 588, 64);
		contentPane.add(lblTitle);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 11, 113, 49);
		contentPane.add(btnBack);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(427, 75, 152, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblCandidateId = new JLabel("ID");
		lblCandidateId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCandidateId.setHorizontalAlignment(SwingConstants.CENTER);
		lblCandidateId.setBounds(400, 73, 32, 20);
		contentPane.add(lblCandidateId);
		
		textFieldFname = new JTextField();
		textFieldFname.setColumns(10);
		textFieldFname.setBounds(222, 154, 501, 37);
		contentPane.add(textFieldFname);
		
		textFieldLname = new JTextField();
		textFieldLname.setColumns(10);
		textFieldLname.setBounds(222, 253, 501, 37);
		contentPane.add(textFieldLname);
		
		textFieldPartyId = new JTextField();
		textFieldPartyId.setColumns(10);
		textFieldPartyId.setBounds(222, 353, 501, 37);
		contentPane.add(textFieldPartyId);
		
		JLabel lblFname = new JLabel("First Name");
		lblFname.setHorizontalAlignment(SwingConstants.CENTER);
		lblFname.setBounds(418, 129, 126, 14);
		contentPane.add(lblFname);
		
		JLabel lblLname = new JLabel("Last Name");
		lblLname.setHorizontalAlignment(SwingConstants.CENTER);
		lblLname.setBounds(418, 228, 126, 14);
		contentPane.add(lblLname);
		
		JLabel lblPartyId = new JLabel("Party ID");
		lblPartyId.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartyId.setBounds(418, 328, 126, 14);
		contentPane.add(lblPartyId);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(418, 413, 126, 29);
		contentPane.add(btnUpdate);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(260, 466, 442, 34);
		contentPane.add(lblNotification);
		
		
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new FrontPageAdmin().setVisible(true);
				dispose();
			}
		});
		
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fname	= textFieldFname.getText();
				String lname	= textFieldLname.getText();
				
				int candidateId = -1;
				if(textFieldID.getText() != null && !"".equals(textFieldID.getText())) {
					candidateId = Integer.parseInt(textFieldID.getText());
				}
				
				int partyId		= -1;
				if(textFieldPartyId.getText() != null && !"".equals(textFieldPartyId.getText())) {
					partyId = Integer.parseInt(textFieldPartyId.getText());
				}
				
				
				if(fname == null || lname == null || partyId < 1 || 
						"".equals(fname) || "".equals(lname)) {
					notification = "Could Not Update Candidate!";
				}
				else if(candidateService.selectByFnameAndLname(fname, lname) != null) {
					notification = "Candidate Already Exists!";
				}else {
					if(candidateService.updateCandidate(candidateId, fname, lname, partyId)) {
						notification = "Candidate Successfully Updated!";
					}
					else {
						notification = "Could Not Update Candidate!";
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
