package uiLayer;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PrintReceipt extends JDialog{

	private static final long serialVersionUID = 1L;
	private final JScrollPane scrollPane = new JScrollPane();

	public PrintReceipt(String t) {
		
		setResizable(false);
		getContentPane().setLayout(null);
		scrollPane.setBounds(10, 11, 424, 477);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setText(t);
		// TODO Auto-generated constructor stub
	}
}
