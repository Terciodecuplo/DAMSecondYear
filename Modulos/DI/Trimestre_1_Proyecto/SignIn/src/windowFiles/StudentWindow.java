package windowFiles;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class StudentWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public StudentWindow() {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("/Users/josemurciabelmonte/Documents/DAM/SEGUNDO CURSO/DAMSecondYear/Modulos/DI/Trimestre_1_Proyecto/SignIn/src/pictures/students_yeah.gif"));
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
		JLabel messageLbl = new JLabel("Yeah! We are DAM students...");
		messageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(messageLbl, BorderLayout.SOUTH);
	}

}
