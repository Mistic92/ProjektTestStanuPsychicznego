/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateClassMap;

/**
 *                 String createTableSessions=("CREATE TABLE wyniki"
                 + "("
                 + "wynik_id INT(15)NOT NULL PRIMARY KEY,"
                 + "data VARCHAR(10) NOT NULL,"
                 + "pacjent_id INT(9) NOT NULL,"
                 + "lekarz_id INT(5) NOT NULL,"
                 + "wynik int(2) NOT NULL,"
 * @author Lukasz
 */
public class wyniki
{
    private int wynik_id;
    private String data;
    private int pacjent_id;
    private int lekarz_id;
    private int wynik;

    public wyniki(int wynik_id, String data, int pacjent_id, int lekarz_id, int wynik)
    {
        this.wynik_id = wynik_id;
        this.data = data;
        this.pacjent_id = pacjent_id;
        this.lekarz_id = lekarz_id;
        this.wynik = wynik;
    }

    
    
    public int getWynik_id()
    {
        return wynik_id;
    }

    public void setWynik_id(int wynik_id)
    {
        this.wynik_id = wynik_id;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public int getPacjent_id()
    {
        return pacjent_id;
    }

    public void setPacjent_id(int pacjent_id)
    {
        this.pacjent_id = pacjent_id;
    }

    public int getLekarz_id()
    {
        return lekarz_id;
    }

    public void setLekarz_id(int lekarz_id)
    {
        this.lekarz_id = lekarz_id;
    }

    public int getWynik()
    {
        return wynik;
    }

    public void setWynik(int wynik)
    {
        this.wynik = wynik;
    }
    
    
}
