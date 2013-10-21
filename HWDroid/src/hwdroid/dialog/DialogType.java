package hwdroid.dialog;

import hwdroid.dialog.DialogInterface.OnClickListener;
import hwdroid.dialog.DialogInterface.OnMultiChoiceClickListener;
import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.DropdownDialogBtnItem;
import hwdroid.widget.item.Item;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hw.droid.R;

public abstract class DialogType {
	
	protected Context mContext;
	protected PopupWindow mPopupWindow;
	protected ItemAdapter mAdapter;
	protected LocalHandler mHandler;
	protected DialogInterface mDialogInterface;
	
	private ArrayList<String> mMessageList;	
	protected ArrayList<Item> mItemList;
	private LinearLayout mTitleView;
	private TextView mTitleText;
	private FrameLayout mCustomView;
	
	public String mTitleString;
	public String mButton1Text;
	public String mButton2Text;
	public String mButton3Text;
	public OnClickListener mOnClickListener;
	public OnClickListener mPositiveOnClickListener;
	public OnClickListener mNegativeOnClickListener;
	public OnClickListener mDismissOnClickListener;
	public DialogInterface.OnCancelListener mOnCancelListener;
	public DialogInterface.OnDismissListener mOnDismissListener;
	public OnMultiChoiceClickListener mMultiChoiceClickListener;
	
	public final int HANDLER_UPDATE_ADAPTER = 0x100;
	public final int HANDLER_CLOSE_DIALOG = 0x101;

	public void copyDialogType(DialogType type) {
		mTitleString = type.mTitleString;
		mButton1Text = type.mButton1Text;
		mButton2Text = type.mButton2Text;
		mButton3Text = type.mButton3Text;
		mOnClickListener = type.mOnClickListener;
		mPositiveOnClickListener = type.mPositiveOnClickListener;
		mNegativeOnClickListener = type.mNegativeOnClickListener;
		mNegativeOnClickListener = type.mNegativeOnClickListener;
		mDismissOnClickListener = type.mDismissOnClickListener;
		mOnCancelListener = type.mOnCancelListener;
		mOnDismissListener = type.mOnDismissListener;
		mMultiChoiceClickListener = type.mMultiChoiceClickListener;
	}
	
	public DialogType(Context context, 
			PopupWindow popupWindow, 
			DialogInterface dialogInterface, 
			ItemAdapter adapter,
			View menuView
			) {
		mContext = context;
		mDialogInterface = dialogInterface;
		mPopupWindow = popupWindow;
		mAdapter = adapter;
		mHandler = new LocalHandler();
		
		mTitleView = (LinearLayout)menuView.findViewById(R.id.dialog_title_view);
		mTitleText = (TextView) menuView.findViewById(R.id.dialog_title_text);
		mCustomView = (FrameLayout)menuView.findViewById(R.id.dialog_custom_view);
		init();
	}
	
	public void init() {
		mMessageList = new ArrayList<String>();
		mItemList = new ArrayList<Item>();
	}
	
	public void onDropdownDialogItemClick(AdapterView<?> arg0, View arg1,
			int arg2, long arg3) {
		int which = 0;
		
		if(mMessageList != null) {
			which += mMessageList.size();
		}
		
		Item item = (Item)mAdapter.getItem(arg2);
        if(item.getTag(1) != null || item.getTag(11) != null) {
			OnClickListener l = (OnClickListener)item.getTag(1);
			if(l != null) {
				l.onClick(mDialogInterface, DialogInterface.BUTTON_POSITIVE);
			}
			
			OnClickListener ll = (OnClickListener)item.getTag(1);
			if(ll != null) {
				
			}
			
			cancel();
		} else if(item.getTag(2) != null || item.getTag(22) != null) {
			OnClickListener l = (OnClickListener)item.getTag(2);
			if(l != null) {
				l.onClick(mDialogInterface, DialogInterface.BUTTON_NEGATIVE);
			}
			
			DialogInterface.OnCancelListener ll = (DialogInterface.OnCancelListener)item.getTag(22);
			if(ll != null) {
				ll.onCancel(mDialogInterface);
			}
			
			cancel();
		} else if(item.getTag(3) != null || item.getTag(33) != null) {
			DialogInterface.OnDismissListener ll = (DialogInterface.OnDismissListener)item.getTag(33);
			if(ll != null) {
				ll.onDismiss(mDialogInterface);
			}
			
			cancel();
		} else {
			callBack(arg2-which);
		}
		
	}
	
	public abstract Item createItemView(String text, boolean selected);
	public abstract void showChild();
	
	public void updateItem(int value, int maxValue) {
		
	}
	
	public void setView(View v) {
		mCustomView.setVisibility(View.VISIBLE);
		mCustomView.addView(v);
	}
	
	public void addItem(String text, boolean selected) {
		Item item = createItemView(text, selected);
		mItemList.add(item);
	}
	
	public void addItem(String text, DialogInterface.OnCancelListener listener, boolean selected) {
		mOnCancelListener = listener;
		Item item = createItemView(text, selected);
		mItemList.add(item);
	}
	
