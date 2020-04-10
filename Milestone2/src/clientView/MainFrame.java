/**
 * 
 */
package clientView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import clientController.Listeners;

/**
 * @author lukeg
 *
 */
public class MainFrame extends JFrame {

	private Listeners l;
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
	 * Insert button
	 */
	private JButton showCatalogue;
	/**
	 * Find button
	 */
	private JButton addRemove;
	/**
	 * Browse button
	 */
	private JButton viewStudentRegs;
	/**
	 * Button used to create a new BST
	 */
	private JButton searchCatalogue;
	/**
	 * Panel contains the top buttons
	 */
	private JPanel topButtonPanel;
	/**
	 * Panel containing the bottom buttons
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
		super("Main Window");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		FlowLayout fl = new FlowLayout();
		
		topButtonPanel = new JPanel();
		bottomButtonPanel = new JPanel();
		
		topPanel = new JPanel();
		
		centerPanel = new JPanel();
		
		topLabel = new JLabel("Main Window");
		catalogueContent = new JTextArea(20, 40);
		scrollPane = new JScrollPane(catalogueContent);
		showCatalogue = new JButton("Show Catalogue");
		addRemove = new JButton("Add or Remove a Course");
		viewStudentRegs = new JButton("View Student Registrations");
		searchCatalogue = new JButton("Search Through Catalogue");
		
		topPanel.add(topLabel);
		topButtonPanel.add(showCatalogue);
		topButtonPanel.add(addRemove);
		bottomButtonPanel.add(viewStudentRegs);
		bottomButtonPanel.add(searchCatalogue);
		centerPanel.add(scrollPane);
		topButtonPanel.add(bottomButtonPanel);
		add(topPanel, BorderLayout.NORTH);
		add(topButtonPanel, BorderLayout.SOUTH);
		//add(bottomButtonPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		
		l = new Listeners(this); // Create Listeners/GUI Controller
		
		LoginWindow temp = new LoginWindow(this, "Login Window");
	}

	/**
	 * @param gc
	 */
	public MainFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public MainFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param gc
	 */
	public MainFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	public JButton getAddRemove() {
		return addRemove;
	}
	
	public JButton getSearchCatalogue() {
		return searchCatalogue;
	}
	
	public JButton getViewStudentRegs() {
		return viewStudentRegs;
	}
	
	public static void main(String[] args) {
		MainFrame test = new MainFrame();
	}
}
