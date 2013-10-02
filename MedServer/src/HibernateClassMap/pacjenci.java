/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateClassMap;

/**
 *                 + "pacjent_id INT(9)UNIQUE NOT NULL PRIMARY KEY,"
                 + "imie VARCHAR(35) NOT NULL,"
                 + "nazwisko VARCHAR(35) NOT NULL,"
                 + "pesel VARCHAR(11) NOT NULL UNIQUE,"
                 + "ulica varchar(65) NOT NULL,"
                 + "nr_dom varchar(4) NOT NULL,"
                 + "tel varchar(11) NOT NULL,"        
                 + "id_kod int(9) NOT NULL,"
 * @author Lukasz
 */
public class pacjenci
{
    private int pacjent_id;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String ulica;
    private String nr_dom;
    private String tel;
    private int id_kod;

    public pacjenci(int pacjent_id, String imie, String nazwisko, String pesel, String ulica, String nr_dom, String tel, int id_kod)
    {
        this.pacjent_id = pacjent_id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.ulica = ulica;
        this.nr_dom = nr_dom;
        this.tel = tel;
        this.id_kod = id_kod;
    }

    
    
    
    public int getPacjent_id()
    {
        return pacjent_id;
    }

    public void setPacjent_id(int pacjent_id)
    {
        this.pacjent_id = pacjent_id;
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

    public String getPesel()
    {
        return pesel;
    }

    public void setPesel(String pesel)
    {
        this.pesel = pesel;
    }

    public String getUlica()
    {
        return ulica;
    }

    public void setUlica(String ulica)
    {
        this.ulica = ulica;
    }

    public String getNr_dom()
    {
        return nr_dom;
    }

    public void setNr_dom(String nr_dom)
    {
        this.nr_dom = nr_dom;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public int getId_kod()
    {
        return id_kod;
    }

    public void setId_kod(int id_kod)
    {
        this.id_kod = id_kod;
    }
    
    
}
