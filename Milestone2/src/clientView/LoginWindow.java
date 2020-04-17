package clientView;

import java.awt.BorderLayout;
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
		north = new JPanel();
		topLabel = new JLabel("Please Login with your Username and Password.");
		
		student = new JRadioButton("Student", true);
		admin = new JRadioButton("Admin");
		
		ButtonGroup bg = new ButtonGroup(); 
		bg.add(student);
		bg.add(admin);
		
		north.add(student);
		north.add(admin);
		north.add(topLabel);
		add(north, BorderLayout.NORTH);
		
		
		// Center Panel
		center = new JPanel();
		username = new JTextField(8);
		password = new JPasswordField(14);
		
		center.add(new JLabel("Student Username:"));
		center.add(username);

		center.add(new JLabel("Password:"));
		center.add(password);

		
		add(center, BorderLayout.CENTER);
		
		// South Panel
		south = new JPanel();
		login = new JButton("Login");
		
		south.add(login);
		
		add(south, BorderLayout.SOUTH);
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
