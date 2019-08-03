package com.example.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


   @BindView(R.id.progressBar2) ProgressBar progressBar;
   @BindView(R.id.start_btn) Button start_btn;
   @BindView(R.id.stop_btn) Button stop_btn;
   @BindView(R.id.percent)
    TextView percent;
   static String TAG="abc";

    ResponseReceiver responseReceiver = new ResponseReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),MyIntentService.class);
                    startService(intent);
            }
        });

    }



    public class ResponseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(MyIntentService.ACTION_1)){
                int value = intent.getIntExtra("a",-1);

                new ShowProgressBarTask().execute(value);
            }

        }
    }


    class ShowProgressBarTask extends AsyncTask<Integer,Integer,Integer>{

        @Override
        protected Integer doInBackground(Integer... integers) {
            Log.d(TAG, "doInBackground: "+integers.length);
            for(int i=0;i<integers.length;i++){
                Log.d(TAG, "doInBackground:" + integers[i]);
            }
            return integers[0];
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d(TAG, "onPostExecute: change update " + integer);
            percent.setText(integer + "% ");
            progressBar.setProgress(integer);
            if(integer == 100) {
                percent.setText("Completed");
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(responseReceiver,new IntentFilter(MyIntentService.ACTION_1));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(responseReceiver);
    }
}
