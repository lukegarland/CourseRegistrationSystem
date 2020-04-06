import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;
/**
 * Model containing methods for GUIController to use.
 * 
 * This model uses a binary search tree in order to 
 * store and manipulate the data of students. The model 
 * contains tools to add view and modify the BInSearchTree
 * as needed by the controller.
 * 
 * @author C. Faith
 * @version 1.0
 * @since April 3, 2020
 *
 */
public class GUIModel {
	/**
	 * Binary search tree containing the student's info.
	 */
	private BinSearchTree BST;
	/**
	 * Imports the data from a file given by filename.
	 * Then, BST is reset and filled with the 
	 * file's contents.
	 * @param filename name of the file to open.
	 */
	public void importFile(String filename) {
		File file = new File(filename); 
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(filename + " file not found.");
			return;
		} 
		BST = new BinSearchTree();
		while (sc.hasNextLine()) {
			String [] temp = sc.nextLine().trim().split("\\s+"); 	
			BST.insert(temp[0], temp[1], temp[2], temp[3]);
		}
		sc.close();

	}
	/**
	 * Creates a list of the data stored in BST
	 * @return String containing the list 
	 */
	public String printTree() {
		if(BST.getRoot() == null) {
			return null; 
		}

		String content = new String(); 

		StringWriter treeInput = new StringWriter(); 
		PrintWriter treeOutput = new PrintWriter(treeInput);
		try {
			BST.print_tree(BST.getRoot(), treeOutput);
		}
		catch(IOException e) {
			System.err.println("Couldn't print Binary Search Tree");
		}
		content = treeInput.toString(); 
		content = content.replace("     ","\t");
		return content;
	}
	/**
	 * Returns the result of searching for a student 
	 * that matches the given id. If found, the student's
	 * data is returned. Otherwise, a message giving the 
	 * fault of the search is returned.
	 * @param id String of the id
	 * @return Output message of the search
	 */
	public String getStudentInfo(String id) {
		if(BST.getRoot() == null) {
			return "No students exist!"; 
		}
		Node temp = BST.find(BST.getRoot(), id);
		if(temp != null) {
			return temp.toString();
		}
		return "User not found!";
	}
	/**
	 * Adds a new student given by the data stored in 
	 * student to BST.
	 * @param student String containing the student's 
	 * data separated by spaces
	 */
	public void addNewStudent(String student) {
		String [] fields = student.split(" ");
		if(fields.length == 4)
			BST.insert(fields[0], fields[1], fields[2], fields[3]);
		
	}

}
