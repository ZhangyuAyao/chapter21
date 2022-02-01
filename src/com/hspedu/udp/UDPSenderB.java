package com.hspedu.udp;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        //1. 创建 DatagramSocket 对象，准备发送和接收数据
        DatagramSocket socket = new DatagramSocket(9998);

        //2. 将需要发送的数据，封装到 DatagramPacket 对象
        byte[] data = "hello 明天吃火锅".getBytes();

        //3. 说明：封装的 DatagramPacket 对象 data 内容是一个字节数组， data.length, 主机（IP），端口
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.0.100"), 9999);

        //4. 发送
        socket.send(packet);

        //5. 接收回传的数据
        byte[] buf = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(buf, buf.length);
        socket.receive(packet2);

        int length = packet2.getLength();//实际接收到的数据字节长度
        byte[] data2 = packet2.getData();//接收到数据

        String s = new String(data2, 0, length);
        System.out.println(s);

        //关闭
        socket.close();

    }
}
