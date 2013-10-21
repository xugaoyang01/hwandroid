package hwdroid.widget.itemview;

import hwdroid.widget.item.DropdownDialogSingleChoiceItem;
import hwdroid.widget.item.Item;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hw.droid.R;

public class DropdownDialogSingleChoiceItemView  extends LinearLayout implements ItemView{
	protected TextView mTextView;
	private RadioButton mRadioButton;
	
    public DropdownDialogSingleChoiceItemView(Context context) {
        this(context, null);
    }

    public DropdownDialogSingleChoiceItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

	@Override
	public void prepareItemView() {
		 mTextView = (TextView) findViewById(R.id.gd_text);
		 mRadioButton = (RadioButton) findViewById(R.id.gd_radio);
	}

	@Override
	public void setObject(Item object) {
		DropdownDialogSingleChoiceItem item = (DropdownDialogSingleChoiceItem) object;
		setTextView(item.mText);
		setCheckBox(item.mCheckStatus);
	}

	@Override
	public void setTextView(String text) {
		if(mTextView != null) {
			mTextView.setText(text);
		}
	}

	@Override
	public void setSubtextView(String text) {
	}

	@Override
	public void setHeaderTextView(String text) {
	}

	@Override
	public void setIcon(ImageView view) {
	}

	@Override
	public void removeIcon() {
	}

	@Override
	public ImageView getIcon() {
		return null;
	}

	@Override
	public void setCheckBox(boolean status) {	
		mRadioButton.setChecked(status);
	}

	@Override
	public void setCheckBox() {
		mRadioButton.setChecked(!mRadioButton.isChecked());
	}

	@Override
	public void setCustomView(int viewId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getCustomView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDividerVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}
}
