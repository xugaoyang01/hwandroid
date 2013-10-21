package hwdroid.dialog;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.DropdownDialogSingleChoiceItem;
import hwdroid.widget.item.Item;

public class SingleChoiceItemDialogType extends DialogType{

	public SingleChoiceItemDialogType(Context context, PopupWindow popupWindow,
			DialogInterface dialogInterface, ItemAdapter adapter,
			View menuView) {
		super(context, popupWindow, dialogInterface, adapter, menuView);
	}

	@Override
	public Item createItemView(String text, boolean selected) {
		DropdownDialogSingleChoiceItem item = new DropdownDialogSingleChoiceItem(text);
	    item.mCheckStatus = selected;
    	item.enabled = true;
        return item;
	}

	@Override
	public void showChild() {
    	for(int i = 0; i < mItemList.size(); i++) {
    		Item item = mItemList.get(i);
    		item.enabled = true;
    		mAdapter.add(item);
    	}
	}
	
	public void callBack(int index) {
		if(mOnClickListener != null) {
			mOnClickListener.onClick(mDialogInterface, index);
			cancel();
		}
	}

}
