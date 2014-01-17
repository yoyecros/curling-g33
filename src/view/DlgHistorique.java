package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.Files;
import model.URLs;
import control.CtrlGetURL;
import control.CtrlHistory;
import control.CtrlTestURL;

public class DlgHistorique extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JScrollPane js;
	private JButton verif;
	
	public DlgHistorique(){
		Vector<Files> temp = new Vector<Files>();
		Files [] dispFilesHistory = new Files[CtrlHistory.getFilesFromHistory().size()];
		temp.toArray(dispFilesHistory);
		setTitle("Historique des fichiers déjà vérifiés");
		setBounds(370, 360, 550, 300);
		
		final JTable tableFiles = new JTable(new ModeleStatiqueObjet(dispFilesHistory));
		tableFiles.getColumnModel().getColumn(1).setMaxWidth(130);
		tableFiles.getColumnModel().getColumn(2).setMaxWidth(60);
		js = new JScrollPane(tableFiles);
		this.add(js);
		
		js.setBounds(267, 83, 200, 200);
		
		verif = new JButton("Vérifier le(s) fichier(s)\nsélectionné(s)...");
		verif.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Vector<Files> select = new Vector<Files>();
				//select.add(new Files(tableFiles.getSelectedRows().toString()));
				for(int i=0 ; i<tableFiles.getSelectedRowCount() ; i++) {
					select.add(CtrlHistory.getFilesFromHistory().get(tableFiles.getSelectedRows()[i]));
					CtrlHistory.updateHistory(new Files(CtrlHistory.getFilesFromHistory().get(tableFiles.getSelectedRows()[i]).getChemin(),new Date()));
				}
				Vector<URLs> tabUrl = new Vector<URLs>();
				for(int i=0 ; i<select.size() ; i++) 
					tabUrl.addAll(CtrlGetURL.getUrlsFromFile(select.get(i)));
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
						JOptionPane.showMessageDialog(null, "Pas d'URL dans le(s) fichier(s).");
					}
				
			}
		});
		this.add(verif,BorderLayout.SOUTH);
		
	}
	
	class ModeleStatiqueObjet extends AbstractTableModel {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'à' hh:mm:ss");
		private model.Files[] Filess;
	 
	    private final String[] entetes = {"Chemin", "Date de vérification", "Extension"};
	 
	    public ModeleStatiqueObjet(model.Files[] pFiles) {
	        super();
	 
	        Filess = pFiles;
	    }
	 
	    public int getRowCount() {
	        return Filess.length;
	    }
	 
	    public int getColumnCount() {
	        return entetes.length;
	    }
	 
	    public String getColumnName(int columnIndex) {
	        return entetes[columnIndex];
	    }
	 
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        switch(columnIndex){
	            case 0:
	                return (CtrlHistory.getFilesFromHistory().get(rowIndex).getChemin());
	            case 1:
	                return (formatter.format(CtrlHistory.getFilesFromHistory().get(rowIndex).getDate()));
	            case 2:
	                return (CtrlHistory.getFilesFromHistory().get(rowIndex).getExtension());
	            default:
	                return null; //Ne devrait jamais arriver
	        }
	    }
	}
	
}

