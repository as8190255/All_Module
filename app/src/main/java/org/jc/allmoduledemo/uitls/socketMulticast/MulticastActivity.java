package org.jc.allmoduledemo.uitls.socketMulticast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jc.allmoduledemo.R;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastActivity extends AppCompatActivity {

    String message[] = {"失物招领：有谁在操场丢失钥匙一串，请到学校广播站认领。"
            ,"大风蓝色预警：预计今天下午有北风6级，请有关单位和人员做好防范准备。"};
    int port = 9876;//组播的端口
    InetAddress group = null;//组播的组地址
    MulticastSocket mutiSocket = null;//组播套接字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multicast);

        try {
            group = InetAddress.getByName("230.198.112.0");//设置广播组地址
            mutiSocket = new MulticastSocket(port);//多点广播套接字将在port端口广播
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
                            packet = new DatagramPacket(buff, buff.length,group,port);
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
/**

 public class SocketSearch extends JFrame implements Runnable,ActionListener{
 private static final long serialVersionUID = -5923790809266120014L;
 int port;
 InetAddress group = null;
 MulticastSocket socket = null;
 JButton startButton;
 JButton stopButton;
 JButton cleanButton;
 JTextArea currentMsg;
 JTextArea receiveMsg;
 Thread thread;
 boolean isStop = false;//停止接收广播信息的标志

 public static void main(String[] args) throws SocketException {
 new SocketSearch();
 }

 public SocketSearch(){
 setTitle("接收广播信息");
 Container container = this.getContentPane();
 startButton = new JButton("开始接收");
 stopButton = new JButton("停止接收");
 cleanButton = new JButton("清空信息");
 startButton.addActionListener(this);
 stopButton.addActionListener(this);
 cleanButton.addActionListener(this);
 currentMsg = new JTextArea(3,20);//创建3行20列的多行文本框
 currentMsg.setForeground(Color.red);//设置字体颜色为红色
 receiveMsg = new JTextArea(8,20);//默认字体颜色为黑色
 container.setLayout(new BorderLayout());
 JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//创建一带水平分隔条的面板
 JScrollPane currScrollPane = new JScrollPane();
 currScrollPane.setViewportView(currentMsg);
 JScrollPane recvScrollPane = new JScrollPane();
 recvScrollPane.setViewportView(receiveMsg);
 currentMsg.setEditable(false);
 receiveMsg.setEditable(false);
 sp.add(currScrollPane);
 sp.add(recvScrollPane);
 container.add(sp,BorderLayout.CENTER);
 JPanel bottomJPanel = new JPanel();
 bottomJPanel.add(startButton);
 bottomJPanel.add(stopButton);
 bottomJPanel.add(cleanButton);
 container.add(bottomJPanel,BorderLayout.SOUTH);
 setSize(500,400);
 setVisible(true);
 thread = new Thread(this);
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 port = 9876;
 try
 {
 group = InetAddress.getByName("230.198.112.0");
 socket = new MulticastSocket(port);
 socket.joinGroup(group);
 }
 catch (Exception e)
 {

 }
 }








 @Override
 public void actionPerformed(ActionEvent e1) {
 if(e1.getSource() == startButton)
 {
 startButton.setEnabled(false);
 stopButton.setEnabled(true);
 if(!(thread.isAlive()))
 {
 thread = new Thread(this);
 }
 try
 {
 thread.start();
 isStop = false;
 }
 catch (Exception ee)
 {

 }
 }
 if(e1.getSource() == stopButton)
 {
 startButton.setEnabled(true);
 stopButton.setEnabled(false);
 isStop = true;
 }
 if(e1.getSource() == cleanButton)
 {
 receiveMsg.setText("");
 }
 }

 @Override
 public void run() {
 while(true)
 {
 byte buff[] = new byte[8192];
 DatagramPacket packet = null;
 packet = new DatagramPacket(buff, buff.length,group,port);
 try
 {
 socket.receive(packet);
 String message = new String(packet.getData(),0,packet.getLength());
 currentMsg.setText("正在接收的内容：\n"+message);
 receiveMsg.append(message+"\n");
 }
 catch (Exception e)
 {

 }
 if(isStop == true)
 {
 break;
 }
 }
 }

 }
 */