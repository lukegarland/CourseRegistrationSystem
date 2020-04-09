/**
 * 
 */
package clientView;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * @author lukeg
 *
 */
public class MainFrame extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public MainFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gc
	 */
	public MainFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public MainFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param gc
	 */
	public MainFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

}
