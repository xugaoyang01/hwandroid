/*
 * Copyright (C) 2010 Cyril Mottier (http://www.cyrilmottier.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hwdroid.widget.itemview;

import hwdroid.widget.item.Item;
import hwdroid.widget.item.SubtitleItem;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hw.droid.R;

/**
 * View representation of the {@link SubtitleItem}.
 * 
 * @author Cyril Mottier
 */
public class SubtitleItemView extends LinearLayout implements ItemView {

    private TextView mTextView;
    private TextView mSubtitleView;

    public SubtitleItemView(Context context) {
        this(context, null);
    }

    public SubtitleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void prepareItemView() {
        mTextView = (TextView) findViewById(R.id.hw_text);
        mSubtitleView = (TextView) findViewById(R.id.hw_subtitle);
    }

    public void setObject(Item object) {
        final SubtitleItem item = (SubtitleItem) object;
        mTextView.setText(item.mText);
        mSubtitleView.setText(item.subtitle);
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
