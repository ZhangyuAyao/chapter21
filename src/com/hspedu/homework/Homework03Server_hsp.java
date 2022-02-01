package com.hspedu.homework;

import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class Homework03Server_hsp {
    public static void main(String[] args) throws Exception {
        //1. 监听9999端口
        ServerSocket serverSocket = new ServerSocket(9999);
        //2. 等待客户端连接
        System.out.println("服务端在9999端口等待。。。");
        Socket socket = serverSocket.accept();
        //3. 读取 客户端发送要下载的文件名
        // 这里老师使用了 while 读取文件名，是考虑到将来客户端发送的数据较大的情况
        InputStream inputStream = socket.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        String downLoadFileName = "";
        while ((len = inputStream.read(b)) != -1) {
            downLoadFileName += new String(b, 0, len);
        }
        System.out.println("客户端希望下载文件名=" + downLoadFileName);

        String resFileName = "";
        if("高山流水".equals(downLoadFileName)){
            resFileName = "src\\高山流水.mp4";
        } else {
            resFileName = "src\\无名.mp4";
        }

        //4. 创建一个输入流，读取文件
        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(resFileName));

        //5. 使用工具类StreamUtils，读取文件到一个字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //6. 得到socket关联的输出流
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

        //7. 写入到数据通道，返回给客户端
        bos.write(bytes);
        socket.shutdownOutput();//很关键

        //8 关闭相关的资源
        bos.close();
        bis.close();
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
