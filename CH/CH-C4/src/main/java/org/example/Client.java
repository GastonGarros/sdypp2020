package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public Client (String ip, int port){
        try {
            Socket s = new Socket(ip,port);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter (s.getOutputStream(),true);

            Gson gson = new Gson();
            ExchangeMsg em = new ExchangeMsg("getTask", "nada");
            String stringMsg = gson.toJson(em);
            System.out.println(" MSG "+stringMsg);
            pw.println(stringMsg);
            pw.flush();
            System.out.println(" MSG SENT ");
            //LEeR

            String response = br.readLine();
            if (response.startsWith("HTTP 200")){
                System.out.println(" TASK IS UPLOADED OK");
            }
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Client c = new Client ("localhost", 9000);
    }
}
