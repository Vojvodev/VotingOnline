package org.baze.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.baze.dataaccess.ElectionDataAccess;
import org.baze.dataaccess.LogsDataAccess;
import org.baze.model.Log;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateElectionPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldYear;
	private JTextField textFieldStart;
	private JTextField textFieldType;
	private JTextField textFieldActive;
	private JTextField textFieldEnd;

	private String notification = "";

	/**
	 * Create the frame.
	 */
	public UpdateElectionPage() {
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
		
		JLabel lblUpdateElection = new JLabel("Update Election");
		lblUpdateElection.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateElection.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblUpdateElection.setBounds(205, 11, 588, 64);
		contentPane.add(lblUpdateElection);
		
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(438, 66, 152, 20);
		contentPane.add(textFieldId);
		
		JLabel lblCandidateId = new JLabel("ID");
		lblCandidateId.setHorizontalAlignment(SwingConstants.CENTER);
		lblCandidateId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCandidateId.setBounds(411, 64, 32, 20);
		contentPane.add(lblCandidateId);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(97, 128, 335, 37);
		contentPane.add(textFieldName);
		
		textFieldYear = new JTextField();
		textFieldYear.setColumns(10);
		textFieldYear.setBounds(599, 128, 335, 37);
		contentPane.add(textFieldYear);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(97, 103, 126, 14);
		contentPane.add(lblName);
		
		JLabel lblYear = new JLabel("Year (yyyy)");
		lblYear.setHorizontalAlignment(SwingConstants.LEFT);
		lblYear.setBounds(599, 103, 126, 14);
		contentPane.add(lblYear);
		
		textFieldStart = new JTextField();
		textFieldStart.setColumns(10);
		textFieldStart.setBounds(97, 216, 335, 37);
		contentPane.add(textFieldStart);
		
		JLabel lblStartDate = new JLabel("Start Date (yyyy-mm-dd)");
		lblStartDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblStartDate.setBounds(97, 191, 309, 14);
		contentPane.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date (yyyy-mm-dd)");
		lblEndDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblEndDate.setBounds(599, 191, 244, 14);
		contentPane.add(lblEndDate);
		
		textFieldType = new JTextField();
		textFieldType.setColumns(10);
		textFieldType.setBounds(97, 312, 335, 37);
		contentPane.add(textFieldType);
		
		textFieldActive = new JTextField();
		textFieldActive.setColumns(10);
		textFieldActive.setBounds(599, 312, 335, 37);
		contentPane.add(textFieldActive);
		
		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setBounds(97, 287, 126, 14);
		contentPane.add(lblType);
		
		JLabel lblActive = new JLabel("Active (1 or 0)");
		lblActive.setHorizontalAlignment(SwingConstants.LEFT);
		lblActive.setBounds(599, 287, 193, 14);
		contentPane.add(lblActive);
		
		textFieldEnd = new JTextField();
		textFieldEnd.setColumns(10);
		textFieldEnd.setBounds(599, 216, 335, 37);
		contentPane.add(textFieldEnd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(437, 376, 159, 44);
		contentPane.add(btnUpdate);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(232, 466, 588, 34);
		contentPane.add(lblNotification);
		
		
		
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
				
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
				
				String name 	 = textFieldName.getText();
				String type		 = textFieldType.getText();
				Date year 		 = null;
				Date start 		 = null;
				Date end 		 = null;
				boolean active   = true;
				
				int id 			 = -1;
				if(textFieldId.getText() != null && !"".equals(textFieldId.getText())) {
					id = Integer.parseInt(textFieldId.getText());
				}
				
				int activeNum 	 = -1;
				if(textFieldActive.getText() != null && !"".equals(textFieldEnd.getText())) {
					activeNum = Integer.parseInt(textFieldActive.getText());
				}
				
				try {
		            Date parsedYear  = new Date(dateFormat1.parse(textFieldYear.getText()).getTime());
		            Date parsedStart = new Date(dateFormat2.parse(textFieldStart.getText()).getTime());
		            Date parsedEnd 	 = new Date(dateFormat3.parse(textFieldEnd.getText()).getTime());
		            
		            year 		= new Date(parsedYear.getTime());
		            start		= new Date(parsedStart.getTime());
		            end			= new Date(parsedEnd.getTime());
		        } catch (ParseException ex) {
		            ex.printStackTrace();
		        }
				
				
				if(name == null || type == null || year == null || start == null || end ==null || 
						"".equals(name) || "".equals(type) || activeNum == -1 || id == -1) {
					notification = "Could Not Update Elections!";
				}
				else if(ElectionDataAccess.getById(id) == null) {
					notification = "Those Elections Do Not Exist!";
				}
				else {
					active = (activeNum == 1) ? true : false;
					if(ElectionDataAccess.update(id, name, type, year, start, end, active)) {
						notification = "Elections Successfully Updated!";
					}
					else {
						notification = "Could Not Update Elections!";
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
