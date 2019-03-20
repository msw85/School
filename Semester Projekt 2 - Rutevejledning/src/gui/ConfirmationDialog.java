package gui;

import javax.swing.JOptionPane;
/**
 * 
 * @author Gruppe 6, DMAA0917
 * 
 * This is used various places in GUI to display option panes to the user.
 *
 */
public class ConfirmationDialog extends JOptionPane {
	public ConfirmationDialog() {
		setMessageType(JOptionPane.QUESTION_MESSAGE);
		setOptionType(JOptionPane.YES_NO_OPTION);
	}

}
