/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.NewHibernateUtil;
import pojo.Ticket;

/**
 *
 * @author Rizky A. Darmawan
 */
public class DAOTicket {
    public void add(Ticket ticket)
    {
        Transaction trans=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            session.save(ticket);
            trans.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void delete(int id)
    {
        Transaction trans=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            Ticket prod=(Ticket)session.load(Ticket.class, new Integer(id));
            session.delete(prod);
            trans.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public List lihatdata() {
        Transaction trans = null;
        List pem = new ArrayList();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from Ticket");
            pem = query.list();
            if (pem != null && pem.size() > 0) {
                return pem;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }
    
     public List<Ticket> cari(int id){
        Ticket lth = new Ticket();
        List<Ticket> l = new ArrayList();
        
        Transaction trans = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            Query query = session.createQuery("from Ticket where id_booking = :id");
            query.setInteger("id", id);
            lth = (Ticket) query.uniqueResult();
            l = query.list();
            trans.commit();
        } catch (Exception e){
        }
        return l;
    }
     
     public void ubah(Ticket lth){
        Transaction trans = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            session.update(lth);
            trans.commit();
        } catch (Exception e){
            
        }
    }
}
