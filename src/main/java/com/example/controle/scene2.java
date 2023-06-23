package com.example.controle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.Attributes;

public class scene2 {


    @FXML
    private TextField Portid;
    @FXML
    private TextField Messageid;

    @FXML

    private TextField Hostid;

    @FXML
    private ListView testview;


    PrintWriter pw ;


    @FXML
    protected void onConnect () throws IOException {

        String host=Hostid.getText();
        int port = Integer.parseInt(Portid.getText());
        Socket s = new Socket(host, port);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = s.getOutputStream();
        String Ip = s.getRemoteSocketAddress().toString();
       pw = new PrintWriter(os,true);
        new Thread(() -> {
            while (true){
                try {
                    String response = br.readLine();
                    Platform.runLater(()->{
                        testview.getItems().add(response);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

        @FXML
        public void onsubmit(){
            String  message = Messageid.getText();
            pw.println(message);
        }


}