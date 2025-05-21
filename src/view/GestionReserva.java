package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import ConnectBDD.Connect;
import Controlador.Coordinador;
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

public class GestionReserva extends JFrame {

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
					GestionReserva frame = new GestionReserva();
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
	public GestionReserva() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 35, 276, 323);
		contentPane.add(scrollPane);

		tableModel = new DefaultTableModel(new Object[] { "DNI_Persona", "Sesión_Código" }, 0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		Controlador.Coordinador.mostrarDatosReserva(tableModel);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Menu().setVisible(true);
				GestionReserva.this.dispose();
			}
		});
		btnVolver.setBounds(10, 392, 85, 21);
		contentPane.add(btnVolver);

		JButton btnImportarXML = new JButton("Importar XML");
		btnImportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Coordinador.importarXML();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(btnImportarXML,
							"Ha ocurrido un error al importar o se ha cancelado la operación.");
				}
			}
		});
		btnImportarXML.setBounds(553, 92, 166, 21);
		contentPane.add(btnImportarXML);

		JButton btnExportarSesiones = new JButton("Exportar Sesiones");
		btnExportarSesiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Coordinador.exportarXML();
					JOptionPane.showMessageDialog(btnExportarSesiones,
							"Se han exportado las sesiones a la ruta especificada.");
				} catch (ParserConfigurationException | TransformerException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(btnExportarSesiones,
							"Ha ocurrido un error al exportar o se ha cancelado la operación.");
				}
			}
		});
		btnExportarSesiones.setBounds(553, 123, 166, 21);
		contentPane.add(btnExportarSesiones);

		JButton btnEditarReserva = new JButton("Actualizar Reservas");
		btnEditarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coordinador.editarReserva();
				JOptionPane.showMessageDialog(btnEditarReserva, "Se han actualizado las reservas.");
			}
		});
		btnEditarReserva.setBounds(553, 154, 166, 21);
		contentPane.add(btnEditarReserva);

		JButton btnEliminarReserva = new JButton("Eliminar Reserva");
		btnEliminarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coordinador.eliminarReserva();
				JOptionPane.showMessageDialog(btnEliminarReserva, "Se ha eliminado la reserva.");
			}
		});
		btnEliminarReserva.setBounds(553, 185, 166, 21);
		contentPane.add(btnEliminarReserva);

		// ORDENAR ALFABETICAMENTE
		TableRowSorter sorter = new TableRowSorter(tableModel);
		table.setRowSorter(sorter);

		// FILTRO
		JComboBox filtroCombo = new JComboBox();
		filtroCombo.setModel(new DefaultComboBoxModel(new String[] { "Filtrar", "DNI_Persona", "Sesión_Código" }));
		filtroCombo.setBounds(10, 38, 113, 21);
		contentPane.add(filtroCombo);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				sorter.setRowFilter(new RowFilter() {
					@Override
					public boolean include(Entry entry) {

						int columnaElegida = 0;
						if ("DNI_Persona" == (String) filtroCombo.getSelectedItem()) {
							columnaElegida = 0;
						} else if ("Sesión_Código" == (String) filtroCombo.getSelectedItem()) {
							columnaElegida = 1;
						}

						String nombre = entry.getValue(columnaElegida).toString();
						String searchText = textField.getText();
						return nombre.startsWith(searchText);
					}
				});
			}
		});
		textField.setBounds(133, 39, 102, 21);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}