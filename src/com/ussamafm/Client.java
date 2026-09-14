package com.ussamafm;
import java.io.*;
import java.net.Socket;
public class Client implements Runnable{
    private Socket client;
    private String methode;
    private String path;
    private String version;
    public Client(Socket client){
        this.client =client;
    }
    public void run() {
        try ( Socket clientSocket = client;
                InputStream input = clientSocket.getInputStream();
              BufferedReader reader = new BufferedReader(new InputStreamReader(input));
              OutputStream output = clientSocket.getOutputStream();
              BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output)); ){
        String line;
        String requestLine[] = reader.readLine().split(" ",3);
        methode = requestLine[0];
        path = requestLine[1];
        version = requestLine[2];
        while ((line = reader.readLine()) != null && !(line.isEmpty())) {
                System.out.println(line);
            }
        System.out.println(methode + path + version);
        String body = switch (path) {
            case "/bruh" -> "hello world";
            case "/home" -> "this is home";
            default -> "THE IS INDEX";
        };
        String response = "HTTP"+path+"1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: "
                + body.getBytes().length + "\r\n" + "\r\n" + body;

        writer.write(response);
//    output.write(response.getBytes());
//        output.flush();
//        client.close();
        writer.flush();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
