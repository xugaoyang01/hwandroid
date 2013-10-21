package hwdroid.dialog;

import java.text.NumberFormat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

public class ProgressDialog extends AlertDialog{
    public static final int STYLE_SPINNER = 0;
    public static final int STYLE_HORIZONTAL = 1;
    
	int mMaxValue = 0;
	int mProgressValue = 0;
	CharSequence mTitle;
	CharSequence mMessage;
	DialogInterface.OnCancelListener mOnCancelListener;

	public ProgressDialog(Context context) {
		super(context);
	}
	
	public int getMax () {
		return mMaxValue;
	}
	
	public int getProgress () {
	    return mProgressValue;	
	}
	
	public int getSecondaryProgress () {
		return 0;
	}
	
	public void incrementProgressBy (int diff) {
		mProgressValue = mProgressValue + diff;
		mXXDropDownDialog.updateProgressItemMessage(mProgressValue, mMaxValue);
	}
	
	public void incrementSecondaryProgressBy (int diff) {
		
	}
	
	public boolean isIndeterminate () {
		return false;
	}
	
	public void onStart () {
		
	}
	
	public void setIndeterminate (boolean indeterminate) {
		
	}
	
	public void setIndeterminateDrawable (Drawable d) {
		
	}
	
	public void setMax (int max) {
		mMaxValue = max;
	}
	
	public void setMessage(CharSequence message) {
		mMessage = message;
	}
	
	public void setProgress (int value) {
		mProgressValue = value;
	}
	
	public void setProgressDrawable (Drawable d) {
		
	}
	
	public void setProgressNumberFormat (String format) {
		
	}
	
	public void setProgressPercentFormat (NumberFormat format) {
		
	}
	
	public void setProgressStyle (int style) {
		
	}
	
	public void setSecondaryProgress (int secondaryProgress) {
		
	}
	
	@Override
    public void show(View v) {
		mXXDropDownDialog.setProgressItem(mMessage, mOnCancelListener);
        mXXDropDownDialog.show(v);
    }
	
	public static ProgressDialog show (
			Context context, 
			CharSequence title, 
			CharSequence message) {
		
		ProgressDialog dialog = new ProgressDialog(context);
		return dialog;
	}
	
	public static ProgressDialog show (
			Context context, 
			CharSequence title, 
			CharSequence message, 
			boolean indeterminate, 
			boolean cancelable, 
			DialogInterface.OnCancelListener cancelListener, View v) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setIndeterminate(indeterminate);
		dialog.setCancelable(cancelable);
		dialog.setOnCancelListener(cancelListener);
		dialog.show(v);
		return dialog;
	}
	
	public static ProgressDialog show (
			Context context, 
			CharSequence title, 
			CharSequence message, 
			boolean indeterminate, 
			boolean cancelable,
			View v) {
		return ProgressDialog.show(context, title, message, indeterminate, false, null, v);
	}
	
	public static ProgressDialog show (
			Context context, 
			CharSequence title, 
			CharSequence message, 
			boolean indeterminate,
			View v) {
		return ProgressDialog.show(context, title, message, indeterminate, false, v);
	}

}
