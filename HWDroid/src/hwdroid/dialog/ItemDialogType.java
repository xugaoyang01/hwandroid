package hwdroid.dialog;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.DropdownDialogTextItem;
import hwdroid.widget.item.Item;

public class ItemDialogType extends DialogType{
	
	public ItemDialogType(Context context, 
			PopupWindow popupWindow,
			DialogInterface dialogInterface, 
			ItemAdapter adapter,
			View menuView) {
		super(context, popupWindow, dialogInterface, adapter, menuView);
	}

	@Override
	public Item createItemView(String text, boolean selected) {
		DropdownDialogTextItem item = new DropdownDialogTextItem(text);
    	item.enabled = true;
    	item.setTag(mOnClickListener);
        return item;
	}

	@Override
	public void showChild() {
    	for(int i = 0; i < mItemList.size(); i++) {
    		Item item = mItemList.get(i);
    		item.setTag(mOnClickListener);
    		mAdapter.add(item);
    	}
	}

}
