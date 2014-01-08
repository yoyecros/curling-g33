package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import java.awt.Font;
import java.io.File;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import model.URLs;
import control.CtrlGetURL;
import control.CtrlTestURL;

public class DlgPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JFileChooser choix;
	private JButton btnNewButton_1 = new JButton("V\u00E9rifier les URLs...");

	/**
	 * Create the frame.
	 */
	public DlgPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Curling");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 414, 26);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(67, 84, 190, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("S\u00E9lectionner...");
		listenerChoix ecout = new listenerChoix(this);
		btnNewButton.addActionListener(ecout);
		btnNewButton.setBounds(267, 83, 119, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = choix.getSelectedFile();
				Vector<URLs> tabUrl = CtrlGetURL.getUrlsFromFile(f);
				if (tabUrl.size() > 0) {
					Vector<URLs> tabUrlKO = CtrlTestURL.checkListURLs(tabUrl);
					if(tabUrlKO.isEmpty()) JOptionPane.showMessageDialog(null, "Toutes les URLs sont valides.");
					else {
						JOptionPane.showMessageDialog(null, "Il y a "+tabUrlKO.size()+" URL(s) non valide(s).");
						DlgListeURLs list = new DlgListeURLs(tabUrlKO);
						list.setVisible(true);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Pas d'URL dans le fichier.");
				}
			}
		});
		btnNewButton_1.setBounds(123, 158, 183, 23);
		contentPane.add(btnNewButton_1);
	}
	
	class listenerChoix implements ActionListener {
		private DlgPrincipal fenetre;
		public listenerChoix (DlgPrincipal f)
		{
			this.fenetre = f;
		}
		public void actionPerformed(ActionEvent e) {
			choix = new JFileChooser();
			FileNameExtensionFilter filterHTML = new FileNameExtensionFilter(
					"html", "html");
			FileNameExtensionFilter filterTXT = new FileNameExtensionFilter(
					"txt", "txt");
			FileNameExtensionFilter filterDOCX = new FileNameExtensionFilter(
					"docx", "docx");
			choix.setFileFilter(null);
			choix.addChoosableFileFilter(filterHTML);
			choix.addChoosableFileFilter(filterTXT);
			choix.addChoosableFileFilter(filterDOCX);
			int returnVal = choix.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       textField.setText(choix.getSelectedFile().getPath());
		       fenetre.btnNewButton_1.setEnabled(true);
		    }
		}
		
	}
	
}