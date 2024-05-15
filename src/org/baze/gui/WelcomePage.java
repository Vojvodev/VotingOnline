package org.baze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import org.baze.model.Candidate;
import org.baze.model.User;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomePage extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	public static User currentUser = null;
	public static Candidate winner = null;
	private JPanel contentPane;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WelcomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome To ONLINE VOTING SYSTEM");
		lblWelcome.setBounds(155, 11, 706, 49);
		lblWelcome.setFont(new Font("Tahoma", Font.ITALIC, 40));
		contentPane.add(lblWelcome);
		
		JButton btnLoginAdmin = new JButton("LOGIN -- Admin");	
		btnLoginAdmin.setBorder(new LineBorder(new Color(178, 34, 34)));
		btnLoginAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoginAdmin.setBounds(388, 263, 171, 33);
		contentPane.add(btnLoginAdmin);
		
		JButton btnLoginUser = new JButton("LOGIN -- User");	
		btnLoginUser.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnLoginUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoginUser.setBounds(388, 336, 171, 33);
		contentPane.add(btnLoginUser);
		
		JLabel lblLoginProceed = new JLabel("Login to proceed..");
		lblLoginProceed.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoginProceed.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginProceed.setBounds(279, 176, 381, 33);
		contentPane.add(lblLoginProceed);
		
		
		
	// ----------------------------------------------- BUTTON ACTIONS -------------------------------------
		
		
		
		btnLoginAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginPageAdmin().setVisible(true);
				dispose();
			}
		});
		
		
		btnLoginUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginPageUser().setVisible(true);
				dispose();
			}
		});
		
		
		
	}

}
