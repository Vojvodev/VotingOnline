package org.baze.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class StatisticsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public StatisticsPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLUE));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 10, 113, 49);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Total Number of Votes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(139, 112, 330, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblVotingPercentage = new JLabel("Voting Percentage");
		lblVotingPercentage.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblVotingPercentage.setBounds(139, 178, 330, 29);
		contentPane.add(lblVotingPercentage);
		
		JLabel lblValid = new JLabel("");
		lblValid.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblValid.setBounds(369, 256, 456, 29);
		contentPane.add(lblValid);
		
		JLabel lblNumberOfVotes = new JLabel("--");
		lblNumberOfVotes.setBounds(497, 112, 113, 29);
		contentPane.add(lblNumberOfVotes);
		
		JLabel lblPercentage = new JLabel("--");
		lblPercentage.setBounds(497, 178, 113, 29);
		contentPane.add(lblPercentage);
		
		
		double percentage = (FrontPageUser.numberOfVotes / FrontPageUser.numberOfVoters) * 100.0;
		lblNumberOfVotes.setText("" + FrontPageUser.numberOfVotes);
		lblPercentage.setText("" + percentage + "%");
		
		if(percentage > 50) {
			lblValid.setText("The results are valid!");
			lblValid.setForeground(Color.GREEN);
		}
		else {
			lblValid.setText("The results are not valid!");
			lblValid.setForeground(Color.RED);
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
