package clientController;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;

import clientView.*;
/**
 * Responsible for creating and managing all listeners for all GUI interactions.
 * @author Guillaume Raymond-Fauteux
 * @since April 10 2020
 * @version 0.0
 *
 */
public class Listeners 
{

	private MainFrame mainFrame;
	private Client client;
	
	public Listeners(Client c, MainFrame m)
	{
		mainFrame = m;
		client = c;
	}
	
	//Constructor for testing (no client needed)
	public Listeners(MainFrame m)
	{
		mainFrame = m;
		
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
			System.out.println(studentName);
			addRemoveDialog.submitPressed();
			
			addRemoveDialog.getAddButton().addActionListener((ActionEvent ee) -> {
				String[] results = addRemoveDialog.getCourseInfo();
				for(String st : results) {
					System.out.println(st);
				}
			});
			
			addRemoveDialog.getRemoveButton().addActionListener((ActionEvent eee) -> {
				String[] results = addRemoveDialog.getCourseInfo();
				for(String st : results) {
					System.out.println(st);
				}
			});
		});
	}
	
	/**
	 * Creates the search catalogue pop-up window and assigns appropriate listeners.
	 */
	private void searchCatalogueDialog() {
		SearchCatalogue searchCatalogueDialog = new SearchCatalogue(mainFrame);
		
		searchCatalogueDialog.getSubmitButton().addActionListener((ActionEvent e) -> {
			String[] results = searchCatalogueDialog.getCourseInfo();
			for(String st : results) {
				System.out.println(st);
			}
		});
	}
	
	/**
	 * Creates the search student pop-up window and assigns appropriate listeners.
	 */
	private void searchStudentDialog() {
		SearchStudent searchStudentDialog = new SearchStudent(mainFrame);
		
		searchStudentDialog.getSubmitButton().addActionListener((ActionEvent e) -> {
			String studentName = searchStudentDialog.getStudentName();
			System.out.println(studentName);
		});
	}

	//Main for testing
	public static void main(String args[]) {
		MainFrame mainFrame = new MainFrame();
		
		Listeners listener = new Listeners(mainFrame);
	}
	
}
