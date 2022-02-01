# chapter21-网络编程
![image.png](https://note.youdao.com/yws/res/9/WEBRESOURCE08c7bf06c3f3f2983c532c8deccc72e9)

---
## 网络的相关概念
### 网络通讯
1. 概念：两台设备之间通过网络实现数据传输
2. 将数据通过网络从一台设备传输到另一台设备
3. java.net包下提供了一系列的类或接口，供程序员使用，完成网络通信

### 网络
1. 概念：两台或多态设备通过一定物理设备连接起来构成了网络
2. 根据网络的覆盖不同，对网络进行分类：
   1）局域网：覆盖范围最小，仅仅覆盖一个教室或一个机房
   2）城域网：覆盖范围较大，可以覆盖一个城市
   3）广域网：覆盖范围最大，可以覆盖全国，甚至全球，万维网是广域网的代表

---

## 网络基础
### IP地址
1. 概念：用于唯一表示网络中的每台计算机/主机
2. 查看ip：ipconfig
3. ip地址的表示形式：点分十进制 xx.xx.xx.xx
4. 每一个十进制数的范围：0-255
5. ip地址的组成=网络地址+主机地址，比如：192.168.16.69
6. ipv6是互联网工程任务组设计的用于替代ipv4的下一代IP协议，其地址数量号称可以为全世界的每一粒沙子编上一个地址
7. 由于ipv4最大的问题在于网络地址资源有限，严重制约了互联网的应用和发展。ipv6的使用，不仅能解决网络地址资源数量的问题，而且也解决了多种接入设备连入互联网的障碍

![image.png](https://note.youdao.com/yws/res/8/WEBRESOURCE15a616979c9c70e308dce4054e48e678)
上图ipv6的地址：xxxx.xxxx.xxxx.xxxx.xxxx.xxxx.xxxx.xxxx 为啥每个 x 是16进制？
1. ipv6的地址为16字节，总共8段，因此每个冒号之间是2个字节
2. 2个字节即为16位，每4位可以构成一个16进制，因此2字节即16位可以构成4个16进制数

### ipv4地址分类
分为A、B、C、D、E五类
- A类：1个字节作为网络号，3个字节作为主机号
- B类：2个字节作为网络号，2个字节作为主机号
- C类：3个字节作为网络号，1个字节作为主机号
> 开发中主要使用A、B、C这三类地址
> 要大概记得A、B、C三类地址的大致范围
![image.png](https://note.youdao.com/yws/res/9/WEBRESOURCE5c8c4c4ac73842952759099685daeb09)

### 域名和端口
- 域名：
1. www.baidu.com
2. 好处：为了方便记忆，解决记ip的困难
3. 概念：将ip地址映射成域名，这里怎么映射上，HTTP协议

- 端口号：
1. 概念，用于标识计算机上某个特定的网络程序
2. 表示形式：以整数形式，范围0-65535 [2个字节表示端口 0~2^16-1]
3. 0-1024已经被占用，比如 ssh 22，ftp 21，smtp 25（邮件服务），http 80
4. 常见的网络端口号
   1）tomcat：8080
   2）mysql：3306
   3）oracle：1521
   4）sqlserver：1433

![image.png](https://note.youdao.com/yws/res/2/WEBRESOURCE7c3c6da96b7650cdc8adff8fef824232)
分析：
1. 一台主机有多个服务，外界的浏览器或者程序通过IP找到主机，通过端口找到服务。
2. 每个端口只允许一个服务占用
3. 坦克大战是单机版本，没有占用端口

### 网络通信协议


- 协议(tcp/ip)
  TCP/IP(Transmission Control Protocol/Internet Protocol)的简写，中文译名传输控制协议/英特网互联协议，又叫网络通讯协议。这个协议是Internet最基本的协议、Internet国际互联网络的基础，简单地说，就是由网络层的IP协议和传输层的TCP协议组成的

![image.png](https://note.youdao.com/yws/res/4/WEBRESOURCE74d91eb0620506325d6083b6dfda34d4)

![image.png](https://note.youdao.com/yws/res/f/WEBRESOURCE0908171422c50a48b6484f433a743e6f)
分析：
1. osi模型是理论模型，并没有真正实现，简化成了TCP/IP模型
2. 因为TCP、IP使用的太广泛了，所以传输层也叫TCP层，网络层也叫IP层，实际上这两层还包含了别的协议

#### TCP和UDP
- tcp协议：传输控制协议
1. 使用tcp协议之前，需要先建立TCP连接，形成传输数据通道
2. 传输前，采用“三次握手”方式，是==可靠的==
3. TCP协议进行通信的两个应用程序：客户端、服务器
4. 在连接中可进行大数据量的传输
5. 传输完毕，需释放已建立的连接，==效率低==

- UDP协议：用户数据协议
1. 将数据、源、目的封装成数据包，不需要建立连接
2. 每个数据包的大小限制在64K内，不适合传输大量数据
3. 因无需连接，故是==不可靠的==
4. 发送数据结束时无需释放资源（因为不是面向连接的），速度快
5. 举例：发短信


## InetAddress类
- 相关方法
1. 获取本机 InetAddress 对象 getLoacalHost
2. 根据指定主机名/域名获取ip地址对象 getByName
3. 获取InetAddress 对象的主机名 getHostName
4. 获取InetAddress 对象的地址 getHostAddress

```
    //1. 获取本机的 InetAddress 对象
    InetAddress localHost = InetAddress.getLocalHost();
    System.out.println(localHost);

    //2. 根据制定主机名 获取InetAddress 对象
    InetAddress host1 = InetAddress.getByName("DESKTOP-PIKVTQP");
    System.out.println("host1=" + host1);

    //3. 根据域名返回 InetAddress 对象，比如 www.baidu.com 对应的
    InetAddress host2 = InetAddress.getByName("www.baidu.com");
    System.out.println("host2=" + host2);

    //4. 通过 InetAddress 对象，获取对应的地址
    String hostAddress = host2.getHostAddress();
    System.out.println("host2 对应的ip =" + hostAddress);//14.215.177.39

    //5. 通过 InetAddress 对象，获取对应的主机名/域名
    String hostName = host2.getHostName();
    System.out.println("host2对应的主机名/域名=" + hostName); //www.baidu.com
    
```

## Socket
### 基本介绍
1. 套接字（Socket） 开发网络应用程序被广泛采用，以至于成为事实上的标准
2. 通信的两端都要有 Socket ，是两台机器间通信的端点
3. 网络通信其实就是Socket 间的通信
4. Socket 允许程序==把网络连接当成一个流==，数据在两个 Socket 间通过 IO 传输
5. 一般主动发起通信的应用程序属客户端，等待通信请求的为服务端

- Socket大概使用
1. 基于客户端-服务端的网络通信
2. 底层使用的是TCP/IP协议
3. 应用场景举例：客户端发送数据，服务端接受并显示控制台
4. 基于Socket的TCP编程
   ![image.png](https://note.youdao.com/yws/res/f/WEBRESOURCE4eae2f9072bc86f716a3156fdf0f66ff)

### 应用案例1（==字节流==）
1. 编写一个服务器端，和一个客户端
2. 服务器端在 9999 端口监听
3. 客户端连接到服务器端，发送“hello，server”，然后退出
4. 服务器接受到 客户端发送的信息，输出，并退出

> 注意写的是两个程序：服务端、客户端

思路分析：
![image.png](https://note.youdao.com/yws/res/2/WEBRESOURCEd093124f4ab6935a26adbcc5061679a2)
服务端：

```
public class SocketTCP01Server {
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
        //4. IO读取
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));//根据读取到的实际长度，显示内容
        }
        //5. 关闭流和socket、serverSocket
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
```
客户端：

```
public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1. 连接服务端（ip， 端口）
        //解读：连接本机的 9999 端口，如果连接成功，返回 Socket 对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket返回=" + socket.getClass());
        //2. 连接上后，生成Socket，通过socket.getOutputStream()
        // 得到 和 socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3. 通过输出流，写入数据到 数据通道
        outputStream.write("hello, server".getBytes());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        outputStream.write("second hello, server".getBytes());
        //4. 关闭流对象和socket，必须关闭
        outputStream.close();
        socket.close();
        System.out.println("客户端退出...");
    }
}
```
### 应用案例2（==字节流==）
1. 编写一个服务端和一个客户端
2. 服务器端在 9999 端口监听
3. 客户端连接到服务端，发送"hello, server" 并接收服务器端回发的"hello, client"，再退出
4. 服务器端接收到客户端发送的信息，输出，并发送"hello,client",再退出

![image.png](https://note.youdao.com/yws/res/d/WEBRESOURCE3ac4dc41a270e3e3ee50086f902d651d)
分析：只是在案例1的基础上，服务端回复客户端一条数据

- 服务端：完成 回送消息的绿色线
1. socket.getOutputStream()
2. 写入数据到数据通道
3. 关闭socket和io...

- 客户端：完成回送消息的绿色线
1. socket.getInputStream()
2. 读取数据通道的数据
3. 显示
4. 关闭socket和io...

服务端：

```
public class SocketTCP02Server {
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
        //4. IO读取
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));//根据读取到的实际长度，显示内容
        }

        //5. 获取socket 相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello, client".getBytes());
        //  设置结束标记
        socket.shutdownOutput();
        //6. 关闭流和socket、serverSocket
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
```

客户端：

```
public class SocketTCP02Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1. 连接服务端（ip， 端口）
        //解读：连接本机的 9999 端口，如果连接成功，返回 Socket 对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket返回=" + socket.getClass());
        //2. 连接上后，生成Socket，通过socket.getOutputStream()
        // 得到 和 socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3. 通过输出流，写入数据到 数据通道
        outputStream.write("hello, server".getBytes());
        //  设置结束标记
        socket.shutdownOutput();
        //4. 获取和 socket 关联的输入流，读取数据（字节）并显示
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }
        //5. 关闭流对象和socket，必须关闭
        outputStream.close();
        socket.close();
        System.out.println("客户端退出...");
    }
}
```


> 注意：这里在输出数据之后要添加结束标记 ==socket.shutDownOutput()==，不然客户端和服务端不知道数据传输已经结束，会继续等待

### 应用案例3（使用==字符流==）
1. 编写一个服务端和一个客户端
2. 服务器端在 9999 端口监听
3. 客户端连接到服务端，发送"hello, server" 并接收服务器端回发的"hello, client"，再退出
4. 服务器端接收到客户端发送的信息，输出，并发送"hello,client",再退出

分析：在案例2的基础上，将字节流转换成字符流

服务端：

```
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
```
客户端：

```
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
        bw.close();//关闭外层流，后打开的流先关闭
        br.close();//关闭外层流
        socket.close();
        System.out.println("客户端退出...");
    }
}
```


> 注意：
> 1. 这里输出数据要插入换行符，表示写入的内容结束 bw.newLine()，==注意==，要求对方使用readLine()来读取！！！
> 2. 如果使用了字符流，需要手动刷新，否则数据不会写入数据通道 br.flush()
> 3. 使用br、bw时只需要关闭外层流,后打开的流先关闭

### 应用案例4：
1. 编写一个服务端， 和一个客户端
2. 服务器端在8888端口监听
3. 客户端连接到服务端，发送一张图片 e:\\qie.png
4. 服务器端接收到 客户端发送的图片，保存到 src 下，发送“收到图片”再退出
5. 客户端接受到服务端发送的“收到图片”，再退出
6. 该程序要求使用 StreamUtils.java （自己写好的工具）

说明：使用BufferedInputStream 和 BufferedOutputStream 字节流

示意图：
![image.png](https://note.youdao.com/yws/res/5/WEBRESOURCE0f4e3a6c48ae72e372473a2cbc0e1e05)
分析：
1. 客户端图片从本地通过输入流读入到byte数组（使用StreamUtils.StreamToByteArray,将输入流转成byte数组)
2. 客户端通过输出流将字节流（byte数组）传输到服务端
3. 服务端接收传输过来的字节流，转换成byte数组（使用StreamUtils.StreamToByteArray，将输入流转成byte数组)
4. 服务端通过输出流将byte数组保存到本地

> ==注意==：使用字符流输出BufferedWriter, 在写入了数据之后，要使用flush()把内容刷新到数据通道

```
public class StreamUtils {
    public static byte[] StreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len=is.read(b))!=-1) {
            bos.write(b, 0, len);
        }
        byte[] array = bos.toByteArray();
        bos.close();
        return array;
    }

    public static String streamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line+"\r\n");
        }
        return builder.toString();

    }
}
```

```
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
        String destFile = "src\\tiger2.jpg";
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
```

```
public class TCPFileUploadClient {
    public static void main(String[] args) throws Exception {
        //1. 客户端连接服务端，得到Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        //创建读取磁盘文件的输入流
        String filePath = "c:\\ioTest\\happy_tiger_new_year.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

        //bytes 就是filePath 对应的字节数组
        byte[] bytes = StreamUtils.StreamToByteArray(bis);

        //通过socket 获取到输出流，将bytes数据发送给服务端
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes); //将文件对应的字节数组的内容，写入到数据通道
        bis.close();
        socket.shutdownOutput();//设置写入数据的结束标记

        //====接收从服务端回复的消息
        InputStream inputStream = socket.getInputStream();
        //使用StreamUtils 的方法，直接将 inputStream 读取到的内容 转成字符串
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);


        //关闭相关的流
        bos.close();
        socket.close();
    }
}
```
### netstat指令
查看网络状态
1. netstat -an 可以查看当前主机网络情况，包括==端口监听==情况和==网络连接==情况
2. netstat -an | more可以分页显示
3. netstat -anb 可以查看具体哪个程序在监听端口

![image.png](https://note.youdao.com/yws/res/0/WEBRESOURCE3c42fb6d0621403aaebdee36afe79330)
说明：
1. Listening 表示某个端口在监听
2. 如果有一个外部程序（客户端）连接到该端口，就会显示一条连接信息

- TCP网络通信不为人知的秘密
1. 当客户端连接到服务端后，实际上客户端也是通过一个端口和服务端进行通讯的，这个端口是TCP/IP来分配，是不确定的，是随机的

### UDP网络通信编程[==了解==]
1. 类 DatagramSocket 和 DatagramPacket[数据包/数据报]实现了基于UDP协议网络程序
2. UDP数据报通过数据报套接字 DatagramSocket 发送和接收，系统不保证UDP数据包一定能安全送到目的地，也不能确定什么时候可以抵达
3. DatagramPacket 对象封装了UDP数据报，在数据报中包含了发送端的IP地址和端口号以及接收端的IP地址和端口号
4. UDP协议中每个数据报都给出了完整的地址信息，因此无须建立发送方和接收方的连接

- 基本流程
1. 核心的两个类 DatagramSocket 和 DatagramPacket
2. 建立发送端，接收端（没有客户端和服务端概念）
3. 发送数据前，建立数据包 DatagramPacket 对象
4. 调用DatagramSocket的发送、接收方法
5. 关闭DatagramSocket

![image.png](https://note.youdao.com/yws/res/3/WEBRESOURCEde0bc7dac3e2e5f9f97f0e3e5cde4d23)
UDP说明：
1. 没有明确的服务端和客户端，演变成数据的发送端和接收端
2. 接收数据和发送数据是通过 DatagramSocket 对象完成
3. 将数据封装到 DatagramPacket 对象/装包
4. 当接收到 DatagramPacket 对象，需要进行拆包，取出数据
5. DatagramSocket 可以指定在哪个端口接收数据

#### 应用案例1-UDP
1. 编写一个接收端A，和一个发送端B
2. 接收端A在9999端口等待接受数据（receive）
3. 发送端B向接收端A 发送数据“hello,明天吃火锅~”
4. 接收端A接收到发送B发送的数据，回复“好的明天见~”再退出
5. 发送端接收回复的数据，再退出

接收端：

```
public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //1. 创建一个 DatagramSocket 对象，准备在9999端口接收数据
        DatagramSocket socket = new DatagramSocket(9999);
        //2. 构建一个 DatagramPacket 对象，准备接收数据
        // 在前面讲解UDP协议时，老师说过一个数据包最大 64K
        byte[] buf = new byte[64 * 1024];//其实不需要那么大，因为只传一段字符串
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        //3. 调用 接收方法，将通过网络传输的 DatagramPacket 对象
        // 填充到 packet 对象中
        //老师提示：当有数据报发送到 本机的9999端口时，就会接收到数据
        //  如果没有数据包发送到 本机的9999端口时，就会阻塞等待.
        System.out.println("接收端A 等待接收数据...");
        socket.receive(packet);

        //4. 可以把 packet 进行拆包，取出数据，并显示
        int length = packet.getLength();//实际接收到的数据字节长度
        byte[] data = packet.getData();//接收到数据

        String s = new String(data, 0, length);
        System.out.println(s);

        //5. 回传数据“好的，明天见~”
        byte[] bytes = "好的，明天见~".getBytes();
        DatagramPacket packet1 = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.0.100"), 9998);
        socket.send(packet1);

        //6. 关闭接受端
        socket.close();


    }
}
```

发送端
```
public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        //1. 创建 DatagramSocket 对象，准备发送和接收数据
        DatagramSocket socket = new DatagramSocket(9998);

        //2. 将需要发送的数据，封装到 DatagramPacket 对象
        byte[] data = "hello 明天吃火锅".getBytes();

        //3. 说明：封装的 DatagramPacket 对象 data 内容是一个字节数组， data.length, 主机（IP），端口
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.0.100"), 9999);

        //4. 发送
        socket.send(packet);

        //5. 接收回传的数据
        byte[] buf = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(buf, buf.length);
        socket.receive(packet2);

        int length = packet2.getLength();//实际接收到的数据字节长度
        byte[] data2 = packet2.getData();//接收到数据

        String s = new String(data2, 0, length);
        System.out.println(s);

        //关闭
        socket.close();

    }
}
```