    public void addItems(CharSequence[] items, final OnClickListener listener, boolean[] selected) {
    	if(items == null) return;
    	
    	mOnClickListener = listener;
		for(int i = 0; i < items.length; i++) {
		    addItem(items[i].toString(), selected[i]);
		}
    }
    
    public void addItems(CharSequence[] items, final DialogInterface.OnMultiChoiceClickListener listener, boolean[] selected) {
    	if(items == null) return;
    	
    	boolean[] checked;
    	
    	if(selected == null) {
    		checked = new boolean[items.length];
    	} else {
    		checked = selected;
    	}
    	
    	mMultiChoiceClickListener = listener;
		for(int i = 0; i < items.length; i++) {
		    addItem(items[i].toString(), checked[i]);
		}
    }
	
	public void callBack(int index) {
		if(mOnClickListener != null) mOnClickListener.onClick(mDialogInterface, index);
	}
	
	public void show(View v) {
		if(mTitleString != null) {
			mTitleView.setVisibility(View.VISIBLE);
			mTitleText.setText(mTitleString);
		} else {
			mTitleView.setVisibility(View.GONE);
			mTitleText.setText("");
		}
		
    	for(int i = 0; i < mMessageList.size(); i++) {
    		Item item = createItemView(mMessageList.get(i), false);
    		mAdapter.add(item);
    	}
    	
    	showChild();
    	
    	if(mButton1Text != null) {
    		Item btn1 = new DropdownDialogBtnItem(mButton1Text);
    		btn1.setTag(1, mPositiveOnClickListener);
    		mAdapter.add(btn1);
    	}
    	
    	if(mButton2Text != null) {
    		Item btn2 = new DropdownDialogBtnItem(mButton2Text);
    		btn2.setTag(2, mNegativeOnClickListener);
    		btn2.setTag(22, mOnCancelListener);
    		mAdapter.add(btn2);
    	}
    	
    	if(mButton3Text != null) {
    		Item btn3 = new DropdownDialogBtnItem(mButton3Text);
    		btn3.setTag(3, mDismissOnClickListener);
    		btn3.setTag(33, mOnDismissListener);
    		mAdapter.add(btn3);
    	}    	
    	mPopupWindow.setAnimationStyle(R.style.HWDroid_Animation_DropDownDialog);
    	View layout = mPopupWindow.getContentView().findViewById(R.id.pop_layout);
    	Animation upAnim = AnimationUtils.loadAnimation(mContext, R.anim.hw_dropdown_dialog_from_bottom);
    	layout.startAnimation(upAnim);
    	mPopupWindow.showAtLocation(v, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
	}
    
	public void setOnCancelListener(DialogInterface.OnCancelListener listener) {
		mOnCancelListener = listener;
	}
	
	public void setOnDismissListener(DialogInterface.OnDismissListener listener) {	
		mOnDismissListener = listener;
	}
	
    public void setTitle(int titleId) {
    	mTitleString = mContext.getString(titleId);
    }
    
    public void setTitle(CharSequence title) {
    	if(title == null) return;
    	mTitleString = title.toString();
    }
    
    public void setMessage(int titleId) {
    	mMessageList.add(mContext.getString(titleId));
    }
    
    public void setMessage(CharSequence title) {
    	mMessageList.add(title.toString());
    }
    
    public void setButton1(String text, final OnClickListener listener) {
    	if(listener == null) {
    		mPositiveOnClickListener = new OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					cancel();
				}};
    	} else {
        	mPositiveOnClickListener = listener;
    	}
    	
    	mButton1Text = text;
    }
    
    public void setButton2(String text, final OnClickListener listener) {
    	if(listener == null) {
    		mNegativeOnClickListener = new OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					cancel();
				}};
    	} else {
    		mNegativeOnClickListener = listener;
    	}
    	
    	mButton2Text = text;
    }
    
    public void setButton3(String text, final OnClickListener listener) {
    	if(listener == null) {
    		mDismissOnClickListener = new OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					cancel();
				}};
    	} else {
    		mDismissOnClickListener = listener;
    	}
    	
    	mButton3Text = text;
    }
    
    @SuppressLint("HandlerLeak")
	public class LocalHandler extends Handler {
        @Override
            public void handleMessage(Message msg) {
        	switch(msg.what) { 
        	case HANDLER_UPDATE_ADAPTER:
        		try {
					if(mAdapter != null) {
						mAdapter.notifyDataSetChanged();
					}
        		}catch(Exception e) {
        			
        		}
			    break;
        	case HANDLER_CLOSE_DIALOG:
        		try {
        		    mPopupWindow.dismiss();
        		} catch(Exception e){
        			
        		}
        		break;
        	default:
        		break;
        	}
        }
    }
    
	public void cancel() {
		mHandler.sendEmptyMessage(HANDLER_CLOSE_DIALOG);
	}
	
	public void callDismiss() {
		if(mOnDismissListener != null) {
			mOnDismissListener.onDismiss(mDialogInterface);
		}
		cancel();
	}

}
