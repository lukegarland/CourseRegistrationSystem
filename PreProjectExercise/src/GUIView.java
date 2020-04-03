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

public class GUIView extends JFrame{
	private static final long serialVersionUID = 1L;

	private JTextArea myTextArea;
	private JLabel topLabel;
	
	private JButton insert;
	private JButton find;
	private JButton browse;
	private JButton createTree;
	
	private JPanel buttonPanel;
	private JPanel textPanel;
	private JPanel centerPanel;
	
	private JScrollPane scrollPane;
	
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
	
	void addInsertListener(ActionListener action) {
		insert.addActionListener(action);
	}
	void addFindListener(ActionListener action) {
		find.addActionListener(action);
	}
	void addBrowseListener(ActionListener action) {
		browse.addActionListener(action);
	}
	void addCreateListener(ActionListener action) {
		createTree.addActionListener(action);
	}

	public void addTextContent(String treeContent) {
		myTextArea.setText(treeContent + "Test");
	}

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
	public void showMessage(String message) {
		UIManager.put("OptionPane.okButtonText", "OK");
		JOptionPane.showMessageDialog(this, message);
	}
	
	private boolean fileFound(String filename) {
		File tmpDir = new File(filename);
		return tmpDir.exists();
	}

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
