/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medserver;

import Hibernate.HibernateUtil;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ţukasz
 */
public class Konsola extends Thread {

    private final static Logger LOGGER = Logger.getLogger(RunServer.class.getName());
    private static String stopcode = "123";
    private static String kod;
    private static int probastop = 0;
    private static String polecenie;
    DbCon dbcon = new DbCon();
    Options options = new Options();
    TcpThread TCP = new TcpThread();
    

    public void run() {
        
       // komunikatyStartowe();
        
        while (true) {
            Scanner skan = new Scanner(System.in);
            polecenie = skan.nextLine();
            Porownanie(polecenie);
        }
    }//run

    
    private void komunikatyStartowe()
    {
        System.out.println(dbcon.countLekarze());
        if(dbcon.countLekarze()<2)
            System.out.println("W bazie nie istnieje zaden lekarz. Prosze o dodanie jednego za pomoca komendy: "
                    + "\n --> nowylekarz imie nazwisko nr_lekarza haslo <--");
            System.out.println("Spis wszystkich dostepnych polecen znajduje sie pod poleceniem --> help <--");
            System.out.println("Domyslny stopcode serwera to 123. Zalecana jest jego zmiana po uruchomieniu serwera komenda:"
                    + "\n --> zmianakodu aktualny_stopcode nowy_stopcode <--");
    }//komunikaty startowe
    
    private void Porownanie(String komenda) {
        try {
            if (komenda.startsWith("stop")) {
                kod = komenda.replaceFirst("stop", "");
                kod = kod.replaceFirst(" ", "");
                if (kod.equals(stopcode)) {
                    ServerStop();
                } else {
                    probastop++;
                    LOGGER.log(Level.WARNING, "Podano zly kod zatrzymania!! {0}", kod);
                    if (probastop >= 3) {
                        LOGGER.warning("Pauzuje watek na 5s!!");
                        Pause(5);
                    }//if
                }//else
            }//if stop
            else if (komenda.startsWith("restart")) {
                restart();
            } 
            else if (komenda.startsWith("nowylekarz"))
            {
                if(addLekarz(komenda)==true) {
                    LOGGER.log(Level.INFO, "Dodano nowego lekarza!");
                }
                else {
                    LOGGER.log(Level.WARNING, "Blad podczas dodawania nowego lekarza!");
                }
            }
            else if (komenda.startsWith("newoptions")) {
                options.writeOptions();
            }
            else if (komenda.startsWith("help")) {
                System.out.println("Wpisz stop <kod> aby wylaczyc serwer.");
                System.out.println("Wpisz nowylekarz imie nazwisko nr_lekarza haslo aby dodac nowego lekarza");
                System.out.println("Wpisz newoptions aby utworzyc ponownie plik opcji. Pamietaj aby zrestartowac serwer.");
                System.out.println("Wpisz restart aby zrestartowac serwer.");
                System.out.println("Wpisz zmianakodu aktualny_stopcode nowy_stopcode.");
            }
            else if (komenda.startsWith("zmianakodu"))
            {
                zmianaStopcode(komenda);
            }
            else
            {
                System.out.println("Nie rozpoznaje komendy. Wpisz help aby zobaczyc dostepne polecenia.");
            }
        } catch (NumberFormatException e) {
            LOGGER.info("Nie rozpoznaje komendy!");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }//porownanie
//91072241277
    
    /**
     * Zatrzymywanie serwera
     */
    private void ServerStop() {

        LOGGER.info("\n#########################"
                + "\nSerwer poprawnie zakonczyl dzialanie. Stopcode poprawny " + stopcode
                + "\n#########################");
        System.exit(0);
    }//serverpause

    /**
     * Uspienia watku konsoli. Wywolywane w przypadku podania zlego stopcode.
     * S jest podawane w sekundach.
     * @param s 
     */
    private static void Pause(long s) {
        try {
            Thread.currentThread().sleep(s * 1000);
            probastop = 0;
            polecenie = null;
        } catch (InterruptedException e) {
            LOGGER.severe("Wystabil blad wstrzymania watku konsoli! ");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }//pause

/**
 * Restartowanie serwera. Konieczny plik server.bat
 */
    private void restart() {
        try {
            LOGGER.info("////////////////"
                    + "\nRESTART"
                    + "\n////////////////");
            Runtime.getRuntime().exec("cmd /c start server.bat");
            System.exit(0);
        } //restart
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }//restart
    
    
    /**
     * Uruchamia procedure dodania lekarza.
     * @param tekst
     * @return 
     */
    private boolean addLekarz(String tekst)
    {
        tekst = tekst.replaceFirst("nowylekarz ", "");
        String imie, nazwisko, haslo;
        int nr_lekarza;
        String[] tablica = tekst.split(" ");
        imie = tablica[0];
        nazwisko = tablica[1];
        try{
        nr_lekarza = Integer.parseInt(tablica[2]);
        }
        catch(Exception e)
        {
            System.out.println("Bledny numer lekarza. Moze zawierac tylko cyfry!");
            return false;
        }
        haslo=tablica[3];
        return dbcon.addLekarz(imie, nazwisko, haslo, nr_lekarza);
    }
    private void zmianaStopcode(String tekst)
    {
        tekst = tekst.replaceFirst("zmianakodu ", "");
        String[] tablica = tekst.split(" ");
        System.out.println(tablica[0] + " "+tablica[1]);
        if(tablica[0].equals(stopcode))
        {
            stopcode=tablica[1];
            System.out.println("Ustawiono nowy stopcode: "+tablica[1]);
        }
        else
            System.out.println("Bledny stopcode");

    }//zmiana stopcode
}//class
