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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.baze.dataaccess.FeedbackDataAccess;

public class LeaveFeedback extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private String notification = "";
	
	/**
	 * Create the frame.
	 */
	public LeaveFeedback() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLUE));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLeaveFeedback = new JLabel("Leave Feedback");
		lblLeaveFeedback.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeaveFeedback.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblLeaveFeedback.setBounds(210, 10, 588, 64);
		contentPane.add(lblLeaveFeedback);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 10, 113, 49);
		contentPane.add(btnBack);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setRows(8);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(112, 152, 766, 234);
		contentPane.add(textArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmit.setBounds(425, 416, 156, 42);
		contentPane.add(btnSubmit);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(282, 468, 442, 34);
		contentPane.add(lblNotification);
		
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
	
	
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new FrontPageUser().setVisible(true);
				dispose();
			}
		});
		

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String content = textArea.getText();
				
				if(content != null && !"".equals(content)) {
					if(FeedbackDataAccess.create(content)) {
						notification = "Feedback Sent!";
					}
					else {
						notification = "Could Not Send Feedback!";
					}
				}
				else {
					notification = "Could Not Send Feedback!";
				}
				
				lblNotification.setText(notification);
			}
		});
		
		
		
	}
}
