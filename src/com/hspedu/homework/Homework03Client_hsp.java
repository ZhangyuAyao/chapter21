package com.hspedu.homework;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class Homework03Client_hsp {
    public static void main(String[] args) throws IOException {


        //1. 接受用户输入，制定下载文件名
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入下载文件名");
        String downloadFilename = scanner.next();

        //2. 客户端连接服务端，准备发送文件名
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        //3. 获取和socket关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(downloadFilename.getBytes());
        //设置写入结束的标志
        socket.shutdownOutput();

        //4. 读取服务端返回的文件（字节数据）
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //5. 得到一个输出流，准备将 bytes 写入到磁盘文件
        String filePath = "c:\\ioTest\\" + downloadFilename + ".mp4";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        bos.write(bytes);

        //6. 关闭相关的资源
        bos.close();
        bis.close();
        outputStream.close();
        socket.close();

    }
}
