package com.example.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
        String TAG = "abc";

    public static String ACTION_1 = "MY_ACTION_1";
    public MyIntentService() {
        super("SimpleIntentService");
        Log.d(TAG, "MyIntentService: ");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(TAG, "onHandleIntent: ");
        Intent broadcastintent = new Intent();
        broadcastintent.setAction(MyIntentService.ACTION_1);


        for(int i=1;i<=100;i++){
            broadcastintent.putExtra("a",i);
            sendBroadcast(broadcastintent);
            SystemClock.sleep(100);
        }

    }



}
