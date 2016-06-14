package view.yeni_cihaz;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.YeniCihaz;
import model.Uretici;
import view.listeners.EventListerner;
import controllers.yeni_cihaz.YeniCihazController;
import controllers.users.listeners.MailEvent;
import controllers.users.listeners.UserListener;

public class JTableList extends JTable implements EventListerner {
	
	private static final long serialVersionUID = 1L;
	
	private TableModel model = new TableModel();

	public JTableList() {
		this.setModel(model);
		this.getTableHeader().setReorderingAllowed(false);
		this.getColumnModel().getColumn(0).setPreferredWidth(50);
		this.getColumnModel().getColumn(1).setPreferredWidth(150);
		this.getColumnModel().getColumn(2).setPreferredWidth(400);
		loadYeniCihazlar();
	}
	
	public void loadYeniCihazlar(){
		try {
			for (YeniCihaz cihaz : YeniCihazController.getInstance().yeniCihazlar()) {
				Uretici uretici = Uretici.findById(cihaz.getUreticiId());
				model.insertRow(0, new String[] {cihaz.getId().toString(), uretici.getAd(), cihaz.getUrl()});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Erro", e.getMessage(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	private class TableModel extends DefaultTableModel{
		
		private static final long serialVersionUID = 1L;
		
		public TableModel() {
			super(new Object[][]{}, new String[] {"ID", "Üretici Adý", "URL"});	 
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	}

	@Override
	public void useradd(MailEvent<User> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	@Override
	public void cmdEdit() {
		System.out.println(this.getSelectedRow());
	}

	@Override
	public void cmdRemove() {
		if (this.getSelectedRow() != -1) {
			int row = this.getSelectedRow();
			Long userId = Long.parseLong((String) this.getValueAt(row, 0));
			try {
				UserController.getInstance().remove(userId);
				model.removeRow(row);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void cmdDetails() {
		System.out.println(this.getSelectedRow());
	}
	
	@Override
	public void cmdAdd() {
		Form.toggle();
	}

}
