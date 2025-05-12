package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import ConnectBDD.Connect;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class gestionReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
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
		Controlador.Coordinador.mostrarDatos(tableModel);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(10, 392, 85, 21);
		contentPane.add(btnVolver);
		
		JButton btnCargarBinario = new JButton("Importar XML");
		btnCargarBinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCargarBinario.setBounds(563, 92, 166, 21);
		contentPane.add(btnCargarBinario);
		
		JButton btnCopiaSeguridad = new JButton("Exportar Sesiones");
		btnCopiaSeguridad.setBounds(563, 123, 166, 21);
		contentPane.add(btnCopiaSeguridad);
		
		JButton btnCopiaSeguridad_1 = new JButton("Editar Reserva");
		btnCopiaSeguridad_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCopiaSeguridad_1.setBounds(563, 154, 166, 21);
		contentPane.add(btnCopiaSeguridad_1);
		
		JButton btnCopiaSeguridad_2 = new JButton("Eliminar Reserva");
		btnCopiaSeguridad_2.setBounds(563, 185, 166, 21);
		contentPane.add(btnCopiaSeguridad_2);
		
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