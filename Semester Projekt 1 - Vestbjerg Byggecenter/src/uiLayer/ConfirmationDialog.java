package uiLayer;

import javax.swing.JOptionPane;

public class ConfirmationDialog extends JOptionPane {
	public ConfirmationDialog() {
		setMessageType(JOptionPane.QUESTION_MESSAGE);
		setOptionType(JOptionPane.YES_NO_OPTION);
	}

}
