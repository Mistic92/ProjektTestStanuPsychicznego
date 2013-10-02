/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ţukasz
 */
public class TcpThread extends Thread {
 
   private static final Logger LOGGER = Logger.getLogger(RunServer.class.getName());    
   private Socket socket;
   private ObjectInputStream Sinput,sin;
   private static ObjectOutputStream Soutput,seo;
   private Object write;
   DbCon dbcon = new DbCon(); 
    
    public void setSocket(Socket socket)
    {
        this.socket = socket;  
    }    
    
    @Override
    public void run()
    {
        
       try {
            Soutput = new ObjectOutputStream(socket.getOutputStream());
            Soutput.flush();
            Sinput = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            LOGGER.severe("Wystabil blad polaczenia! ");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return;
        } catch (Exception e) {
            LOGGER.severe("Wystabil nieznany blad polaczenia! ");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return;
        }  
      
       while(true)
       {
            try {
                
                String firstline = (String) Sinput.readObject();
               
                if(firstline.startsWith("getusers"))
                {
                    ArrayList<PacjenciClass> pacjenci = new ArrayList<PacjenciClass>();
                    pacjenci = dbcon.getPacjenci();
                    Soutput.writeObject(pacjenci);
                }//getpacjenci                
                else if (firstline.startsWith("addWynik")) 
                {
                    String[] s;
                    s=firstline.split(",");
                    String pesel = s[1];
                    String nr_lekarza = s[2];
                    String wynik = s[3];
                    Object a = dbcon.addWynik(pesel, nr_lekarza, wynik);
                    Soutput.writeObject(a);
                    
                }
                else if (firstline.startsWith("login"))    
                {
                    String[] s;
                    s=firstline.split(",");
                    /*
                     * Kody zwracane przez logowanie
                     * 0 - nie istnieje
                     * 1 - zalogowano
                     * 2 - blad logowania
                     */  
                    String nr_lekarza = s[1];
                    String pass = s[2];
                    write=dbcon.loggingCheck(nr_lekarza, pass);
                    Soutput.writeObject(write);
                }//login
                else if (firstline.startsWith("addPacjent"))
                {
                    String[] s;
                    s=firstline.split(",");
                    String[] p = new String[11];
                    for(int i=0;i<=p.length-1;i++)
                    {
                        p[i]=Character.toString(s[3].charAt(i));
                    }
                    /*
                     * KODY ZWRACANE PRZEZ ADDNEWUSER
                     *  0 - blad 
                     *  1 - dodano
                     *  2 - istnieje 
                     */
                    String imie = s[1];
                    String nazwisko = s[2];
                    String[] pesel = p;
                    String miasto =s[4];
                    String ulica =s[5];
                    String nr_dom =s[6];
                    String tel =s[7];
                    String kod =s[8];
                    if(validatePesel(pesel)==true)
                    {
                    write=dbcon.addNewUser( imie, nazwisko, pesel,miasto,ulica,nr_dom,tel,kod);
                    Soutput.writeObject(write);
                    }
                    else
                    {
                    Soutput.writeObject(0);
                    }
                }
                else if (firstline.startsWith("getLekarz"))
                {
                  Object w;
                  String nr_l = (String) Sinput.readObject();
                  w=dbcon.getLekarz(nr_l);
                  Soutput.writeObject(w);
                }
                else if(firstline.startsWith("deletePacjent"))
                {
                    Object w;
                    String nr_p = (String) Sinput.readObject();
                    w=dbcon.deletePacjent(nr_p);
                    Soutput.writeObject(w);
                }
                else if(firstline.startsWith("countPacjent"))
                {
                  Object w;
                  w=dbcon.countPacjenci();
                  Soutput.writeObject(w);
                }
                else if(firstline.startsWith("countWyn"))
                {
                   Object w;
                   String pesel = (String) Sinput.readObject();
                   w=dbcon.countWforP(pesel);
                   Soutput.writeObject(w);
                }
                else if (firstline.startsWith("getWyniki"))
                {
                  String[] s;
                  s=firstline.split(",");
                  ArrayList<WynikiClass> wyniki = new ArrayList<WynikiClass>();
                  wyniki= dbcon.getWyniki(s[1]);      
                  Soutput.writeObject(wyniki);  
                }
                else if(firstline.startsWith("updatePacjent"))
                {
                    String[] s;
                    s=firstline.split(",");
                    String imie = s[1];
                    String nazwisko = s[2];
                    String pesel = s[3];
                    String miasto =s[4];
                    String ulica =s[5];
                    String nr_dom =s[6];
                    String tel =s[7];
                    String kod =s[8];
                    write=dbcon.updateUser(imie, nazwisko, pesel,miasto,ulica,nr_dom,tel,kod);
                    Soutput.writeObject(write);
                }
            } catch (IOException | ClassNotFoundException e) {
                String err = e.toString();              
                if (err.contains("Connection reset") || err.contains("EOFException")) {
                    LOGGER.log(Level.INFO, "Polaczenie zakonczone! {0} watek {1}", new Object[]{socket.toString(), Thread.currentThread()});
                    return;
                } else {
                    LOGGER.log(Level.SEVERE, "Wystabil nieznany blad polaczenia! {0} watek {1}", new Object[]{socket.toString(), Thread.currentThread()});
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);                  
                    return;
                }//else
            }//catch
           
       }//while true
        
    }//run
    
    
    /**
     * Sprawdza poprawnosc peselu. 
     * @param pesel
     * @return 
     * true lub false
     */
    private boolean validatePesel(String[] pesel)
   {
        if(pesel.length!=11)
        {
            return false;
        }
        else
        {
            int sumaKontrolna = 0;
            int[] wagi = {1, 3, 7, 9, 1, 3, 7 ,9 ,1 ,3};
            int[] peselInt = new int[11];
            for (int i=0;i<11;i++)
            {         
                peselInt[i]=Integer.parseInt(pesel[i]);
            }
            for (int i=0;i<10;i++)
            {
                sumaKontrolna+=peselInt[i]*wagi[i];  
            }
            int cyfraKontrolna = peselInt[10];
            sumaKontrolna %= 10;
            sumaKontrolna = 10 - sumaKontrolna;
            sumaKontrolna %= 10;
            if(sumaKontrolna==cyfraKontrolna) {
                return true;
            }
            else {
                return false;
            }               
        }
    }//validate pesel
    
    
    
}//class
