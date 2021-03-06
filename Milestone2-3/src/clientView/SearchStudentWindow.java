package clientView;

import java.awt.*;

import javax.swing.*;

/**
 * The dialog window of the GUI to search for a student's courses.
 * 
 * Provides the member variables and methods required for the creation of and interaction with the search student
 * pop-up window.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class SearchStudentWindow extends JDialog{
	/**
	 * Default not used.
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Text field where user enters the student's name.
	 */
	private JTextField studentName;
	/**
	 * Label for the studentName text field.
	 */
	private JLabel studentNameLabel;
	
	/**
	 * Text area where student's registered courses will appear.
	 */
	private JTextArea studentContent;
	/**
	 * ScrollPane to enable scrolling through studentContent.
	 */
	private JScrollPane studentContentScroll;
	
	/**
	 * North panel where students name will be requested.
	 */
	private JPanel north;
	/**
	 * Center panel where students registered courses will be displayed.
	 */
	private JPanel center;
	
	/**
	 * Button to search for student.
	 */
	private JButton submitButton;
	
	/**
	 * Constructs a dialog pane where user can search through a student's courses.
	 * @param owner JFrame which owns the dialog pane.
	 */
	public SearchStudentWindow(MainFrame owner) {
		this(owner, "Search for student");
	}
	
	/**
	 * Constructs a dialog pane where user can search through a student's courses.
	 * @param owner JFrame which owns the dialog pane.
	 * @param title Name of the dialog pane.
	 */
	public SearchStudentWindow(MainFrame owner, String title) {
		super(owner, title);
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//North panel
		north = new JPanel();
		studentNameLabel = new JLabel("Student name or ID:");
		studentName = new JTextField(20);
		submitButton = new JButton("Submit");
		
		north.add(studentNameLabel);
		north.add(studentName);
		north.add(submitButton);
		add(north, BorderLayout.NORTH);
		if(!owner.isAdmin()) {
			studentName.setEditable(false);
			studentName.setText(owner.getStudentId());
			submitButton.doClick();
			submitPressed();
		}
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		pack();
	}
	/**
	 * Populates the remaining of the dialog pane when a student's name or id has been input.
	 */
	public void submitPressed() {
		if(center != null)
		{
			pack();
			return;
		}
		//Center Panel
		center = new JPanel();
		studentContent = new JTextArea(20,50);
		studentContentScroll = new JScrollPane(studentContent);
		studentContent.setEditable(false);
		center.add(studentContentScroll);
		add(center, BorderLayout.CENTER);
		
		pack();
	}
	
	/**
	 * Writes to the student content text area.
	 * @param info String to be written to text area.
	 */
	public void writeToStudentContent(String info) {
		studentContent.setText(info);
		studentContent.setCaretPosition(0);
	}
	
	/**
	 * Retrieves the name of the student inputted into the student name text field.
	 * @return User inputted data in student name text field.
	 */
	public String getStudentName() {
		return studentName.getText();
	}
	
//Getters and setters.
	/**
	 * Gets the submitButton JButton.
	 * @return the submitButton
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}
}
