package it.corso.java.rubrica.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import it.corso.java.rubrica.business.RubricaBusiness;
import it.corso.java.rubrica.model.Contatto;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import it.corso.java.rubrica.business.RubricaBusiness;
import it.corso.java.rubrica.model.Contatto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class Rubrica {

	private JFrame frame;
	private JTextField Nome;
	private JTextField Cognome;
	private JTextField Telefono;
	private JTable listaContatti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rubrica window = new Rubrica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Rubrica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 635, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(32, 31, 565, 372);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Inserisci Contatti", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(6, 11, 61, 16);
		panel.add(lblNewLabel);
		
		Nome = new JTextField();
		Nome.setBounds(89, 6, 130, 26);
		panel.add(Nome);
		Nome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cognome");
		lblNewLabel_1.setBounds(6, 44, 61, 16);
		panel.add(lblNewLabel_1);
		
		Cognome = new JTextField();
		Cognome.setBounds(89, 39, 130, 26);
		panel.add(Cognome);
		Cognome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono");
		lblNewLabel_2.setBounds(6, 82, 61, 16);
		panel.add(lblNewLabel_2);
		
		Telefono = new JTextField();
		Telefono.setBounds(89, 77, 130, 26);
		panel.add(Telefono);
		Telefono.setColumns(10);
		
		JButton btnNewButton = new JButton("Aggiungi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contatto nuovoContatto = new Contatto();
				nuovoContatto.setCognome(Cognome.getText());
				nuovoContatto.setNome(Nome.getText());
				nuovoContatto.setTelefono(Telefono.getText());
				
				try {
					int id = RubricaBusiness.getInstance().aggiungiContatto(nuovoContatto);
					if(id > 0) {
						JOptionPane.showMessageDialog(null, "Contatto inserito con successo!"); //Messaggio di conferma e svuoto i campi.
						Cognome.setText("");
						Nome.setText("");
						Telefono.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(102, 119, 117, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Annulla");
		btnNewButton_1.setBounds(102, 149, 117, 29);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Ricerca Contatti", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 6, 532, 314);
		panel_1.add(scrollPane);
		
		
		//Parte aggiunta a codice!
		listaContatti = new JTable();
		listaContatti.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Cognome", "Telefono"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		listaContatti.getColumnModel().getColumn(0).setPreferredWidth(103);
		listaContatti.getColumnModel().getColumn(1).setPreferredWidth(215);
		listaContatti.getColumnModel().getColumn(2).setPreferredWidth(207);
		listaContatti.getColumnModel().getColumn(3).setPreferredWidth(244);
		
		DefaultTableModel dtm = (DefaultTableModel) listaContatti.getModel();
		
		List<Contatto> contatti;
		try {
			contatti = RubricaBusiness.getInstance().ricercaContatti();
			
			for (Contatto c : contatti) {
				Vector rowData = new Vector();
				rowData.add(c.getId());
				rowData.add(c.getNome());
				rowData.add(c.getCognome());
				rowData.add(c.getTelefono());
				
				dtm.addRow(rowData);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		scrollPane.setViewportView(listaContatti);
		
	}
}
