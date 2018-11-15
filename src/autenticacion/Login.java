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
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

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
		frame.setBounds(new Rectangle(448, 400));
		centreWindow(frame);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblTitulo = new JLabel("Ingrese al sistema con su usuario y password");
		lblTitulo.setBounds(22, 35, 397, 36);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(73, 126, 120, 36);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    validarIngreso();
				}
			}
		});
		textField.setBounds(243, 135, 120, 22);
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);

		JLabel lblContrasea = new JLabel("Password");
		lblContrasea.setBounds(73, 184, 120, 36);
		lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    validarIngreso();
                }
			}
		});
		passwordField.setBounds(243, 192, 120, 22);
		panel.add(passwordField);

		Login inicio = this;

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validarIngreso();
			}

		});

		JButton btnRegistro = new JButton("Registrar");
		btnRegistro.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnRegistro.setVisible(true); //Si hace falta lo agregamos al boton al la itfz
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
		btnSalir.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		btnIngresar.setBounds(93, 277, 100, 25);
		btnRegistro.setBounds(243, 277, 100, 25);
		btnSalir.setBounds(168, 326, 100, 25);
		panel.setLayout(null);
		panel.add(lblTitulo);
		panel.add(lblUsuario);
		panel.add(textField);
		panel.add(lblContrasea);
		panel.add(btnIngresar);
		panel.add(btnRegistro);
		panel.add(btnSalir);
		
		JLabel lbloElijaRegistrar = new JLabel("(o elija registrar para dar de alta un usuario nuevo)");
		lbloElijaRegistrar.setHorizontalAlignment(SwingConstants.LEFT);
		lbloElijaRegistrar.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lbloElijaRegistrar.setBounds(42, 67, 365, 36);
		panel.add(lbloElijaRegistrar);
	}

	private void validarIngreso() {
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