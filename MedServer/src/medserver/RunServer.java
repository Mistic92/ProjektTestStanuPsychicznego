/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MedServer;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RunServer  {

    //loger
    private final static Logger LOGGER = Logger.getLogger(RunServer.class.getName());
    static FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    //serwerowe
    private ServerSocket serverSocket;
    private static int port;
    private static int proba = 0;
   //odwolania do klas
    DbCon dbcon = new DbCon();
   static Options options = new Options();
   
   /**
    * Wystartowanie serwera na danym porcie. Jesli wywali blad to start jest ponawiany 2 razy. 
    * @param port 
    */
    public void StartServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            LOGGER.log(Level.INFO, "Serwer rozpoczal dzialanie na porcie {0}", port);
            while (true) 
              { 
                Socket socket = serverSocket.accept();
                LOGGER.log(Level.INFO, "Zaakceptowano polaczenie od {0}", new Object[]{socket.toString()});
                TcpThread t = new TcpThread();
                t.setSocket(socket);        
                t.start(); 
              }//while
         //  }//if
           // else if(stan ==2)
          //      LOGGER.info("Start przerwany przez uzytkownika!");
            }     //try 
         catch (Exception e) {
            LOGGER.severe("Wystapil krytyczny blad uruchamiania serwera");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            proba++;
            String blad = e.getLocalizedMessage();
            if (proba == 3 && blad.contains("value out of range")) {
                LOGGER.warning("Nie udalo sie wystartowac serwera. Podany port ma za duza wartosc!!");
            }
            if (proba < 3) {
                try {
                    LOGGER.info(proba + " proba ponownego uruchomienia serwera");
                    Thread.currentThread().wait(1000);
                    StartServer(this.port);
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.SEVERE, "Blad wstrzymania i proby ponownego uruchomienia serwera!!", ex);
                }
            }
            if(proba==3) {
                 LOGGER.severe("Nie udalo sie uruchomic serwera!!");
             }
        }//catch
    }//StartServer

}

