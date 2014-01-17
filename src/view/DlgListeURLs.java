package view;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;

import model.URLs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Vector;


public class DlgListeURLs extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scroll;
	private JTable table;
	private JButton btnNewButton;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public DlgListeURLs(Vector<URLs> pTab) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable(new ModeleStatiqueObjet(pTab));
		table.setEnabled(true);
		scroll = new JScrollPane(table);
		scroll.setColumnHeaderView(table);
		contentPane.add(scroll, BorderLayout.CENTER);
		
		btnNewButton = new JButton("OK");
		listenerOK ecout = new listenerOK(this);
		btnNewButton.addActionListener(ecout);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		lblNewLabel = new JLabel("Liste des URLs");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
	}

	class ModeleStatiqueObjet extends AbstractTableModel {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private URLs[] urls;
	 
	    private final String[] entete = {"URL"};
	 
	    public ModeleStatiqueObjet(Vector<URLs> pURLs) {
	        super();
	        
	        urls = new URLs[pURLs.size()];
	        
	        pURLs.toArray(urls);
	        
	    }
	 
	    public int getRowCount() {
	        return urls.length;
	    }
	 
	    public int getColumnCount() {
	        return entete.length;
	    }
	 
	  
	    public Object getValueAt(int rowIndex, int columnIndex) {
	            return urls[rowIndex].getUrl() ;
	    }

	}
	
	class listenerOK implements ActionListener {
		private DlgListeURLs fenetre;
		public listenerOK (DlgListeURLs f)
		{
			this.fenetre = f;
		}
		public void actionPerformed (ActionEvent e) {
				this.fenetre.setVisible(false);		
		}
	}
	
}
