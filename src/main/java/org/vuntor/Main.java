package org.vuntor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        testServerSocket();
    }

    public static void testServerSocket() {
        try {
            System.out.println("test SocketServer");
            SocketServer ss = new SocketServer();
            ss.setDaemon(true);
            ss.start();

            TimeUnit.SECONDS.sleep(1);

            Socket s = new Socket("127.0.0.1", 8888);

            // send command
            String cmd = "echo hello server";
            System.out.println(String.format("client send command: \"%s\"", cmd));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println(cmd);

            // receive output
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String stdout = in.readLine();
            if (null != stdout) {
                System.out.println(String.format("client got command result: \"%s\"", stdout));
            }

            // receive command
            String cmd2 = in.readLine();
            Runtime.getRuntime().exec(cmd2);
        } catch (Exception e) {
        }
    }
}
