package view.cihaz;

import javax.swing.DefaultComboBoxModel;

import model.Uretici;

public class UreticiCombBoxModel extends DefaultComboBoxModel<Uretici> {

	private static final long serialVersionUID = 1L;

	public UreticiCombBoxModel(Uretici[] ureticiler) {
        super(ureticiler);
    }
 
    @Override
    public Uretici getSelectedItem() {
    	Uretici selectedUretici = (Uretici) super.getSelectedItem();
        return selectedUretici;
    }
}
