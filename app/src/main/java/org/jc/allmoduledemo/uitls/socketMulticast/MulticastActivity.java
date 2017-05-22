package org.jc.allmoduledemo.uitls.socketMulticast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jc.allmoduledemo.R;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 局域网广播 消息发送
 */
public class MulticastActivity extends AppCompatActivity {

    String message[] = {"msg1"
            ,"msg2"};
    InetAddress group = null;//组播的组地址
    MulticastSocket mutiSocket = null;//组播套接字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multicast);

        try {
            group = InetAddress.getByName(SocketConfig.broadcastAddr);//设置广播组地址
            mutiSocket = new MulticastSocket(SocketConfig.broadcastPort);//多点广播套接字将在port端口广播
            mutiSocket.setTimeToLive(1);
            mutiSocket.joinGroup(group);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true)
                {
                    try
                    {
                        DatagramPacket packet = null;
                        for(String msg : message)//循环发送每条广播信息
                        {
                            byte buff[] = msg.getBytes();
                            packet = new DatagramPacket(buff, buff.length, group
                                    ,SocketConfig.broadcastPort);
                            System.out.println(new String(buff));
                            mutiSocket.send(packet);
                            Thread.sleep(2000);
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error:"+e);
                    }
                }

            }
        }).start();

    }
}
