/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MedServer;

import java.io.Serializable;

/**
 *
 * @author Pantera
 */
public class WynikiClass implements Serializable{
    private String data;
    private int wynik;
    
  public void setWynik(int wynik)
  {
      this.wynik=wynik;
  }
  public void setData(String data)
  {
      this.data = data;
  }
  
  public int getWynik()
  {
      return wynik;
  }
  public String getData()
  {
      return data;
  }
}
