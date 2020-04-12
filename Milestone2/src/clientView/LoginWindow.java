package clientView;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
	
	private JButton returnToMain;
	
	private JTextField id;
	private JTextField password;
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
		north.add(topLabel);
		add(north, BorderLayout.NORTH);
		
		
		// Center Panel
		center = new JPanel();
		id = new JTextField(8);
		password = new JTextField(14);
		
		center.add(new JLabel("Student Username or ID:"));
		center.add(id);

		center.add(new JLabel("Password:"));
		center.add(password);

		
		add(center, BorderLayout.CENTER);
		
		// South Panel
		south = new JPanel();
		JButton login = new JButton("Login");
		login.addActionListener(new LoginListener());
		
		south.add(login);
		
		add(south, BorderLayout.SOUTH);
		setModalityType(ModalityType.APPLICATION_MODAL);
		pack();
		setVisible(true);
		

	}

	/**
	 * TODO for now it goes to main menu
	 *
	 */
	class LoginListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog i = (JDialog) SwingUtilities.getRoot((JButton)e.getSource());
			i.setVisible(false); // Close
		}
		
	}

}
