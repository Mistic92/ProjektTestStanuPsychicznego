/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanpsychiczny;


public class Pacjent {
  private String nazwisko;
  private String imie;
  private String pesel;
  
  Pacjent(String nazwisko,String imie,String pesel)
  {
      this.imie=imie;
      this.nazwisko=nazwisko;
      this.pesel=pesel;
  }
  
   public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
    
    public String getPesel() {
        return pesel;
    }

 
}
