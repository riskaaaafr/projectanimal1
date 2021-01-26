/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Contact;
import org.hibernate.Query;
import org.hibernate.Transaction;
import pojo.NewHibernateUtil;


/**
 *
 * @author Rizky A. Darmawan
 */
public class DAOContact {
    public void addContact(Contact contact)
     {
        Transaction trans=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            session.save(contact);
            trans.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteContact(int id_contact)
    {
        Transaction trans=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            Contact prod=(Contact)session.load(Contact.class,new Integer(id_contact));
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
            Query query = session.createQuery("from Contact");
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
    
     public List<Contact> cari(int idJ){
        Contact lth = new Contact();
        List<Contact> l = new ArrayList();
        
        Transaction trans = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            Query query = session.createQuery("from Contact where id_contact = :id");
            query.setInteger("id", idJ);
            lth = (Contact) query.uniqueResult();
            l = query.list();
            trans.commit();
        } catch (Exception e){
        }
        return l;
    }
     
     public void ubah(Contact lth){
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

