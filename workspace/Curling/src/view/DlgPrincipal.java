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
import java.io.FileFilter;
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
	private JFileChooser choixFile, choixFolder;
	private JButton btnNewButton_1 = new JButton("V\u00E9rifier les URLs...");
	private int select;

	/**
	 * Create the frame.
	 */
	public DlgPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Curling");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblNewLabel.setBounds(50, 11, 414, 26);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(67, 84, 190, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		//--------- boutton file ------------
		JButton btFile = new JButton("Fichier...");
		listenerChoixFile ecoutFile = new listenerChoixFile(this);
		btFile.addActionListener(ecoutFile);
		btFile.setBounds(267, 83, 87, 23);
		contentPane.add(btFile);
		
		
		//-------- boutton folder --------------
		JButton btFolder = new JButton("Dossier...");
		listenerChoixFolder ecoutFolder = new listenerChoixFolder(this);
		btFolder.addActionListener(ecoutFolder);
		btFolder.setBounds(360, 83, 87, 23);
		contentPane.add(btFolder);
		
		
		
		
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(select == 1) {
					File f = choixFile.getSelectedFile();
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
				else {
					File filesFolder = choixFolder.getSelectedFile();
					
					FileFilter filesFilter = new FileFilter() {
						public boolean accept(File file) {
							return !(file.isDirectory());
						}
					};
						
					File[] filesSelected = filesFolder.listFiles(filesFilter);
					Vector<URLs> tabUrl = new Vector<URLs>();
					for(int i=0 ; i<filesSelected.length ; i++) {
						tabUrl.addAll((CtrlGetURL.getUrlsFromFile(filesSelected[i])));
					}
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
				
			}
		});
		btnNewButton_1.setBounds(173, 158, 183, 23);
		contentPane.add(btnNewButton_1);
	}
	
	
	//------------ file -------------------
	class listenerChoixFile implements ActionListener {
		private DlgPrincipal fenetre;
		public listenerChoixFile (DlgPrincipal f)
		{
			this.fenetre = f;
		}
		public void actionPerformed(ActionEvent e) {
			choixFile = new JFileChooser();
			FileNameExtensionFilter filterHTML = new FileNameExtensionFilter(
					"html", "html");
			FileNameExtensionFilter filterTXT = new FileNameExtensionFilter(
					"txt", "txt");
			FileNameExtensionFilter filterDOCX = new FileNameExtensionFilter(
					"docx", "docx");
			choixFile.addChoosableFileFilter(filterHTML);
			choixFile.addChoosableFileFilter(filterTXT);
			choixFile.addChoosableFileFilter(filterDOCX);
			int returnVal = choixFile.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       textField.setText(choixFile.getSelectedFile().getPath());
		       fenetre.btnNewButton_1.setEnabled(true);
		       select=1;
		    }
		}
	}
		//---------------- Folder ----------------
		class listenerChoixFolder implements ActionListener {
			private DlgPrincipal fenetre;
			public listenerChoixFolder (DlgPrincipal f)
			{
				this.fenetre = f;
			}
			public void actionPerformed(ActionEvent e) {
				choixFolder = new JFileChooser();
				choixFolder.setAcceptAllFileFilterUsed(false);
				choixFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = choixFolder.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       textField.setText(choixFolder.getSelectedFile().getPath());
			       fenetre.btnNewButton_1.setEnabled(true);
			       select=2;
			    }
			}
		
	}
	
}