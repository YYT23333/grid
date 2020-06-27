package com.nju.grid;

import com.nju.grid.service.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GridApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext=SpringApplication.run(GridApplication.class, args);
        applicationContext.getBean(SocketServer.class).start();
    }

}
