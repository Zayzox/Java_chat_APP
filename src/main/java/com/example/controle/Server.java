package com.example.controle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private int ClienNbre ;
    private List<Communication> clientConnectes = new ArrayList<Communication>();
    public static void main(String[] args){
        new Server().start();
    }

    @Override
    public void run() {
        try{
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Le Serveur essaie de demarrer");
            while (true){

                Socket s = ss.accept();
                ++ClienNbre;
                Communication NewCommunication = new Communication(s,ClienNbre);
                clientConnectes.add(NewCommunication);
                NewCommunication.start();
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    class Communication extends Thread{
        private Socket s;
        private int ClientNbr;

        Communication(Socket s,int ClientNbr){
            this.s = s;
            this.ClientNbr =ClienNbre;
        }

        @Override
        public void run() {
            try {
                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                OutputStream os = s.getOutputStream();
                System.out.println("le Client numero "+ ClienNbre+" vient de se connecter");
                PrintWriter pw = new PrintWriter(os,true);
                pw.println("Vous etes le client "+ClienNbre);
                pw.println("Envoyez le message que vous voulez");
                while (true) {
                    String UserRequest = br.readLine();
                    if(UserRequest.contains("=>")){
                        String[] usermessage = UserRequest.split("=>");
                        if(usermessage.length == 2){
                            String msg = usermessage[1];
                            int numclient = Integer.parseInt(usermessage[0]);
                            Send(msg,s,numclient);
                        }
                    }
                    else
                        Send(UserRequest,s,-1);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        void Send(String UserRequest,Socket socket,int numclient) throws IOException {
            for (Communication client : clientConnectes){
                if (client.s != socket){
                    if(client.ClientNbr == numclient || numclient==-1){
                        PrintWriter pw = new PrintWriter(client.s.getOutputStream(),true);
                        pw.println(UserRequest);
                    }

                }

            }
        }
    }
}
