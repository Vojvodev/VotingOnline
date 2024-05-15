package org.baze.gui;

import java.awt.Color;
import java.awt.Font;
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

import org.baze.service.UserService;

public class LoginPageUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField textFieldPassword;

	private UserService userService = new UserService();
	private String notificationMessage = "";
	
	
	/**
	 * Create the frame.
	 */
	public LoginPageUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(30, 144, 255)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginUser = new JLabel("LOGIN -- User");
		lblLoginUser.setBounds(308, 10, 395, 49);
		lblLoginUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginUser.setFont(new Font("Tahoma", Font.BOLD, 40));
		contentPane.add(lblLoginUser);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUsername.setBounds(291, 139, 198, 37);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(291, 254, 198, 37);
		contentPane.add(lblPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(553, 142, 150, 34);
		contentPane.add(textFieldUsername);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldPassword.setBounds(553, 254, 150, 34);
		contentPane.add(textFieldPassword);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(350, 420, 334, 34);
		contentPane.add(lblNotification);
		
		
		JButton btnLogin = new JButton("LOGIN");		
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(428, 345, 198, 37);
		contentPane.add(btnLogin);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 10, 113, 49);
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
		
		
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String username = textFieldUsername.getText();
				String password = new String(textFieldPassword.getPassword());
				
				WelcomePage.currentUser = userService.loginUser(username, password);
				
				if(WelcomePage.currentUser != null) {
					
					WelcomePage.currentUser.setLoggedIn(true);
					
					new FrontPageUser().setVisible(true);;
					dispose();
				}
				else {
					notificationMessage = "Wrong username/password!";
					lblNotification.setText(notificationMessage);
				}
					
			}
		});
		
		
	}
}
