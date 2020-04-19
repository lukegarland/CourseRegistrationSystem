package clientController;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import clientView.*;
import common.*;
/**
 * Controller responsible for creating and managing all listeners for all GUI interactions.
 * This is effectively the GUI Controller in our MVC design. The controller also manages
 * the dialog pop-up menus and their listeners.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class Listeners 
{

	/**
	 * The primary JFrame which contains all buttons to access sub menus.
	 */
	private MainFrame mainFrame;
	/**
	 * The client which can send messages to and from the server.
	 */
	private Client client;
	
	/**
	 * Constructs a listener which keeps track of all user interaction with the GUI.
	 * The user is asked to first login and then the mainframe is setup according to 
	 * the user's clearance level.
	 * 
	 * @param c The client from which messages may be sent and received from server.
	 * @param m the main JFrame from which GUI system is rooted.
	 */
	public Listeners(Client c, MainFrame m)
	{
		mainFrame = m;
		client = c;
		loginToServer();
		
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
		mainFrame.getAddOffering().addActionListener((ActionEvent eee) -> {
			addOffering();
		});
	}
	
	/**
	 * Creates the add course pop-up window and assigns appropriate listeners.
	 * Only used in the admin view of the mainFrame
	 */
	private void addOffering() {
		
		AddCourseOfferingWindow addOfferingDialog = new AddCourseOfferingWindow(mainFrame);
		String response = client.communicate(MessageTypes.getCatalogue, "");
		addOfferingDialog.writeToCourseContent(response);
		
		addOfferingDialog.getShowCatalogueButton().addActionListener((ActionEvent e) -> {
			String r1 = client.communicate(MessageTypes.getCatalogue, "");
			addOfferingDialog.writeToCourseContent(r1);
		});
		
		
		addOfferingDialog.getAddButton().addActionListener((ActionEvent e) -> {
			
			String[] results = addOfferingDialog.getCourseInfo();
			String r1 = client.communicate(MessageTypes.addOffering, results[0] + " " + results[1] + " " + results[2] + " " + results[3]);
			
			if(r1 != null)
				addOfferingDialog.displayMessage(r1);
			else 
				addOfferingDialog.setVisible(false);

		});
		addOfferingDialog.setVisible(true);
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
	/**
	 * Creates the admin/student login pop-up window and assigns appropriate listeners.
	 */
	private void loginToServer() {
		LoginWindow loginDialog = new LoginWindow(mainFrame, "Login Window");
			loginDialog.getLoginButton().addActionListener((ActionEvent e) -> {
			
			String results = loginDialog.getUsername() + " " + loginDialog.getPassword();
			String r1;
			if(loginDialog.isStudent()) 
				r1 = client.communicate(MessageTypes.loginStudent, results);
			else
				r1 = client.communicate(MessageTypes.loginAdmin, results);
			if(r1 != null) {
				loginDialog.setVisible(false);
				mainFrame = new MainFrame(r1);
			} else
				loginDialog.displayError("Invalid username or password. Please try again.");

		});
			loginDialog.pack();
			loginDialog.setVisible(true);
			
	}


	
}
