package clientView;

import java.awt.BorderLayout;
import java.awt.Font;
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
 * @version 0.1
 * @since April 11, 2020
 *
 */
public class MainFrame extends JFrame {

	/**
	 * Default
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
	 * Panel contains the first two buttons
	 */
	private JPanel topButtonPanel;
	/**
	 * Panel containing the last two buttons
	 */
	private JPanel bottomButtonPanel;
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
	 * @throws HeadlessException
	 */
	public MainFrame() throws HeadlessException {
		//Future work
		//LoginWindow temp = new LoginWindow(this, "Login Window");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Initializing the Panels
		topButtonPanel = new JPanel();
		bottomButtonPanel = new JPanel();
		topPanel = new JPanel();
		centerPanel = new JPanel();
		
		//Initializing the individual components
		topLabel = new JLabel("Main Window");
		
		catalogueContent = new JTextArea(20,50);
		catalogueContent.setEditable(false);
		catalogueContent.setFont(new Font("Comic Sans MS", Font.PLAIN,10));
		scrollPane = new JScrollPane(catalogueContent);
		
		showCatalogue = new JButton("Show Catalogue");
		addRemove = new JButton("Add or Remove a Course");
		viewStudentRegs = new JButton("View Student Registrations");
		searchCatalogue = new JButton("Search Through Catalogue");
		
		//Adding the components to their final panels
		topPanel.add(topLabel);
		topButtonPanel.add(showCatalogue);
		topButtonPanel.add(addRemove);
		
		bottomButtonPanel.add(viewStudentRegs);
		bottomButtonPanel.add(searchCatalogue);
		
		centerPanel.add(scrollPane);
		
		topButtonPanel.add(bottomButtonPanel);
		
		//Adding the panels to the JFrame in the desired location
		add(topPanel, BorderLayout.NORTH);
		add(topButtonPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		
		//Sets the size and visibility
		pack();
		setVisible(true);
		
		
	}
	/**
	 * Sets the text to the String given by content
	 * @param content String to be displayed
	 */
	public void fillCatalogueContent(String content)
	{
		catalogueContent.setText(content);
		catalogueContent.setCaretPosition(0); //Scroll to top
	}
	/***GETTERS AND SETTERS***/
	public JButton getAddRemove() {
		return addRemove;
	}
	
	public JButton getSearchCatalogue() {
		return searchCatalogue;
	}
	
	public JButton getViewStudentRegs() {
		return viewStudentRegs;
	}
	public JButton getShowCatalogue() {
		return showCatalogue;
	}
	

}
