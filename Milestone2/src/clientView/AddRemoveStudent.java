package clientView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Provides the member variables and methods required for the creation of and interaction with the add/remove student
 * from course pop-up window.
 * @author Guillaume Raymond-Fauteux
 * @since April 10 2020
 * @version 0.0
 *
 */
public class AddRemoveStudent extends JDialog {

	private static final long serialVersionUID = 1L;

	/*
	 * Text field where user enters the student's name.
	 */
	private JTextField studentName;
	/**
	 * Label for the studentName text field.
	 */
	private JLabel studentNameLabel;
	
	/*
	 * Text field where user enters the course name.
	 */
	private JTextField courseName;
	/**
	 * Label for the courseName text field.
	 */
	private JLabel courseNameLabel;
	
	/**
	 * Text field where user enters the course number.
	 */
	private JTextField courseNumber;
	/**
	 * Label for the courseNumber text field.
	 */
	private JLabel courseNumberLabel;
	
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
	 * West panel where student's registered courses will appear.
	 */
	private JPanel west;
	/**
	 * East panel where course name and number to be added/removed will be requested.
	 */
	private JPanel east;
	
	/**
	 * Button to search for student's courses.
	 */
	private JButton submitButton;
	/**
	 * Button to add course to student.
	 */
	private JButton addButton;
	/**
	 * Button to remove course from student.
	 */
	private JButton removeButton;
	
	/**
	 * Constructs a dialog pane where user can add or remove a course from a student.
	 * @param owner JFrame which owns the created dialog pane.
	 */
	public AddRemoveStudent(JFrame owner) {
		this(owner, "Add/remove student");
	}
	
	/**
	 * Constructs a dialog pane where user can add or remove a course from a student.
	 * @param owner JFrame which owns the created dialog pane.
	 * @param title Name of the dialog pane.
	 */
	public AddRemoveStudent(JFrame owner, String title)
	{
		super(owner, title);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//North panel
		north = new JPanel();
		studentNameLabel = new JLabel("Student name:");
		studentName = new JTextField(20);
		submitButton = new JButton("Submit");
		
		north.add(studentNameLabel);
		north.add(studentName);
		north.add(submitButton);
		add(north, BorderLayout.NORTH);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Populates the remaining of the dialog pane when a students name has been input.
	 */
	public void submitPressed() {
		//West Panel
		west = new JPanel();
		studentContent = new JTextArea(20,30);
		studentContentScroll = new JScrollPane(studentContent);
		
		west.add(studentContentScroll);
		add(west, BorderLayout.WEST);
		
		//East Panel
		east = new JPanel();
		east.setLayout( new GridLayout(10,2) );
		courseName = new JTextField(4);
		courseNameLabel = new JLabel("Course name:");
		
		courseNumber = new JTextField(3);
		courseNumberLabel = new JLabel("Course number:");
		
		addButton = new JButton("Add course");
		removeButton = new JButton("Remove course");
		
		east.add(courseNameLabel);
		east.add(courseName);
		
		east.add(courseNumberLabel);
		east.add(courseNumber);
		
		east.add(addButton);
		east.add(removeButton);
		add(east, BorderLayout.EAST);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Fetches the course name and number from the appropriate text fields.
	 * @return Array containing the name and number of course in first first and second elements respectively.
	 */
	public String[] getCourseInfo() {
		String[] results = new String[2];
		results[0] = courseName.getText();
		results[1] = courseNumber.getText();
		return results;
	}
	
	/**
	 * Writes to the studentContent text area.
	 * @param info String to be written to text area.
	 */
	public void writeToStudentContent(String info) {
		studentContent.append(info);
	}
	
	/**
	 * Retrieves the string in the studentName text field.
	 * @return User inputted text in studentName text field.
	 */
	public String getStudentName() {
		return studentName.getText();
	}
	
//Getters and setters
	public JButton getSubmitButton() {
		return submitButton;
	}
	
	public JButton getAddButton() {
		return addButton;
	}
	
	public JButton getRemoveButton() {
		return removeButton;
	}
}
