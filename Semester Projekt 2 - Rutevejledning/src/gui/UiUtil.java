package gui;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

public class UiUtil {
	public UiUtil() {
		// TODO Auto-generated constructor stub
	}
	public static NumberFormatter initFormatter() {
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		return formatter;
	}

	public static DefaultTableModel getTable() {

		DefaultTableModel tableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		return tableModel;
	}

	public static boolean validPhoneInput (String s){
		Pattern pattern = Pattern.compile("^\\d{8}$");
		Matcher matcher = pattern.matcher(s);
		return matcher.find();
	}

	public static boolean validEmailInput (String s){
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
		Matcher matcher = pattern.matcher(s);
		return matcher.find();
	}
}
