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


public class AddCandidatePage extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldFname;
	private JTextField textFieldLname;
	private JTextField textFieldPartyid;
	private CandidateService candidateService = new CandidateService();
	
	private String notification = "";
	
	/**
	 * Create the frame.
	 */
	public AddCandidatePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.RED));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Create A New Candidate");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblTitle.setBounds(207, 11, 588, 64);
		contentPane.add(lblTitle);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 11, 113, 49);
		contentPane.add(btnBack);
		
		textFieldFname = new JTextField();
		textFieldFname.setBounds(244, 163, 501, 37);
		contentPane.add(textFieldFname);
		textFieldFname.setColumns(10);
		
		textFieldLname = new JTextField();
		textFieldLname.setColumns(10);
		textFieldLname.setBounds(244, 262, 501, 37);
		contentPane.add(textFieldLname);
		
		textFieldPartyid = new JTextField();
		textFieldPartyid.setColumns(10);
		textFieldPartyid.setBounds(244, 362, 501, 37);
		contentPane.add(textFieldPartyid);
		
		JLabel lblFname = new JLabel("First Name");
		lblFname.setHorizontalAlignment(SwingConstants.CENTER);
		lblFname.setBounds(440, 138, 126, 14);
		contentPane.add(lblFname);
		
		JLabel lblLname = new JLabel("Last Name");
		lblLname.setHorizontalAlignment(SwingConstants.CENTER);
		lblLname.setBounds(440, 237, 126, 14);
		contentPane.add(lblLname);
		
		JLabel lblPartyId = new JLabel("Party ID");
		lblPartyId.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartyId.setBounds(440, 337, 126, 14);
		contentPane.add(lblPartyId);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreate.setBounds(440, 422, 126, 29);
		contentPane.add(btnCreate);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(279, 466, 442, 34);
		contentPane.add(lblNotification);
		
		
		
		
		
		// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new FrontPageAdmin().setVisible(true);
				dispose();
			}
		});
		
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fname = textFieldFname.getText();
				String lname = textFieldLname.getText();
				
				int partyId  = -1;
				if(textFieldPartyid.getText() != null && !"".equals(textFieldPartyid.getText())) {
					partyId = Integer.parseInt(textFieldPartyid.getText());
				}
				
				
				if(fname == null || lname == null || partyId < 1 || 
						"".equals(fname) || "".equals(lname)) {
					notification = "Could Not Create Candidate!";
				}
				else if(candidateService.selectByFnameAndLname(fname, lname) != null) {
					notification = "Candidate Already Exists!";
				}
				else {
					if(candidateService.createCandidate(fname, lname, partyId)) {
						notification = "Candidate Successfully Created!";
					}
					else {
						notification = "Could Not Create Candidate!";
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
