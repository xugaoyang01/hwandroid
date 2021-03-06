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
import hwdroid.widget.item.LongTextItem;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * View representation of the {@link LongTextItem}.
 * 
 * @author Cyril Mottier
 */
public class LongTextItemView extends TextView implements ItemView {

    public LongTextItemView(Context context) {
        this(context, null);
    }

    public LongTextItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LongTextItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void prepareItemView() {
    }

    public void setObject(Item item) {
        setText(((LongTextItem) item).text);
    }

}
