/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MedServer;

import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *MainClass. Uruchamia wszystkie "uslugi". 
 * @author Łukasz
 */
public class StartClass {
  
    private final static Logger LOGGER = Logger.getLogger(RunServer.class.getName());
    static FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    
    static Options options = new Options();
    static int port;
    //Scanner skan = new Scanner(System.in);
    static RunServer server = new RunServer();
    static Konsola konsola = new Konsola();
    static DbCon dbcon = new DbCon();
    
    /**
     * Klasa main. tworzy polaczenie z baza danych, startuje konsole a nastepnie serwer. 
     * @param args 
     */
    public static void main(String[] args) {
        log_setup();
        LOGGER.info("\n************************************"
                  + "\n        Wlaczenie serwera"
                  + "\n************************************");
        Pause(1);
        int start =0;
         start= options.read();
        if(start == 1) {
           int stan;
            stan = dbcon.dataBaseConnect(); 
            if(stan==1)
            {
                konsola.start();  
                server.StartServer(5555);
            }
            else if(stan ==2) {
                LOGGER.info("Start przerwany przez uzytkownika!");
            }            
        }else
        {
            LOGGER.warning("Przerwane przez uzytkownika!");
            Scanner hold = new Scanner(System.in);
            System.out.println("Wcisnij dowolny klawisz aby zamknac.");
            hold.nextLine();
        }
    }//mainc

    /**
     * Metoda do skonfigurowania logera. 
     */
    private static void log_setup() {
        int limit = 1000000;
        try {
            fileTxt = new FileHandler("server.log", limit, 1, true);
        } catch (Exception err) {
        }
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        LOGGER.addHandler(fileTxt);
        LOGGER.setLevel(Level.ALL);
    }//logsetup

    /**
     * Metoda do pauzowania watku. s*500
     * @param s 
     */
    public static void Pause(long s) {
        try {
            Thread.currentThread().sleep(s * 500);
        } catch (InterruptedException e) {
            LOGGER.severe("Wystapil krytyczny blad pauzy glownego watku");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }//pause
    
    /**
     * @param port 
     */
  public void setPort(int port)
  {
      this.port=port;
  } 
    
    
}
