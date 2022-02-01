package com.hspedu.homework;

import javax.rmi.CORBA.Util;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class Homework03Client {
    public static void main(String[] args) throws IOException {
        //1. 连接到服务器
        //2. 发送音乐文件名
        //3. 接收音乐
        Socket socket = new Socket(InetAddress.getByName("192.168.0.100"), 9999);
        Scanner scanner = new Scanner(System.in);
        byte[] bytes = scanner.next().getBytes();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        socket.shutdownOutput();

        //1. 接收音乐文件，到内存
        //2. 保存到e:\\
        InputStream inputStream = socket.getInputStream();
        byte[] bytes1 = StreamUtils.streamToByteArray(inputStream);
        String filePath = "c:\\ioTest\\接收的文件（航拍）.mp4";
        BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(new FileOutputStream(filePath));
        bufferedOutputStream1.write(bytes1);

        //关闭
        bufferedOutputStream1.close();
        bufferedOutputStream.close();
        socket.close();

    }
}
