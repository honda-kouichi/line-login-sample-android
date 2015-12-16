package net.actindi.line_login_sample;

import android.app.Application;
import jp.line.android.sdk.LineSdkContextManager;

/**
 * Created by kouichihonda on 2015/12/15.
 */
public class LineLoginSampleApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        LineSdkContextManager.initialize(this);
    }
}
