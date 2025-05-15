package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import BBDD.Connect;
import main.LogronoAPP;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class consultarReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable table;
	public DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestionReserva frame = new gestionReserva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public consultarReserva() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 35, 276, 323);
		contentPane.add(scrollPane);

		tableModel = new DefaultTableModel(new Object[] { "DNI_Persona", "Sesión_Código" }, 0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		Controlador.Coordinador.mostrarDatosReserva(tableModel);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new menuEmpleado().setVisible(true);
				consultarReserva.this.dispose();
			}
		});
		btnVolver.setBounds(10, 392, 85, 21);
		contentPane.add(btnVolver);

		// ORDENAR ALFABETICAMENTE
		TableRowSorter sorter = new TableRowSorter(tableModel);
		table.setRowSorter(sorter);
	}
}