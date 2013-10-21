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
package hwdroid.app;

import com.hw.droid.R;

import hwdroid.util.Config;
import hwdroid.widget.ActionBar;
import hwdroid.widget.ActionBarHost;
import hwdroid.widget.ActionBarItem;
import hwdroid.widget.ActionBar.OnActionBarListener;
import hwdroid.widget.ActionBar.Type;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

/**
 * <p>
 * An HWActivity is a regular Activity that hosts an {@link ActionBar}. It is
 * extremely simple to use as you have nothing particular to do. Indeed, the
 * {@link ActionBar} is automatically added to your own layout when using the
 * {@link #getContentView()} method. You can also use one of the
 * setActionBarContentView utility methods. As a result, a basic HWActivity will
 * often be initialized using the following snippet of code:
 * </p>
 * 
 * <pre>
 * protected void onCreate(Bundle savedInstanceState) {
 *     super.onCreate(savedInstanceState);
 * 
 *     setActionBarContentView(R.layout.main);
 * }
 * </pre>
 * <p>
 * An {@link ActionBar} is a widget that may contains actions items and a title.
 * You can also set the title putting an extra string with the key
 * {@link ActionBarActivity#HW_ACTION_BAR_TITLE} in your Intent:
 * </p>
 * 
 * <pre>
 * Intent intent = new Intent(this, MyHWActivity.class);
 * intent.putExtra(ActionBarActivity.HW_ACTION_BAR_TITLE, &quot;Next screen title&quot;);
 * startActivity(intent);
 * </pre>
 * <p>
 * <em><strong>Note</strong>: An HWActivity automatically handle the type of the {@link ActionBar}
 * (taken from {@link ActionBar.Type}) depending on the value returned by the
 * {@link HWApplication#getHomeActivityClass()} method. However you can force the
 * type of the action bar in your constructor. Make the Activity declared in the AndroidManifest.xml 
 * has at least a constructor with no arguments as required by the Android platform.</em>
 * </p>
 * 
 * <pre>
 * public MyHWActivity() {
 *     super(ActionBar.Type.Dashboard);
 * }
 * </pre>
 * <p>
 * All Activities that inherits from an HWActivity are notified when an action
 * button is tapped in the
 * {@link #onHandleActionBarItemClick(ActionBarItem, int)} method. By default
 * this method does nothing but return false.
 * </p>
 * 
 * @see HWApplication#getHomeActivityClass()
 * @see ActionBarActivity#HW_ACTION_BAR_TITLE
 * @see HWActivity#setActionBarContentView(int)
 * @see HWActivity#setActionBarContentView(View)
 * @see HWActivity#setActionBarContentView(View, LayoutParams)
 * @author Cyril Mottier
 */
public class HWActivity extends Activity implements ActionBarActivity {

    private static final String LOG_TAG = HWActivity.class.getSimpleName();

    private boolean mDefaultConstructorUsed = false;

    private Type mActionBarType;
    private ActionBarHost mActionBarHost;

    /**
     * <p>
     * Default constructor.
     * </p>
     * <p>
     * <em><strong>Note</strong>: This constructor should never be used manually. 
     * In order to instantiate an Activity you should let the Android system do 
     * it for you by calling startActivity(Intent)</em>
     * </p>
     */
    public HWActivity() {
        this(Type.Normal);
        mDefaultConstructorUsed = true;
    }

