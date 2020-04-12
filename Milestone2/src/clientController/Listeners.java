package clientController;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import clientView.*;
import common.*;
/**
 * Responsible for creating and managing all listeners for all GUI interactions.
 * This is effectively the GUI Controller
 * @author Guillaume Raymond-Fauteux
 * @since April 10 2020
 * @version 1.0
 *
 */
public class Listeners 
{

	/**
	 * The primary Jrame which contains all buttons to access sub menus.
	 */
	private MainFrame mainFrame;
	/**
	 * The client which can send messages to and from the server.
	 */
	private Client client;
	
	/**
	 * Constructs a listener which keeps track of all user interaction with the GUI.
	 * @param c The client from which messages may be sent and received from server.
	 * @param m the main JFrame from which GUI system is rooted.
	 */
	public Listeners(Client c, MainFrame m)
	{
		this(m);
		client = c;
	}
	
	/**
	 * Constructs a listener which keeps track of all user interaction with the GUI.
	 * @param m the main JFrame from which GUI system is rooted.
	 */
	private Listeners(MainFrame m)
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
		AddRemoveStudentWindow addRemoveDialog = new AddRemoveStudentWindow(mainFrame);
		
		
		addRemoveDialog.getSubmitButton().addActionListener((ActionEvent e) -> {
			String studentName = addRemoveDialog.getStudentName();

			String response = client.communicate(MessageTypes.searchStudentCourses, studentName);
			
			if(response == null)
			{
				addRemoveDialog.setVisible(false);
				return;
			}
			
			
			addRemoveDialog.submitPressed();
			addRemoveDialog.writeToStudentContent(response);
			
			
			//If no listeners are already attached to sub-dialog buttons, add them.
			if( addRemoveDialog.getAddButton().getActionListeners().length == 0) {
			
				addRemoveDialog.getAddButton().addActionListener((ActionEvent ee) -> {
					String[] results = addRemoveDialog.getCourseInfo(); 
					String r1 = client.communicate(MessageTypes.addCourse, studentName + " " + results[0] + " " + results[1] + " " + results[2]);
					
					if(r1 != null) //successful message back
						JOptionPane.showMessageDialog(addRemoveDialog, r1);
					else
						addRemoveDialog.setVisible(false);
					
				});
				
				
				
				
				addRemoveDialog.getRemoveButton().addActionListener((ActionEvent eee) -> {
					String[] results = addRemoveDialog.getCourseInfo();
					String r1 = client.communicate(MessageTypes.removeCourse, studentName + " " + results[0] + " " + results[1]);
					
					if(r1 != null)
						JOptionPane.showMessageDialog(addRemoveDialog, r1);
					else
						addRemoveDialog.setVisible(false);

				});
			}
			
		});
		
		addRemoveDialog.setVisible(true);
		
	}
	
	/**
	 * Creates the search catalogue pop-up window and assigns appropriate listeners.
	 */
	private void searchCatalogueDialog() {
		SearchCatalogueWindow searchCatalogueDialog = new SearchCatalogueWindow(mainFrame);
		
		searchCatalogueDialog.getSubmitButton().addActionListener((ActionEvent e) -> {
			
			searchCatalogueDialog.submitPressed();
			
			String[] results = searchCatalogueDialog.getCourseInfo();
			String r1 = client.communicate(MessageTypes.searchCatalogue, results[0] + " " + results[1]);
			
			if(r1 != null)
				searchCatalogueDialog.writeToCourseContent(r1);
			else 
				searchCatalogueDialog.setVisible(false);

		});
		
		searchCatalogueDialog.setVisible(true);
		searchCatalogueDialog.pack();
	}
	
	/**
	 * Creates the search student pop-up window and assigns appropriate listeners.
	 */
	private void searchStudentDialog() {
		SearchStudentWindow searchStudentDialog = new SearchStudentWindow(mainFrame);
		
		searchStudentDialog.getSubmitButton().addActionListener((ActionEvent e) -> {
			
			searchStudentDialog.submitPressed();
			String results = searchStudentDialog.getStudentName();
			
			String r1 = client.communicate(MessageTypes.searchStudentCourses, results);
			if(r1 != null)
				searchStudentDialog.writeToStudentContent(r1);
			else
				searchStudentDialog.setVisible(false);

		});
		
		searchStudentDialog.setVisible(true);
		searchStudentDialog.pack();
	}


	
}
