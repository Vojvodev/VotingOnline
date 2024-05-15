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

public class LoginPageAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField textFieldPassword;

	
	private UserService userService = new UserService();
	private String notification = "";
	
	/**
	 * Create the frame.
	 */ 
	public LoginPageAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(178, 34, 34)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN -- Admin");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblLogin.setBounds(167, 11, 609, 51);
		contentPane.add(lblLogin);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUsername.setBounds(239, 143, 198, 37);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(239, 258, 198, 37);
		contentPane.add(lblPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldUsername.setBounds(501, 146, 150, 34);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldPassword.setBounds(501, 258, 150, 34);
		contentPane.add(textFieldPassword);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(319, 429, 334, 34);
		contentPane.add(lblNotification);
				
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(376, 349, 198, 37);
		contentPane.add(btnLogin);
				
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 13, 113, 49);
		contentPane.add(btnBack);
		
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				WelcomePage.currentUser = null;
				
				new WelcomePage().setVisible(true);
				dispose();
			}
		});
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				String username = textFieldUsername.getText();
				String password = new String(textFieldPassword.getPassword());
				
				WelcomePage.currentUser = userService.loginUser(username, password);
				if(WelcomePage.currentUser != null && WelcomePage.currentUser.isAdmin()){
					
					WelcomePage.currentUser.setLoggedIn(true);
					notification = "Successfully Logged In!";
					
					if(notification != null && !"".equals(notification)) {
						Log log = new Log(WelcomePage.currentUser.getId(), notification);
						LogsDataAccess.createLog(log);	
					}
					
					new FrontPageAdmin().setVisible(true);
					dispose();
				
				}
				else {
					notification = "Wrong username/password!";
					lblNotification.setText(notification);
					
				}
				
			}
		});
		
		
		
		
	}
}
