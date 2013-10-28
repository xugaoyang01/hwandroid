package hwdroid.dialog;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.DropdownDialogMultiChoiceItem;
import hwdroid.widget.item.Item;

public class MultiChoiceItemDialogType extends DialogType {
	
	public MultiChoiceItemDialogType(Context context, XXDropDownDialog popupWindow,
			DialogInterface dialogInterface, 
			ItemAdapter adapter,
			View menuView) {
		super(context, popupWindow, dialogInterface, adapter, menuView);
	}
	
	@Override
	public void init() {
		super.init();
	}

	@Override
	public Item createItemView(String text, boolean selected) {
		DropdownDialogMultiChoiceItem item = new DropdownDialogMultiChoiceItem(text);
    	item.enabled = true;
	    item.mCheckStatus = selected;
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
		if(mMultiChoiceClickListener != null) {
			DropdownDialogMultiChoiceItem item = (DropdownDialogMultiChoiceItem)mItemList.get(index);
			item.mCheckStatus = !item.mCheckStatus;
			mMultiChoiceClickListener.onClick(mDialogInterface, index, item.mCheckStatus);
			
    		if(!mHandler.hasMessages(HANDLER_UPDATE_ADAPTER)) {
    			mHandler.sendEmptyMessageDelayed(HANDLER_UPDATE_ADAPTER, 50);
    		}
		}
	}

}
