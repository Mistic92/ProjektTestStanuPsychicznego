
package stanpsychiczny;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class StanPsychiczny {

 
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    Gui log = new Gui();
    TcpCon conn= new TcpCon();
    if(conn.connect("localhost",5555)==false)
    {
     String msg="Bład polaczenia z serwerem";
     Powiadomienia.w_msg(msg, null);           
    }
    else
    {
      log.oLogowania();
      
    }
    
    }//main
}//StanPsychiczny
