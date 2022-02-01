package com.hspedu.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class TCPFileUploadServe {
    public static void main(String[] args) throws IOException {
        //1. 服务端在本机监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端在8888端口监听...");
        //2. 等待连接
        Socket socket = serverSocket.accept();

        //3. 读取客户端发送的数据
        //  通过socket得到输入流
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.StreamToByteArray(bis);
        //4. 将得到 bytes 数组，写入到指定的路径，就得到一个文件了
        String destFile = "src\\tiger2.exe";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
        bos.write(bytes);
        bos.close();

        //向客户端回复“收到照片”
        //通过socket 获取到输出流（字符）
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("收到图片");
        bufferedWriter.flush();//把内容刷新到数据通道
        socket.shutdownOutput();//设置写入结束标记

        //关闭其它资源
        bis.close();
        socket.close();
        serverSocket.close();
    }
}
