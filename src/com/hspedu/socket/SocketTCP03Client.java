package com.hspedu.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1. 连接服务端（ip， 端口）
        //解读：连接本机的 9999 端口，如果连接成功，返回 Socket 对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket返回=" + socket.getClass());
        //2. 连接上后，生成Socket，通过socket.getOutputStream()
        // 得到 和 socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3. 通过输出流，写入数据到 数据通道,使用字符流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write("hello, server 字符流");//这里输出数据要插入换行符，表示写入的内容结束,注意，要求对方使用readLine()来读取！！！
        bw.newLine();
        bw.flush();//如果使用了字符流，需要手动刷新，否则数据不会写入数据通道

        //4. 获取和 socket 关联的输入流，读取数据（字符）并显示
        InputStream inputStream = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String s = br.readLine();
        System.out.println(s);

        //5. 关闭流对象(只需要关闭外层流br,bw即可)和socket，必须关闭
        br.close();//关闭外层流，后打开的流先关闭
        bw.close();//关闭外层流
        socket.close();
        System.out.println("客户端退出...");
    }
}
