package view.cihaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.cihaz.CihazController;
import controllers.cihaz_ozellik_atama.CihazOzellikAtamaController;
import model.Cihaz;
import model.CihazOzellikAtama;
import model.Uretici;
import model.User;
import util.Parser;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class CihazView extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Cihaz cihaz = new Cihaz();
	private static Uretici uretici = new Uretici();
	private static Uretici secilenUretici;
	private static List<CihazOzellikAtama> cihazOzellikAtamaList = new ArrayList<CihazOzellikAtama>();
	
	private final JPanel contentPanel = new JPanel();	
	private JButton jbKaydet;
	private JButton jbVazgec;
	private JTextField jtfUrl;
	private JTextField jtfAd;
	private Parser parser ;
	private JLabel lblretici;
	private JComboBox<Uretici> jcbUretici;
	private JLabel lblDuyurulma;
	private JPanel panel;
	private JComboBox cbDuyurulmaYil;
	private JComboBox cbDuyurulmaAy;
	private JLabel lblSim;
	private JTextField tfSim;
	private JLabel lblgBant;
	private JTextField tf2gBant;
	/**
	 * Create the dialog.
	 */
	public CihazView(String cihazUrl, String ureticiAdi) {
		Uretici secilenUretici = null;
		try {
			secilenUretici = uretici.findByAd(ureticiAdi);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		parser = new Parser(cihazUrl);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUrl = new JLabel("Url:");
			GridBagConstraints gbc_lblUrl = new GridBagConstraints();
			gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
			gbc_lblUrl.anchor = GridBagConstraints.WEST;
			gbc_lblUrl.gridx = 0;
			gbc_lblUrl.gridy = 0;
			contentPanel.add(lblUrl, gbc_lblUrl);
		}
		{
			jtfUrl = new JTextField();
			jtfUrl.setText(cihazUrl);
			GridBagConstraints gbc_jtfUrl = new GridBagConstraints();
			gbc_jtfUrl.insets = new Insets(0, 0, 5, 0);
			gbc_jtfUrl.fill = GridBagConstraints.HORIZONTAL;
			gbc_jtfUrl.gridx = 1;
			gbc_jtfUrl.gridy = 0;
			contentPanel.add(jtfUrl, gbc_jtfUrl);
			jtfUrl.setColumns(10);
		}

		{
			lblretici = new JLabel("\u00DCretici:");
			GridBagConstraints gbc_lblretici = new GridBagConstraints();
			gbc_lblretici.anchor = GridBagConstraints.WEST;
			gbc_lblretici.insets = new Insets(0, 0, 5, 5);
			gbc_lblretici.gridx = 0;
			gbc_lblretici.gridy = 1;
			contentPanel.add(lblretici, gbc_lblretici);
		}
		{
			UreticiCombBoxModel ureticiCombBoxModel = new UreticiCombBoxModel();
			jcbUretici = new JComboBox<Uretici>(ureticiCombBoxModel);
			try {
				for(Uretici uretici : uretici.all()){
					if(uretici.getId() == secilenUretici.getId()){
						jcbUretici.getModel().setSelectedItem(secilenUretici);
					}
					jcbUretici.addItem(uretici);
					jcbUretici.setRenderer(new UreticiComboBoxRenderer());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			GridBagConstraints gbc_jcbUreticiAdi = new GridBagConstraints();
			gbc_jcbUreticiAdi.insets = new Insets(0, 0, 5, 0);
			gbc_jcbUreticiAdi.anchor = GridBagConstraints.WEST;
			gbc_jcbUreticiAdi.gridx = 1;
			gbc_jcbUreticiAdi.gridy = 1;
			contentPanel.add(jcbUretici, gbc_jcbUreticiAdi);
		}
		{
			cihaz.setAd(parser.CihazAdiBul(secilenUretici));
		}
		{
			JLabel lblCihazAd = new JLabel("Cihaz ad\u0131:");
			GridBagConstraints gbc_lblCihazAd = new GridBagConstraints();
			gbc_lblCihazAd.anchor = GridBagConstraints.WEST;
			gbc_lblCihazAd.insets = new Insets(0, 0, 5, 5);
			gbc_lblCihazAd.gridx = 0;
			gbc_lblCihazAd.gridy = 2;
			contentPanel.add(lblCihazAd, gbc_lblCihazAd);
		}
		jtfAd = new JTextField();
		GridBagConstraints gbc_jtfAd = new GridBagConstraints();
		gbc_jtfAd.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfAd.anchor = GridBagConstraints.WEST;
		gbc_jtfAd.insets = new Insets(0, 0, 5, 0);
		gbc_jtfAd.gridx = 1;
		gbc_jtfAd.gridy = 2;
		contentPanel.add(jtfAd, gbc_jtfAd);
		jtfAd.setColumns(20);
		jtfAd.setText(cihaz.getAd());		
		{
			lblDuyurulma = new JLabel("Duyurulma:");
			GridBagConstraints gbc_lblDuyurulma = new GridBagConstraints();
			gbc_lblDuyurulma.anchor = GridBagConstraints.WEST;
			gbc_lblDuyurulma.insets = new Insets(0, 0, 5, 5);
			gbc_lblDuyurulma.gridx = 0;
			gbc_lblDuyurulma.gridy = 3;
			contentPanel.add(lblDuyurulma, gbc_lblDuyurulma);
		}
		{
			panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.anchor = GridBagConstraints.NORTHWEST;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 3;
			contentPanel.add(panel, gbc_panel);
			{
				cbDuyurulmaYil = new JComboBox();
				cbDuyurulmaYil.setSelectedIndex(-1);
				for(int i = 1995; i < 2020; i++){
					cbDuyurulmaYil.addItem(i);
					if(parser.duyurulmaYilBul() == i){
						cbDuyurulmaYil.setSelectedItem(i);;
					}
				}
				panel.add(cbDuyurulmaYil);
			}
			{
				String aylar[]={"Ocak", "Şubat", "Mart ", "Nisan", "Mayıs", "Haziran", "Temmuz", "Agustos", "Eylül", "Ekim ", "Kasım", "Aralık"};
				cbDuyurulmaAy = new JComboBox(aylar);
				cbDuyurulmaAy.setSelectedIndex(-1);
				String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
				for(int i=0; i<months.length; i++ ){
					if(months[i].equals(parser.duyurulmaAyBul())){
						cbDuyurulmaAy.setSelectedItem(aylar[i]);
					}
				}
				panel.add(cbDuyurulmaAy);
			}
		}
		{
			lblSim = new JLabel("SIM");
			GridBagConstraints gbc_lblSim = new GridBagConstraints();
			gbc_lblSim.anchor = GridBagConstraints.WEST;
			gbc_lblSim.insets = new Insets(0, 0, 5, 5);
			gbc_lblSim.gridx = 0;
			gbc_lblSim.gridy = 4;
			contentPanel.add(lblSim, gbc_lblSim);
		}
		{
			tfSim = new JTextField();
			GridBagConstraints gbc_tfSim = new GridBagConstraints();
			gbc_tfSim.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfSim.anchor = GridBagConstraints.WEST;
			gbc_tfSim.insets = new Insets(0, 0, 5, 0);
			gbc_tfSim.gridx = 1;
			gbc_tfSim.gridy = 4;
			contentPanel.add(tfSim, gbc_tfSim);
			tfSim.setColumns(40);
			tfSim.setText(parser.simBul());
		}
		{
			lblgBant = new JLabel("2G Bant");
			GridBagConstraints gbc_lblgBant = new GridBagConstraints();
			gbc_lblgBant.anchor = GridBagConstraints.WEST;
			gbc_lblgBant.insets = new Insets(0, 0, 0, 5);
			gbc_lblgBant.gridx = 0;
			gbc_lblgBant.gridy = 5;
			contentPanel.add(lblgBant, gbc_lblgBant);
		}
		{
			tf2gBant = new JTextField();
			GridBagConstraints gbc_tf2gBant = new GridBagConstraints();
			gbc_tf2gBant.fill = GridBagConstraints.HORIZONTAL;
			gbc_tf2gBant.gridx = 1;
			gbc_tf2gBant.gridy = 5;
			contentPanel.add(tf2gBant, gbc_tf2gBant);
			tf2gBant.setColumns(10);
			tf2gBant.setText(parser.ikigBantBul());
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				jbKaydet = new JButton("Kaydet");
				jbKaydet.setHorizontalAlignment(SwingConstants.LEFT);
				jbKaydet.setActionCommand("OK");
				buttonPane.add(jbKaydet);
				getRootPane().setDefaultButton(jbKaydet);
			}
			{
				jbVazgec = new JButton("Vazgec");
				jbVazgec.setActionCommand("Cancel");
				buttonPane.add(jbVazgec);
			}
		}
		registerListeners();
	}
	
	private void registerListeners() {
		jbKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				secilenUretici = (Uretici) jcbUretici.getModel().getSelectedItem();
				if(cbDuyurulmaYil.getSelectedIndex() != -1){
					cihaz.setDuyurulmaYil(cbDuyurulmaYil.getSelectedItem().toString());
					cihaz.setDuyurulma(cihaz.getDuyurulmaYil());
				}
				if(cbDuyurulmaAy.getSelectedIndex()!= -1){
					cihaz.setDuyurulmaAy(cbDuyurulmaAy.getSelectedItem().toString());
					cihaz.setDuyurulma(cihaz.getDuyurulmaYil().toString()+ ", " + cihaz.getDuyurulmaAy());
				}
				cihaz.setSim(tfSim.getText());
				if(!tf2gBant.getText().equals("")){
					cihazOzellikAtamaList.add(new CihazOzellikAtama(1, tf2gBant.getText().trim()));
				}
				cmdSave();
			}
		});
		jbVazgec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdCancel();
			}
		});
	}
	
	private void cmdSave(){
		try {			
			CihazController.getInstance().save(cihaz,secilenUretici);
			for(CihazOzellikAtama cihazOzellikAtama : cihazOzellikAtamaList){
				CihazOzellikAtamaController.getInstance().save(cihaz, cihazOzellikAtama);
			}
			JOptionPane.showMessageDialog(this, "Başarılı", "", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
	}
	
	private void cmdCancel(){
		dispose();
	}	

}
