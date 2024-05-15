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

public class AddUserPage extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
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
	public AddUserPage() {
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
		
		JLabel lblCreateANew = new JLabel("Create A New User");
		lblCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateANew.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblCreateANew.setBounds(220, 11, 588, 64);
		contentPane.add(lblCreateANew);
		
		textFieldFname = new JTextField();
		textFieldFname.setColumns(10);
		textFieldFname.setBounds(77, 133, 335, 37);
		contentPane.add(textFieldFname);
		
		textFieldLname = new JTextField();
		textFieldLname.setColumns(10);
		textFieldLname.setBounds(579, 133, 335, 37);
		contentPane.add(textFieldLname);
		
		JLabel lblFname = new JLabel("First Name");
		lblFname.setHorizontalAlignment(SwingConstants.LEFT);
		lblFname.setBounds(77, 108, 126, 14);
		contentPane.add(lblFname);
		
		JLabel lblLname = new JLabel("Last Name");
		lblLname.setHorizontalAlignment(SwingConstants.LEFT);
		lblLname.setBounds(579, 108, 126, 14);
		contentPane.add(lblLname);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreate.setBounds(418, 399, 159, 44);
		contentPane.add(btnCreate);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(284, 466, 442, 34);
		contentPane.add(lblNotification);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(77, 221, 335, 37);
		contentPane.add(textFieldUsername);
		
		JLabel lblLUsername = new JLabel("Username");
		lblLUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblLUsername.setBounds(77, 196, 126, 14);
		contentPane.add(lblLUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(579, 221, 335, 37);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(579, 196, 110, 14);
		contentPane.add(lblPassword);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(77, 317, 335, 37);
		contentPane.add(textFieldEmail);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(579, 317, 335, 37);
		contentPane.add(textFieldAddress);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(77, 292, 126, 14);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setBounds(579, 292, 126, 14);
		contentPane.add(lblAddress);
		
		
		
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
				
				String fname 	= textFieldFname.getText();
				String lname 	= textFieldLname.getText();
				String username = textFieldUsername.getText();
				String password = new String(passwordField.getPassword());
				String email 	= textFieldEmail.getText();
				String address 	= textFieldAddress.getText();
				
				if(fname == null || lname == null || username == null || password == null || email == null || address == null ||
						"".equals(fname) || "".equals(lname) || "".equals(username) || "".equals(password) || "".equals(email) || "".equals(address)) {
					notification = "Could Not Create User!";
				}
				else if(userService.selectByUsernameAndPassword(username, password) != null) {
					notification = "User Already Exists!";
				}
				else {
					if(userService.createUser(fname, lname, username, password, email, address)) {
						notification = "User Successfully Created!";
					}
					else {
						notification = "Could Not Create User!";
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
