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
 * Clase encargada de mostrar el menu para Administradores.
 */
public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * JPanel Menu
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnConsultarUsuarios = new JButton("Consultar Usuarios");
		btnConsultarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConsultarUsuariosAdministrador().setVisible(true);
		        Menu.this.dispose();
			}
		});
		contentPane.add(btnConsultarUsuarios);
		
		JButton btnGestionarReservas = new JButton("Gestionar Reservas");
		btnGestionarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionReserva().setVisible(true);
		        Menu.this.dispose();
			}
		});
		contentPane.add(btnGestionarReservas);
	}

}
