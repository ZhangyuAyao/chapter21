package com.hspedu.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class Homework01Server {
    public static void main(String[] args) throws IOException {
        //监听端口9999
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("监听端口9999...");
        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);
        String respond = null;
        if(s.equals("name")) {
            respond = "我是张宇";
        } else if(s.equals("hobby")) {
            respond = "编写java程序";
        }

        if(respond == null) {
            respond = "你说啥呢？";
        }

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(respond);

        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
        serverSocket.close();



    }
}
