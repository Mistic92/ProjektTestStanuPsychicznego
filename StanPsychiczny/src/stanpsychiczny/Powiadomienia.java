/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanpsychiczny;

import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author Pantera
 */
public class Powiadomienia {
    
    /**
     *
     * @param msg
     * @param tytul
     * @param ikona
     */
    public static void w_msg(String msg,Icon ikona)
    {
        JOptionPane.showMessageDialog(null,                        
                                msg,                         
                                "Uwaga!",             
                                JOptionPane.WARNING_MESSAGE, 
                                ikona                       
                                ); 
    }
    public static int chose_msg(String msg,String tytul,Icon ikon)
    {
        int odp=JOptionPane.showConfirmDialog(null, 
                                 msg,
                                 tytul,
                                 JOptionPane.YES_NO_OPTION,
                                 JOptionPane.WARNING_MESSAGE);
        return odp;
    }
    
    public static void info_msg(String msg,Icon ikona)
    {
        JOptionPane.showMessageDialog(null, 
                                 msg,
                                 "Informacja",
                                 JOptionPane.INFORMATION_MESSAGE,
                                 ikona);
    }
    
}
