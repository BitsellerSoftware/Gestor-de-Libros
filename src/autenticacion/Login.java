package autenticacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import interfaz.InterfazGrafica;

public class Login {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private String ruta;

	/**
	 * Launch the application.
	 */
	public static void startInterface(String ruta) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login(ruta);
					login.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error iniciando", "Error inicio", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	/**
	 * Create the application.
	 */
	public Login(String ruta) {
		this.ruta = ruta; //inicializo ruta
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Ingreso al sistema");
		frame.setBounds(new Rectangle(600, 400));
		centreWindow(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblTitulo = new JLabel("Ingrese al sistema con su usuario y password");
		lblTitulo.setBounds(100, 33, 431, 36);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(155, 97, 120, 36);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));

		textField = new JTextField();
		textField.setBounds(325, 106, 120, 22);
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);

		JLabel lblContrasea = new JLabel("Password");
		lblContrasea.setBounds(155, 155, 120, 36);
		lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 16));

		passwordField = new JPasswordField();
		passwordField.setBounds(325, 163, 120, 22);
		panel.add(passwordField);

		Login inicio = this;

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
					String line;
					StringTokenizer st;
					String user = "";
					String pass = "";
					while ((line = br.readLine()) != null) {
						st = new StringTokenizer(line, ",");
						user = st.nextToken();
						pass = st.nextToken();
						if (user.equals(textField.getText())
								&& pass.equals(String.valueOf(passwordField.getPassword()))) {
							br.close();
							textField.setText("");
							passwordField.setText("");
							InterfazGrafica.startInterface(ruta); //comienzo interfaz grafica
							frame.setVisible(false);
							return;
						}
					}
					br.close();
					JOptionPane.showMessageDialog(null, "Usuario o password son incorrectos", "Error login",
							JOptionPane.ERROR_MESSAGE);

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "El archivo de usuarios no pudo ser encontrado", "",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		JButton btnRegistro = new JButton("Registrar");
		btnRegistro.setVisible(false); //Si hace falta lo agregamos al boton al la itfz
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText() != null && !textField.getText().equals("") && passwordField.getPassword() != null && !passwordField.getPassword().equals("")) {
					List<String> users = new ArrayList<String>();
					try {
						BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
						String line = null;
						StringTokenizer st;
						String usr;
						while ((line = br.readLine()) != null) {
							st = new StringTokenizer(line, ",");
							usr = st.nextToken();
							users.add(usr);
							st.nextToken();
						}
	
						if (users.contains(textField.getText())) {
							JOptionPane.showMessageDialog(null, "El codigo de usuario ya existe", "",
									JOptionPane.ERROR_MESSAGE);
							br.close();
						} else {
							br.close();
							FileWriter file = new FileWriter(new File("usuarios.txt"), true);
							BufferedWriter writer = new BufferedWriter(file);
							PrintWriter pw = new PrintWriter(writer);
							String Name = textField.getText();
							String Pass = passwordField.getText();
							pw.println(Name + "," + Pass);
							pw.close();
							textField.setText("");
							passwordField.setText("");
							JOptionPane.showMessageDialog(null, "El usuario se grabo correctamente", "",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "El archivo de usuarios no pudo ser encontrado", "",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuario y/o password no pueden ser vacios", "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		btnIngresar.setBounds(140, 263, 100, 25);
		btnRegistro.setBounds(250, 263, 100, 25);
		btnSalir.setBounds(360, 263, 100, 25);
		panel.setLayout(null);
		panel.add(lblTitulo);
		panel.add(lblUsuario);
		panel.add(textField);
		panel.add(lblContrasea);
		panel.add(btnIngresar);
		panel.add(btnRegistro);
		panel.add(btnSalir);
	}

	/**
	 * Centra la ventana
	 * @param frame
	 */
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
}