package uiLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllerLayer.EmployeeController;
import modelLayer.Product;

public class ShowEmp extends JDialog {
	
	private JTextField txtName;
	private JTextField txtAdd;
	private JTextField txtZip;
	private JTextField txtCity;
	private JTextField txtPho;
	private JTextField txtSalery;
	private JTextField txtBank;
	
	private EmployeeController empCon = new EmployeeController();

	public ShowEmp(int id) {
		getContentPane().setLayout(null);
		
		JButton btnAnnul = new JButton("Luk");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnul.setBounds(135, 281, 89, 23);
		getContentPane().add(btnAnnul);
		
		txtName = new JTextField();
		txtName.setText(empCon.getEmployee(id).getName());
		txtName.setEditable(false);
		txtName.setBounds(66, 11, 157, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAdd = new JTextField();
		txtAdd.setText(empCon.getEmployee(id).getAddress());
		txtAdd.setEditable(false);
		txtAdd.setBounds(66, 48, 157, 20);
		getContentPane().add(txtAdd);
		txtAdd.setColumns(10);
		
		JLabel lblName = new JLabel("Navn:");
		lblName.setBounds(10, 15, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblAdd = new JLabel("Adresse:");
		lblAdd.setBounds(10, 50, 46, 14);
		getContentPane().add(lblAdd);
		
		JLabel lblZip = new JLabel("Postnr:");
		lblZip.setBounds(10, 85, 46, 14);
		getContentPane().add(lblZip);
		
		JLabel lblCity = new JLabel("By:");
		lblCity.setBounds(10, 120, 46, 14);
		getContentPane().add(lblCity);
		
		JLabel lblTlf = new JLabel("Telefonnr:");
		lblTlf.setBounds(10, 155, 59, 14);
		getContentPane().add(lblTlf);
		
		txtZip = new JTextField();
		txtZip.setText(empCon.getEmployee(id).getZip());
		txtZip.setEditable(false);
		txtZip.setBounds(66, 82, 157, 20);
		getContentPane().add(txtZip);
		txtZip.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setText(empCon.getEmployee(id).getCity());
		txtCity.setEditable(false);
		txtCity.setBounds(66, 117, 157, 20);
		getContentPane().add(txtCity);
		txtCity.setColumns(10);
		
		txtPho = new JTextField();
		txtPho.setText(empCon.getEmployee(id).getPhone());
		txtPho.setEditable(false);
		txtPho.setBounds(66, 152, 157, 20);
		getContentPane().add(txtPho);
		txtPho.setColumns(10);
		
		JLabel lblSalery = new JLabel("Timeløn:");
		lblSalery.setBounds(10, 188, 46, 14);
		getContentPane().add(lblSalery);
		
		JLabel lblBank = new JLabel("Bank:");
		lblBank.setBounds(10, 223, 46, 14);
		getContentPane().add(lblBank);
		
		txtSalery = new JTextField();
		txtSalery.setBounds(66, 185, 157, 20);
		txtSalery.setText(Double.toString(empCon.getEmployee(id).getSalery()));
		txtSalery.setEditable(false);
		getContentPane().add(txtSalery);
		txtSalery.setColumns(10);
		
		txtBank = new JTextField();
		txtBank.setText(empCon.getEmployee(id).getBankInfo());
		txtBank.setEditable(false);
		txtBank.setBounds(66, 220, 157, 20);
		getContentPane().add(txtBank);
		txtBank.setColumns(10);
		
		JRadioButton rdbtnIsLeader = new JRadioButton("");
		if(empCon.getEmployee(id).getIsLeader()) {
			rdbtnIsLeader.setSelected(true);
		} else {
			rdbtnIsLeader.setSelected(false);
		}
		rdbtnIsLeader.setEnabled(false);
		rdbtnIsLeader.setBounds(62, 251, 109, 23);
		getContentPane().add(rdbtnIsLeader);
		
		JLabel lblIsLeader = new JLabel("Leder:");
		lblIsLeader.setBounds(11, 255, 46, 14);
		getContentPane().add(lblIsLeader);
		
	}

}
