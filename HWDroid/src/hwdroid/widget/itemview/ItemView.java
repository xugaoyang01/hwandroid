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

import android.view.View;
import android.widget.ImageView;
import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.Item;

/**
 * <p>
 * An ItemView defines several methods necessary to the {@link ItemAdapter} in
 * order to process {@link Item}s.
 * </p>
 * <p>
 * When developing your own ItemViews, make sure they all implement this
 * interface.
 * </p>
 * 
 * @author Cyril Mottier
 */
public interface ItemView {

    void prepareItemView();
    void setObject(Item item);
    void setTextView(String text); 
    void setSubtextView(String text);
    void setHeaderTextView(String text);
    void setIcon(ImageView view);
    void removeIcon();
    ImageView getIcon();
    void setCheckBox(boolean status);
    void setCheckBox();
    void setCustomView(int viewId);
    View getCustomView();
    void setDividerVisible(boolean visible);
}
