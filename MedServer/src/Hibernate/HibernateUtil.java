/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import MedServer.RunServer;
import MedServer.StartClass;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Lukasz
 */
public class HibernateUtil
{
    private final static Logger LOGGER = Logger.getLogger(RunServer.class.getName());  
    private static final SessionFactory sessionFactory;
    
    static
    {
        try
        {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            LOGGER.log(Level.INFO, "Pomyślnie użyto hibernate połączenia.");
        } catch (Throwable ex)
        {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            LOGGER.log(Level.SEVERE, ex.getMessage()+" Blad polaczenia z baza!", ex.getStackTrace());               
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
