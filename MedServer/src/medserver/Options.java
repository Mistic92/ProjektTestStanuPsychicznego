/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medserver;

/**
 *Sluzy do tworzenia i odczytu pliku opcji. 
 * @author ţukasz
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Options {
    private final static Logger LOGGER = Logger.getLogger(RunServer.class.getName());

    RunServer client = new RunServer();
    //SimpleEncDec szyfr = new SimpleEncDec();
    StartClass main = new StartClass();
    DbCon dbcon = new DbCon();
    private File f;
    private int port;
    private String login;
    private String pass;
    private String url;
    private int proba = 0;
    private static int status=0;
  
    /**
     * Odczytuje plik opcji options.txt 
     * Jesli wyrzuci wyjatek to pyta czy utworzyc nowy plik opcji.
     * @return 
     */
    public int read()
    {
        try
        {
            FileInputStream inStream = new FileInputStream("options.txt");
            DataInputStream in = new DataInputStream(inStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            
            url = br.readLine();
            url = url.replaceFirst("url: ", "");
            //dbcon.setUrl(url);
           
            
            login = br.readLine();
            login = login.replaceFirst("login: ","");
           // dbcon.setLogin(login);
           
            
            pass = br.readLine();
            pass = pass.replaceFirst("pass: ", "");
            //dbcon.setPass(pass);
           
            status = 1;
            br.close();
        }//try
        catch(Exception e)
        {
            if(e.getMessage().contains("options.txt ("))
           {
               LOGGER.info("Brak pliku opcji! Czy chcesz go utworzyc? tak/nie");
                   Scanner skan = new Scanner(System.in);  
                    String wybor = skan.nextLine();
                    if(wybor.equals("tak")) 
                    {
                     newOptionsFile();  
                     read();
                    }             
           }//if
           else {
                LOGGER.log(Level.SEVERE,e.getMessage(),e);
                status = 2;
                if(proba<2)
                {
                 proba++;
                 newOptionsFile();  
                 read();
                }
            }
        }//catch
        return status;
    }//read
    
    public String getUrl()
    {
        return url;
    }
    public String getLogin()
    {
        return login;
    }
    public String getPass()
    {
       return pass; 
    }
    /**
     * Utworzenie samego pliku. 
     */
    private void newOptionsFile()
    {
        try
        {
            f=new File("options.txt"); 
           writeOptions();
        }
        catch(Exception e)
        {
          LOGGER.log(Level.SEVERE,e.getMessage(),e);  
        }
    }
    
    /**
     * Tworzenie pliku opcji. Zapis do niego. 
     */
    public void writeOptions()
    {
        try{
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
           
                
                Scanner skan4 = new Scanner(System.in);
                System.out.println("Podaj adres bazy w stylu: localhost:3306/");
                url = skan4.nextLine();
                bw.write("url: "+url);
                bw.newLine();
                
                Scanner skan2 = new Scanner(System.in);
                System.out.println("Podaj login dostepu do bazy danych."+"\n"+"Admin badz uzytkownik z odpowiednimi prawami.");
                login = skan2.nextLine();
                bw.write("login: "+login);
                bw.newLine();
                
                Scanner skan3 = new Scanner(System.in);
                System.out.println("Podaj haslo uzytkownika. Tymczasowo nie jest szyfrowane!!");
                pass = skan3.nextLine();
                //pass = szyfr.Encrypt(pass);
                bw.write("pass: "+pass);      
                bw.close();        
        }
        catch(Exception e)
        {
            LOGGER.log(Level.SEVERE,e.getMessage(),e);
        }
    }
}//class

