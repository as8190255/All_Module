package org.jc.allmoduledemo.uitls.socketMulticast;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.jc.allmoduledemo.R;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 局域网广播 消息接受
 */
public class MulticastSearchActivity extends AppCompatActivity {
    InetAddress addr;
    MulticastSocket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multicast_search);

        try {
            addr = InetAddress.getByName(SocketConfig.broadcastAddr);
            socket  = new MulticastSocket(SocketConfig.broadcastPort);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    byte buff[]=new byte[8192];
                    DatagramPacket packet=new DatagramPacket(buff, buff.length, addr
                            ,SocketConfig.broadcastPort);
                    try {
                        socket.receive(packet);
                        String msg = new String(packet.getData(), 0, packet.getLength());
                        msg = msg.concat(" " + packet.getAddress());
                        Message message = new Message();
                        message.obj = msg;
                        message.what = 2;
                        mHandler.sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2){
                Toast.makeText(MulticastSearchActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
