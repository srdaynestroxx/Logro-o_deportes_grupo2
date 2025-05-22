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
 * Clase para que los Empleados consulten los usuarios.
 */
public class ConsultarUsuariosEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * JPanel ConsultarUsuariosEmpleado.
	 */
	private JPanel contentPane;
	/**
	 * Tabla ConsultarUsuariosEmpleado.
	 */
	private JTable table;
	/**
	 * DTM ConsultarUsuariosEmpleado.
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
					ConsultarUsuariosEmpleado frame = new ConsultarUsuariosEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
					 try (main.LogronoAPP.logger) {

							main.LogronoAPP.logger.logError("Error cargando ventana.", e);

						}
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultarUsuariosEmpleado() {
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
				new Object[] { "DNI", "Nombre", "Apellido", "Rol", "Mail", "Telefono" }, 0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		Controlador.Coordinador.mostrarDatosUsuarioEmpleado(tableModel);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuEmpleado().setVisible(true);
		        ConsultarUsuariosEmpleado.this.dispose();
		        
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
				Coordinador.cargarFicheroBinario(tableModel, btnCargarCopia);
				
				 try (main.LogronoAPP.logger) {
						main.LogronoAPP.logger.logSession("Se ha usado la opción 'Cargar copia de seguridad'.");
					}
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				 try (main.LogronoAPP.logger) {
						main.LogronoAPP.logger.logError("Error con Input u Output", e1);
					}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				 try (main.LogronoAPP.logger) {
						main.LogronoAPP.logger.logError("Error encontrando la clase", e1);
					}
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
					
					 try (main.LogronoAPP.logger) {
							main.LogronoAPP.logger.logSession("Se ha usado la opción 'Realizar copia de seguridad'.");
						}
					 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					 try (main.LogronoAPP.logger) {
							main.LogronoAPP.logger.logError("Error con la base de datos.", e1);
						}
				}
				System.out.println("Copia de seguridad realizada");
			}
		});
		btnCopiaSeguridad.setBounds(524, 392, 205, 21);
		contentPane.add(btnCopiaSeguridad);
	}
}