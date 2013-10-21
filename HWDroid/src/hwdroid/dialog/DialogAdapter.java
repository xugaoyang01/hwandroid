package hwdroid.dialog;

import hwdroid.widget.item.DropdownDialogTextItem;
import hwdroid.widget.itemview.DropdownDialogTextItemView;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hw.droid.R;

public abstract class DialogAdapter extends BaseAdapter {

	public static final int TYPE_ITEM = 0;
	public static final int TYPE_CANCEL = 1;
	public static final int TYPE_YES_NO = 3;

	private ArrayList<ItemTextType> mDataList;
	protected Context mContext;
	private View.OnClickListener mOnYes;
	private View.OnClickListener mOnCancelOrNo;

	public abstract View newView(Context context, ItemTextType item,
			ViewGroup parent);

	public abstract View bindView(View view, Context context, ItemTextType item);

	public interface OnClickListener {
		public void onClick(DialogInterface dialog, View view, int which);
	}

	public DialogAdapter(Context context, String[] items, int exitType,
			View.OnClickListener cancel) {

		mContext = context;
		mOnCancelOrNo = cancel;
		mDataList = setupItemList(items, exitType);
	}

	public DialogAdapter(Context context, String[] items, int exitType,
			View.OnClickListener cancel, View.OnClickListener yes) {
		this(context, items, exitType, cancel);
		mOnYes = yes;
	}

	public static final class ItemTextType {
		public int id;
		public String text;
		private int type;

		public ItemTextType(String text, int type) {
			this.text = text;
			this.type = type;
		}
	}

	private ArrayList<ItemTextType> setupItemList(String[] items, int exitType) {
		mDataList = new ArrayList<ItemTextType>();
		for (String item : items) {
			mDataList.add(new ItemTextType(item, TYPE_ITEM));
		}

		switch (exitType) {
		case TYPE_CANCEL:
			mDataList.add(new ItemTextType("", TYPE_CANCEL));
			break;
		case TYPE_YES_NO:
			mDataList.add(new ItemTextType("", TYPE_YES_NO));
			break;
		}
		return mDataList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ItemTextType item = (ItemTextType) getItem(position);
		item.id = position;

		if (convertView == null || item.type != TYPE_ITEM 
				|| (convertView != null 
						&& convertView.getTag() == null 
						&& item.type == TYPE_ITEM )) {
			if (item.type == TYPE_CANCEL) {
				DropdownDialogTextItem ItemCancel = new DropdownDialogTextItem();
				convertView = (View) ItemCancel.newView(mContext, parent);
				((DropdownDialogTextItemView) convertView).setTextView(mContext
						.getResources().getString(R.string.hw_cancel));

				if (mOnCancelOrNo != null) {
					convertView.setOnClickListener(mOnCancelOrNo);
				}

			} else if (item.type == TYPE_YES_NO) {
				convertView = View.inflate(mContext,
						R.layout.hw_dropdown_dialog_yes_no_item_view, null);
				if (mOnYes != null) {
					convertView.findViewById(R.id.yes).setOnClickListener(
							mOnYes);
				}
				if (mOnCancelOrNo != null) {
					convertView.findViewById(R.id.no).setOnClickListener(
							mOnCancelOrNo);
				}

			} else if (item.type == TYPE_ITEM) {
				convertView = newView(mContext, item, parent);
			}

		}

		if (item.type == TYPE_ITEM) {
			bindView(convertView, mContext, item);
			if (convertView.getTag() == null) {
				convertView.setTag(new Object());
			}
		}
		return convertView;
	}

	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
}