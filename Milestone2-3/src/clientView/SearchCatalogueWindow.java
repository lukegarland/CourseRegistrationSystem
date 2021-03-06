package clientView;

import java.awt.*;

import javax.swing.*;

/**
 * The dialog window of the GUI to search through the course catalogue.
 * 
 * Provides the member variables and methods required for the creation of and interaction with the search catalogue
 * pop-up window.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class SearchCatalogueWindow extends JDialog{
	/**
	 * Default not used.
	 */
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
	public SearchCatalogueWindow(JFrame owner) {
		this(owner, "Search catalogue");
	}
	
	/**
	 * Constructs a dialog pane where a user can search for course's offerings and details.
	 * @param owner JFrame which owns the dialog pane.
	 * @param title Name of the dialog pane.
	 */
	public SearchCatalogueWindow(JFrame owner, String title) {
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
		
		if(south != null)// if submit was already pressed, dont create more panels
		{
			pack();
			return;
		}
		//South Panel
		south = new JPanel();
		
		courseContent = new JTextArea(20,50);
		courseContentScroll = new JScrollPane(courseContent);
		courseContent.setEditable(false);
		courseContent.setCaretPosition(0);
		
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
		courseContent.setText(info);
		courseContent.setCaretPosition(0);
	}
	
//Getters and setters
	
	/**
	 * Gets the submitButton JButton.
	 * @return the submitButton
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}
}
