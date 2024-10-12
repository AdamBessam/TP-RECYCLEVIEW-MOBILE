package com.ensa.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView ballon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ballon=findViewById(R.id.foot);
        ballon.animate().rotation(360f).setDuration(2000);
        ballon.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);
        ballon.animate().translationYBy(1000f).setDuration(2000);
        ballon.animate().alpha(0f).setDuration(6000);
        Thread t=new Thread(){
            public void run(){
                try {
                    sleep(4000);
                    Intent intent=new Intent(MainActivity.this,ListActivity.class);
                    startActivity(intent);



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }

        };
        t.start();





    }
    @Override
    public void onPause() {
        super.onPause();
        this.finish();
    }
}