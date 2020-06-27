package com.nju.grid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SocketServer {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //socket
    private Integer port;
    private boolean started;
    private ServerSocket serverSocket;

    //线程池
    private ExecutorService threadPool = Executors.newCachedThreadPool();

    public void start() {
        start(6666);
    }

    private void start(Integer port) {
        //启动socket服务器
        try {
            serverSocket = new ServerSocket(port == null ? this.port : port);
            started = true;
            logger.info("Socket服务器已启动，占用端口：{}", serverSocket.getLocalPort());
        } catch (IOException e) {
            logger.error("端口异常信息", e);
            System.exit(0);
        }
        //连接socket
        while (started == true) {
            try {
                Socket socket = serverSocket.accept();
                //todo 暂时设置超时时间为五秒钟，五秒钟内没有收到消息就断开连接
                socket.setSoTimeout(1000*5);
                new SocketClientHandler(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
