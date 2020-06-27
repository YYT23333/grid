package com.nju.grid.service;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class SocketClientHandler extends Thread{

    private Socket clientSocket;
    //todo 随便写的，后面改
    public static int size=24;

    public SocketClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        byte[] msg=new byte[size];
        while(true) {
            try {
                //读消息
                InputStream inputStream = clientSocket.getInputStream();
                inputStream.read(msg);
                System.out.print(new String(msg));
                //回消息
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(("客户端收到\n").getBytes());
                outputStream.flush();
            }catch(SocketException | SocketTimeoutException e){
                //超时就关掉socket
                try {
                    System.out.print("长时间未收到消息，socket已关闭\n");
                    clientSocket.close();
                    break;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        //todo处理msg
    }
}
