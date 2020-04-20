/**
 * 
 */
package clientView;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

/**
 * The dialog window of the admin GUI to add a course offering.
 * 
 * Provides the member variables and methods required for the creation of and interaction with the add course
 * offering pop-up window. The user can add to the course catalogue as well as view the current course 
 * list of the catalogue.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class AddCourseOfferingWindow extends JDialog {
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
	 * Text field where user enters the course offering number.
	 */
	private JTextField courseOffering;
	/**
	 * Label for the courseOffering text field.
	 */
	private JLabel courseOfferingLabel;
	/**
	 * Text field where user enters the offering capacity.
	 */
	private JTextField sectionCap;
	/**
	 * Label for the sectionCap text field.
	 */
	private JLabel sectionCapLabel;
	/**
	 * Text area where the current course list can be viewed.
	 */
	private JTextArea courseContent;
	/**
	 * ScrollPane for the course list.
	 */
	private JScrollPane studentContentScroll;
	/**
	 * Panel for the left side of the frame.
	 */
	private JPanel west;
	/**
	 * Panel for the right side of the frame.
	 */
	private JPanel east;
	/**
	 * Panel for the top side of the frame.
	 */
	private JPanel north;
	/**
	 * Button to add course to the catalog.
	 */
	private JButton addButton;
	/**
	 * Button to refresh the catalog list in the text area.
	 */
	private JButton showCatalogueButton;
	/**
	 * Top label for the course content area.
	 */
	private JLabel topLabel;

	/**
	 * Constructs a dialog pane where user can add a new course offering to the catalog.
	 * @param owner JFrame which owns the created dialog pane.
	 */
	public AddCourseOfferingWindow(MainFrame owner) {
		this(owner, "Add New Offering");
	}

	/**
	 * Constructs a dialog pane where user can add a new course offering to the catalog.
	 * @param owner JFrame which owns the created dialog pane.
	 * @param title Name of the dialog pane.
	 */
	public AddCourseOfferingWindow(MainFrame owner, String title)
	{
		super(owner, title);
		setLayout(new BorderLayout(10,10));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//North Panel
		north = new JPanel();
		
		topLabel = new JLabel("Course List");
		topLabel.setAlignmentX(LEFT_ALIGNMENT);
		topLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		north.add(topLabel);
		add(north, BorderLayout.NORTH);
		
		//West Panel
		west = new JPanel(new GridLayout(0,1));
		
		courseContent = new JTextArea(20,50);
		studentContentScroll = new JScrollPane(courseContent);
		courseContent.setEditable(false);
		
		west.add(studentContentScroll);
		
		add(west, BorderLayout.WEST);

		//East Panel
		east = new JPanel();
		east.setLayout( new GridLayout(0,2) );
		courseName = new JTextField(4);
		courseNameLabel = new JLabel("Course name:");

		courseNumber = new JTextField(4);
		courseNumberLabel = new JLabel("Course number:");

		courseOffering = new JTextField(4);
		courseOfferingLabel = new JLabel("Course Offering:");

		sectionCap = new JTextField(4);
		sectionCapLabel = new JLabel("Section Capacity:");


		addButton = new JButton("Add course");
		showCatalogueButton = new JButton("Current Catalogue");

		east.add(courseNameLabel);
		east.add(courseName);

		east.add(courseNumberLabel);
		east.add(courseNumber);

		east.add(courseOfferingLabel);
		east.add(courseOffering);
		
		east.add(sectionCapLabel);
		east.add(sectionCap);

		east.add(showCatalogueButton);
		east.add(addButton);
		add(east, BorderLayout.EAST);

		setModalityType(ModalityType.APPLICATION_MODAL);
		pack();
		
	}

	/**
	 * Fetches the course name and number from the appropriate text fields.
	 * @return Array containing the name and number of course in first first and second elements respectively.
	 */
	public String[] getCourseInfo() {
		String[] results = new String[4];
		results[0] = courseName.getText();
		results[1] = courseNumber.getText();
		results[2] = courseOffering.getText();
		results[3] = sectionCap.getText();
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
	/**
	 * Gets the addButton JButton.
	 * @return the addButton
	 */
	public JButton getAddButton() {
		return addButton;
	}
	
	/**
	 * Gets the showCatalogueButton JButton.
	 * @return the showCatalogueButton
	 */
	public JButton getShowCatalogueButton() {
		return showCatalogueButton;
	}
	/**
	 * Displays the message given by message to the user in a JOptionPane
	 * @param message message to be displayed
	 */
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}

