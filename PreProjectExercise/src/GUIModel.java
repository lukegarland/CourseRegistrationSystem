import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class GUIModel {
	private BinSearchTree BST;

	public void importFile(String filename) {
		File file = new File(filename); 
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(filename + " file not found.");
		} 
		BST = new BinSearchTree();
		while (sc.hasNextLine()) {
			String [] temp = sc.nextLine().trim().split("\\s+"); 	
			BST.insert(temp[0], temp[1], temp[2], temp[3]);
		}
		sc.close();

	}
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
	public void addNewStudent(String student) {
		String [] fields = student.split(" ");
		BST.insert(fields[0], fields[1], fields[2], fields[3]);
		
	}

}
