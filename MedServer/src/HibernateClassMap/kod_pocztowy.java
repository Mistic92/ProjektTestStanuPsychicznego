/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateClassMap;

/**
 *
 * @author Lukasz
 */
public class kod_pocztowy
{
    private int kod_id;
    private String k_nazwa;
    private int miasto_id;

    public kod_pocztowy(int kod_id, String k_nazwa, int miasto_id)
    {
        this.kod_id = kod_id;
        this.k_nazwa = k_nazwa;
        this.miasto_id = miasto_id;
    }
    
    
    
    
    public int getKod_id()
    {
        return kod_id;
    }

    public void setKod_id(int kod_id)
    {
        this.kod_id = kod_id;
    }

    public String getK_nazwa()
    {
        return k_nazwa;
    }

    public void setK_nazwa(String k_nazwa)
    {
        this.k_nazwa = k_nazwa;
    }

    public int getMiasto_id()
    {
        return miasto_id;
    }

    public void setMiasto_id(int miasto_id)
    {
        this.miasto_id = miasto_id;
    }

    
}
