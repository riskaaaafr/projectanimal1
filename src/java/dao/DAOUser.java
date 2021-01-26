/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.User;
import pojo.NewHibernateUtil;

/**
 *
 * @author Rizky A. Darmawan
 */
public class DAOUser{

    public List<User> getBy(String nama, String pass) {
        User u = new User();
        List<User> user = new ArrayList();

        Transaction trans = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from User where username= :username AND password= :password");
            query.setString("username", nama);
            query.setString("password", pass);
            u = (User) query.uniqueResult();
            user = query.list();

            trans.commit();
        } catch (Exception e) {             
            System.out.println("Errordfds: " + e);
        }
        return user;
    }

    public List<User> getbyid(String username) {
        User plg = new User();
        List<User> user1 = new ArrayList();

        Transaction trans = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from User where username= :username");
            query.setString("username", username);
            plg = (User) query.uniqueResult();
            user1 = query.list();
            trans.commit();
        } catch (Exception e) {       
        }
        return user1;
    }

    public void updateUser(User plg) {
        Transaction trans = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            session.update(plg);
            trans.commit();
        } catch (Exception e) {

        }
    }
    
    public void deleteUser(String username) {
        Transaction trans = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            User plg = (User) session.load(User.class, new String(username));
            session.delete(plg);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addUser(User user)
    {
        {
        Transaction trans=null;
        Session session=NewHibernateUtil.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            session.save(user);
            trans.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    }
}



    
    
    
