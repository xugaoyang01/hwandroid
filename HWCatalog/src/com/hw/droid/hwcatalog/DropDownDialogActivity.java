package com.hw.droid.hwcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import hwdroid.app.HWListActivity;
import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.TextItem;

public class DropDownDialogActivity extends HWListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ItemAdapter adapter = new ItemAdapter(this);
        adapter.add(createTextItem("style 1"));
        adapter.add(createTextItem("style 1"));
        adapter.add(createTextItem("style 1"));
        adapter.add(createTextItem("style 1"));
        adapter.add(createTextItem("style 1"));
        
        setListAdapter(adapter);
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
        
    }
    
    private TextItem createTextItem(String str) {
        final TextItem textItem = new TextItem(str);
        return textItem;
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        final TextItem textItem = (TextItem) l.getAdapter().getItem(position);
    }
}
