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

import org.baze.dataaccess.ElectionDataAccess;
import org.baze.dataaccess.LogsDataAccess;
import org.baze.model.Log;

public class DeleteElectionPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private String notification = "";
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public DeleteElectionPage() {
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
		
		JLabel lblDeleteElections = new JLabel("Delete Elections");
		lblDeleteElections.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteElections.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblDeleteElections.setBounds(205, 11, 588, 64);
		contentPane.add(lblDeleteElections);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(298, 348, 442, 34);
		contentPane.add(lblNotification);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(422, 130, 190, 49);
		contentPane.add(textField);
		
		JLabel lblCandidateId = new JLabel("ID");
		lblCandidateId.setHorizontalAlignment(SwingConstants.CENTER);
		lblCandidateId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCandidateId.setBounds(358, 128, 69, 51);
		contentPane.add(lblCandidateId);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(440, 270, 156, 42);
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
				int id = -1; 
				if(textField.getText() != null && !"".equals(textField.getText())) {
					id = Integer.parseInt(textField.getText());
				}
				
				if(id < 1) {
					notification = "Could Not Delete Elections!";
				}
				else if(ElectionDataAccess.getById(id) == null) {
					notification = "Elections Do Not Exist!";
				}
				else {
					if(ElectionDataAccess.delete(id)) {
						notification = "Elections Successfully Deleted!";
					}
					else {
						notification = "Could Not Delete Elections!";
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
