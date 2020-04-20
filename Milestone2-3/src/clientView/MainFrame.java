package clientView;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * Main window of the GUI.
 * 
 * Initializes and controls the JFrame of the GUI.
 * Also contains methods to display messages to the screen,
 * update the GUI, and get the button content.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class MainFrame extends JFrame {

	/**
	 * Default not used
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Center text area of the GUI
	 */
	private JTextArea catalogueContent;
	/**
	 *  Top text label of the GUI
	 */
	private JLabel topLabel;
	/**
	 * Display catalogue button
	 */
	private JButton showCatalogue;

	/**
	 * Add or remove button
	 */
	private JButton addRemove;
	/**
	 * View registrations button
	 */
	private JButton viewStudentRegs;
	/**
	 * Button used to search through the catalogs
	 */
	private JButton searchCatalogue;
	/**
	 * Button used to add a new course offering in admin mode
	 */
	private JButton addOffering;
	/**
	 * Panel contains the first two buttons
	 */
	private JPanel topButtonPanel;
	/**
	 * Panel containing the last three buttons
	 */
	private JPanel bottomButtonPanel;
	/**
	 * Panel containing all buttons
	 */
	private JPanel buttonPanel;
	/**
	 * Panel containing the center text 
	 */
	private JPanel centerPanel;
	/**
	 * Panel containing the upper text
	 */
	private JPanel topPanel;
	/**
	 * Scroll pane for the center panel
	 */
	private JScrollPane scrollPane;
	/**
	 * Controls whether the MainFrame is launched in admin or student mode
	 */
	private boolean isAdmin;
	/**
	 * Id of the student if launched in student mode
	 */
	private int studentId;


	/**
	 * Constructs an empty and invisible frame for the login window.
	 * @throws HeadlessException if a Headless exception occurs
	 */
	public MainFrame() throws HeadlessException {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		//Sets the size and visibility
		pack();

		setVisible(false);


	}
	/**
	 * Constructs the main window or frame of the GUI. The window will be formatted in an admin 
	 * or student view based on the String given by message.
	 * @param message message from the server containing whether the user is an admin or student
	 */
	public MainFrame(String message) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Setting up whether mainFrame is in admin or student mode 
		String [] content = message.split("\\s+");
		if(content[0].equals("admin"))
			isAdmin = true;
		else {
			isAdmin = false;
			studentId = Integer.parseInt(content[1]);
		}

		//Initializing the Panels
		topButtonPanel = new JPanel();
		bottomButtonPanel = new JPanel();
		topPanel = new JPanel();
		centerPanel = new JPanel();


		//Initializing the individual components
		if(isAdmin)
			topLabel = new JLabel("Welcome to the Admin Registration System");
		else
			topLabel = new JLabel("Welcome to the Student Registration System");

		catalogueContent = new JTextArea(20,50);
		catalogueContent.setEditable(false);
		catalogueContent.setFont(new Font("Comic Sans MS", Font.PLAIN,10));
		scrollPane = new JScrollPane(catalogueContent);

		showCatalogue = new JButton("Show Catalogue");
		addRemove = new JButton("Add or Remove a Course");
		viewStudentRegs = new JButton("View Student Registrations");
		searchCatalogue = new JButton("Search Through Catalogue");
		if(isAdmin)
			addOffering = new JButton("Add New Course Offering");

		//Adding the components to their final panels
		topPanel.add(topLabel);
		topButtonPanel.add(showCatalogue);
		topButtonPanel.add(addRemove);

		bottomButtonPanel.add(viewStudentRegs);
		bottomButtonPanel.add(searchCatalogue);

		if(isAdmin)
			bottomButtonPanel.add(addOffering);

		centerPanel.add(scrollPane);

		GridLayout allButtons = new GridLayout(2,3);
		buttonPanel = new JPanel(allButtons);
		buttonPanel.add(topButtonPanel);
		buttonPanel.add(bottomButtonPanel);

		//Adding the panels to the JFrame in the desired location
		add(topPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}
	/**
	 * Sets the text to the String given by content
	 * @param content String to be displayed
	 */
	public void fillCatalogueContent(String content)
	{
		add(centerPanel, BorderLayout.CENTER);
		catalogueContent.setText(content);
		catalogueContent.setCaretPosition(0); //Scroll to top
		pack();
	}

	/***GETTERS AND SETTERS***/

	/**
	 * Gets the addRemove JButton.
	 * @return the addRemove button
	 */
	public JButton getAddRemove() {
		return addRemove;
	}
	/**
	 * Gets the searchCatalogue JButton.
	 * @return the searchCatalogue button
	 */
	public JButton getSearchCatalogue() {
		return searchCatalogue;
	}
	/**
	 * Gets the viewStudentRegs JButton.
	 * @return the viewStudentRegs button
	 */
	public JButton getViewStudentRegs() {
		return viewStudentRegs;
	}
	/**
	 * Gets the showCatalog JButton.
	 * @return the showCatalog button
	 */
	public JButton getShowCatalogue() {
		return showCatalogue;
	}
	/**
	 * Gets the addOffering JButton.
	 * @return the addOffering button
	 */
	public JButton getAddOffering() {
		return addOffering;
	}
	/**
	 * Returns whether the user is an admin or student.
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}
	/**
	 * Gets the id of the student if logged in as a student
	 * @return the studentId
	 */
	public String getStudentId() {
		return Integer.toString(studentId);
	}
	
}
