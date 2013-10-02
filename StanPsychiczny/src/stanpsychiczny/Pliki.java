/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanpsychiczny;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Pantera
 */
public class Pliki {
    ArrayList<String> odp = new ArrayList<String>();
    
    public ArrayList<String> readFile(String plik) throws IOException {
    FileReader plikczyt = new FileReader(plik);
    
  try ( BufferedReader bufferedReader = new BufferedReader(plikczyt)) {
    String textLine = bufferedReader.readLine();
    do {
      
      odp.add(textLine);
      textLine = bufferedReader.readLine();
    } while (textLine != null);
  }
        return odp;
}

}
