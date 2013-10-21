package hwdroid.dialog;

import android.content.Context;
import android.os.Message;
import android.view.View;

public abstract class Dialog {
    /**
     * The identifier for the positive button.
     */
    public static final int BUTTON_POSITIVE = -1;

    /**
     * The identifier for the negative button. 
     */
    public static final int BUTTON_NEGATIVE = -2;

    /**
     * The identifier for the neutral button. 
     */
    public static final int BUTTON_NEUTRAL = -3;

    /**
     * @deprecated Use {@link #BUTTON_POSITIVE}
     */
    @Deprecated
    public static final int BUTTON1 = BUTTON_POSITIVE;

    /**
     * @deprecated Use {@link #BUTTON_NEGATIVE}
     */
    @Deprecated
    public static final int BUTTON2 = BUTTON_NEGATIVE;

    /**
     * @deprecated Use {@link #BUTTON_NEUTRAL}
     */
    @Deprecated
    public static final int BUTTON3 = BUTTON_NEUTRAL;
    
	public Dialog(Context context){	
	}
	
	public Dialog(Context context, int theme){	
	}
	
	public abstract void setCancelMessage (Message msg);
	public abstract void setCancelable (boolean flag);
	public abstract void setCanceledOnTouchOutside (boolean cancel);
	public abstract void setDismissMessage (Message msg);
	public abstract void setOnCancelListener (DialogInterface.OnCancelListener listener);
	public abstract void setOnDismissListener (DialogInterface.OnDismissListener listener);
	public abstract void setTitle (int titleId);
	public abstract void setTitle (CharSequence title);
	public abstract void setView(View v);
	public abstract void show (View v);
	public abstract void dismiss();

}
