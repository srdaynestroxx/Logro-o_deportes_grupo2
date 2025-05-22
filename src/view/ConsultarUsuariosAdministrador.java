package view;
/**
 * @author Grupo2 - Logroño Deportes
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import ConnectBDD.Connect;
import Controlador.Coordinador;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * Clase para que los administradores consulten los usuarios.
 */
public class ConsultarUsuariosAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * JPanel ConsultarUsuariosAdministrador
	 */
	private JPanel contentPane;
	/**
	 * Tabla ConsultarUsuariosAdministrador.
	 */
	private JTable table;	
	/**
	 * DTM ConsultarUsuariosAdministrador.
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
					ConsultarUsuariosAdministrador frame = new ConsultarUsuariosAdministrador();
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
	public ConsultarUsuariosAdministrador() {

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
		Controlador.Coordinador.mostrarDatosUsuarioAdministrador(tableModel);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Menu().setVisible(true);
		        ConsultarUsuariosAdministrador.this.dispose();
		        
		        try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logSession("Se ha vuelto al menú.");
				}
			}
		});
		btnVolver.setBounds(10, 392, 85, 21);
		contentPane.add(btnVolver);

		JButton btnCargarCopia = new JButton("Cargar copia de seguridad");
		btnCargarCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				Coordinador.cargarFicheroBinario(tableModel);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			 try (main.LogronoAPP.logger) {
					main.LogronoAPP.logger.logSession("Se ha usado la opción 'Cargar copia de seguridad'.");
				}
			}
		});
		btnCargarCopia.setBounds(348, 392, 166, 21);
		contentPane.add(btnCargarCopia);

		JButton btnCopiaSeguridad = new JButton("Realizar copia de seguridad");
		btnCopiaSeguridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.Coordinador.realizarFicheroBinario(btnCopiaSeguridad);
				} catch (SQLException e1) {

					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Copia de seguridad realizada");
				JOptionPane.showMessageDialog(btnCopiaSeguridad, "Se han guardado los datos en un fichero binario.");
				
				 try (main.LogronoAPP.logger) {
						main.LogronoAPP.logger.logSession("Se ha usado la opción 'Realizar copia de seguridad'.");
					}
			}
		});
		btnCopiaSeguridad.setBounds(524, 392, 205, 21);
		contentPane.add(btnCopiaSeguridad);
	}
}