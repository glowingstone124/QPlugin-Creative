package org.qo.creativeuploader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketConnection {
    public void run() throws Exception{
        Socket socket = new Socket("localhost", 1234);
        // 获取输入流，用于接收服务器发送的数据
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 获取输出流，用于向服务器发送数据
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("CREATIVEBOT");
        out.println("CREATIVE123456");
        out.println("0");
    }
}

