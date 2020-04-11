package clientView;

import java.awt.*;
import java.awt.Dialog.ModalityType;

import javax.swing.*;

/**
 * Provides the member variables and methods required for the creation of and interaction with the search catalogue
 * pop-up window.
 * @author Guillaume Raymond-Fauteux
 * @since April 10 2020
 * @version 0.0
 *
 */
public class SearchCatalogue extends JDialog{
	private static final long serialVersionUID = 1L;
	
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
	 * Text area containing all course offerings/additional information.
	 */
	private JTextArea courseContent;
	/**
	 *  ScrollPane to enable scrolling through courseContent.
	 */
	private JScrollPane courseContentScroll;
	
	/**
	 * North panel where course name and number fields will be displayed.
	 */
	private JPanel north;
	/**
	 * Center panel where submit button will be displayed.
	 */
	private JPanel center;
	/**
	 * South panel where courseContent text area will be displayed.
	 */
	private JPanel south;
	
	/**
	 * Button to search for course.
	 */
	private JButton submitButton;
	
	/**
	 * Constructs a dialog pane where a user can search for course's offerings and details.
	 * @param owner JFrame which owns the dialog pane.
	 */
	public SearchCatalogue(JFrame owner) {
		this(owner, "Search catalogue");
	}
	
	/**
	 * Constructs a dialog pane where a user can search for course's offerings and details.
	 * @param owner JFrame which owns the dialog pane.
	 * @param title Name of the dialog pane.
	 */
	public SearchCatalogue(JFrame owner, String title) {
		super(owner, title);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//North panel
		north = new JPanel();
		
		courseNameLabel = new JLabel("Course name:");
		courseName = new JTextField(4);
		
		courseNumberLabel = new JLabel("Course number:");
		courseNumber = new JTextField(3);
		
		north.add(courseNameLabel);
		north.add(courseName);
		north.add(courseNumberLabel);
		north.add(courseNumber);
		add(north, BorderLayout.NORTH);
		
		//Center panel
		center = new JPanel();
		
		submitButton = new JButton("Submit");
		
		center.add(submitButton);
		add(center, BorderLayout.CENTER);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		pack();
	}
	
	/**
	 * Populates the remaining of the dialog pane when a course name and number has been input.
	 */
	public void submitPressed() {
		//South Panel
		south = new JPanel();
		
		courseContent = new JTextArea(10,40);
		courseContentScroll = new JScrollPane(courseContent);
		
		south.add(courseContentScroll);
		add(south, BorderLayout.SOUTH);
		
		pack();
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
	 * Writes to the courseContent text area.
	 * @param info String to be written to text area.
	 */
	public void writeToCourseContent(String info) {
		courseContent.append(info);
	}
	
//Getters and setters
	public JButton getSubmitButton() {
		return submitButton;
	}
}
