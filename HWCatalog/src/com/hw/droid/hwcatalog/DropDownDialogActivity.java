package com.hw.droid.hwcatalog;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import hwdroid.app.HWListActivity;
import hwdroid.dialog.AlertDialog;
import hwdroid.dialog.Dialog;
import hwdroid.dialog.DialogInterface;
import hwdroid.dialog.ProgressDialog;
import hwdroid.widget.ItemAdapter;
import hwdroid.widget.item.TextItem;

public class DropDownDialogActivity extends HWListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ItemAdapter adapter = new ItemAdapter(this);
        adapter.add(createTextItem(" message style"));
        adapter.add(createTextItem("items style"));
        adapter.add(createTextItem("single choice items style"));
        adapter.add(createTextItem("multi choice items style"));
        adapter.add(createTextItem("progress bar style"));
        adapter.add(createTextItem("custom view style"));
        
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
        if( position == 0) {
	        Dialog alertDialog = new AlertDialog.Builder(this). 
	                setTitle("title"). 
	                setMessage("message"). 
	                setPositiveButton("OK", new DialogInterface.OnClickListener() { 
	                    
	                    @Override 
	                    public void onClick(DialogInterface dialog, int which) { 
	                    	Toast.makeText(DropDownDialogActivity.this, "ok", 1000).show();
	                    } 
	                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
	                     
	                    @Override 
	                    public void onClick(DialogInterface dialog, int which) { 
	                    	Toast.makeText(DropDownDialogActivity.this, "cancel", 1000).show();
	                    } 
	                }).
	                create(); 
	        alertDialog.show();
        } else if(position == 1) {
        	
        	CharSequence[] items = new CharSequence[]{"item1", "item2", "item3"};
	        Dialog alertDialog = new AlertDialog.Builder(this). 
	                setTitle("title"). 
	                setItems(items, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DropDownDialogActivity.this, "chick item" + (which + 1), 1000).show();
							dialog.cancel();
							
						}}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
		                     
		                    @Override 
		                    public void onClick(DialogInterface dialog, int which) { 
		                    	Toast.makeText(DropDownDialogActivity.this, "cancel", 1000).show();
		                    } 
		                }).create(); 
	        alertDialog.show();        	
        } else if(position == 2) {
        	
        	CharSequence[] items = new CharSequence[]{"item1", "item2", "item3"};
	        Dialog alertDialog = new AlertDialog.Builder(this). 
	                setTitle("title").
	                setSingleChoiceItems(items, 1,  new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DropDownDialogActivity.this, "chick item" + (which + 1), 1000).show();
							dialog.cancel();
							
						}}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
		                     
		                    @Override 
		                    public void onClick(DialogInterface dialog, int which) { 
		                    	Toast.makeText(DropDownDialogActivity.this, "cancel", 1000).show();
		                    } 
		                }).create(); 
	        alertDialog.show();        	
        } else if(position == 3) {
        	
        	boolean[] choiceStatus = new boolean[]{false, true, true};
        	CharSequence[] items = new CharSequence[]{"item1", "item2", "item3"};
	        Dialog alertDialog = new AlertDialog.Builder(this). 
	                setTitle("title").
	                setMultiChoiceItems(items, choiceStatus,  new DialogInterface.OnMultiChoiceClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which,
								boolean isChecked) {
							Toast.makeText(DropDownDialogActivity.this, "item " + which  + "is " + (isChecked?"choice":"not choice") , 1000).show();
						}
					}).setPositiveButton("OK", new DialogInterface.OnClickListener() { 
	                    
	                    @Override 
	                    public void onClick(DialogInterface dialog, int which) { 
	                    	Toast.makeText(DropDownDialogActivity.this, "ok", 1000).show();
	                    } 
	                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
		                     
		                    @Override 
		                    public void onClick(DialogInterface dialog, int which) { 
		                    	Toast.makeText(DropDownDialogActivity.this, "cancel", 1000).show();
		                    } 
		                }).create(); 
	        alertDialog.show();        	
        } else if(position == 4) {
        	ProgressDialog dialog = new ProgressDialog(this);
        	dialog.setCancelable(true);
        	dialog.setMessage("progress bar....");
        	dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
        	dialog.show();
        }
    }
}