    /**
     * <p>
     * Create a new Activity with an {@link ActionBar} of the given type.
     * </p>
     * <p>
     * <em><strong>Note</strong>: This constructor should never be used manually. 
     * In order to instantiate an Activity you should let the Android system do 
     * it for you by calling startActivity(Intent)</em>
     * </p>
     * 
     * @param actionBarType The {@link ActionBar.Type} for this Activity
     */
    public HWActivity(ActionBar.Type actionBarType) {
        super();
        mActionBarType = actionBarType;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        ensureLayout();
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mDefaultConstructorUsed) {
            // HACK cyril: This should have been done in the default
            // constructor. Unfortunately, the getApplication() method returns
            // null there. Hence, this has to be done here.
            if (getClass().equals(getHWApplication().getHomeActivityClass())) {
                mActionBarType = Type.Dashboard;
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ensureLayout();
    }

    /**
     * The current {@link ActionBar.Type} of the hosted {@link ActionBar}
     * 
     * @return The current {@link ActionBar.Type} of the hosted
     *         {@link ActionBar}
     */
    public ActionBar.Type getActionBarType() {
        return mActionBarType;
    }

    public int createLayout() {
        switch (mActionBarType) {
            case Dashboard:
                return R.layout.hw_content_dashboard;
            case Empty:
                return R.layout.hw_content_empty;
            case Normal:
            default:
                return R.layout.hw_content_normal;
        }
    }

    /**
     * Call this method to ensure a layout has already been inflated and
     * attached to the top-level View of this Activity.
     */
    protected void ensureLayout() {
        if (!verifyLayout()) {
            setContentView(createLayout());
        }
    }

    /**
     * Verify the given layout contains everything needed by this Activity. A
     * HWActivity, for instance, manages an {@link ActionBarHost}. As a result
     * this method will return true of the current layout contains such a
     * widget.
     * 
     * @return true if the current layout fits to the current Activity widgets
     *         requirements
     */
    protected boolean verifyLayout() {
        return mActionBarHost != null;
    }

    public HWApplication getHWApplication() {
        return (HWApplication) getApplication();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        onPreContentChanged();
        onPostContentChanged();
    }

    public void onPreContentChanged() {
        mActionBarHost = (ActionBarHost) findViewById(R.id.hw_action_bar_host);
        if (mActionBarHost == null) {
            throw new RuntimeException("Your content must have an ActionBarHost whose id attribute is R.id.hw_action_bar_host");
        }
        mActionBarHost.getActionBarImpl().setOnActionBarListener(mActionBarListener);
    }

    public void onPostContentChanged() {

        boolean titleSet = false;

        final Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra(ActionBarActivity.HW_ACTION_BAR_TITLE);
            if (title != null) {
                titleSet = true;
                setTitle(title);
            }
        }

        if (!titleSet) {
            // No title has been set via the Intent. Let's look in the
            // ActivityInfo
            try {
                final ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), 0);
                if (activityInfo.labelRes != 0) {
                    setTitle(activityInfo.labelRes);
                }
            } catch (NameNotFoundException e) {
                // Do nothing
            }
        }

        final int visibility = intent.getIntExtra(ActionBarActivity.HW_ACTION_BAR_VISIBILITY, View.VISIBLE);
        getActionBarImpl().setVisibility(visibility);
    }

    @Override
    public void setTitle(CharSequence title) {
        getActionBarImpl().setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    public ActionBar getActionBarImpl() {
        ensureLayout();
        return mActionBarHost.getActionBarImpl();
    }

    public ActionBarItem addActionBarItem(ActionBarItem item) {
        return getActionBarImpl().addItem(item);
    }

    public ActionBarItem addActionBarItem(ActionBarItem item, int itemId) {
        return getActionBarImpl().addItem(item, itemId);
    }

    public ActionBarItem addActionBarItem(ActionBarItem.Type actionBarItemType) {
        return getActionBarImpl().addItem(actionBarItemType);
    }

    public ActionBarItem addActionBarItem(ActionBarItem.Type actionBarItemType, int itemId) {
        return getActionBarImpl().addItem(actionBarItemType, itemId);
    }

    public FrameLayout getContentView() {
        ensureLayout();
        return mActionBarHost.getContentView();
    }

    /**
     * <p>
     * Set the activity content from a layout resource. The resource will be
     * inflated, adding all top-level views to the activity.
     * </p>
     * <p>
     * This method is an equivalent to setContentView(int) that automatically
     * wraps the given layout in an {@link ActionBarHost} if needed..
     * </p>
     * 
     * @param resID Resource ID to be inflated.
     * @see #setActionBarContentView(View)
     * @see #setActionBarContentView(View, LayoutParams)
     */
    public void setActionBarContentView(int resID) {
        final FrameLayout contentView = getContentView();
        contentView.removeAllViews();
        LayoutInflater.from(this).inflate(resID, contentView);
    }

    /**
     * <p>
     * Set the activity content to an explicit view. This view is placed
     * directly into the activity's view hierarchy. It can itself be a complex
     * view hierarchy.
     * </p>
     * <p>
     * This method is an equivalent to setContentView(View, LayoutParams) that
     * automatically wraps the given layout in an {@link ActionBarHost} if
     * needed.
     * </p>
     * 
     * @param view The desired content to display.
     * @param params Layout parameters for the view.
     * @see #setActionBarContentView(View)
     * @see #setActionBarContentView(int)
     */
    public void setActionBarContentView(View view, LayoutParams params) {
        final FrameLayout contentView = getContentView();
        contentView.removeAllViews();
        contentView.addView(view, params);
    }

    /**
     * <p>
     * Set the activity content to an explicit view. This view is placed
     * directly into the activity's view hierarchy. It can itself be a complex
     * view hierarchy.
     * </p>
     * <p>
     * This method is an equivalent to setContentView(View) that automatically
     * wraps the given layout in an {@link ActionBarHost} if needed.
     * </p>
     * 
     * @param view The desired content to display.
     * @see #setActionBarContentView(int)
     * @see #setActionBarContentView(View, LayoutParams)
     */
    public void setActionBarContentView(View view) {
        final FrameLayout contentView = getContentView();
        contentView.removeAllViews();
        contentView.addView(view);
    }

    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
        return false;
    }

    private OnActionBarListener mActionBarListener = new OnActionBarListener() {
        public void onActionBarItemClicked(int position) {
            if (position == OnActionBarListener.HOME_ITEM) {

                final HWApplication app = getHWApplication();
                switch (mActionBarType) {
                    case Normal:
                        final Class<?> klass = app.getHomeActivityClass();
                        if (klass != null && !klass.equals(HWActivity.this.getClass())) {
                            if (Config.HW_INFO_LOGS_ENABLED) {
                                Log.i(LOG_TAG, "Going back to the home activity");
                            }
                            Intent homeIntent = new Intent(HWActivity.this, klass);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);
                        }
                        break;
                    case Dashboard:
                        final Intent appIntent = app.getMainApplicationIntent();
                        if (appIntent != null) {
                            if (Config.HW_INFO_LOGS_ENABLED) {
                                Log.i(LOG_TAG, "Launching the main application Intent");
                            }
                            startActivity(appIntent);
                        }
                        break;
                }

            } else {
                if (!onHandleActionBarItemClick(getActionBarImpl().getItem(position), position)) {
                    if (Config.HW_WARNING_LOGS_ENABLED) {
                        Log.w(LOG_TAG, "Click on item at position " + position + " dropped down to the floor");
                    }
                }
            }
        }
    };

}
