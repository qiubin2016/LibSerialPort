package com.example.libserialport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mButton;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("serial_port");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Log.i(TAG, "onClick");
                try {
                    SerialPort serial = new SerialPort(new File("/dev/ttyS0"), 9600, 0);
                    OutputStream outputStream = serial.getOutputStream();
                    outputStream.write(new String("00001111").getBytes());
                    serial.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

//    public native String stringFromJNI01();
}
