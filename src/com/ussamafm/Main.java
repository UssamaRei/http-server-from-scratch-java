package com.ussamafm;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.*;

public class Main{
public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8081);
        System.out.println("server is running");
        //Socket
    while(true) {
        Socket client = server.accept();
        Thread.startVirtualThread(new Client(client));
    }

}}
