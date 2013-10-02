/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanpsychiczny;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Pantera
 */
 class ZaznaczenieListy implements ListSelectionListener {  
        @Override
        public void valueChanged(ListSelectionEvent e) { 
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();

                int minIndex = lsm.getAnchorSelectionIndex();
                
                    if (lsm.isSelectedIndex(minIndex)) {
                        System.out.println("indx: " + minIndex);
                  }
        }
    }