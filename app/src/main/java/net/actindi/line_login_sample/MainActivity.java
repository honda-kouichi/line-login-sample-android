package net.actindi.line_login_sample;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import jp.line.android.sdk.LineSdkContext;
import jp.line.android.sdk.LineSdkContextManager;
import jp.line.android.sdk.login.LineAuthManager;
import jp.line.android.sdk.login.LineLoginFuture;
import jp.line.android.sdk.login.LineLoginFutureListener;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLineLogInButton(View view)
    {
        LineSdkContext lineSdkContext = LineSdkContextManager.getSdkContext();
        if(lineSdkContext == null)
        {
            new AlertDialog.Builder(this).setMessage("login error").create().show();
            return;
        }
        LineAuthManager lineAuthManager = lineSdkContext.getAuthManager();
        if(lineAuthManager == null)
        {
            new AlertDialog.Builder(this).setMessage("login error").create().show();
            return;
        }
        LineLoginFuture lineLoginFuture = lineAuthManager.login(this);
        if(lineLoginFuture == null)
        {
            new AlertDialog.Builder(this).setMessage("login error").create().show();
            return;
        }
        lineLoginFuture.addFutureListener(new LineLoginFutureListener()
        {
            @Override
            public void loginComplete(LineLoginFuture lineLoginFuture)
            {
                switch (lineLoginFuture.getProgress())
                {
                    case SUCCESS:
                        new AlertDialog.Builder(MainActivity.this).setMessage("login success")
                                .create().show();
                        break;
                    case CANCELED:
                        new AlertDialog.Builder(MainActivity.this).setMessage("login canceled")
                                .create().show();
                        break;
                    default:
                        String message = new StringBuffer()
                                .append("login error:")
                                .append(lineLoginFuture.getProgress()).toString();
                        new AlertDialog.Builder(MainActivity.this).setMessage(message)
                                .create().show();
                }
            }
        });
    }
}
