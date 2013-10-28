package hwdroid.dialog;

import hwdroid.dialog.DialogInterface.OnClickListener;
import hwdroid.dialog.DialogInterface.OnMultiChoiceClickListener;
import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.DropdownDialogBtnItem;
import hwdroid.widget.item.Item;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.hw.droid.R;
import hwdroid.dialog.DialogInterface;

public abstract class DialogType {
	
	protected Context mContext;
	protected XXDropDownDialog mPopupWindow;
	protected ItemAdapter mAdapter;
	protected LocalHandler mHandler;
	protected DialogInterface mDialogInterface;
	
	private ArrayList<String> mMessageList;	
	protected ArrayList<Item> mItemList;
	private LinearLayout mTitleView;
	private TextView mTitleText;
	private FrameLayout mCustomView;
	private LinearLayout mFooterView;
	
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
			XXDropDownDialog popupWindow, 
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
		mFooterView = (LinearLayout)menuView.findViewById(R.id.dialog_footer_view);
		init();
	}
	
	public void init() {
		mMessageList = new ArrayList<String>();
		mItemList = new ArrayList<Item>();
	}
	
	private void createFooterView() {
		
		boolean hasDivider = false;
		
    	if(mButton1Text != null) {
    		Button btn1 = new Button(mContext);
    		btn1.setBackgroundResource(R.drawable.hw_dropdown_dialog_btn_footer_view);
    		btn1.setText(mButton1Text);
    		btn1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
    		mFooterView.addView(btn1);
    		btn1.setOnClickListener(new android.view.View.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					
					if(mPositiveOnClickListener != null) {
						mPositiveOnClickListener.onClick(mDialogInterface, DialogInterface.BUTTON_POSITIVE);
					}
					
					cancelDialog();
				}});
    		
    		hasDivider = true;
    	}
    	
    	if(mButton2Text != null) {
    		if(hasDivider == true) {
    			ImageView dividerImg= new ImageView(mContext);
                final LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 0);
                dividerImg.setLayoutParams(lp);
    			dividerImg.setBackgroundResource(R.drawable.hw_dropdown_dialog_btn_footer_view);
    			mFooterView.addView(dividerImg);
    		}
    		
    		Button btn2 = new Button(mContext);
    		btn2.setBackgroundResource(R.drawable.hw_dropdown_dialog_btn_footer_view);
    		btn2.setText(mButton2Text);
    		btn2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
    		mFooterView.addView(btn2);
    		
    		btn2.setOnClickListener(new android.view.View.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					
					if(mNegativeOnClickListener != null) {
						mNegativeOnClickListener.onClick(mDialogInterface, DialogInterface.BUTTON_NEGATIVE);
					}
					
					if(mOnCancelListener != null) {
						mOnCancelListener.onCancel(mDialogInterface);
					}
					
					cancelDialog();
				}});
    		
    		hasDivider = true;
    	}
    	
    	if(mButton3Text != null) {
    		if(hasDivider == true) {
    			ImageView dividerImg= new ImageView(mContext);
                final LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 0);
                dividerImg.setLayoutParams(lp);
    			dividerImg.setBackgroundResource(R.drawable.dialog_list_btn_bg_normal);
    			mFooterView.addView(dividerImg);
    		}
    		
    		Button btn3 = new Button(mContext);
    		btn3.setBackgroundResource(R.drawable.hw_dropdown_dialog_btn_footer_view);
    		btn3.setText(mButton3Text);
    		btn3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
    		mFooterView.addView(btn3);
    		
    		btn3.setOnClickListener(new android.view.View.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					
					if(mOnDismissListener != null) {
						mOnDismissListener.onDismiss(mDialogInterface);
					}
					
					cancelDialog();
				}});
    	}    
	}
	
	public void onDropdownDialogItemClick(AdapterView<?> arg0, View arg1,
			int arg2, long arg3) {
		int which = 0;
		
		if(mMessageList != null) {
			which += mMessageList.size();
		}
		
		callBack(arg2-which);
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
	
	public void createDialogRootView() {
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
    	
    	createFooterView();
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
					cancelDialog();
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
					cancelDialog();
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
					cancelDialog();
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
        		    mPopupWindow.cancel();
        		} catch(Exception e){
        			
        		}
        		break;
        	default:
        		break;
        	}
        }
    }
    
	public void cancelDialog() {
		mHandler.sendEmptyMessage(HANDLER_CLOSE_DIALOG);
	}
	
	public void callDismiss() {
		if(mOnDismissListener != null) {
			mOnDismissListener.onDismiss(mDialogInterface);
		}
		cancelDialog();
	}

}
