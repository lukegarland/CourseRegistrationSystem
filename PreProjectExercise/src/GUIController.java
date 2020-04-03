import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController {
	GUIModel theModel;
	GUIView theView;
	
	public GUIController(GUIModel theModel, GUIView theView) {
		this.theModel = theModel;
		this.theView = theView;
		theView.addInsertListener(new InsertListener());
		theView.addFindListener(new FindListener());
		theView.addBrowseListener(new BrowseListener());
		theView.addCreateListener(new CreateListener());
	}
	

	class InsertListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String student = theView.getNewStudentInfo();
			theView.showMessage("Added: " + student);
			theModel.addNewStudent(student);
			
		}
	}
	class FindListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = theView.getIdFromUser();
			theView.showMessage(theModel.getStudentInfo(id));
		}
	}
	class BrowseListener implements ActionListener{
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
	
	class CreateListener implements ActionListener{
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
