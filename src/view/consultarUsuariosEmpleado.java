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

public class consultarUsuariosEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					consultarUsuariosEmpleado frame = new consultarUsuariosEmpleado();
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
	public consultarUsuariosEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 37, 675, 239);
		contentPane.add(scrollPane);

		tableModel = new DefaultTableModel(
				new Object[] { "DNI", "Nombre", "Apellido", "Rol", "Mail", "Telefono", "Contraseña" }, 0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		Controlador.Coordinador.mostrarDatosUsuario(tableModel);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new menu().setVisible(true);
				consultarUsuariosEmpleado.this.dispose();
			}
		});
		btnVolver.setBounds(10, 392, 85, 21);
		contentPane.add(btnVolver);

		JButton btnCargarBinario = new JButton("Cargar fichero binario");
		btnCargarBinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCargarBinario.setBounds(348, 392, 166, 21);
		contentPane.add(btnCargarBinario);

		JButton btnCopiaSeguridad = new JButton("Realizar copia de seguridad");
		btnCopiaSeguridad.setBounds(524, 392, 205, 21);
		contentPane.add(btnCopiaSeguridad);

	}
}