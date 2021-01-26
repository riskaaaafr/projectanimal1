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
import pojo.Animal;
import pojo.NewHibernateUtil;

/**
 *
 * @author Rizky A. Darmawan
 */
public class DAOAnimal {
     public void add(Animal animal)
     {
        Transaction trans=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            session.save(animal);
            trans.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void delete(int id_animal)
    {
        Transaction trans=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            Animal prod=(Animal)session.load(Animal.class,new Integer(id_animal));
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
            Query query = session.createQuery("from Animal");
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
    
     public List<Animal> cari(int idJ){
        Animal lth = new Animal();
        List<Animal> l = new ArrayList();
        
        Transaction trans = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trans = session.beginTransaction();
            Query query = session.createQuery("from Animal where id_animal = :id");
            query.setInteger("id", idJ);
            lth = (Animal) query.uniqueResult();
            l = query.list();
            trans.commit();
        } catch (Exception e){
        }
        return l;
    }
     
     public void ubah(Animal lth){
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
