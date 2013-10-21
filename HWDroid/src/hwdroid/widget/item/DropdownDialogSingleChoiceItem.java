package hwdroid.widget.item;

import hwdroid.widget.itemview.ItemView;
import android.content.Context;
import android.view.ViewGroup;

import com.hw.droid.R;

public class DropdownDialogSingleChoiceItem extends TextItem{
	public boolean mCheckStatus;
	
    public DropdownDialogSingleChoiceItem() {
    }

    public DropdownDialogSingleChoiceItem(String text) {
        this.mText = text;
    }
    
    public DropdownDialogSingleChoiceItem(String text, boolean status) {
        this.mText = text;
        this.mCheckStatus = status;
    }
	
    @Override
    public ItemView newView(Context context, ViewGroup parent) {
    	ItemView view = createCellFromXml(context, R.layout.hw_dropdown_dialog_single_choice_item_view, parent);
    	view.prepareItemView();
    	return view;
    }
}
