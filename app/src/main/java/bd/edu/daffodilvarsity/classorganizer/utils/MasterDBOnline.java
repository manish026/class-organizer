package bd.edu.daffodilvarsity.classorganizer.utils;

import android.content.Context;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

/**
 * Created by Mushfiqus Salehin on 10/5/2017.
 * musfiqus@gmail.com
 */

public class MasterDBOnline extends MasterDBOffline {
    private static final String TAG = "MasterDBOnline";

    private static MasterDBOnline mInstance = null;

    private MasterDBOnline(Context context) {
        super(context, new PrefManager(context).getOnlineDbName(), new PrefManager(context).getDatabaseVersion());
        setForcedUpgrade();
    }

    public static MasterDBOnline getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance != null) {
            mInstance = null;
        }
        mInstance = new MasterDBOnline(context.getApplicationContext());
        return mInstance;
    }

    @Override
    protected void errorLog(Exception e) {
        Log.e(TAG, e.toString());
        Crashlytics.logException(e);
    }
}