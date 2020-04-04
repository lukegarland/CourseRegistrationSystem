/**
 * Front end application used to test and use the registration GUI.
 * @author C. Faith
 * @version 1.0
 * @since April 3, 2020
 *
 */
public class GUIApp {
	/**
	 * Creates and initializes a GUIController to use
	 * @param args Default - not used
	 */
	public static void main(String[] args) {
		GUIController myGUI = new GUIController(new GUIModel(), new GUIView());
	}

}
