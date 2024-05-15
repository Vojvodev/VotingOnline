package org.baze.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ResultsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public ResultsPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLUE));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("The Winner Is");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 80));
		lblNewLabel.setBounds(175, 55, 667, 132);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 10, 113, 49);
		contentPane.add(btnBack);
		
		JLabel lblName = new JLabel("--");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(282, 197, 470, 36);
		contentPane.add(lblName);
		
		JLabel lblParty = new JLabel("--");
		lblParty.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblParty.setHorizontalAlignment(SwingConstants.CENTER);
		lblParty.setBounds(282, 255, 470, 36);
		contentPane.add(lblParty);
		
		
		
		if(WelcomePage.winner != null) {
			lblName.setText(WelcomePage.winner.getFname() + " " + WelcomePage.winner.getLname());
			lblParty.setText(WelcomePage.winner.getPartyName());
		}
		
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrontPageUser().setVisible(true);
				dispose();
			}
		});
		
		
		
		
	}

}
