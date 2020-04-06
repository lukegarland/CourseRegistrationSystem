import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Controller for a registration GUI.
 * 
 * The controller initializes and contains the view 
 * and model of the GUI.
 * 
 * @author C. Faith
 * @version 1.0
 * @since April 3, 2020
 *
 */
public class GUIController {
	/**
	 * The model of the GUI.
	 */
	GUIModel theModel;
	/**
	 * The graphics container of the GUI
	 */
	GUIView theView;
	/**
	 * Constructs a GUIController with given parameters for
	 * theModel and theView. The GUI's buttons are given 
	 * their Listeners
	 * @param theModel model of the GUI
	 * @param theView graphics container of the GUI
	 */
	public GUIController(GUIModel theModel, GUIView theView) {
		this.theModel = theModel;
		this.theView = theView;
		theView.addInsertListener(new InsertListener());
		theView.addFindListener(new FindListener());
		theView.addBrowseListener(new BrowseListener());
		theView.addCreateListener(new CreateListener());
	}
	
	/**
	 * ActionListener for the 'Insert' Button.
	 * @author C. Faith
	 * @version 1.0
	 * @since April 3, 2020
	 *
	 */
	class InsertListener implements ActionListener{
		/**
		 * Gets the student info from the View. 
		 * Then, the new student is added to the BST.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			String student = theView.getNewStudentInfo();
			if(student != null)
			{
				theView.showMessage("Added: " + student);
				theModel.addNewStudent(student);
			}

		}
	}
	/**
	 * ActionListener for the 'Find' Button.
	 * @author C. Faith
	 * @version 1.0
	 * @since April 3, 2020
	 *
	 */
	class FindListener implements ActionListener{
		/**
		 * Gets the id from the user and displays the 
		 * result of searching for it in the BST.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = theView.getIdFromUser();
			theView.showMessage(theModel.getStudentInfo(id));
		}
	}
	/**
	 * ActionListener for the 'Browse' Button.
	 * @author C. Faith
	 * @version 1.0
	 * @since April 3, 2020
	 *
	 */
	class BrowseListener implements ActionListener{
		/**
		 * Gets the content of the tree from theModel
		 * then displays the list in theView.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String treeContent = theModel.printTree();
			if(treeContent == null) {
				theView.showMessage("The current tree is empty.\nPlease fill or create a new tree.");
			}
			else 
				theView.addTextContent(treeContent);
		}
	}
	/**
	 * ActionListener for the 'Create Tree From File' Button.
	 * @author C. Faith
	 * @version 1.0
	 * @since April 3, 2020
	 *
	 */
	class CreateListener implements ActionListener{
		/**
		 * Gets the filename from the user and tells theModel 
		 * to import a BST from the selected file.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String filename = theView.getFilename();
			if(filename == null) {
				theView.showMessage("Please enter a valid filename.");
			}
			else {
				theModel.importFile(filename);
			}
		}
	}
}
