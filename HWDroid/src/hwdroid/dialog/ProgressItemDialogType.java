package hwdroid.dialog;

import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.Item;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hw.droid.R;

public class ProgressItemDialogType extends DialogType{
	
	public final int HANDLER_UPDATE_PROGRESS = 0x200;
	
	private LinearLayout mProgressView;
	private TextView mProgressMessage;
	private TextView mProgressCount;
	private ProgressBar mProgressBar;
	
	private int mValue;
	private int mMaxValue;
	
	private ProgressHandler mProgressHandler;

	public ProgressItemDialogType(Context context, XXDropDownDialog popupWindow,
			DialogInterface dialogInterface, ItemAdapter adapter,
			View menuView) {
		super(context, popupWindow, dialogInterface, adapter, menuView);
		mProgressHandler = new ProgressHandler();
		
		mProgressView = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.hw_dropdown_dialog_progress_item_view, null, false);
        mProgressMessage = (TextView) mProgressView.findViewById(R.id.dialog_progress_message_text);
        mProgressCount = (TextView) mProgressView.findViewById(R.id.dialog_progress_count_text);  
        mProgressBar = (ProgressBar) mProgressView.findViewById(R.id.progress_bar);
	}

	@Override
	public Item createItemView(String text, boolean selected) { 	   	
        return null;
	}

	@Override
	public void showChild() {
        setView(mProgressView);
	}

	@Override
	public void updateItem(int value, int maxValue) {
		if(mMaxValue != maxValue) {
			mMaxValue = maxValue;
			mProgressBar.setMax(mMaxValue);
		}
		
		mValue = value;

    	if(mProgressCount != null) { 
    		if(!mProgressHandler.hasMessages(HANDLER_UPDATE_PROGRESS)) {
    			mProgressHandler.sendEmptyMessageDelayed(HANDLER_UPDATE_PROGRESS, 0);
    		}
    	}
	}
	
    public void setMessage(int titleId) {
    	mProgressMessage.setText(mContext.getString(titleId));
    }
    
    public void setMessage(CharSequence title) {
    	if(title == null) return;
    	mProgressMessage.setText(title.toString());
    }
    
    @SuppressLint("HandlerLeak")
	private class ProgressHandler extends Handler {
        @Override
            public void handleMessage(Message msg) {
        	switch(msg.what) { 
        	case HANDLER_UPDATE_PROGRESS:
        		    if(mValue > mMaxValue) {
        		    	return;
        		    }
        		    
        		    mProgressCount.setText("" + mValue + "/" + mMaxValue);
        		    mProgressBar.setProgress(mValue);
			    break;
        	default:
        		break;
        	}
        }
    }
}
