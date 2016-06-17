package view.cihaz;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import model.Uretici;

public class UreticiComboBoxRenderer extends BasicComboBoxRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
     
            if (value instanceof Uretici)
            {
            	Uretici foo = (Uretici)value;
                setText( foo.getAd());
            }
     
            return this;
        }
}
