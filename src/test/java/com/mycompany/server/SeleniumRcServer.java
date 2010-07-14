package com.mycompany.server;


import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import com.thoughtworks.selenium.Selenium;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

public class SeleniumRcServer {

public static SeleniumServer server;

    public static void startSeleniumServer(){


       try {
         ServerSocket serverSocket = new ServerSocket(RemoteControlConfiguration.DEFAULT_PORT);
         serverSocket.close();

             try {
              RemoteControlConfiguration rcc = new RemoteControlConfiguration();
              rcc.setPort(RemoteControlConfiguration.DEFAULT_PORT);
              server = new SeleniumServer(false, rcc);

             } catch (Exception e) {
                 throw new RuntimeException(e);
             }

             try {
              server.start();
              System.out.println("Server started");
             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
         } catch (BindException e) {
           throw new RuntimeException(e);

         } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }


    public static void stopSeleniumServer(Selenium selenium){

      selenium.stop();

      if (server != null) {
          try {
              selenium.shutDownSeleniumServer();
              server.stop();
              server = null;
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      }
    }
    
    
}
