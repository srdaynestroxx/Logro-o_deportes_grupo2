package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import ConnectBDD.Connect;
import main.LogronoAPP;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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

public class gestionReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable table;
	public DefaultTableModel tableModel;
	private JTextField textField;


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
	public gestionReserva() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 35, 276, 323);
		contentPane.add(scrollPane);
		
		tableModel = new DefaultTableModel(new Object[] { "DNI_Persona", "Sesión_Código"},
                0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		Controlador.Coordinador.mostrarDatosReserva(tableModel);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(10, 392, 85, 21);
		contentPane.add(btnVolver);
		
		JButton btnImportarXML = new JButton("Importar XML");
		btnImportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LogronoAPP.importarXML();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnImportarXML.setBounds(563, 92, 166, 21);
		contentPane.add(btnImportarXML);
		
		JButton btnExportarSesiones = new JButton("Exportar Sesiones");
		btnExportarSesiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LogronoAPP.exportarXML();
				} catch (ParserConfigurationException | TransformerException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnExportarSesiones.setBounds(563, 123, 166, 21);
		contentPane.add(btnExportarSesiones);
		
		JButton btnEditarReserva = new JButton("Actualizar Reserva");
		btnEditarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogronoAPP.editarReserva();
			}
		});
		btnEditarReserva.setBounds(563, 154, 166, 21);
		contentPane.add(btnEditarReserva);
		
		JButton btnEliminarReserva = new JButton("Eliminar Reserva");
		btnEliminarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogronoAPP.eliminarReserva();
			}
		});
		btnEliminarReserva.setBounds(563, 185, 166, 21);
		contentPane.add(btnEliminarReserva);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Filtrar", "DNI_Persona", "Sesión_Código"}));
		comboBox.setBounds(21, 38, 85, 21);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(116, 39, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}