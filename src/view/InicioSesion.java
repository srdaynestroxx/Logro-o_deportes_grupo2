package view;
/**
 * @author Grupo2 - Logroño Deportes
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * Clase encargada de realizar el inicio de sesion
 */
public class InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * JPanel InicioSesion.
	 */
	private JPanel contentPane;
	/**
	 * JTextField InicioSesion.
	 */
	private JTextField textFieldDNI;
	/**
	 * JLabel InicioSesion.
	 */
	private JLabel lblImagen;
	/**
	 * JPasswordField InicioSesion.
	 */
	private JPasswordField passwordFieldContraseña;

	/**
	 * Launch the application.
	 * @param args Parametro para argumentos de java.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
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
	public InicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInicioSesion = new JLabel("INICIAR SESION");
		lblInicioSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInicioSesion.setBounds(235, 77, 163, 44);
		contentPane.add(lblInicioSesion);

		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDNI.setBounds(208, 143, 45, 13);
		contentPane.add(lblDNI);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(208, 166, 220, 19);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContraseña.setBounds(208, 195, 194, 19);
		contentPane.add(lblContraseña);

		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon("C:\\Users\\1AW3-24\\git\\Logro-o_deportes_grupo2\\src\\imagenes\\logoLD.png"));
		lblImagen.setBounds(11, 11, 214, 110);
		contentPane.add(lblImagen);

		JButton btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.Coordinador.InicioSesion(btnIniciarSesion, textFieldDNI, passwordFieldContraseña);
			        InicioSesion.this.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnIniciarSesion.setBounds(259, 263, 116, 21);
		contentPane.add(btnIniciarSesion);

		passwordFieldContraseña = new JPasswordField();
		passwordFieldContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		passwordFieldContraseña.setBounds(208, 234, 220, 19);
		contentPane.add(passwordFieldContraseña);
	}
}