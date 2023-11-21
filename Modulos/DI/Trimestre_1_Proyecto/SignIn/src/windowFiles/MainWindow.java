package windowFiles;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userFld;
	private JPasswordField passwordField;
	private JButton signInBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 30, 93, 178, 23, 163, 74, 21, 0 };
		gbl_contentPane.rowHeights = new int[] { 45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel imageLogoLbl = new JLabel("");
		imageLogoLbl.setIcon(new ImageIcon(
				"/Users/josemurciabelmonte/Documents/DAM/SEGUNDO CURSO/DAMSecondYear/Modulos/DI/Trimestre_1_Proyecto/SignIn/src/pictures/uem_logo_resized_2.png"));
		GridBagConstraints gbc_imageLogoLbl = new GridBagConstraints();
		gbc_imageLogoLbl.anchor = GridBagConstraints.WEST;
		gbc_imageLogoLbl.gridwidth = 2;
		gbc_imageLogoLbl.gridheight = 4;
		gbc_imageLogoLbl.insets = new Insets(0, 0, 5, 5);
		gbc_imageLogoLbl.gridx = 4;
		gbc_imageLogoLbl.gridy = 1;
		contentPane.add(imageLogoLbl, gbc_imageLogoLbl);

		JLabel userLbl = new JLabel("USUARIO");
		GridBagConstraints gbc_userLbl = new GridBagConstraints();
		gbc_userLbl.insets = new Insets(0, 0, 5, 5);
		gbc_userLbl.anchor = GridBagConstraints.WEST;
		gbc_userLbl.gridx = 1;
		gbc_userLbl.gridy = 2;
		contentPane.add(userLbl, gbc_userLbl);

		userFld = new JTextField();
		GridBagConstraints gbc_userFld = new GridBagConstraints();
		gbc_userFld.fill = GridBagConstraints.BOTH;
		gbc_userFld.insets = new Insets(0, 0, 5, 5);
		gbc_userFld.gridx = 2;
		gbc_userFld.gridy = 2;
		contentPane.add(userFld, gbc_userFld);
		userFld.setColumns(10);

		JLabel passLbl = new JLabel("CONTRASEÑA");
		GridBagConstraints gbc_passLbl = new GridBagConstraints();
		gbc_passLbl.anchor = GridBagConstraints.WEST;
		gbc_passLbl.fill = GridBagConstraints.VERTICAL;
		gbc_passLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passLbl.gridx = 1;
		gbc_passLbl.gridy = 4;
		contentPane.add(passLbl, gbc_passLbl);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		contentPane.add(passwordField, gbc_passwordField);

		JLabel positionLbl = new JLabel("CARGO");
		GridBagConstraints gbc_positionLbl = new GridBagConstraints();
		gbc_positionLbl.fill = GridBagConstraints.BOTH;
		gbc_positionLbl.insets = new Insets(0, 0, 5, 5);
		gbc_positionLbl.gridx = 1;
		gbc_positionLbl.gridy = 6;
		contentPane.add(positionLbl, gbc_positionLbl);

		JComboBox roleCb = new JComboBox();
		roleCb.setModel(new DefaultComboBoxModel(new String[] { "Seleccione una opción", "Profesor", "Alumno" }));
		roleCb.setSelectedIndex(0);
		GridBagConstraints gbc_roleCb = new GridBagConstraints();
		gbc_roleCb.insets = new Insets(0, 0, 5, 5);
		gbc_roleCb.fill = GridBagConstraints.BOTH;
		gbc_roleCb.gridx = 2;
		gbc_roleCb.gridy = 6;
		contentPane.add(roleCb, gbc_roleCb);
		roleCb.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (roleCb.getSelectedIndex() != 0 && roleCb.getItemCount() == 3) {
					roleCb.removeItemAt(0);
				}

			}
		});

		JLabel lblNewLabel = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 8;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 8;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 8;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JButton cleanBtn = new JButton("LIMPIAR");
		cleanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userFld.setText("");
				passwordField.setText("");
				roleCb.insertItemAt("Seleccione una opción", 0);
				roleCb.setSelectedIndex(0);
			}
		});

		JLabel lblNewLabel_3 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 9;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 4;
		gbc_lblNewLabel_4.gridy = 9;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 5;
		gbc_lblNewLabel_5.gridy = 9;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		GridBagConstraints gbc_cleanBtn = new GridBagConstraints();
		gbc_cleanBtn.anchor = GridBagConstraints.SOUTH;
		gbc_cleanBtn.insets = new Insets(5, 0, 5, 5);
		gbc_cleanBtn.gridx = 2;
		gbc_cleanBtn.gridy = 10;
		contentPane.add(cleanBtn, gbc_cleanBtn);

		signInBtn = new JButton("ENTRAR");
		GridBagConstraints gbc_signInBtn = new GridBagConstraints();
		gbc_signInBtn.anchor = GridBagConstraints.SOUTHEAST;
		gbc_signInBtn.insets = new Insets(0, 0, 5, 5);
		gbc_signInBtn.gridx = 4;
		gbc_signInBtn.gridy = 10;
		contentPane.add(signInBtn, gbc_signInBtn);

		JButton exitBtn = new JButton("SALIR");
		GridBagConstraints gbc_exitBtn = new GridBagConstraints();
		gbc_exitBtn.insets = new Insets(0, 0, 5, 5);
		gbc_exitBtn.anchor = GridBagConstraints.PAGE_END;
		gbc_exitBtn.gridx = 5;
		gbc_exitBtn.gridy = 10;
		contentPane.add(exitBtn, gbc_exitBtn);
		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		signInBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				char[] pass = { 'u', 'e', 'm' };
				if (roleCb.getSelectedItem().toString().equals("Alumno")) {

					if (userFld.getText().equals("Jose") && Arrays.equals(pass, passwordField.getPassword())) {
						Arrays.fill(passwordField.getPassword(), '0');
						JOptionPane.showMessageDialog(frame, "Credenciales correctas. Accediendo a la plataforma.",
								"Bienvenida", JOptionPane.OK_OPTION);
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									StudentWindow frame = new StudentWindow();
									frame.setVisible(true);
									frame.setLocationRelativeTo(null);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

					} else {
						JOptionPane.showMessageDialog(frame,
								"El usuario o la contraseña son incorrectas. Por favor, vuelva a introducirlas.",
								"Login incorrecto", JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (roleCb.getSelectedItem().toString().equals("Profesor")) {
					JOptionPane.showMessageDialog(frame, "No existe ningún Profesor con esas credenciales.",
							"Login incorrecto", JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(frame, "Por favor, seleccione un rol para iniciar sesión.",
							"Login incorrecto", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
	}

}
