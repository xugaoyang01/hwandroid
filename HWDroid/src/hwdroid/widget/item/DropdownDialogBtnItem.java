package hwdroid.widget.item;

import hwdroid.widget.itemview.ItemView;
import android.content.Context;
import android.view.ViewGroup;

import com.hw.droid.R;

public class DropdownDialogBtnItem extends TextItem{
	
    public DropdownDialogBtnItem() {
    }

    public DropdownDialogBtnItem(String text) {
        this.mText = text;
    }
	
    @Override
    public ItemView newView(Context context, ViewGroup parent) {
    	ItemView view = createCellFromXml(context, R.layout.hw_dropdown_dialog_btn_item_view, parent);
    	view.prepareItemView();
    	return view;
    }
}
