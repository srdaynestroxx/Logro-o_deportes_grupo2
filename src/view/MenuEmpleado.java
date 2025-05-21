package view;
/**
 * @author Grupo2 - Logroño Deportes
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase encargada de mostrar el menu para Empleados.
 */
public class MenuEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * JPanel MenuEmpleado.
	 */
	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @param args Parametro para argumentos de java.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public MenuEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnConsultarUsuarios = new JButton("Consultar Usuarios");
		btnConsultarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConsultarUsuariosEmpleado().setVisible(true);
		        MenuEmpleado.this.dispose();
		        
		        try (main.LogronoAPP.logger) {

					main.LogronoAPP.logger.logSession("Ventana 'Consultar Usuarios' abierta (Menu cerrado).");

				}
			}
		});
		contentPane.add(btnConsultarUsuarios);
		
		JButton btnGestionarReservas = new JButton("Consultar Reservas");
		btnGestionarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConsultarReserva().setVisible(true);
		        MenuEmpleado.this.dispose();
		        
		        try (main.LogronoAPP.logger) {

					main.LogronoAPP.logger.logSession("Ventana 'Consultar Reservas' abierta (Menu cerrado).");

				}
			}
		});
		contentPane.add(btnGestionarReservas);
	}

}