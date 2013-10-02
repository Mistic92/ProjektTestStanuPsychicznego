/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medserver;

import java.io.Serializable;

/**
 * Klasa magazyn danych. Uzywana w polaczeniu z arraylist. Tylko akcesory. 
 * @author ţukasz
 */
public class PacjenciClass implements Serializable {
   
  private int pacjent_id;  
  private String imie;
  private String nazwisko;
  private String[] pesel = new String[11];
  private String miasto;
  private String ulica;
  private String nr_domu;
  private String kod_pocz;
  private String tel;
  
  //set
  public void setPacjentId(int pacjent_id)
  {
      this.pacjent_id=pacjent_id;
  }
  public void setImie(String imie)
  {
      this.imie = imie;
  }
  public void setNazwisko(String nazwisko)
  {
      this.nazwisko=nazwisko;
  }
  public void setPesel(String[] pesel)
  {
      this.pesel=pesel;
  }
  public void setMiasto(String miasto)
  {
    this.miasto=miasto;  
  }
  public void setUlica(String ulica)
  {
    this.ulica=ulica;  
  }
  public void setNr_domu(String nr_domu)
  {
    this.nr_domu=nr_domu;  
  }
  public void setKod_pocztowy(String kod_pocz)
  {
    this.kod_pocz=kod_pocz;  
  }
  public void setTel(String tel)
  {
    this.tel=tel;
  }
 
  //get
  public int getPacjentId()
  {
      return pacjent_id;
  }
  public String getImie()
  {
      return imie;
  }
  public String getNazwisko()
  {
      return nazwisko;
  }
  public String[] getPesel()
  {
      return pesel;
  }
  public String getPeselCaly()
  {
      String p = null;
      for(int i=0;i<=pesel.length-1;i++)
      {
          p=pesel[i];
      }
      return p;
  }
  public String getMiasto()
  {
   return miasto;            
  }
  public String getUlica()
  {
   return ulica;
  }
  public String getNr_dom()
  {
    return nr_domu;  
  }
  public String getKod_pocztowy()
  {
    return kod_pocz;
  }
  public String getTel()
  {
    return tel;  
  }
  
}
