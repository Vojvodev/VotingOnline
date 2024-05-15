package org.baze.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.baze.dataaccess.CandidateDataAccess;
import org.baze.dataaccess.ElectionDataAccess;
import org.baze.dataaccess.LogsDataAccess;
import org.baze.dataaccess.UserDataAccess;
import org.baze.model.Candidate;
import org.baze.model.Election;
import org.baze.model.Log;
import org.baze.model.User;

public class FrontPageAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public static List<Candidate> allCandidates = new ArrayList<Candidate>();
	public static List<User> 	  allUsers		= new ArrayList<User>();
	public static List<Election>  allElections	= new ArrayList<Election>();
	public static List<Log>		  logs			= new ArrayList<Log>();
	
	
	/**
	 * Create the frame.
	 */
	public FrontPageAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(255, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminOptions = new JLabel("Admin OPTIONS");
		lblAdminOptions.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAdminOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminOptions.setBounds(375, 11, 294, 49);
		contentPane.add(lblAdminOptions);
		
		JButton btnAddCandidate = new JButton("Add Candidate");
		btnAddCandidate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddCandidate.setBounds(128, 192, 153, 33);
		contentPane.add(btnAddCandidate);
		
		JButton btnCreateElection = new JButton("Create Election");
		btnCreateElection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateElection.setBounds(699, 192, 153, 33);
		contentPane.add(btnCreateElection);
		
		JButton btnDeleteCandidate = new JButton("Delete Candidate");
		btnDeleteCandidate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteCandidate.setBounds(128, 280, 153, 33);
		contentPane.add(btnDeleteCandidate);
		
		JButton btnUpdateCandidate = new JButton("Update Candidate");
		btnUpdateCandidate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdateCandidate.setBounds(128, 236, 153, 33);
		contentPane.add(btnUpdateCandidate);
		
		JButton btnDeleteElection = new JButton("Delete Election");
		btnDeleteElection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteElection.setBounds(699, 280, 153, 33);
		contentPane.add(btnDeleteElection);
		
		JButton btnUpdateElection = new JButton("Update Election");
		btnUpdateElection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdateElection.setBounds(699, 236, 153, 33);
		contentPane.add(btnUpdateElection);
		
		JButton btnAllCandidates = new JButton("All Candidates");
		btnAllCandidates.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAllCandidates.setBounds(114, 148, 153, 33);
		contentPane.add(btnAllCandidates);
		
		JButton btnAllElections = new JButton("All Elections");
		btnAllElections.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAllElections.setBounds(685, 148, 153, 33);
		contentPane.add(btnAllElections);
		
		JButton btnBack = new JButton("<-- Go Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(10, 11, 113, 49);
		contentPane.add(btnBack);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddUser.setBounds(414, 192, 153, 33);
		contentPane.add(btnAddUser);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteUser.setBounds(414, 280, 153, 33);
		contentPane.add(btnDeleteUser);
		
		JButton btnUpdateUser = new JButton("Update User");
		btnUpdateUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdateUser.setBounds(414, 236, 153, 33);
		contentPane.add(btnUpdateUser);
		
		JButton btnAllUsers = new JButton("All Users");
		btnAllUsers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAllUsers.setBounds(400, 148, 153, 33);
		contentPane.add(btnAllUsers);
		
		JButton btnLogs = new JButton("See Logs -->");
		btnLogs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogs.setBounds(855, 11, 121, 49);
		contentPane.add(btnLogs);
	

	
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
	
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				WelcomePage.currentUser = null;
				
				new WelcomePage().setVisible(true);
				dispose();
			}
		});
		

		btnLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrontPageAdmin.logs = LogsDataAccess.getLogs();
				
				new LogsPage().setVisible(true);
				dispose();
			}
		});
		
		
	// --------------- CANDIDATES ----------------------	
		
		btnAllCandidates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			FrontPageAdmin.allCandidates = CandidateDataAccess.selectAllCandidates();			// FILL THEM UP BEFORE GOING TO THE NEXT PAGE
				
				new AllCandidatesPage().setVisible(true);
				dispose();
			}
		});
		
		btnAddCandidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddCandidatePage().setVisible(true);
				dispose();
			}
		});
		
		btnUpdateCandidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateCandidatePage().setVisible(true);
				dispose();
			}
		});
		
		btnDeleteCandidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteCandidatePage().setVisible(true);
				dispose();
			}
		});
	
		
		
		
	// ----------------- USERS ---------------------
	
		
		btnAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrontPageAdmin.allUsers = UserDataAccess.selectAllUsers();			// FILL THEM UP BEFORE GOING TO THE NEXT PAGE
				
				new AllUsersPage().setVisible(true);
				dispose();
			}
		});
		
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddUserPage().setVisible(true);
				dispose();
			}
		});
		
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateUserPage().setVisible(true);
				dispose();
			}
		});
		
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteUserPage().setVisible(true);
				dispose();
			}
		});
	
		
		
		
	// ----------------- ELECTIONS ---------------------
		
		
		btnAllElections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrontPageAdmin.allElections = ElectionDataAccess.selectActiveElections();
				
				new AllElectionsPage().setVisible(true);
				dispose();
			}
		});
		
		btnCreateElection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddElectionPage().setVisible(true);
				dispose();
			}
		});
		
		btnUpdateElection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateElectionPage().setVisible(true);
				dispose();
			}
		});
		
		btnDeleteElection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteElectionPage().setVisible(true);
				dispose();
			}
		});
		
		
	
	
	}
}
