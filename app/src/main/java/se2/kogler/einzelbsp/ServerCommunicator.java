package se2.kogler.einzelbsp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerCommunicator implements Runnable {
    private String hostname;
    private int port;
    private Socket clientSocket;
    private String serverResponse;
    private String payload;

    public ServerCommunicator(String hostname, int port, String payload) {
        super();
        this.hostname = hostname;
        this.port = port;
        this.payload = payload;
    }

    @Override
    public void run() {
        // try initializing the socket
        try {
            this.clientSocket = new Socket(this.hostname, this.port);

            DataOutputStream out = new DataOutputStream(this.clientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            this.clientSocket.getInputStream()
                    )
            );

            out.writeBytes(this.payload + '\n');
            this.serverResponse = in.readLine();
            Log.i("server-response", "Server responded with: " + this.serverResponse);

            this.clientSocket.close();

        } catch (Exception e) {
            Log.e("network","Error while trying to connect to server.");
            e.printStackTrace();
        }
    }

    public String getServerResponse() {
        return serverResponse;
    }
}
