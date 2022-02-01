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
public class Homework02SenderB {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9999);
        byte[] question = "四大名著是哪些？".getBytes();
        DatagramPacket packet = new DatagramPacket(question, question.length, InetAddress.getByName("192.168.0.100"), 8888);
        socket.send(packet);

        //接收回复
        byte[] answer = new byte[1024];
        DatagramPacket packet1 = new DatagramPacket(answer, answer.length);
        socket.receive(packet1);

        int length = packet1.getLength();
        byte[] data2 = packet1.getData();
        System.out.println(new String(answer, 0, packet1.getLength()));

        //关闭
        socket.close();


    }
}
