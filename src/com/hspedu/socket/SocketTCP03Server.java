package com.hspedu.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class SocketTCP03Server {
    public static void main(String[] args) throws IOException {
        //1. 在本机 的9999端口监听，等待链接
        //  细节：要求在本机没有其它服务监听9999
        //  细节：这个 ServerSocket 可以通过accept() 返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端，在9999端口监听，等待连接...");
        //2. 当没有客户端连接9999端口时，程序会阻塞，等待链接
        // 如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();

        System.out.println("socket = " + socket.getClass());

        //3. 通过socket.getInputStream(),读取客户端写入到数据通道的数据，显示
        InputStream inputStream = socket.getInputStream();
        //4. IO读取，使用字符流，使用InputStreamReader 将 inputStream 转成字符流
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String s = br.readLine();
        System.out.println(s);

        //5. 获取socket 相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write("hello client 字符流");
        bw.newLine();//插入一个换行符，表示回复的内容结束
        bw.flush();//注意bw需要手动flush()，不然不会写入到

        //6. 关闭流(关闭外层流即可)和socket、serverSocket
        bw.close();//关闭外层流，后打开的流先关闭
        br.close();//关闭外层流
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
