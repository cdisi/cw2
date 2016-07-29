package view.layout;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.yeni_cihaz.YeniCihazTableList;


public class MainJFrame extends JFrame {

	public static final Dimension PREFERREDSIZE = new Dimension(700,400);
	
	private static final long serialVersionUID = 1L;
	
	public MainJFrame() {
		super("CW");
		
		JPanel jpBody = new JPanel();
		jpBody.setLayout(new BorderLayout());
		
		JScrollPane jspList = new JScrollPane();
		YeniCihazTableList jTableList = new YeniCihazTableList();
		jspList.setViewportView(jTableList);
		
		jpBody.add(new Options(jTableList), BorderLayout.SOUTH);
		jpBody.add(jspList, BorderLayout.CENTER);
		
		setJMenuBar(new MenuOver(jTableList));
		
		this.setContentPane(jpBody);
	}
	
	public static void createAndShowGUI() {
        JFrame frame = new MainJFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(PREFERREDSIZE);
        frame.setPreferredSize(PREFERREDSIZE);
 
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
