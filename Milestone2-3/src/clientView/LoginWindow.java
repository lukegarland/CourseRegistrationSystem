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
 * Login dialog window of the GUI.
 * 
 * Provides the member variables and methods required for the creation of and interaction 
 * with the login window of the course registration system. The user can login as an admin or student.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class LoginWindow extends JDialog {
	/**
	 * Default not used
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Button to submit username and password
	 */
	private JButton login;
	/**
	 * Text field for the username to be entered into
	 */
	private JTextField username;
	/**
	 * Password text field for the password to be entered into
	 */
	private JPasswordField password;
	/**
	 * Radio button to used to login as an admin
	 */
	private JRadioButton admin;
	/**
	 * Radio button to used to login as an student
	 */
	private JRadioButton student;
	/**
	 * The top panel of the frame
	 */
	private JPanel north;
	/**
	 * The center panel of the frame
	 */
	private JPanel center;
	/**
	 * The bottom panel of the frame
	 */
	private JPanel south;
	/**
	 * The top label of the frame to welcome the user to the login window
	 */
	private JLabel topLabel;
	/**
	 * Constructs a dialog pane where user can login as a student or admin.
	 * @param owner JFrame which owns the created dialog pane.
	 * @param title Name of the dialog pane.
	 */
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
	/**
	 * Gets the login JButton.
	 * @return the login button
	 */
	public JButton getLoginButton() {
		return login;
	}
	/**
	 * Returns the username entered by the user
	 * @return String containing the username
	 */
	public String getUsername() {
		return username.getText();
	}
	/**
	 * Returns the password entered by the user
	 * @return String containing the password
	 */
	public String getPassword() {
		return String.valueOf(password.getPassword());
	}
	/**
	 * Displays the message given by message to the user in a JOptionPane
	 * @param message message to be displayed
	 */
	public void displayError(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	/**
	 * Returns the type of user login that was selected
	 * @return whether student is selected
	 */
	public boolean isStudent() {
		return student.isSelected();
	}
	
}
