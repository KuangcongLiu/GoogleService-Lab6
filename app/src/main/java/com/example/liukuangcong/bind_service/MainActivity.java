package com.example.liukuangcong.bind_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private DemoBindService demoBindService;
    private boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = new Intent (this,DemoBindService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

    }
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DemoBindService.LocalService localService = (DemoBindService.LocalService) service;
            demoBindService = localService.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void setFirstMessage(View view)
    {
        TextView textView= (TextView ) findViewById(R.id.textView);
        textView.setText(demoBindService.getFirstMessage());

    }
    public void setSecondMessage(View view){
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(demoBindService.getSecondMessage());
    }
    @Override
    protected void onDestroy()
    {
        if( isBound)
        {
            unbindService(serviceConnection);
            isBound = false;
        }
        super.onDestroy();
    }




}
