package view;
/**
 * @author Grupo2 - Logroño Deportes
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import ConnectBDD.Connect;
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
import javax.swing.RowFilter.Entry;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Clase para que los Empleados consulten reservas.
 */
public class ConsultarReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * JPanel ConsultarReserva
	 */
	private JPanel contentPane;
	/**
	 * Tabla ConsultarReserva.
	 */
	public static JTable table;
	/**
	 * DTM ConsultarReserva.
	 */
	public DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 * @param args Parametro para argumentos de java.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarReserva frame = new ConsultarReserva();
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
	public ConsultarReserva() {
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
				new MenuEmpleado().setVisible(true);
				ConsultarReserva.this.dispose();
				
				 try (main.LogronoAPP.logger) {
						main.LogronoAPP.logger.logSession("Se ha vuelto al menú.");
					}
			}
		});
		btnVolver.setBounds(10, 392, 85, 21);
		contentPane.add(btnVolver);

		// ORDENAR ALFABETICAMENTE
		TableRowSorter sorter = new TableRowSorter(tableModel);
		table.setRowSorter(sorter);

		// FILTRO
		JComboBox filtroCombo = new JComboBox();
		filtroCombo.setModel(new DefaultComboBoxModel(new String[] { "Filtrar", "DNI_Persona", "Sesión_Código" }));
		filtroCombo.setBounds(10, 38, 113, 21);
		contentPane.add(filtroCombo);

		JTextField textField = new JTextField();
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