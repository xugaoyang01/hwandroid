package hwdroid.dialog;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.DropdownDialogTextItem;
import hwdroid.widget.item.Item;

public class MessageItemDialogType extends DialogType{

	public MessageItemDialogType(Context context, 
			PopupWindow popupWindow,
			DialogInterface dialogInterface, 
			ItemAdapter adapter,
			View menuView) {
		super(context, popupWindow, dialogInterface, adapter, menuView);
	}

	@Override
	public Item createItemView(String text, boolean selected) {
		DropdownDialogTextItem item = new DropdownDialogTextItem(text);
    	item.enabled = false;
        return item;
	}

	@Override
	public void showChild() {
	}

}
