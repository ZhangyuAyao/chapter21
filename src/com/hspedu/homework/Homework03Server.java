package com.hspedu.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class Homework03Server {
    public static void main(String[] args) throws Exception {
        //1. 监听端口9999
        //2. 接收音乐文件名
        //3. 根据客户端文件名，返回音乐文件
        //4. 如果没有该文件名，则返回一个默认的音乐
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("监听端口9999中...");
        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();


        String filePath = "c:\\ioTest\\无名.mp4";
        if("航拍".equals(s)) {
            filePath = "c:\\ioTest\\高山流水.mp4";
            System.out.println("航拍");
        } else
            System.out.println("默认");
        //1. 先将文件读取至内存
        //2. 再通过socket传输
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();

        bufferedOutputStream.close();
        bufferedInputStream.close();
        socket.close();
        serverSocket.close();
    }
}
