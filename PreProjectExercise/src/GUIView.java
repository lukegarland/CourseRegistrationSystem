import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * View class for a course registration system.
 * 
 * Initializes the and controls the JFrame of the GUI.
 * Also contains methods to display messages to the user,
 * update the GUI, and get input from the user.
 * 
 * @author C. Faith
 * @version 1.0
 * @since April 3, 2020
 *
 */
public class GUIView extends JFrame{
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Center text area of the GUI
	 */
	private JTextArea myTextArea;
	/**
	 *  Top text area of the GUI
	 */
	private JLabel topLabel;
	/**
	 * Insert button
	 */
	private JButton insert;
	/**
	 * Find button
	 */
	private JButton find;
	/**
	 * Browse button
	 */
	private JButton browse;
	/**
	 * Button used to create a new BST
	 */
	private JButton createTree;
	/**
	 * Panel contains the buttons
	 */
	private JPanel buttonPanel;
	/**
	 * Panel containing the upper text
	 */
	private JPanel textPanel;
	/**
	 * Panel containing the center text 
	 */
	private JPanel centerPanel;
	/**
	 * Scroll pane for the center panel
	 */
	private JScrollPane scrollPane;
	/**
	 * Default Constructor for the view. Initializes the necessary 
	 * panels and components for the frame.
	 */
	public GUIView() {
		super("Main Window");
		setSize(500, 400);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		FlowLayout fl = new FlowLayout();
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(fl);
		
		textPanel = new JPanel();
		
		centerPanel = new JPanel();
		
		topLabel = new JLabel("An Application to Maintain Student Records");
		myTextArea = new JTextArea(20, 40);
		scrollPane = new JScrollPane(myTextArea);
		insert = new JButton("Insert");
		find = new JButton("Find");
		browse = new JButton("Browse");
		createTree = new JButton("Create Tree From File");
		
		textPanel.add(topLabel);
		buttonPanel.add(insert);
		buttonPanel.add(find);
		buttonPanel.add(browse);
		buttonPanel.add(createTree);
		centerPanel.add(scrollPane);
		
		add(textPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	/**
	 * Adds the actionListener to for the 'Insert' button
	 * @param action action to be added
	 */
	void addInsertListener(ActionListener action) {
		insert.addActionListener(action);
	}
	/**
	 * Adds the actionListener to for the 'Find' button
	 * @param action action to be added
	 */
	void addFindListener(ActionListener action) {
		find.addActionListener(action);
	}
	/**
	 * Adds the actionListener to for the 'Browse' button
	 * @param action action to be added
	 */
	void addBrowseListener(ActionListener action) {
		browse.addActionListener(action);
	}
	/**
	 * Adds the actionListener to for the 'Create Tree From File' button
	 * @param action action to be added
	 */
	void addCreateListener(ActionListener action) {
		createTree.addActionListener(action);
	}

	/**
	 * Sets the text given by treeContent to the text area
	 * @param treeContent text to be set into the text area
	 */
	public void addTextContent(String treeContent) {
		myTextArea.setText(treeContent + "Test");
	}

	/**
	 * Asks the user to input a filename to import from
	 * @return the filename if it exists, null otherwise
	 */
	public String getFilename() {
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		UIManager.put("OptionPane.okButtonText", "OK");
		JTextField treeFile = new JTextField(10);
		Object[] inputFile = {"Enter the file name:", treeFile};
		int option = JOptionPane.showConfirmDialog(null, inputFile, "Input", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String filename = treeFile.getText();
			if(fileFound(filename)) { 
				return filename;
			}
			return null;
		}
		return null;
	}
	/**
	 * Displays the text given my message in a new window.
	 * @param message message to be displayed
	 */
	public void showMessage(String message) {
		UIManager.put("OptionPane.okButtonText", "OK");
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
	 * Checks to see if the file given by filename exists
	 * @param filename name of the file to be checked
	 * @return true if it does, false otherwise
	 */
	private boolean fileFound(String filename) {
		File tmpDir = new File(filename);
		return tmpDir.exists();
	}

	/**
	 * Gets the ID given by the user
	 * @return id if OK is selected, null otherwise
	 */
	public String getIdFromUser() {
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		UIManager.put("OptionPane.okButtonText", "OK");
		JTextField idField = new JTextField(10);
		Object[] inputID = {"Please enter the student's id:", idField};
		int option = JOptionPane.showConfirmDialog(null, inputID, "Input", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String id = idField.getText();
			return id;
		}
		return null;
	}

	/**
	 * Prompts the user to enter the data of a new 
	 * student to be entered to the tree
	 * @return data of the new student separated by spaces
	 */
	public String getNewStudentInfo() {
		UIManager.put("OptionPane.cancelButtonText", "Return to Main Window");
		UIManager.put("OptionPane.okButtonText", "Insert");
		JTextField field1 = new JTextField(10);
		JTextField field2 = new JTextField(10);
		JTextField field3 = new JTextField(10);
		JTextField field4 = new JTextField(10);
		Object[] message = {
				"Enter the Student ID:", field1,
				"Enter Faculty:", field2,
				"Enter Student's Major:", field3,
				"Enter Year:", field4,
			};
		int option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String id = field1.getText() + " " + field2.getText() + " " + field3.getText() + " " + field4.getText();
			return id;
		}
		return null;
	}
	
}
