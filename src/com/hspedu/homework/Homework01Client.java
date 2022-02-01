package com.hspedu.homework;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class Homework01Client {
    public static void main(String[] args) throws IOException {
        //使用字符流的方式，发送name / hobby
        Socket socket = new Socket(InetAddress.getByName("192.168.0.100"), 9999);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(s);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(s);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        //得到回传的消息
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s1 = bufferedReader.readLine();
        System.out.println(s1);

        bufferedReader.close();
        bufferedWriter.close();
        socket.close();

    }
}
