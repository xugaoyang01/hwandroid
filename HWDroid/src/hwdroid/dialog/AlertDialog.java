package hwdroid.dialog;

import hwdroid.dialog.DialogInterface.OnCancelListener;
import hwdroid.dialog.DialogInterface.OnDismissListener;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.hw.droid.R;

public class AlertDialog extends Dialog{
	public  XXDropDownDialog mXXDropDownDialog;
	
	public AlertDialog(Context context) {
		this(context, 0);
	}
	
	public AlertDialog(Context context, int attr) {
		super(context, attr);
		initXXDropDownDialog(context);
	}
	
	private void initXXDropDownDialog(Context context) {
		mXXDropDownDialog = new XXDropDownDialog(context);
	}
	
	@Override
	public void setCancelMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCancelable(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCanceledOnTouchOutside(boolean cancel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDismissMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOnCancelListener(OnCancelListener listener) {
		mXXDropDownDialog.setOnCancelListener(listener);
	}

	@Override
	public void setOnDismissListener(OnDismissListener listener) {
		mXXDropDownDialog.setOnDismissListener(listener);
	}

	@Override
	public void show() {
		mXXDropDownDialog.showDialog();
	}
    
    public void setTitle(int titleId) {
    	mXXDropDownDialog.setTitle(titleId);
    }
    
    public void setTitle(CharSequence title) {
    	mXXDropDownDialog.setTitle(title);
    }
    
    public void setView(View v) {
    	mXXDropDownDialog.setView(v);
    }
    
	@Override
	public void dismiss() {
		mXXDropDownDialog.callDismiss();
	}
	
    public void setButton(int whichButton, CharSequence text, DialogInterface.OnClickListener listener) {
    	if(whichButton == BUTTON_NEGATIVE) {
    		mXXDropDownDialog.setButton1(text, listener);
    	}
    }
    
    public View getCustomView() {
    	return null;
    }
	
    public static class Builder {
    	Context mContext;
    	AlertDialog mDialog;

        public Builder(Context context) {
        	mContext = context;
        	mDialog = new AlertDialog(mContext);
        }
            
        public Context getContext() {
            return mContext;
        }

        public Builder setTitle(int titleId) {
        	mDialog.mXXDropDownDialog.setTitle(titleId);
            return this;
        }
        
        public Builder setTitle(CharSequence title) {
        	mDialog.mXXDropDownDialog.setTitle(title);
            return this;
        }
        
        public Builder setCustomTitle(View customTitleView) {
            return this;
        }
        
        public Builder setMessage(int messageId) {
        	mDialog.mXXDropDownDialog.setMessage(messageId);
            return this;
        }
        
        public Builder setMessage(CharSequence message) {
        	mDialog.mXXDropDownDialog.setMessage(message);
            return this;
        }
        
        public Builder setIcon(int iconId) {
            return this;
        }
        
        public Builder setIcon(Drawable icon) {
            return this;
        }
        
        public Builder setPositiveButton(final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.setButton1(R.string.hw_ok, listener);
            return this;
        }

        public Builder setPositiveButton(int textId, final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.setButton1(textId, listener);
            return this;
        }
        
        public Builder setPositiveButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.setButton1(text,listener);
            return this;
        }
        
        public Builder setNegativeButton(final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.setButton2(R.string.hw_cancel, listener);
            return this;
        }
        
        public Builder setNegativeButton(int textId, final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.setButton2(textId, listener);
            return this;
        }
        
        public Builder setNegativeButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.setButton2(text, listener);
            return this;
        }
        
        public Builder setNeutralButton(int textId, final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.addItem(textId);
            return this;
        }
        
        public Builder setNeutralButton(CharSequence text, final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.addItem(text);
            return this;
        }
        
        public Builder setCancelable(boolean cancelable) {
        	mDialog.mXXDropDownDialog.setButton2(R.string.hw_cancel, null);
            return this;
        }
        
        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        	mDialog.setOnCancelListener(onCancelListener);
            return this;
        }
        

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        	mDialog.setOnDismissListener(onDismissListener);
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            return this;
        }
        
        public Builder setItems(int itemsId, final DialogInterface.OnClickListener listener) {
            return this;
        }
        
        public Builder setItems(CharSequence[] items, final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.addItems(items, listener);
            return this;
        }
        
        public Builder setAdapter(final ListAdapter adapter, final DialogAdapter.OnClickListener listener) {
            mDialog.mXXDropDownDialog.setAdapter(adapter, listener);
            return this;
        }
        
        public Builder setCursor(final Cursor cursor, final DialogInterface.OnClickListener listener,
                String labelColumn) {
            return this;
        }
        
        public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems, 
                final DialogInterface.OnMultiChoiceClickListener listener) {
            return this;
        }
        
        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, 
                final DialogInterface.OnMultiChoiceClickListener listener) {
        	mDialog.mXXDropDownDialog.setMultiChoiceItems(items, checkedItems, listener);
            return this;
        }
        
        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, 
                final DialogInterface.OnMultiChoiceClickListener listener) {
            return this;
        }
        
        public Builder setSingleChoiceItems(int itemsId, int checkedItem, 
                final DialogInterface.OnClickListener listener) {
            return this;
        }
        
        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, 
                final DialogInterface.OnClickListener listener) {
            return this;
        }
        
        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, final DialogInterface.OnClickListener listener) {
        	mDialog.mXXDropDownDialog.setSingleChoiceItems(items, checkedItem, listener);
        	return this;
        } 
        
        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, final DialogInterface.OnClickListener listener) {
            return this;
        }
        
        public Builder setOnItemSelectedListener(final AdapterView.OnItemSelectedListener listener) {
            return this;
        }
        
        public Builder setView(View v) {
        	mDialog.setView(v);
            return this;
        }
        
        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop,
                int viewSpacingRight, int viewSpacingBottom) {
            return this;
        }
        
        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean enabled) {
            return this;
        }

        public AlertDialog create() {
            return mDialog;
        }
        
        public Builder setIconAttribute(int attr) {
        	return this;
        }
    }
}
