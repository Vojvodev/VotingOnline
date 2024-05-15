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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.baze.dataaccess.LogsDataAccess;
import org.baze.model.Log;
import org.baze.service.UserService;

public class UpdateUserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldFname;
	private JTextField textFieldLname;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JTextField textFieldEmail;
	private JTextField textFieldAddress;
	
	private UserService userService = new UserService();
	private String notification = "";
	
	
	
	/**
	 * Create the frame.
	 */
	public UpdateUserPage() {
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
		
		JLabel lblUpdateAUser = new JLabel("Update A User");
		lblUpdateAUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateAUser.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblUpdateAUser.setBounds(208, 11, 588, 64);
		contentPane.add(lblUpdateAUser);
		
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(431, 76, 152, 20);
		contentPane.add(textFieldId);
		
		JLabel lblUserId = new JLabel("ID");
		lblUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserId.setBounds(404, 74, 32, 20);
		contentPane.add(lblUserId);
		
		textFieldFname = new JTextField();
		textFieldFname.setColumns(10);
		textFieldFname.setBounds(91, 132, 335, 37);
		contentPane.add(textFieldFname);
		
		textFieldLname = new JTextField();
		textFieldLname.setColumns(10);
		textFieldLname.setBounds(593, 132, 335, 37);
		contentPane.add(textFieldLname);
		
		JLabel lblFname = new JLabel("First Name");
		lblFname.setHorizontalAlignment(SwingConstants.LEFT);
		lblFname.setBounds(91, 107, 126, 14);
		contentPane.add(lblFname);
		
		JLabel lblLname = new JLabel("Last Name");
		lblLname.setHorizontalAlignment(SwingConstants.LEFT);
		lblLname.setBounds(593, 107, 126, 14);
		contentPane.add(lblLname);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(432, 398, 159, 44);
		contentPane.add(btnUpdate);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(298, 465, 442, 34);
		contentPane.add(lblNotification);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(91, 220, 335, 37);
		contentPane.add(textFieldUsername);
		
		JLabel lblLUsername = new JLabel("Username");
		lblLUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblLUsername.setBounds(91, 195, 126, 14);
		contentPane.add(lblLUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(593, 220, 335, 37);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(593, 195, 110, 14);
		contentPane.add(lblPassword);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(91, 316, 335, 37);
		contentPane.add(textFieldEmail);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(593, 316, 335, 37);
		contentPane.add(textFieldAddress);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(91, 291, 126, 14);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setBounds(593, 291, 126, 14);
		contentPane.add(lblAddress);
		
		
		
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
				
				String fname 	= textFieldFname.getText();
				String lname 	= textFieldLname.getText();
				String username = textFieldUsername.getText();
				String password = new String(passwordField.getPassword());
				String email 	= textFieldEmail.getText();
				String address 	= textFieldAddress.getText();
				
				int id 			= -1;
				if(textFieldId.getText() != null && !"".equals(textFieldId.getText())) {
					id = Integer.parseInt(textFieldId.getText());
				}
				
				
				if(fname == null || lname == null || username == null || password == null || email == null || address == null || id < 1 ||
						fname == "" || lname == "" || username == "" || password == "" || email == "" || address =="") {
					notification = "Could Not Update User!";
				}
				else if(userService.selectById(id) == null) {
					notification = "User Does Not Exist!";
				}
				else {
					if(userService.updateUser(id, fname, lname, username, password, email, address)) {
						notification = "User Successfully Updated!";
					}
					else {
						notification = "Could Not Update User!";
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
