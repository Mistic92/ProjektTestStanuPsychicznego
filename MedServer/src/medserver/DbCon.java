/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MedServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Łukasz
 */

public class DbCon {
    
    Encode encode = new Encode();
    private final static Logger LOGGER = Logger.getLogger(RunServer.class.getName());
    //DB
    private static Connection conn;
    private String url1 = "jdbc:mysql://";
    private String dbName="medserv";
    private String driver = "com.mysql.jdbc.Driver";
    private String url2 ; 
    private String userName;
    private String password ;
    private boolean conwithbase=false;
    //INNE
    private static int stan=0; 


    
    /**
     * Tworzy polaczenie z baza danych
     * jesli brakuje tabel to je tworzy.
     * @return 
     * int jako stan zakonczenia
     */
    public int dataBaseConnect() {
       
        try {
            try{
                Options options = new Options();
                options.read();
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url1+options.getUrl() + dbName, options.getLogin(), options.getPass());
            if(conwithbase==false)
            LOGGER.log(Level.INFO, "Pomyslnie polaczono z baza danych!! {0}{1}{2}", new Object[]{url1, url2, dbName});
            
            conwithbase=true;
            String checkTableLekarze = "Select * from lekarze where lekarz_id=1";
            PreparedStatement checkLekarze = getConn().prepareStatement(checkTableLekarze);
            checkLekarze.executeQuery();
            
            String checkTableMiasto = "Select * from miasto where miasto_id=1";
            PreparedStatement checkMiasto = getConn().prepareStatement(checkTableMiasto);
            checkMiasto.executeQuery();
            
            String checkTableKod = "Select * from kod_pocztowy where kod_id=1";
            PreparedStatement checkKod = getConn().prepareStatement(checkTableKod);
            checkKod.executeQuery();  
            
            String checkTablePacjenci = "SELECT * from pacjenci where pacjent_id=1";   
            PreparedStatement checkPacjenci = getConn().prepareStatement(checkTablePacjenci);
            checkPacjenci.executeQuery();
                       
            String checkTableWyniki = "Select * from wyniki where wynik_id=1";
            PreparedStatement checkWyniki = getConn().prepareStatement(checkTableWyniki);
            checkWyniki.executeQuery();      
             stan=1;
            }
            catch(SQLException e){
                if(e.getMessage().contains("Unknown database"))
                {
                    LOGGER.warning("Brak odpowiedniej bazy danych!!");
                  System.out.println("Czy chcesz utworzyc odpowiednia baze danych? tak/nie");
                      Scanner skan = new Scanner(System.in);  
                     String wybor = skan.nextLine();
                    if(wybor.equals("tak"))                    
                    {
                      createDB();
                      dataBaseConnect(); 
                    }
                    else
                    {
                        stan =2;
                    }
                }//brak bazy
                
                if(e.getMessage().contains("Table 'medserv.lekarze' doesn't exist"))
                { 
                  LOGGER.warning("Brak tabeli LEKARZE!");
                  System.out.println("Czy chcesz utworzyc tabele LEKARZE? tak/nie");
                  Scanner skan = new Scanner(System.in);
                  String wybor = skan.nextLine();
                  if(wybor.equals("tak"))   
                     {
                       createTableLekarze();
                       dataBaseConnect();
                      }
                  else
                  {
                      stan = 2;
                  }
                }//brak lekarze
                
                if(e.getMessage().contains("Table 'medserv.miasto' doesn't exist"))
                { 
                  LOGGER.warning("Brak tabeli miasto!");
                  System.out.println("Czy chcesz utworzyc tabele miasto? tak/nie");
                      Scanner skan = new Scanner(System.in);
                         String wybor = skan.nextLine();
                     if(wybor.equals("tak"))
                      {
                       createTableMiasto();
                        dataBaseConnect();
                       }
                     else
                     {
                         stan =2;
                     }
                }//brak miasto
                
                if(e.getMessage().contains("Table 'medserv.kod_pocztowy' doesn't exist"))
                { 
                  LOGGER.warning("Brak tabeli kod_pocztowy!");
                  System.out.println("Czy chcesz utworzyc tabele kod_pocztowy? tak/nie");
                      Scanner skan = new Scanner(System.in);
                         String wybor = skan.nextLine();
                     if(wybor.equals("tak"))
                      {
                       createTableKod();
                        dataBaseConnect();
                       }
                     else
                     {
                         stan =2;
                     }
                }//brak kod_pocztowy
                
                if(e.getMessage().contains("Table 'medserv.pacjenci' doesn't exist"))
                { 
                  LOGGER.warning("Brak tabeli pacjenci!");
                  System.out.println("Czy chcesz utworzyc tabele pacjenci? tak/nie");
                      Scanner skan = new Scanner(System.in);
                         String wybor = skan.nextLine();
                     if(wybor.equals("tak"))
                      {
                       createTablePacjenci();
                        dataBaseConnect();
                       }
                     else
                     {
                         stan =2;
                     }
                }//brak pacjenci
               
                
                else if(e.getMessage().contains("Table 'medserv.wyniki' doesn't exist"))
                { 
                  LOGGER.warning("Brak tabeli WYNIKI!");
                  System.out.println("Czy chcesz utworzyc tabele WYNIKI? tak/nie");
                  Scanner skan = new Scanner(System.in);
                  String wybor = skan.nextLine();
                  if(wybor.equals("tak"))   
                     {
                       createTableWyniki();
                       dataBaseConnect();
                      }
                  else
                  {stan = 2;
                  LOGGER.log(Level.SEVERE, e.getLocalizedMessage()+" Blad polaczenia z baza!", e.getStackTrace());
                    }
                }//brak wyniki   
                
            }//catch maly
        }//try 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {  
            LOGGER.log(Level.SEVERE, e.getMessage()+" Blad polaczenia z baza!", e.getStackTrace());
            System.out.println("Skasuj plik konfiguracyjny.");
            }//catch
         return stan;
    }//dataBaseConnect    
    
    /**
     * Utworzenie bazy danych
     */
   private void createDB()
    {
        
       try{      
           Options options = new Options();
           options.read();
                LOGGER.info("Tworzenie bazy danych...");
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url1+options.getUrl(), options.getLogin(), options.getPass());
                PreparedStatement createUsers = getConn().prepareStatement("CREATE SCHEMA "+dbName);
                 createUsers.executeUpdate();
                 conn=null;
                LOGGER.info("Pomyslnie utworzono baze danych!!");
          }//try
       catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e)
       {
           LOGGER.log(Level.WARNING,e.getMessage(),e);
       }
    } //createDB
   
   /**
    * Zwraca polaczenie Connection
    * @return 
    * Connection conn
    */
  private Connection getConn()
    {
        return conn;
    }    
 
  /**
   * Utworzenie tabeli pacjenci
   */
  private void createTableMiasto()
    {
        try {
                LOGGER.info("Tworzenie tabeli miasto...");
                 String createTableMiasto=("CREATE TABLE miasto"
                 + "("
                 + "miasto_id INT(9)UNIQUE NOT NULL PRIMARY KEY,"
                 + "m_nazwa VARCHAR(65) NOT NULL"
                 + ")");
                 PreparedStatement createMiasto = getConn().prepareStatement(createTableMiasto);
                 createMiasto.executeUpdate();
               LOGGER.info("Utworzono tabele miasto!!");
        } //try
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }//createTableMiasto
  
   /**
   * Utworzenie tabeli pacjenci
   */
  private void createTableKod()
    {
        try {
                LOGGER.info("Tworzenie tabeli kod_pocztowy...");
                 String createTableKod=("CREATE TABLE kod_pocztowy"
                 + "("
                 + "kod_id INT(9)UNIQUE NOT NULL PRIMARY KEY,"
                 + "k_nazwa VARCHAR(6) NOT NULL,"
                 + "miasto_id int(9) NOT NULL,"
                 + "FOREIGN KEY (miasto_id) REFERENCES miasto(miasto_id)"
                 + ")");
                 PreparedStatement createKod = getConn().prepareStatement(createTableKod);
                 createKod.executeUpdate();
               LOGGER.info("Utworzono tabele kod_pocztowy!!");
        } //try
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }//createTableKod
  
  /**
   * Utworzenie tabeli pacjenci
   */
  private void createTablePacjenci()
    {
        try {
                LOGGER.info("Tworzenie tabeli pacjenci...");
                 String createTableUsers=("CREATE TABLE pacjenci"
                 + "("
                 + "pacjent_id INT(9)UNIQUE NOT NULL PRIMARY KEY,"
                 + "imie VARCHAR(35) NOT NULL,"
                 + "nazwisko VARCHAR(35) NOT NULL,"
                 + "pesel VARCHAR(11) NOT NULL UNIQUE,"
                 + "ulica varchar(65) NOT NULL,"
                 + "nr_dom varchar(4) NOT NULL,"
                 + "tel varchar(11) NOT NULL,"        
                 + "id_kod int(9) NOT NULL,"
                 + "FOREIGN KEY (id_kod) REFERENCES kod_pocztowy(kod_id)"      
                 + ")");
                 PreparedStatement createUsers = getConn().prepareStatement(createTableUsers);
                 createUsers.executeUpdate();
               LOGGER.info("Utworzono tabele pacjenci!!");
        } //try
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }//createTablePacjenci
    
  /**
   * Utworzenie tabeli Lekarze
   */
    private void createTableLekarze()
    {
        try{
              LOGGER.info("Tworzenie tabeli LEKARZE...");
                 String createTableSessions=("CREATE TABLE lekarze"
                 + "("
                 + "lekarz_id INT(5)NOT NULL PRIMARY KEY,"
                 + "imie VARCHAR(35) NOT NULL,"
                 + "nazwisko VARCHAR(35) NOT NULL,"
                 + "pass VARCHAR(65) NOT NULL,"
                 + "nr_lekarza INT(10) NOT NULL UNIQUE"
                 + ")");
                 PreparedStatement createUsers = getConn().prepareStatement(createTableSessions);
                 createUsers.executeUpdate();                 
               LOGGER.info("Utworzono tabele LEKARZE!");
        }//try
        catch(Exception ex)
        {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }//createTableSessions
    
    /**
     * Utworzenie tabeli wyniki
     */
    private void createTableWyniki()
    {
        try{
              LOGGER.info("Tworzenie tabeli WYNIKI...");
                 String createTableSessions=("CREATE TABLE wyniki"
                 + "("
                 + "wynik_id INT(15)NOT NULL PRIMARY KEY,"
                 + "data VARCHAR(10) NOT NULL,"
                 + "pacjent_id INT(9) NOT NULL,"
                 + "lekarz_id INT(5) NOT NULL,"
                 + "wynik int(2) NOT NULL,"
                 + "Foreign Key (pacjent_id) REFERENCES pacjenci(pacjent_id) "
                 + ")");
                 PreparedStatement createUsers = getConn().prepareStatement(createTableSessions);
                 createUsers.executeUpdate();                 
               LOGGER.info("Utworzono tabele WYNIKI!");
        }//wyn
        catch(Exception ex)
        {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }//createTableSessions    
  
    /**
     * Zamkniecie polaczenia z baza
     */
    public void closeConn()
    {
        try {conn.close();} catch (Exception ex) {
            LOGGER.log(Level.SEVERE,ex.getMessage(), ex);
        }
    }//closeConn;    
    
    /**
     * Pobranie listy pacjentow z bazy i otrzymanie arraylista
     * @return 
     * ArrayList<PacjenciClass>
     */
    public ArrayList<PacjenciClass> getPacjenci()
    {
        ArrayList<PacjenciClass> ArPac = new ArrayList<>();
        PacjenciClass pc;
        String getPacjentow = "SELECT pacjent_id,nazwisko,imie,pesel,m_nazwa,ulica,nr_dom,"
                +"k_nazwa,tel FROM pacjenci p,kod_pocztowy k,miasto m "
                +"where "
		+"k.kod_id =p.id_kod "  
                +"and m.miasto_id=k.miasto_id order by nazwisko;";      
        try
        {
            PreparedStatement pacjenty = getConn().prepareStatement(getPacjentow);
            ResultSet rs = pacjenty.executeQuery();
            while(rs.next())
            {
               pc = new PacjenciClass();
               pc.setPacjentId(rs.getInt("pacjent_id"));
               pc.setNazwisko(rs.getString("nazwisko"));
               pc.setImie(rs.getString("imie"));
               String spesel=rs.getString("pesel");
               pc.setPesel(spesel.split(spesel, 1));    //dzielenie calego peselu na tablice stringow[11]
               pc.setMiasto(rs.getString("m_nazwa"));
               pc.setUlica(rs.getString("ulica"));
               pc.setNr_domu(rs.getString("nr_dom"));
               pc.setKod_pocztowy(rs.getString("k_nazwa"));
               pc.setTel(rs.getString("tel"));
               ArPac.add(pc);
                //System.out.println("Login:"+rs.getString("user_login")+" dolaczyl:"+rs.getString("user_join_t"));
            }

        }
        catch(Exception e)
        {
            LOGGER.log(Level.SEVERE,e.getMessage(),e);      
        }               
        return ArPac;
    }//getPacjenci
    
    /**
     * Metoda od logowania, sprawdza czy lekarz istnieje a potem porownuje hasla
     * @param nr_lekarza
     * @param password
     * @return 
     * 0 - nie istnieje
     * 1 - zalogowano
     * 2 - blad logowania
     */
    public int loggingCheck(String nr_lekarza, String password)
    {
        String hashpass = null;
        String pass2 = encode.encode(password);
        int status;
        try {
            String pyt = "SELECT * from lekarze where nr_lekarza=?";
            PreparedStatement check = getConn().prepareStatement(pyt);
            check.setString(1, nr_lekarza);
            ResultSet rscheck = check.executeQuery();
            while(rscheck.next())
            {
                hashpass =rscheck.getString("pass");
            }
            
            if(hashpass.equals(pass2)==true) {
                status=1;
                LOGGER.log(Level.INFO, "Pass uzytkownika:{0} poprawne", nr_lekarza);
                
            }
            else {
                status=2;
                LOGGER.log(Level.WARNING, "Pass uzytkownika:{0} bledne!", nr_lekarza);
                
            }
        } 
        catch(NullPointerException ex)
        {
            LOGGER.info("Brak uzytkownika w bazie! ");
            status=2;
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Blad podczas logowania", ex);
            status=2;
        }
        return status;
       
    } //login
    
    /**
     * Metoda od dodawania nowego usera
     * @param imie
     * @param nazwisko
     * @param pesel
     * @return 
     *  0 - blad 
     *  1 - dodano
     *  2 - istnieje 
     */
   public int addNewUser(String imie, String nazwisko, String[] pesel,String miasto,String ulica,String nr_dom,String tel,String kod)
    {
        String p ="";
        for(int i=0;i<=pesel.length-1;i++)
        {
            p+=pesel[i];
        }
    
        try{
            String czy;
            PreparedStatement czyistnieje = getConn().prepareStatement("SELECT pesel FROM pacjenci WHERE pesel=?");
            czyistnieje.setString(1, p);
            ResultSet rs = czyistnieje.executeQuery();
            while(rs.next())
            {
                czy = rs.getString("pesel");
                if(czy.equals(pesel.toString()))
                {
                    return 2;
                }
            }
        }//try
        catch(Exception e)
        {
            LOGGER.log(Level.SEVERE,e.getMessage(),e);
        }     
   
        String InsertQuery="INSERT INTO pacjenci VALUES";
        String InsertQuery1="INSERT INTO miasto VALUES";
        String InsertQuery2="INSERT INTO kod_pocztowy VALUES";
        int id = maxPacjenci();
        int id1 = countMiasto();
        int id2 = countKod();
        int miastospr,kodspr;
        try {
            miastospr=checkMiasto(miasto);
            kodspr=checkKod(kod);
            if(miastospr==0)
            {              
              PreparedStatement insert_miasto = getConn().prepareStatement(InsertQuery1+"(?, ?)"); 
              insert_miasto.setInt(1,id1);
              insert_miasto.setString(2, miasto);
              insert_miasto.execute();
            }
            if(kodspr==0)
            {              
              PreparedStatement insert_kod = getConn().prepareStatement(InsertQuery2+"(?, ?, ?)"); 
              insert_kod.setInt(1,id2);
              insert_kod.setString(2, kod);
              if(miastospr==0){insert_kod.setInt(3,id1);}else{insert_kod.setInt(3,miastospr);}             
              insert_kod.execute();
            }
            PreparedStatement insert_osoba = getConn().prepareStatement(InsertQuery + "(?, ?, ?, ?, ?, ?, ?, ?)");
            insert_osoba.setInt(1, id);
            insert_osoba.setString(2, imie);
            insert_osoba.setString(3,nazwisko);
            insert_osoba.setString(4,p);
            insert_osoba.setString(5,ulica);
            insert_osoba.setString(6,nr_dom);
            insert_osoba.setString(7,tel);
            if(kodspr==0){insert_osoba.setInt(8,id2);}else{insert_osoba.setInt(8,kodspr);}
            insert_osoba.execute();
            
            //LOGGER.log(Level.INFO, "Pomyslnie dodano nowego uzytkownika:{0} !!", pesel.toStrng());
            return 1;
        } 
        catch (Exception err) {
            
            if(err.getLocalizedMessage().contains("Duplicate entry"))
            {
                LOGGER.log(Level.INFO,"Próba dodania pacjenta o tym samym PESEL-u");
                return 2;
            }
            else
            {
            LOGGER.log(Level.SEVERE, err.getMessage(), err);
            return 2;    
            }
        }
    }//addNewUser    
   
     public int updateUser(String imie, String nazwisko,String pesel,String miasto,String ulica,String nr_dom,String tel,String kod)
    {
        try {
            String p ="";
            String UpdateQuery="UPDATE pacjenci SET";
            String InsertQuery1="INSERT INTO miasto VALUES";
            String InsertQuery2="INSERT INTO kod_pocztowy VALUES";
            
            int id1 = countMiasto();
            int id2 = countKod();
            int miastospr,kodspr;
            
                miastospr=checkMiasto(miasto);
                kodspr=checkKod(kod);
                
                if(miastospr==0)
                {              
                try {
                    PreparedStatement insert_miasto = getConn().prepareStatement(InsertQuery1+"(?, ?)"); 
                    insert_miasto.setInt(1,id1);
                    insert_miasto.setString(2, miasto);
                    insert_miasto.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                if(kodspr==0)
                {              
                try {
                    PreparedStatement insert_kod = getConn().prepareStatement(InsertQuery2+"(?, ?, ?)"); 
                    insert_kod.setInt(1,id2);
                    insert_kod.setString(2, kod);
                    if(miastospr==0){insert_kod.setInt(3,id1);}else{insert_kod.setInt(3,miastospr);}
                    insert_kod.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                PreparedStatement update_osoba = getConn().prepareStatement(UpdateQuery + " imie=?,nazwisko=?,ulica= ?,nr_dom= ?,tel= ?,"
                        + "id_kod=? where pesel= ?");
                
                update_osoba.setString(1, imie);
                update_osoba.setString(2,nazwisko);   
                update_osoba.setString(3,ulica);
                update_osoba.setString(4,nr_dom);
                update_osoba.setString(5,tel);
                if(kodspr==0){update_osoba.setInt(6,id2);}else{update_osoba.setInt(6,kodspr);}
                update_osoba.setString(7,pesel);
                update_osoba.execute();
                
                //LOGGER.log(Level.INFO, "Pomyslnie dodano nowego uzytkownika:{0} !!", pesel.toStrng());
                return 1;
        } //update
        catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
       
    }//update 
   
   public int deletePacjent(String id_p)
   {
        try {
            String pyt = "delete from pacjenci where pesel=?";   
            PreparedStatement check = getConn().prepareStatement(pyt);
            check.setString(1, id_p);
            check.executeUpdate();
                return 1;           
        } catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
       
   }//usunicie pacjenta
    
   
/**
 * Zliczenie liczby pacjenow
 * @return 
 * Liczba pacjenow w bazie
 */
private int maxPacjenci()
{
        int luserow=0;
        try {
            String zliczenie = "SELECT MAX(pacjent_id) AS LICZBA FROM pacjenci";    
             PreparedStatement liczenie = getConn().prepareStatement(zliczenie);
                 ResultSet rsilosc = liczenie.executeQuery();
                 while (rsilosc.next()) {
                     luserow = rsilosc.getInt("LICZBA");
                 }
                 luserow++;//zwiekszenie aby nastepne id
                 return luserow;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Blad podczas zliczania uzytkownikow uzytkownikow", ex);
            return luserow;
        }
}//maxidUsers

public int countPacjenci()
{
        int luserow=0;
        try {
            String zliczenie = "SELECT Count(pacjent_id) AS LICZBA FROM pacjenci";    
             PreparedStatement liczenie = getConn().prepareStatement(zliczenie);
                 ResultSet rsilosc = liczenie.executeQuery();
                 while (rsilosc.next()) {
                     luserow = rsilosc.getInt("LICZBA");
                 }
                
                 return luserow;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Blad podczas zliczania uzytkownikow uzytkownikow", ex);
            return luserow;
        }
}//countUsers
    
    /**
     * Llicza lekarzy z bazie i zwraca +1
     * @return 
     */
     public int countLekarze()
    {
        int luserow=0;
        try {
            String zliczenie = "SELECT COUNT(*) AS LICZBA FROM lekarze";    
             PreparedStatement liczenie = getConn().prepareStatement(zliczenie);
                 ResultSet rsilosc = liczenie.executeQuery();
                 while (rsilosc.next()) {
                     luserow = rsilosc.getInt("LICZBA");
                 }
                 luserow++;//zwiekszenie aby nastepne id
                 return luserow;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Blad podczas zliczania lekarzy", ex);
            return luserow;
        }
    }//countLekarz
     
     private int maxIdWynik()
{
        int maxid=0;
        try {
            String zliczenie = "SELECT MAX(wynik_id) AS LICZBA FROM wyniki";    
             PreparedStatement liczenie = getConn().prepareStatement(zliczenie);
                 ResultSet rsilosc = liczenie.executeQuery();
                 while (rsilosc.next()) {
                     maxid = rsilosc.getInt("LICZBA");
                 }
                 maxid++;//zwiekszenie aby nastepne id
                 return maxid;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Blad podczas zliczania uzytkownikow uzytkownikow", ex);
            return maxid;
        }
}//maxidUsers
   
    
    /**
     * Metoda odpowiadajaca za dowawanie wynikow do bazy
     * @param pesel
     * @param nr_lekarza
     * @param wynik
     * @return 
     * true lub false
     */
    public boolean addWynik(String pesel, String nr_lekarza, String wynik)
    {
        Date date = new Date();
        int id = maxIdWynik();
        int id_pacjenta = findPacjentId(pesel);
        int id_lekarza = findLekarzId(Integer.parseInt(nr_lekarza));
        String InsertQuery="INSERT INTO wyniki VALUES";
  
        try {
            PreparedStatement insert_osoba = getConn().prepareStatement(InsertQuery + "(?, ?, ?, ?, ?)");
            insert_osoba.setInt(1,id);
            insert_osoba.setString(2, getDate(date));
            insert_osoba.setInt(3,id_pacjenta);
            insert_osoba.setInt(4,id_lekarza);
            insert_osoba.setInt(5,Integer.parseInt(wynik));
            insert_osoba.execute();
            //LOGGER.log(Level.INFO, "Pomyslnie dodano nowego uzytkownika:{0} !!", pesel.toStrng());
            return true;
        } catch (SQLException | NumberFormatException err) {
            
            LOGGER.log(Level.SEVERE, err.getMessage(), err);
            return false;
        } 
    }//addWynik
   
    
    /**
     * Metoda zwracajaca aktualny czas serwera.
     * @return 
     */
    private String getDate(java.util.Date utilDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String sqlDate = sdf.format(utilDate);
    return sqlDate;
}
    
public int checkMiasto(String miasto)
    {
        try {
            String sprawdz ="SELECT * FROM miasto where m_nazwa=?";
            int id=0;
            PreparedStatement spr = getConn().prepareStatement(sprawdz);
            spr.setString(1, miasto);
            ResultSet rsilosc = spr.executeQuery();
                 while (rsilosc.next()) {
                     id = rsilosc.getInt("miasto_id");
                 }
             return id;
        } catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } 
    }//sprawdz miasto

public int countMiasto()
{
        try {
            String max = "SELECT COUNT(miasto_id) as LICZBA FROM miasto ";
                      int m=0;
                      PreparedStatement spr = getConn().prepareStatement(max);
                      ResultSet rsilosc = spr.executeQuery();
                      while (rsilosc.next()) {
                            m = rsilosc.getInt("LICZBA");    
                       }
                      m++;
                      return m;
        } catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }      
}//countMiasto

public int checkKod(String kod)
{
        try {
            String sprawdz ="SELECT * FROM kod_pocztowy where k_nazwa=?";
            int id=0;
            PreparedStatement spr = getConn().prepareStatement(sprawdz);
            spr.setString(1, kod);
            ResultSet rsilosc = spr.executeQuery();
                 while (rsilosc.next()) {
                     id = rsilosc.getInt("kod_id");
                 }
             return id;
        } catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } 
}//sprawdz kod_pocztowy

 public int countKod()
 {
        try {
            String max = "SELECT COUNT(miasto_id) as LICZBA FROM kod_pocztowy ";
                      int m=0;
                      PreparedStatement spr = getConn().prepareStatement(max);
                      ResultSet rsilosc = spr.executeQuery();
                      while (rsilosc.next()) {
                            m = rsilosc.getInt("LICZBA");    
                       }
                      m++;
                      return m;
        } catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
 }//countKod
 
public int countWforP(String pesel)
 {
        try {
            String zap = "SELECT COUNT(wynik) as LICZBA FROM wyniki where pacjent_id=?";
            int id = findPacjentId(pesel);
            int cou=0;
            PreparedStatement liczenie = getConn().prepareStatement(zap);
            liczenie.setInt(1, id);
            ResultSet rsilosc = liczenie.executeQuery();
                      while (rsilosc.next()) {
                            cou = rsilosc.getInt("LICZBA");    
                       }
                      return cou;
        } catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
 }

public ArrayList<WynikiClass> getWyniki(String pesel)
{
    
        try {
    ArrayList<WynikiClass> wyniki = new ArrayList<>();
    String zap = "Select wynik,data from wyniki where pacjent_id=?";
    WynikiClass wy;
            int id = findPacjentId(pesel);

            PreparedStatement wyn = getConn().prepareStatement(zap);
            wyn.setInt(1,id);
            ResultSet rs = wyn.executeQuery();
            
             while(rs.next())
                    {
                     wy = new WynikiClass();
                     wy.setData(rs.getString("data"));
                     wy.setWynik(rs.getInt("wynik"));
                     wyniki.add(wy);
                    }     
             return wyniki;
             
        } catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
}
   
    /**
     * Metoda od wyszukiwania id pacjenta poprzez numer pesel.
     * @param pesel
     * @return 
     * int nr pacjenta
     */
    private int findPacjentId(String pesel)
    {
        try {
            String zliczenie = "SELECT pacjent_id FROM pacjenci where pesel=?";    
            int id = 0;
             PreparedStatement liczenie = getConn().prepareStatement(zliczenie);
             liczenie.setString(1, pesel);
                 ResultSet rsilosc = liczenie.executeQuery();
                 while (rsilosc.next()) {
                     id = rsilosc.getInt("pacjent_id");
                 }
             return id;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Blad podczas pobierania numeru pacjenta", ex);
            return 0;
        }
    }//findPacjentID
    
    /**
     * Metoda od wyszukania id lekarza poprzez jego numer
     * @param nr_lekarza
     * @return 
     * int id lekarza
     */
    private int findLekarzId(int nr_lekarza)
    {
        try {
            String zliczenie = "SELECT * FROM lekarze where nr_lekarza=?";    
            int id = 0;
             PreparedStatement liczenie = getConn().prepareStatement(zliczenie);
             liczenie.setInt(1, nr_lekarza);
                 ResultSet rsilosc = liczenie.executeQuery();
                 while (rsilosc.next()) {
                     id = rsilosc.getInt("lekarz_id");
                 }
             return id;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Blad podczas pobierania nr lekarza", ex);
            return 0;
        }
    }//findPacjentID    
    
    public String getLekarz(String nr_l)
    {
        String imie = null;
        String nazwisko = null;
        String z;
        try {
            String pyt = "SELECT * from lekarze where nr_lekarza=?";
            PreparedStatement check = getConn().prepareStatement(pyt);
            check.setString(1, nr_l);
            ResultSet rscheck = check.executeQuery();
            while(rscheck.next())
            {
                imie =rscheck.getString("imie");
                nazwisko = rscheck.getString("nazwisko");
            }
            z=(imie+" "+nazwisko);
            return z;
    }
        catch (SQLException ex) {    
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    
    }
    /**
     * Dodawanie lekarza przez konsole. 
     * @param imie
     * @param nazwisko
     * @param nr_lekarza
     * @param haslo
     * @return 
     * true lub false
     */
    public boolean addLekarz(String imie, String nazwisko, String pass, int nr_lekarza)
    {
        
        String InsertQuery="INSERT INTO lekarze VALUES";
        int id = countLekarze();
        try {
            PreparedStatement insert_osoba = getConn().prepareStatement(InsertQuery + "(?, ?, ?, ?, ?)");
            insert_osoba.setInt(1, id);
            insert_osoba.setString(2, imie);
            insert_osoba.setString(3,nazwisko);
            insert_osoba.setString(4,encode.encode(pass));
            insert_osoba.setInt(5,nr_lekarza);
            insert_osoba.execute();
            LOGGER.log(Level.INFO, "Pomyslnie dodano nowego lekarza:{0} !!", nr_lekarza);
            return true;
        } catch (Exception err) {
            
            LOGGER.log(Level.SEVERE, err.getMessage(), err);
            return false;
        } 
    }
}//class
