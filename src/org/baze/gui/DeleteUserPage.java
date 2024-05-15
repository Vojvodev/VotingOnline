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
import org.baze.service.UserService;

public class DeleteUserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private UserService userService = new UserService();
	private String notification = "";
	private JTextField textField;
	
	
	/**
	 * Create the frame.
	 */
	public DeleteUserPage() {
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
		
		JLabel lblDeleteAUser = new JLabel("Delete A User");
		lblDeleteAUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteAUser.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblDeleteAUser.setBounds(211, 11, 588, 64);
		contentPane.add(lblDeleteAUser);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(418, 154, 190, 49);
		contentPane.add(textField);
		
		JLabel lblUserId = new JLabel("ID");
		lblUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserId.setBounds(347, 154, 69, 51);
		contentPane.add(lblUserId);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(436, 276, 156, 42);
		contentPane.add(btnDelete);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(295, 377, 442, 34);
		contentPane.add(lblNotification);
		
		
		
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
					notification = "Could Not Delete User!";
				}
				else if(userService.selectById(id) == null) {
					notification = "User Does Not Exist!";
				}
				else {
					if(userService.deleteUser(id)) {
						notification = "User Successfully Deleted!";
					}
					else {
						notification = "Could Not Delete User!";
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
