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

import hwdroid.widget.AsyncImageView;
import hwdroid.widget.item.Item;
import hwdroid.widget.item.ThumbnailItem;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hw.droid.R;

/**
 * View representation of the {@link ThumbnailItem}.
 * 
 * @author Cyril Mottier
 */
public class ThumbnailItemView extends RelativeLayout implements ItemView {

    private TextView mTextView;
    private TextView mSubtitleView;
    private AsyncImageView mThumbnailView;

    public ThumbnailItemView(Context context) {
        this(context, null);
    }

    public ThumbnailItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThumbnailItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void prepareItemView() {
        mTextView = (TextView) findViewById(R.id.hw_text);
        mSubtitleView = (TextView) findViewById(R.id.hw_subtitle);
        mThumbnailView = (AsyncImageView) findViewById(R.id.hw_thumbnail);
    }

    public void setObject(Item object) {
        final ThumbnailItem item = (ThumbnailItem) object;
        mTextView.setText(item.mText);
        mSubtitleView.setText(item.subtitle);
        mThumbnailView.setDefaultImageResource(item.drawableId);
        mThumbnailView.setUrl(item.drawableURL);
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
