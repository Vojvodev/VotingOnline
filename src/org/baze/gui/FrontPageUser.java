package org.baze.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.baze.dataaccess.CandidateDataAccess;
import org.baze.dataaccess.ElectionDataAccess;
import org.baze.dataaccess.FeedbackDataAccess;
import org.baze.dataaccess.UserDataAccess;
import org.baze.dataaccess.VoteDataAccess;
import org.baze.model.Candidate;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrontPageUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String notification = UserDataAccess.getNotificationbyId(WelcomePage.currentUser.getId());
	
	public static int numberOfVotes = 0; 
	public static int numberOfVoters = 0; 
	public static ArrayList<Candidate> votingListCandidates = new ArrayList<Candidate>();
	
	/**
	 * Create the frame.
	 */
	public FrontPageUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(30, 144, 255)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVote = new JButton("Vote");
		btnVote.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVote.setBounds(259, 128, 461, 48);
		contentPane.add(btnVote);
		
		JButton btnFindACandidate = new JButton("Find A Candidate");
		btnFindACandidate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFindACandidate.setBounds(315, 221, 176, 33);
		contentPane.add(btnFindACandidate);
		
		JButton btnSeeResults = new JButton("See Results");
		btnSeeResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSeeResults.setBounds(315, 265, 176, 33);
		contentPane.add(btnSeeResults);
		
		JButton btnSeeStatistics = new JButton("See Statistics");
		btnSeeStatistics.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSeeStatistics.setBounds(501, 265, 176, 33);
		contentPane.add(btnSeeStatistics);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 11, 113, 49);
		contentPane.add(btnBack);
		
		JLabel lblNotification = new JLabel(notification);
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(278, 448, 442, 34);
		contentPane.add(lblNotification);
		
		JButton btnReportProblem = new JButton("Report A Problem");
		btnReportProblem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReportProblem.setBounds(501, 309, 176, 33);
		contentPane.add(btnReportProblem);
		
		JButton btnLeaveFeedback = new JButton("Leave Feedback");
		btnLeaveFeedback.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLeaveFeedback.setBounds(315, 309, 176, 33);
		contentPane.add(btnLeaveFeedback);
		
		JButton btnSeeVotingList = new JButton("See Voting List");
		btnSeeVotingList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSeeVotingList.setBounds(501, 221, 176, 33);
		contentPane.add(btnSeeVotingList);
		
		ImageIcon gifIcon = new ImageIcon("assets/serbia.gif");
		JLabel label = new JLabel(gifIcon);
		label.setBounds(463, 353, gifIcon.getIconWidth(), gifIcon.getIconHeight());
		contentPane.add(label);
		
		JLabel lblUseroptions = new JLabel("User --Options");
		lblUseroptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblUseroptions.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblUseroptions.setBounds(195, 11, 588, 64);
		contentPane.add(lblUseroptions);
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				WelcomePage.currentUser = null;
				
				new WelcomePage().setVisible(true);
				dispose();
			}
		});
		
		

		btnVote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VotePage().setVisible(true);
				dispose();
			}
		});
		

		btnSeeResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// CREATE POP UP WINDOW -------------
				int electionsId = -1;
				JFrame frame = new JFrame("Text Input Popup");
	            	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JTextField textField = new JTextField();
		        Object[] message = {"Elections ID:", textField};
		        
		        int option = JOptionPane.showConfirmDialog(frame, message, "Text Input", JOptionPane.OK_CANCEL_OPTION);
		        if (option == JOptionPane.OK_OPTION) {
		            if(textField.getText() != null && !"".equals(textField.getText())) {
		            	electionsId = Integer.parseInt(textField.getText());
		            	
		            	if(ElectionDataAccess.getById(electionsId) != null) {
		            	
		            		WelcomePage.winner = VoteDataAccess.getWinner(electionsId);
							new ResultsPage().setVisible(true);
							dispose();
		            	}
		            	else {
		            		notification = "No Election With ID " + electionsId;
		            	}
		            	
		            }
		            else {
		            	notification = "No Election With ID " + electionsId;
		            }
		            
		            lblNotification.setText(notification);
		        } 
			}
			
		});
		

		btnSeeStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// CREATE POP UP WINDOW -------------
				int electionsId = -1;
				JFrame frame = new JFrame("Text Input Popup");
	            	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JTextField textField = new JTextField();
		        Object[] message = {"Elections ID:", textField};
		        
		        int option = JOptionPane.showConfirmDialog(frame, message, "Text Input", JOptionPane.OK_CANCEL_OPTION);
		        if (option == JOptionPane.OK_OPTION) {
		            if(textField.getText() != null && !"".equals(textField.getText())) {
		            	electionsId = Integer.parseInt(textField.getText());
		            	
		            	if(ElectionDataAccess.getById(electionsId) != null) {
		            		FrontPageUser.numberOfVotes = VoteDataAccess.getNumberOfVotes(electionsId);
							FrontPageUser.numberOfVoters = UserDataAccess.getNumberOfUsers();
			            	
			            	new StatisticsPage().setVisible(true);
							dispose();
		            	}
		            	else {
		            		notification = "No Election With ID " + electionsId;
		            	}
		            	
		            }
		            else {
		            	notification = "No Election With ID " + electionsId;
		            }
		            
		            lblNotification.setText(notification);
		            
		        } 
			}
		});
		
		

		btnFindACandidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindCandidate().setVisible(true);
				dispose();
			}
		});
		

		btnLeaveFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LeaveFeedback().setVisible(true);
				dispose();
			}
		});
		

		btnReportProblem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeedbackDataAccess.reportProblem();
			}
		});
		

		btnSeeVotingList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrontPageUser.votingListCandidates = CandidateDataAccess.selectAllCandidates();
				
				new VotingListPage().setVisible(true);
				dispose();
			}
		});
		
		
		
	}

}
