package com.example.student.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int counter;

    public synchronized void print() throws InterruptedException {
        /*
        counter++;
        Log.e(TAG, "counter = " + counter);
        TextView view1 = (TextView)findViewById(R.id.button1);
        view1.setText("" + counter);
        */
        while (counter < 1){
            wait();
        }
        Log.e(TAG, "Counter = " + counter);

    }

    public synchronized void click(){
        counter++;
        TextView view1 = findViewById(R.id.textView);
        view1.setText("" + counter);
        notifyAll();
    }

    public class ThreadCounter extends Thread {
        @Override
        public void run() {
            try {
                print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.button1) {
            ThreadCounter td = new ThreadCounter();
            td.start();

        }

        if (view.getId() == R.id.button2){
            click();
        }
    }
}
