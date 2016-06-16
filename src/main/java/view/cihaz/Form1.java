package view.cihaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.JTextComponent;

import controllers.users.UserController;
import model.Cihaz;

public class Form1 extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private static Form1 form = new Form1();

	private JTextField jtfUrl;
	private JTextField jtfAd;

	private JButton jbSave;
	private JButton jbCancel;

	public Form1() {
		createForms();
		createButtons();
		registerListeners();
		configure();
	}	

	private void configure(){
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(this.getRootPane());
	}
	
	private void createForms(){
		JPanel jpForm = new JPanel(new GridLayout(2, 1, 0, 5));

		jpForm.setBorder(BorderFactory.createTitledBorder("Yeni Cihaz"));

		jpForm.add(fieldset(new JLabel("Url: "), jtfUrl = new JTextField(40), new JLabel("Cihaz Adý: "), jtfAd = new JTextField(40)));

		this.add(jpForm, BorderLayout.CENTER);
	}

	private JPanel fieldset(JComponent...components){
		JPanel fieldset = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		int x = 0;
		for (JComponent component : components) {
			c.gridx = x;
			fieldset.add(component, c);
			x++;
		}
		return fieldset;
	}
	
	private void createButtons(){
		JPanel jpButtons = new JPanel();    

		jpButtons.add(jbSave = new JButton("Kaydet"));
		jpButtons.add(jbCancel = new JButton("Vazgeç"));	
		this.add(jpButtons, BorderLayout.SOUTH);		
	}
	
	private void registerListeners() {
		jbSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdSave();
			}
		});
		jbCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdCancel();
			}
		});
	}
	
	private void cmdSave(){
		try {
			Cihaz cihaz = new Cihaz(jtfAd.getText());
			UserController.getInstance().save(user);
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
	
	private void clearForm(JTextComponent... jtcomponets){
		for (JTextComponent component : jtcomponets) {
			component.setText("");
		}
	}
	
	@Override
	public void dispose(){
		super.dispose();
		clearForm(jtfAd, jtfUrl);
	}
	
	public static void toggle(){
		form.setVisible(!form.isVisible());
	}
}