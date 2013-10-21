package hwdroid.dialog;

import hwdroid.widget.ItemAdapter;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.hw.droid.R;

public class XXDropDownDialog extends PopupWindow implements DialogInterface, OnItemClickListener {
	
	private Context mContext;
	private View mMenuView;
	private ListView mListView;
	private ItemAdapter mAdapter;
	
	private DialogType mItemDialogType;
	private DialogType mMessageItemDialogType;
	private DialogType mProgressItemDialogType;
	private DialogType mSingleChoiceItemDialogType;	
	private DialogType mMultiChoiceItemDialogType;
	private DialogType mDialogType;
	DialogAdapter.OnClickListener mListListener;

	public XXDropDownDialog(Context context) {
		super(context);
		mContext = context;
		
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.hw_dropdown_dialog_view, null);
		mListView = (ListView) mMenuView.findViewById(R.id.dialog_listview);
    	mListView.setDivider(null);
    	mListView.setDividerHeight(0);
		
		mListView.setOnItemClickListener(this);	
		mAdapter = new ItemAdapter(mContext);
		mAdapter.setNotifyOnChange(false);
    	mListView.setAdapter(mAdapter);
    	
    	mItemDialogType = new ItemDialogType(context, this, this, mAdapter, mMenuView);
    	mMessageItemDialogType = new MessageItemDialogType(context, this, this, mAdapter, mMenuView);
    	mProgressItemDialogType = new ProgressItemDialogType(context, this, this, mAdapter, mMenuView);
    	mSingleChoiceItemDialogType = new SingleChoiceItemDialogType(context, this, this, mAdapter, mMenuView);
    	mMultiChoiceItemDialogType = new MultiChoiceItemDialogType(context, this, this, mAdapter, mMenuView);
    	mDialogType = mMessageItemDialogType;
		
		setContentView(mMenuView);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		setFocusable(true);
		//this.setAnimationStyle(R.style.AnimBottom);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {	
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						//dismiss();
					}
				}				
				return true;
			}
		});
	}
	
	public void setOnCancelListener(DialogInterface.OnCancelListener listener) {
		mDialogType.setOnCancelListener(listener);
	}
	
	public void setOnDismissListener(DialogInterface.OnDismissListener listener) {	
		mDialogType.setOnDismissListener(listener);
	}
	
	public void callDismiss() {
		mDialogType.callDismiss();
	}
    
    public void updateProgressItemMessage(int value, int maxValue) {
    	mDialogType.updateItem(value, maxValue);
    }
    
    public void setTitle(int titleId) {
        mDialogType.setTitle(titleId);
    }
    
    public void setTitle(CharSequence title) {
        mDialogType.setTitle(title);
    }
    
    public void setMessage(int titleId) {
    	mDialogType.setMessage(titleId);
    }
    
    public void setMessage(CharSequence title) {
    	mDialogType.setMessage(title);
    }
    
    public void setView(View v) {
    	mDialogType.setView(v);
    }
    
    public void setButton1(int titleId, final OnClickListener listener) {
    	mDialogType.setButton1(mContext.getString(titleId), listener);
    }
    
    public void setButton1(CharSequence text, final OnClickListener listener) {
    	if(text == null) return;
    	mDialogType.setButton1(text.toString(), listener);
    }
    
    public void setButton2(int titleId, final OnClickListener listener) {
    	mDialogType.setButton2(mContext.getString(titleId), listener);
    }
    
    public void setButton2(CharSequence text, final OnClickListener listener) {
    	if(text == null) return;
    	mDialogType.setButton2(text.toString(), listener);
    }
    
    public void setButton3(int titleId, final OnClickListener listener) {
    	mDialogType.setButton3(mContext.getString(titleId), listener);
    }
    
    public void setButton3(CharSequence text, final OnClickListener listener) {
    	if(text == null) return;
    	mDialogType.setButton3(text.toString(), listener);
    }
    
    protected void setProgressItem(CharSequence message, DialogInterface.OnCancelListener cancelListener) {
    	setCurrentDialogType(mProgressItemDialogType);
    	if(message == null){
    		setMessage("");
    	} else {
    		setMessage(message.toString());
    	}

		//setButton2(R.string.gd_cancel, null);
		setOnCancelListener(cancelListener);
    }
    
    void addItem(CharSequence text) {
    	if(text == null) return;
    	addItem(text.toString(), false);
    }
    
    public void addItem(int stringId) {
    	addItem(mContext.getString(stringId), false);
    }
    
    void addItem(CharSequence text, boolean selected) {
    	if(text == null) return;
    	setCurrentDialogType(mItemDialogType);
    	mDialogType.addItem(text.toString(), selected);
    }
    
    public void addItem(int stringId, boolean selected) {
    	setCurrentDialogType(mItemDialogType);
    	mDialogType.addItem(mContext.getString(stringId), selected);
    }
    
    public void addItems(CharSequence[] items, final OnClickListener listener) {
    	if(items == null) return;
    	boolean[] selected = new boolean[items.length];
    	addItems(items, selected, listener);
    }
    
    public void addItems(CharSequence[] items, boolean[] checkedItems, final OnClickListener listener) {
    	if(items == null) return;
    	setCurrentDialogType(mItemDialogType);
    	mDialogType.addItems(items, listener, checkedItems);
    }
    
    
    public void addSingleChoiceItem(int stringId) {
    }
    
    public void addSingleChoiceItem(CharSequence text) {
    }
    
    public void setSingleChoiceItems(CharSequence[] items, int checkedItem, final OnClickListener listener) {
    	if(items == null) return;
    	
    	setCurrentDialogType(mSingleChoiceItemDialogType);
    	boolean[] selected = new boolean[items.length];
    	
    	for(int i = 0; i < selected.length; i++) {
    		if(checkedItem == i) {
    			selected[i] = true;
    		}
    	}
    	
    	addItems(items, selected, listener);
    } 
    
    public void addMultiChoiceItem(CharSequence items, boolean checkedItems) {
    	if(items == null) return;
    	setCurrentDialogType(mMultiChoiceItemDialogType);
    	mDialogType.addItem(items.toString(), checkedItems);
    }
    
    public void setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, 
            final DialogInterface.OnMultiChoiceClickListener listener) {
        if(items == null) return;
    	setCurrentDialogType(mMultiChoiceItemDialogType);
        mDialogType.addItems(items, listener, checkedItems);
    }
    
    public void show(View v) {
    	mDialogType.show(v);
    }

	@Override
	public void cancel() {
		mDialogType.cancel();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
	    if (mListListener == null) {
	        mDialogType.onDropdownDialogItemClick(arg0, view, position, id);
	    } else {
	        mListListener.onClick(this, view, position);	        
	    }
	}
	
	private void setCurrentDialogType(DialogType type) {
		if(mDialogType == mMessageItemDialogType) {
			mDialogType = type;		
			changeDialogType(mDialogType);
		}
	}
	
	private void changeDialogType(DialogType type) {
		type.copyDialogType(mMessageItemDialogType);
	}
	
	public void setAdapter(ListAdapter adapter, DialogAdapter.OnClickListener listener) {
	    mListView.setAdapter(adapter);
	    mListListener = listener;
	}

}

