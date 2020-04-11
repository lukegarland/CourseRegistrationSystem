package clientController;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import clientView.*;
import common.*;
/**
 * Responsible for creating and managing all listeners for all GUI interactions.
 * @author Guillaume Raymond-Fauteux
 * @since April 10 2020
 * @version 0.1
 *
 */
public class Listeners 
{

	private MainFrame mainFrame;
	private Client client;
	
	public Listeners(Client c, MainFrame m)
	{
		this(m);
		client = c;
	}
	
	//Constructor for testing (no client needed)
	public Listeners(MainFrame m)
	{
		mainFrame = m;
		
		mainFrame.getShowCatalogue().addActionListener((ActionEvent e) -> {
			String response = client.communicate(MessageTypes.getCatalogue, "");
			mainFrame.fillCatalogueContent(response);
		});
		
		
		mainFrame.getAddRemove().addActionListener((ActionEvent e) -> {
			addRemoveDialog();
		});
		
		mainFrame.getSearchCatalogue().addActionListener((ActionEvent ee) -> {
			searchCatalogueDialog();
		});
		
		mainFrame.getViewStudentRegs().addActionListener((ActionEvent eee) -> {
			searchStudentDialog();
		});
	}
	
	/**
	 * Creates the add/remove student pop-up window and assigns appropriate listeners.
	 */
	private void addRemoveDialog() {
		AddRemoveStudent addRemoveDialog = new AddRemoveStudent(mainFrame);
		
		
		addRemoveDialog.getSubmitButton().addActionListener((ActionEvent e) -> {
			String studentName = addRemoveDialog.getStudentName();
			
			addRemoveDialog.submitPressed();
			
			String response = client.communicate(MessageTypes.searchStudentCourses, studentName);
			
			addRemoveDialog.writeToStudentContent(response);
			
			
			//If no listeners are already attached to sub-dialog buttons, add them.
			if( addRemoveDialog.getAddButton().getActionListeners().length == 0) {
			
				addRemoveDialog.getAddButton().addActionListener((ActionEvent ee) -> {
					String[] results = addRemoveDialog.getCourseInfo(); 
					String r1 = client.communicate(MessageTypes.addCourse, studentName + " " + results[0] + " " + results[1] + " " + results[2]);
					JOptionPane.showMessageDialog(addRemoveDialog, r1);
					
				});
				
				
				
				
				addRemoveDialog.getRemoveButton().addActionListener((ActionEvent eee) -> {
					String[] results = addRemoveDialog.getCourseInfo();
					String r1 = client.communicate(MessageTypes.removeCourse, studentName + " " + results[0] + " " + results[1]);
					JOptionPane.showMessageDialog(addRemoveDialog, r1);
				});
			}
			
		});
		
		addRemoveDialog.setVisible(true);
		
	}
	
	/**
	 * Creates the search catalogue pop-up window and assigns appropriate listeners.
	 */
	private void searchCatalogueDialog() {
		SearchCatalogue searchCatalogueDialog = new SearchCatalogue(mainFrame);
		
		searchCatalogueDialog.getSubmitButton().addActionListener((ActionEvent e) -> {
			
			searchCatalogueDialog.submitPressed();
			
			String[] results = searchCatalogueDialog.getCourseInfo();
			String r1 = client.communicate(MessageTypes.searchCatalogue, results[0] + " " + results[1]);
			searchCatalogueDialog.writeToCourseContent(r1);
		});
		
		searchCatalogueDialog.setVisible(true);
	}
	
	/**
	 * Creates the search student pop-up window and assigns appropriate listeners.
	 */
	private void searchStudentDialog() {
		SearchStudent searchStudentDialog = new SearchStudent(mainFrame);
		
		searchStudentDialog.getSubmitButton().addActionListener((ActionEvent e) -> {
			
			searchStudentDialog.submitPressed();
			String results = searchStudentDialog.getStudentName();
			
			String r1 = client.communicate(MessageTypes.searchStudentCourses, results);
			searchStudentDialog.writeToStudentContent(r1);
		});
		
		searchStudentDialog.setVisible(true);
	}


	
}
