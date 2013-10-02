/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateClassMap;

/**
 *                 String createTableSessions=("CREATE TABLE lekarze"
                 + "("
                 + "lekarz_id INT(5)NOT NULL PRIMARY KEY,"
                 + "imie VARCHAR(35) NOT NULL,"
                 + "nazwisko VARCHAR(35) NOT NULL,"
                 + "pass VARCHAR(65) NOT NULL,"
                 + "nr_lekarza INT(10) NOT NULL UNIQUE"
 * @author Lukasz
 */
public class lekarze
{
    private int lekarz_id;
    private String imie;
    private String nazwisko;
    private String pass;
    private int nr_lekarza;

    public lekarze(int lekarz_id, String imie, String nazwisko, String pass, int nr_lekarza)
    {
        this.lekarz_id = lekarz_id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pass = pass;
        this.nr_lekarza = nr_lekarza;
    }

    
    
    public int getLekarz_id()
    {
        return lekarz_id;
    }

    public void setLekarz_id(int lekarz_id)
    {
        this.lekarz_id = lekarz_id;
    }

    public String getImie()
    {
        return imie;
    }

    public void setImie(String imie)
    {
        this.imie = imie;
    }

    public String getNazwisko()
    {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko)
    {
        this.nazwisko = nazwisko;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public int getNr_lekarza()
    {
        return nr_lekarza;
    }

    public void setNr_lekarza(int nr_lekarza)
    {
        this.nr_lekarza = nr_lekarza;
    }
    
    
}
