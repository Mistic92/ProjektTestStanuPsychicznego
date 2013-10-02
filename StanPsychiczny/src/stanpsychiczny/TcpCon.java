package stanpsychiczny;

import MedServer.PacjenciClass; 
import MedServer.WynikiClass;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TcpCon extends Thread {
   // private final static Logger LOGGER = Logger.getLogger(ClientProjekt.class.getName());
    private static Socket socket;
    private static ObjectInputStream Sinput;
    private static ObjectOutputStream Soutput;
   // private JFrame ramka = new JFrame();
  
    /**
     * Ustawiasz socket polaczenia
     * @param socket 
     */
    void setSocket(Socket socket) {
        this.socket = socket;
    }
   
    /**
     * Uruchamiasz watek sluchajacy serwera. Wlasciwie niepotrzebne bo kazda metoda
     * sama oczekuje odpowiedzi.
     */
    @Override
    public void run()
    {
        while(true)
        {
            try {
                String tekst = (String) Sinput.readObject();
                System.out.println(tekst);
            } catch (IOException | ClassNotFoundException ex) {
           //     LOGGER.log(Level.SEVERE, null, ex);
            } 
        }//while
    }

    /**
     * Ustanawiasz polaczenie z serwerem. Nalezy odpalic :P
     * @param ip
     * @param port
     * @return 
     */
    public boolean connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
          //  LOGGER.info("Polaczono z serwerem:"+ip+"/"+port);
        } catch (Exception e) {
         //   LOGGER.info("Brak polaczenia z serwerem: "+ip+"/"+port);
        }
        try{
            Sinput = new ObjectInputStream(socket.getInputStream());
            Soutput = new ObjectOutputStream(socket.getOutputStream()); 
            return true;
        }
        catch(Exception e)
        {
         //   LOGGER.log(Level.SEVERE,e.getMessage(),e);
            return false;
        }
    }//connect
    
    /**
     * Logujesz tym lekarza do bazy danych. 
     * @param nr_lekarza
     * @param pass
     * @return 
     * 0 - nie istnieje
     * 1 - zalogowano
     * 2 - blad logowania
     */
    public int login(String nr_lekarza, String pass)
    {   
        int status;
        try {
            Soutput.writeObject("login"+","+nr_lekarza+","+pass);
            status=(int) Sinput.readObject();
       //     LOGGER.info("Zarejestrowano poprawnie: "+login);
            return status;
        } catch (IOException | ClassNotFoundException ex) {
        //    LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return 2;
        }   
    }//register
    
    /**
     * Zwraca ArrayList pacjentow
     * @return 
     * ArrayList<PacjenciClass>
     */
    public ArrayList<PacjenciClass> getPacjenci()
    {
        try {
            Soutput.writeObject("getusers");
            ArrayList<PacjenciClass> pacjenci = new ArrayList<PacjenciClass>();
            pacjenci = (ArrayList<PacjenciClass>) Sinput.readObject();
            return pacjenci;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TcpCon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }//getPacjenci

    
    /**
     * Dodawanie nowego pacjenta
     * @param imie
     * @param nazwisko
     * @param pesel - musi byc prawdziwy, serwer sprawdza poprawnosc :P
     * @return 
     * 0 - blad
     * 1 - dodano
     * 2 - istnieje
     */
    public int addPacjent(String imie, String nazwisko, String pesel,String miasto,String ulica, String nr_dom,String tel,String kod)
    { 
        int status;
        try {
            Soutput.writeObject("addPacjent,"+imie+","+nazwisko+","+pesel+","+miasto+","+ulica+","+nr_dom+","+tel+","+kod);

            status =(int) Sinput.readObject();
            return status;
        } catch (IOException | ClassNotFoundException ex) {
          //  LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return 0;
        }   
    }//add pacjent
    
    public int updatePacjent(String imie, String nazwisko, String pesel,String miasto,String ulica, String nr_dom,String tel,String kod)
    { 
        int status;
        try {
            Soutput.writeObject("updatePacjent,"+imie+","+nazwisko+","+pesel+","+miasto+","+ulica+","+nr_dom+","+tel+","+kod);

            status =(int) Sinput.readObject();
            return status;
        } catch (IOException | ClassNotFoundException ex) {
          //  LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return 0;
        }   
    }//update pacjent
    
    public String getLekarz(String nr_l)
    {
        String leka;
        try {
            Soutput.writeObject("getLekarz");
            Soutput.writeObject(nr_l);
            leka= (String) Sinput.readObject();
            return leka;
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(TcpCon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }//get lekarz
    
    public int deletePacjent(Object id)
    {
        int status;
        try {           
            Soutput.writeObject("deletePacjent");
            Soutput.writeObject(id);
            status = (int) Sinput.readObject();
            return status;
        } catch (ClassNotFoundException | IOException ex) {
            
            Logger.getLogger(TcpCon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }    
    }//del pacjent
    
    public Object countPacjent()
    {
       Object status;
        try {           
            Soutput.writeObject("countPacjent");
            status = Sinput.readObject();
            return status;
        } catch (ClassNotFoundException | IOException ex) {
            
            Logger.getLogger(TcpCon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }     
    }//count pacjent
    
    public boolean addWynik(Object pesel,Object nr_lek,int wynik)
    {
       Object status;
       boolean stat;
        try {           
            Soutput.writeObject("addWynik,"+pesel+","+nr_lek+","+wynik);
            status =  Sinput.readObject();
            stat = Boolean.parseBoolean(status.toString());
            return stat;
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(TcpCon.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
       
    }//addwynik
    
    public ArrayList<WynikiClass> getWyniki(String pesel)
    {
        try {
            Soutput.writeObject("getWyniki,"+pesel);
            ArrayList<WynikiClass> wyniki = new ArrayList<WynikiClass>();
            wyniki = (ArrayList<WynikiClass>) Sinput.readObject();
            return wyniki;  
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(TcpCon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }//get wynik
    
    public int conWyn(String pesel)
    {
        Object stat;
        try {
            Soutput.writeObject("countWyn");
            Soutput.writeObject(pesel);
            stat = Sinput.readObject();
            return Integer.parseInt(stat.toString());
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(TcpCon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }  
    }//count wynik
    
    /**
     * Zamykanie polaczenia
     */
    void closeSocket()
    {
       try{
        socket.close();
       // LOGGER.info("Polaczenie zakonczone!");
       }
       catch(Exception e)
       {
         //  LOGGER.log(Level.SEVERE,e.getMessage(),e);
       }
    }
}//class