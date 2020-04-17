package clientView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Not used for Milestone 2.
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 *
 */
public class LoginWindow extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	//private JButton returnToMain;
	private JButton login;
	
	private JTextField username;
	private JPasswordField password;
	
	private JRadioButton admin;
	private JRadioButton student;
	
	private JPanel north;
	private JPanel center;
	private JPanel south;
	private JLabel topLabel;
	
	public LoginWindow(JFrame owner, String title)
	{
		super(owner, title);
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);			
		
		// North Panel
		north = new JPanel(new GridLayout(0,1));
		
		topLabel = new JLabel("Please Login with your Username and Password.");
		topLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		
		student = new JRadioButton("Student", true);
		student.setAlignmentX(CENTER_ALIGNMENT);
		admin = new JRadioButton("Admin");
		admin.setAlignmentX(CENTER_ALIGNMENT);
		
		ButtonGroup bg = new ButtonGroup(); 
		bg.add(student);
		bg.add(admin);
		
		north.setBorder(new EmptyBorder(5, 5, 5, 5));
		north.add(topLabel);
		north.add(Box.createVerticalGlue());
		north.add(student);
		north.add(admin);

		north.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(north, BorderLayout.NORTH);
		
		
		// Center Panel
		center = new JPanel(new GridLayout(0,2));
		username = new JTextField(8);
		password = new JPasswordField(14);
		
		center.add(new JLabel("Username:"));
		center.add(username);

		center.add(new JLabel("Password:"));
		center.add(password);
		center.add(Box.createRigidArea(new Dimension(5,1)));
		center.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(center, BorderLayout.CENTER);
		
		// South Panel
		south = new JPanel();
		login = new JButton("Login");
		
		south.add(login);
		south.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(south, BorderLayout.PAGE_END);
		setModalityType(ModalityType.APPLICATION_MODAL);

	}

	public JButton getLoginButton() {
		return login;
	}
	public String getUsername() {
		return username.getText();
	}
	public String getPassword() {
		return String.valueOf(password.getPassword());
	}

	public void displayError(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public boolean isStudent() {
		return student.isSelected();
	}
	
}
