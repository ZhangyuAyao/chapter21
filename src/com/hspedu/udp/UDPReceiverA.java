package com.hspedu.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //1. 创建一个 DatagramSocket 对象，准备在9999端口接收数据
        DatagramSocket socket = new DatagramSocket(9999);
        //2. 构建一个 DatagramPacket 对象，准备接收数据
        // 在前面讲解UDP协议时，老师说过一个数据包最大 64K
        byte[] buf = new byte[64 * 1024];//其实不需要那么大，因为只传一段字符串
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        //3. 调用 接收方法，将通过网络传输的 DatagramPacket 对象
        // 填充到 packet 对象中
        //老师提示：当有数据报发送到 本机的9999端口时，就会接收到数据
        //  如果没有数据包发送到 本机的9999端口时，就会阻塞等待.
        System.out.println("接收端A 等待接收数据...");
        socket.receive(packet);

        //4. 可以把 packet 进行拆包，取出数据，并显示
        int length = packet.getLength();//实际接收到的数据字节长度
        byte[] data = packet.getData();//接收到数据

        String s = new String(data, 0, length);
        System.out.println(s);

        //5. 回传数据“好的，明天见~”
        byte[] bytes = "好的，明天见~".getBytes();
        DatagramPacket packet1 = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.0.100"), 9998);
        socket.send(packet1);

        //6. 关闭接受端
        socket.close();


    }
}
