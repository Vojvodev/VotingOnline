package org.baze.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.baze.dataaccess.ElectionDataAccess;
import org.baze.dataaccess.LogsDataAccess;
import org.baze.model.Log;

public class AddElectionPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private String notification = "";
	private JTextField textFieldName;
	private JTextField textFieldYear;
	private JTextField textFieldStart;
	private JTextField textFieldType;
	private JTextField textFieldActive;
	private JTextField textFieldEnd;

	/**
	 * Create the frame.
	 */
	public AddElectionPage() {
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
		
		JLabel lblCreateNewElections = new JLabel("Create New Elections");
		lblCreateNewElections.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateNewElections.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblCreateNewElections.setBounds(200, 11, 588, 64);
		contentPane.add(lblCreateNewElections);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(93, 128, 335, 37);
		contentPane.add(textFieldName);
		
		textFieldYear = new JTextField();
		textFieldYear.setColumns(10);
		textFieldYear.setBounds(595, 128, 335, 37);
		contentPane.add(textFieldYear);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(93, 103, 126, 14);
		contentPane.add(lblName);
		
		JLabel lblYear = new JLabel("Year (yyyy)");
		lblYear.setHorizontalAlignment(SwingConstants.LEFT);
		lblYear.setBounds(595, 103, 126, 14);
		contentPane.add(lblYear);
		
		textFieldStart = new JTextField();
		textFieldStart.setColumns(10);
		textFieldStart.setBounds(93, 216, 335, 37);
		contentPane.add(textFieldStart);
		
		JLabel lblStartDate = new JLabel("Start Date (yyyy-mm-dd)");
		lblStartDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblStartDate.setBounds(93, 191, 309, 14);
		contentPane.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date (yyyy-mm-dd)");
		lblEndDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblEndDate.setBounds(595, 191, 244, 14);
		contentPane.add(lblEndDate);
		
		textFieldType = new JTextField();
		textFieldType.setColumns(10);
		textFieldType.setBounds(93, 312, 335, 37);
		contentPane.add(textFieldType);
		
		textFieldActive = new JTextField();
		textFieldActive.setColumns(10);
		textFieldActive.setBounds(595, 312, 335, 37);
		contentPane.add(textFieldActive);
		
		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setBounds(93, 287, 126, 14);
		contentPane.add(lblType);
		
		JLabel lblActive = new JLabel("Active (1 or 0)");
		lblActive.setHorizontalAlignment(SwingConstants.LEFT);
		lblActive.setBounds(595, 287, 193, 14);
		contentPane.add(lblActive);
		
		textFieldEnd = new JTextField();
		textFieldEnd.setColumns(10);
		textFieldEnd.setBounds(595, 216, 335, 37);
		contentPane.add(textFieldEnd);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreate.setBounds(433, 376, 159, 44);
		contentPane.add(btnCreate);
		
		JLabel lblNotification = new JLabel("");
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNotification.setBounds(228, 466, 588, 34);
		contentPane.add(lblNotification);
		
		
		
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
				
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				
				String name 	 = textFieldName.getText();
				String type		 = textFieldType.getText();
				Date year 		 = null;
				Date start 		 = null;
				Date end 		 = null;
				boolean active   = true;
				
				int activeNum 	 = -1;
				if(textFieldActive.getText() != null && !"".equals(textFieldEnd.getText())) {
					activeNum = Integer.parseInt(textFieldActive.getText());
				}
				
				try {
		            Date parsedYear  = new Date(dateFormat1.parse(textFieldYear.getText()).getTime());
		            Date parsedStart = new Date(dateFormat2.parse(textFieldStart.getText()).getTime());
		            Date parsedEnd 	 = new Date(dateFormat2.parse(textFieldEnd.getText()).getTime());
		            
		            year 		= new Date(parsedYear.getTime());
		            start		= new Date(parsedStart.getTime());
		            end			= new Date(parsedEnd.getTime());
		        } catch (ParseException ex) {
		            ex.printStackTrace();
		        }
				
				
				if(name == null || type == null || year == null || start == null || end ==null || 
						"".equals(name) || "".equals(type) || activeNum == -1) {
					notification = "Could Not Create Elections!";
				}
				else if(ElectionDataAccess.getActiveByName(name) != null) {
					notification = "Active Elections With That Name Already Exist!";
				}
				else {
					active = (activeNum == 1) ? true : false;
					if(ElectionDataAccess.create(name, type, year, start, end, active)) {
						notification = "Elections Successfully Created!";
					}
					else {
						notification = "Could Not Create Elections!";
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
