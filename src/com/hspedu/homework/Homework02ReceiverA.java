package com.hspedu.homework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class Homework02ReceiverA {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8888);
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        System.out.println("等待接收数据...");
        socket.receive(packet);
        String question = new String(buf, 0, packet.getLength());
        System.out.println(question);

        String answer = null;
        if("四大名著是哪些？".equals(question)) {
            answer = "四大名著是<<红楼梦>>...";
        } else {
            answer = "what？";
        }

        //回复
        byte[] byteAnswer = answer.getBytes();
        DatagramPacket packet1 = new DatagramPacket(byteAnswer, byteAnswer.length, InetAddress.getByName("192.168.0.100"), 9999);
        socket.send(packet1);
        socket.close();


    }
}
