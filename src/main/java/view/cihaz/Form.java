package view.cihaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.cihaz.CihazController;
import model.Cihaz;
import model.Uretici;
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
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class Form extends JDialog {
	private static Cihaz cihaz = new Cihaz();
	private static Uretici uretici = new Uretici();
	private final JPanel contentPanel = new JPanel();
	private JButton jbKaydet;
	private JButton jbVazgec;
	private JTextField jtfUrl;
	private JTextField jtfAd;
	private Parser parser ;
	private JLabel lblretici;
	private JComboBox jcbUreticiAdi;
	/**
	 * Create the dialog.
	 */
	public Form(String cihazUrl) {
		parser = new Parser(cihazUrl);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			cihaz.setAd(parser.CihazAdiBul());
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
			List<Uretici> ureticiler = uretici.all();
			UreticiCombBoxModel ureticiCombBoxModel = new UreticiCombBoxModel(ureticiler);
			jcbUreticiAdi = new JComboBox(ureticiCombBoxModel);
			GridBagConstraints gbc_jcbUreticiAdi = new GridBagConstraints();
			gbc_jcbUreticiAdi.anchor = GridBagConstraints.WEST;
			gbc_jcbUreticiAdi.insets = new Insets(0, 0, 5, 0);
			gbc_jcbUreticiAdi.gridx = 1;
			gbc_jcbUreticiAdi.gridy = 1;
			contentPanel.add(jcbUreticiAdi, gbc_jcbUreticiAdi);
		}
		{
			JLabel lblCihazAd = new JLabel("Cihaz ad\u0131:");
			GridBagConstraints gbc_lblCihazAd = new GridBagConstraints();
			gbc_lblCihazAd.anchor = GridBagConstraints.EAST;
			gbc_lblCihazAd.insets = new Insets(0, 0, 0, 5);
			gbc_lblCihazAd.gridx = 0;
			gbc_lblCihazAd.gridy = 2;
			contentPanel.add(lblCihazAd, gbc_lblCihazAd);
		}
		jtfAd = new JTextField();
		GridBagConstraints gbc_jtfAd = new GridBagConstraints();
		gbc_jtfAd.anchor = GridBagConstraints.WEST;
		gbc_jtfAd.gridx = 1;
		gbc_jtfAd.gridy = 2;
		contentPanel.add(jtfAd, gbc_jtfAd);
		jtfAd.setColumns(20);
		jtfAd.setText(cihaz.getAd());
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
				jbVazgec = new JButton("Vazgeç");
				jbVazgec.setActionCommand("Cancel");
				buttonPane.add(jbVazgec);
			}
		}
		registerListeners();
	}
	
	private void registerListeners() {
		jbKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			CihazController.getInstance().save(cihaz);
			JOptionPane.showMessageDialog(this, "UsuÃ¡rio Salvo Com Sucesso", "", JOptionPane.INFORMATION_MESSAGE);
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
