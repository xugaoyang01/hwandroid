package hwdroid.widget.itemview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import hwdroid.widget.item.DropdownDialogCustomItem;
import hwdroid.widget.item.Item;

public class DropdownDialogCustomItemView extends LinearLayout implements ItemView{
	
	View mMainView;
	Context mContext;
	
    public DropdownDialogCustomItemView(Context context) {
        this(context, null);
    }

    public DropdownDialogCustomItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

	@Override
	public void prepareItemView() {
	}

	@Override
	public void setObject(Item object) {
		DropdownDialogCustomItem item = (DropdownDialogCustomItem) object;
		setCustomView(item.mCustomView);
	}

	@Override
	public void setTextView(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSubtextView(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeaderTextView(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIcon(ImageView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeIcon() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ImageView getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCheckBox(boolean status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCheckBox() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCustomView(int viewId) {
		mMainView = LayoutInflater.from(mContext).inflate(viewId, null, false);
		addView(mMainView);
	}

	@Override
	public View getCustomView() {
        return mMainView;
	}

	@Override
	public void setDividerVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}
}
