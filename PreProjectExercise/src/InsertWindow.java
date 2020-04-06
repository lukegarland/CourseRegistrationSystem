
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Dialog window for inserting a new student to the BST.
 * @author lukeg
 *	@since April 6, 2020
 */
@SuppressWarnings("unused")
public class InsertWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton insert;
	private JButton returnToMain;
	
	private JTextField id;
	private JTextField facutly;
	private JTextField major;
	private JTextField year;
	private JPanel north;
	private JPanel center;
	private JPanel south;
	private JLabel topLabel;
	
	private String studentInfo;
	
	public InsertWindow(JFrame owner, String title)
	{
		super(owner, title);
		setSize(500,150);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
				
		
		// North Panel
		north = new JPanel();
		topLabel = new JLabel("Insert a New Node");
		north.add(topLabel);
		add(north, BorderLayout.NORTH);
		
		
		// Center Panel
		center = new JPanel();
		id = new JTextField(8);
		
		facutly = new JTextField(14);
		major = new JTextField(20);
		year = new JTextField(4);
		
		center.add(new JLabel("Enter the Student ID:"));
		center.add(id);

		center.add(new JLabel("Enter Facutly:"));
		center.add(facutly);
		
		
		center.add(new JLabel("Enter Student's Major"));
		center.add(major);
		
		center.add(new JLabel("Enter Year:"));
		center.add(year);
		
		add(center, BorderLayout.CENTER);
		
		// South Panel
		south = new JPanel();
		JButton insert = new JButton("Insert");
		insert.addActionListener(new InsertNewStudentListener());
		
		JButton returnToMain = new JButton("Return to Main Window");
		returnToMain.addActionListener(new ReturnToMainListener());
		
		south.add(insert);
		south.add(returnToMain);
		
		add(south, BorderLayout.SOUTH);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		

	}
	


	/**
	 * Listener for the "Insert" Button for this dialog.
	 * Takes all input from JTextFields and populates it into studentInfo
	 * @author lukeg
	 *
	 */
	class InsertNewStudentListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			studentInfo = id.getText() + " " + facutly.getText() + " " + major.getText() + " " + year.getText();
			
			
			JDialog i = (JDialog) SwingUtilities.getRoot((JButton)e.getSource());
			i.setVisible(false); // Close
			
		}
		
	}

	
	/**
	 * Listener for the "Return to main window" button. 
	 * Simply closes the dialog
	 * @author lukeg
	 *
	 */
	class ReturnToMainListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog i = (JDialog) SwingUtilities.getRoot((JButton)e.getSource());
			i.setVisible(false); // Close
		}
		
	}

	public String getStudentInfo() {
		return studentInfo;
	}


}
