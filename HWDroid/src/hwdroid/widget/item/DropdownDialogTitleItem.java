package hwdroid.widget.item;

import hwdroid.widget.itemview.ItemView;
import android.content.Context;
import android.view.ViewGroup;

import com.hw.droid.R;

public class DropdownDialogTitleItem extends DropdownDialogTextItem{

    public DropdownDialogTitleItem(String text) {
        super(text);
    }
    
    @Override
    public ItemView newView(Context context, ViewGroup parent) {
    	ItemView view = createCellFromXml(context, R.layout.hw_dropdown_dialog_title_item_view, parent);
    	view.prepareItemView();
    	return view;
    }

}
