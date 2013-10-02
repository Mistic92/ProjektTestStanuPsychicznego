/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import HibernateClassMap.lekarze;
import HibernateClassMap.pacjenci;
import MedServer.DbCon;
import MedServer.RunServer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Lukasz
 */
public class HibernateOperacje
{
    private final static Logger Log = Logger.getLogger(RunServer.class.getName());
    private static SessionFactory factory; 
    
    
    
    public void polacz()
    {
        try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
    }
    
    
    /**
     * W przypadku skasowania zwraca 1, jesli blad zwraca 0
     * @param id_p
     * @return 
     */
    public int deletePacjent(String pesel)
   {
  Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         pacjenci employee = 
                   (pacjenci)session.get(pacjenci.class, pesel); 
         session.delete(employee); 
         tx.commit();
         return 1;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
         Log.log(Level.WARNING, "Blad usuwania pacjenta", e);
         return 0;
      }
      catch(Exception ex)
      {
          Log.log(Level.SEVERE, "Blad usuwania pacjenta, severe", ex);
          return 0;
      }
      finally {
         session.close();  
      }   
   }//usunicie pacjenta
    
    
     /**
     * Metoda od wyszukania id lekarza poprzez jego numer
     * @param nr_lekarza
     * @return 
     * int id lekarza
     */
    private int findLekarzId(int nr_lekarza)
    {
         int id = 0;
        Session session = factory.openSession();
        Transaction tx = null;
        try
        {
         tx = session.beginTransaction();
         Query query = session.createQuery("from lekarze where nr_lekarza = :id ");
         query.setParameter("id", nr_lekarza);
         
         List<lekarze> l = query.list();
         tx.commit();
         for(lekarze p:l)
         {
             id=p.getLekarz_id();
         }
        }
        catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
         Log.log(Level.WARNING, "Blad usuwania pacjenta", e);
         return 0;
        }
        catch(Exception ex)
        {
            Log.log(Level.SEVERE, "Blad usuwania pacjenta, severe", ex);
            return 0;
        }
        finally {
           session.close();  
           return id;
        }           
   
    }//findLekarzId
   
    
    
}
