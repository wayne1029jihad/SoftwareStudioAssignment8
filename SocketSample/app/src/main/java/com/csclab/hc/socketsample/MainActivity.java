package com.csclab.hc.socketsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.OutputStream;
import java.net.Socket;
import android.util.Log;

public class MainActivity extends Activity implements android.view.View.OnClickListener{
    /** Init Variable for IP page **/
    EditText inputIP;
    Button ipSend;
    String ipAdd = "";

    /** Init Variable for Page 1 **/
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip_page);
        inputIP = (EditText)findViewById(R.id.edIP);
        ipSend = (Button)findViewById(R.id.ipButton);

        ipSend.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Func() for setup page 1 **/
                ipAdd = inputIP.getText().toString();
                jumpToMainLayout();
            }
        });
    }

    /** Function for page 1 setup */
    public void jumpToMainLayout() {
        //TODO: Change layout to activity_main
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
    }

    /** Function for onclick() implement */
    @Override
    public void onClick(View v) {
        Log.d("Client","Client Send");
        Thread t = new thread();
        t.start();
    }

    class thread extends Thread{
        public void run(){
            try{
                System.out.println("Client: Waiting to connect...");
                int serverPort = 2000;

                // Create socket connect server
                Socket socket = new Socket(ipAdd, serverPort);
                System.out.println("Connected!");

                // Create stream communicate with server
                OutputStream out = socket.getOutputStream();
                String strToSend = "Hi I'm client";

                byte[] sendStrByte = new byte[1024];
                System.arraycopy(strToSend.getBytes(), 0, sendStrByte, 0, strToSend.length());
                out.write(sendStrByte);

            }catch (Exception e){
                System.out.println("Error" + e.getMessage());
            }
        }
    }
}
