package clientView;

import java.awt.*;
import javax.swing.*;

public class SearchStudent extends JDialog{
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
	public SearchStudent(JFrame owner) {
		this(owner, "Search for student");
	}
	
	/**
	 * Constructs a dialog pane where user can search through a student's courses.
	 * @param owner JFrame which owns the dialog pane.
	 * @param title Name of the dialog pane.
	 */
	public SearchStudent(JFrame owner, String title) {
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
		
		//Center Panel
		center = new JPanel();
		studentContent = new JTextArea(20,30);
		studentContentScroll = new JScrollPane(studentContent);
		
		center.add(studentContentScroll);
		add(center, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Writes to the student content text area.
	 * @param info String to be written to text area.
	 */
	public void writeToStudentContent(String info) {
		studentContent.append(info);
	}
	
	/**
	 * Retrieves the name of the student inputted into the student name text field.
	 * @return User inputted data in student name text field.
	 */
	public String getStudentName() {
		return studentName.getText();
	}
	
//Getters and setters.
	public JButton getSubmitButton() {
		return submitButton;
	}
}
