package org.vuntor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread {
    @Override
    public void run() {
        // Socket s = new Socket();
        try {
            ServerSocket ss = new ServerSocket(8888);
            while (true) {
                try {
                    Socket client = ss.accept();

                    // read command
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String cmd = in.readLine();
                    System.out.println(String.format("server got command: \"%s\"", cmd));

                    // exec
                    Process p = Runtime.getRuntime().exec(cmd);

                    // output
                    BufferedReader pstdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
                    String output = null;
                    while ((output = pstdout.readLine()) != null) {
                        out.println(output);
                    }

                    // send command to client
                    out.println("echo hello client");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
